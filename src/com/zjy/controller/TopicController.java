package com.zjy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.model.Topic;
import com.zjy.service.TopicService;

@Controller
@RequestMapping("/")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	/*
	 * 列出所有话题
	 */
	@RequestMapping("/listTopic.do")
	public String listTopic(Model model){
		List<Topic> topicList = topicService.listTopic();
		model.addAttribute("topicList", topicList);
		return "topic";
	}
	
	@RequestMapping("/listImage.do")
	public String listImage(Model model){
		List<String> imageList = topicService.listImage();
		model.addAttribute("imageList", imageList);
		return "image";
	}
}
