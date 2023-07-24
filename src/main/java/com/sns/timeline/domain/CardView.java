package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.Entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;

//View용 객체
//--게시글 1개와 매핑됨
@Data // getter,setter
public class CardView {
  // 필드구성	
  // 1.글 1개
	private PostEntity post;
  // 2.글쓴이 정보
	private UserEntity user;
  // 3.댓글들
	private List<CommentView> commentList;
  // 4.좋아요들
  // 5.좋아요눌렀는지 여부-하트처리	
}
