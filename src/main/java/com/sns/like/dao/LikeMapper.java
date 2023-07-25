package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.domain.LIKE;

@Repository
public interface LikeMapper {

	public LIKE selectByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public void deleteLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public int selectCountByPostId(int postId);
	
	
}  
