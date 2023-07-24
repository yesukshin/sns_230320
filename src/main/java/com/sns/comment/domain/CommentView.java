package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;

//댓글하나 comment와 1:1대응
@Data
public class CommentView {

	//댓글하나
	private Comment comment;
	
	//댓글쓴이
	private UserEntity user;
	
}
