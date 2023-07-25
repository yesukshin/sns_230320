package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;
import com.sns.like.domain.LIKE;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
    
	// input : 누가 어느글에
	// output: 없음
	public void likeToggle(int postId, int userId) {
		
		LIKE likeData = likeMapper.selectByPostIdAndUserId(postId,userId);
		
		if (likeData == null) {
			// 좋아요 없으므로 등록
			likeMapper.insertLike(postId,userId);			
		}
		else {
			likeMapper.deleteLike(postId,userId);	
			
		}
	}
	
	public int countByPostId(int postId) {
		
		return likeMapper.selectCountByPostId(postId);
		
	}
	
	public LIKE countByPostIdAndUserId(int postId, int userId) {
		
		return likeMapper.selectByPostIdAndUserId(postId,userId);
	}
}
