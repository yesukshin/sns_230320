package com.sns.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.post.domain.Post;

//MyBatis
@Repository
public interface PostMapper {
	
	public List<Post> selectPostListByUserId(int userId);
	
	public List<Post> selectPostList();

}