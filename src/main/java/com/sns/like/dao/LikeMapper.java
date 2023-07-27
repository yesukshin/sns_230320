package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.domain.LIKE;

@Repository
public interface LikeMapper {
   //ctrl + alt + h => 호출하는 곳 찾는거
	public LIKE selectByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public int selectCountByPostId(int postId);
    
	// ByPostIdAndUserId, ByPostId => 하나의 쿼리로 합친다
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);
	
	
	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public void deleteLike(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);

	
	
}  
