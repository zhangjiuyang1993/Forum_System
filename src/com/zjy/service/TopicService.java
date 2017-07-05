package com.zjy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.mapper.TopicMapper;
import com.zjy.model.Topic;

@Service
public class TopicService {

	@Autowired
	private TopicMapper topicMapper;
	
	public List<Topic> listTopic(){
		return topicMapper.listTopic();
	}
	
	public List<String> listImage(){
		return topicMapper.listImage();
	}
}
