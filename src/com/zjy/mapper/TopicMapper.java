package com.zjy.mapper;

import java.util.List;

import com.zjy.model.Topic;

public interface TopicMapper {
	
	List<Topic> listTopic();
	
	List<String> listImage();
}
