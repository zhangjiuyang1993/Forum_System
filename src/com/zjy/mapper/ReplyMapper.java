package com.zjy.mapper;

import java.util.List;

import com.zjy.model.Comment;
import com.zjy.model.Reply;

public interface ReplyMapper {

	void insertReply(Reply reply);
	
	List<Reply> listReply(int pid);
	
	void insertComment(Comment comment);
	
	List<Comment> listComment(Integer rid);
	
	String getContentByRid(int rid);
}
