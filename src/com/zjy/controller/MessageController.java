package com.zjy.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.model.Message;
import com.zjy.service.MessageService;

@Controller
@RequestMapping("/")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//去消息页面
	@RequestMapping("/toMessage.do")
	public String toMessage(Model model, HttpSession session){
		Integer sessionUid = (Integer) session.getAttribute("uid");
		Map<String,List<Message>> map = messageService.listMessageByUid(sessionUid);
		model.addAttribute("map", map);
		System.out.println(map);
		return "message";
	}
}
