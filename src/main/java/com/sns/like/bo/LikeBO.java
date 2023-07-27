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
	
	// input : postId
    // output: 좋아요갯수
	public int countByPostId(int postId) {
		
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
		
	}
	
	public boolean getLikeByPostIdAndUserId(int postId, Integer userId) {
		
		if (userId == null) {
			return false;
		}
		return false;
//		return likeMapper.selectLikeCountByPostIdOrUserId(postId,userId);
//		if (data==null) {
//			return false;
//			
//		}else {
//			return true;
//		}
			
	}

	public void deleteLikeByPostId(int postId) {
		
		likeMapper.deleteLike(postId,null);	
		
	}
}