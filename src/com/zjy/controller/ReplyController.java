package com.zjy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.service.ReplyService;

@Controller
@RequestMapping("/")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/reply.do")
	public String reply(int pid, String content, HttpSession session){
		int sessionUid = (int) session.getAttribute("uid");
		replyService.reply(sessionUid, pid, content);
		return "redirect:toPost.do?pid"+pid;
	}
	
	@RequestMapping("/comment.do")
	public String comment(int pid, int rid, String content, HttpSession session){
		int sessionUid = (int) session.getAttribute("uid");
		replyService.comment(pid, sessionUid, rid, content);
		return "redirect:toPost.do?pid="+pid;
	}
}
