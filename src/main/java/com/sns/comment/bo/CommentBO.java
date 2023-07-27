package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	//댓글 입력
	public void addComment(int postId, int userId, String content) {
		
		commentMapper.insertComment(postId,userId,content );
	}

    //댓글조회
	public List<Comment> getCommentList() {
		
		return commentMapper.selectCommentList();
	};
	
	// 게시글에 대한 댓글
	// input : postId
	// output : 가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int postId) {
		//결과리스트	
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글목록 가져온다
		// 글에 해당하는 댓글들 가져오고
		List<Comment> commentList =  commentMapper.selectCommentListByPostId(postId);
		
 		// 반복문 순회 comment ->commentView -> commentViewList담는다
		for (Comment comment : commentList) {
			
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			
			// user정보 가져옴
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
			
		}
		
		return commentViewList;
	};
	
	// 댓글삭제
	public void delComment(int commentId) {
			
		commentMapper.deleteCommentByCommentId(commentId);
	}

	public void deleteCommentByPostId(int postId) {
		
		commentMapper.deleteCommentByPostId(postId);
	}

}