package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.domain.Post;

//MyBatis
@Repository
public interface PostMapper {

	public List<Post> selectPostListByUserId(int userId);

	//public List<Post> selectPostList();
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("file") String file);

	public void deletePostByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);

}