package com.sns.post.dao;

import java.util.List;

import com.sns.post.Entity.PostEntity;

public interface PostRepository {

	public List<PostEntity> findAll();
}
