package com.zjy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjy.model.PageBean;
import com.zjy.model.Post;
import com.zjy.model.Reply;
import com.zjy.model.Topic;
import com.zjy.model.User;
import com.zjy.service.PostService;
import com.zjy.service.ReplyService;
import com.zjy.service.TopicService;
import com.zjy.service.UserService;

@Controller
@RequestMapping("/")
public class PostController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private ReplyService replyService;
	
	//去发帖的页面
	@RequestMapping("/toPublish.do")
	public String toPublish(Model model){
		List<Topic> topicList = topicService.listTopic();
		model.addAttribute("topicList", topicList);
		return "publish";
	}
	
	//发帖
	@RequestMapping("/publishPost")
	public String publishPost(Post post){
		int id = postService.publishPost(post);
		return "redirect:toPost.do?pid="+id;
	}
	
	//按时间，倒序，列出帖子
	@RequestMapping("/listPostByTime.do")
	public String listPostByTime(int curPage, Model model){
		PageBean<Post> pageBean = postService.listPostByTime(curPage);
		List<User> userList = userService.listUserByTime();
		List<User> hotUserList = userService.listUserByHot();
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("userList", userList);
		model.addAttribute("hotUserList", hotUserList);
		return "index";
	}
	
	//去帖子详情页面
	@RequestMapping("/toPost.do")
	public String toPost(int pid,Model model,HttpSession session){
		Integer sessionUid = (Integer)session.getAttribute("uid");
		//获取帖子信息
		Post post = postService.getPostByPid(pid);
		//获取评论信息
		List<Reply> replyList = replyService.listReply(pid);
		
		//判断用户是否已经点赞
		boolean liked = false;
		if(sessionUid != null){
			liked = postService.getLikeStatus(pid, sessionUid);
		}
		//向模型中添加数据
		model.addAttribute("post", post);
		model.addAttribute("replyList", replyList);
		model.addAttribute("liked", liked);
		return "post";
	}
	
	//异步点赞
	@RequestMapping(value = "/ajaxClickLike.do",produces = "text/plain;charset=UTF-8")
	public @ResponseBody String ajaxClickLike(int pid, HttpSession session){
		int sessionUid = (int) session.getAttribute("uid");
		return postService.clickLike(pid, sessionUid);
	}
}
