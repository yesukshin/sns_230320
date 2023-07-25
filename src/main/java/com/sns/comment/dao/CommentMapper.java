package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.domain.Comment;

@Repository
public interface CommentMapper {
    
	// 댓글입력
	public void insertComment(@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	// 댓글조회
	public List<Comment> selectCommentList();
	
	// 게시글에 해당하는 댓글조회
	public List<Comment> selectCommentListByPostId(@Param("postId") int postId);
	
	// 댓글삭제
	public void deleteCommentByCommentId(@Param("commentId")  int commentId);
}