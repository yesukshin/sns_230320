package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.Entity.PostEntity;
import com.sns.post.dao.PostMapper;
import com.sns.post.dao.PostRepository;
import com.sns.post.domain.Post;

@Service
public class PostBO {
	@Autowired 
	private PostMapper postMapper; //Mapper라고 붙어 있으면 mybatis
	
	@Autowired 
	private PostRepository postRepository; 
	
	// input : userId
	// ouput : List<Post>
	// 널이 들어올수 없으므로 int : int userId
	public List<Post> getPostListByUserId(int userId){
		
		return postMapper.selectPostListByUserId(userId);
	}
   
	public List<PostEntity> getPostList() {
		
		return postRepository.findAll();
	}
}
