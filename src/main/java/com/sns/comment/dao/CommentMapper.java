package com.sns.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {

	public void insertComment(@Param("userId") int postId,
			@Param("subject") int userId,
			@Param("content") String content);
}
