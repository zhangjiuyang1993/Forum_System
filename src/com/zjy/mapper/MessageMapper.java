package com.zjy.mapper;

import java.util.List;

import com.zjy.model.Message;

public interface MessageMapper {

	void insertMessage(Message message);
	
	List<Message> listMessageByUid(Integer uid);
}
