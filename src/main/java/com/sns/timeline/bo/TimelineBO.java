package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.like.domain.LIKE;
import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

// 브라우저 -> 타임라인컨트롤러 -> 타임라인BO -> PostBo ->POstRepository -> DB
// 다른 엔티티나 도메인의 레퍼지토리를 부를 수 없다
// BO끼리 서로 부를수 없다(상호참조, 순환참조오류)
@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	//input
	//output : List<CardView>
	//비로그인시에도 카드는 뿌려져야 하므로  Integer userId 널허용으로 가져옴
    public List<CardView> generateCardViewList(Integer userId){
    	
    	List<CardView> cardViewList = new ArrayList<>();
    	// 글목록 가져온다
    	// 글목록 반복문 순회
    	// postEntity => cardView => cardViewList에 담는다
    	List<PostEntity> postList = postBO.getPostList();
    	
    	//Iterator 통한 전체 조회
    	Iterator<PostEntity> iterator = postList.iterator();
    	
    	// for (PostEntity post : postList) 
    	//{
    	// CardView cardView = new CardView();
    	// cardView.setPost(post); 
    	// UserEntity user = userBO.getUserEntityById(post.getUserId());
    	// cardView.setPost(user); 
    	//}
    	
    	
    	while (iterator.hasNext()) {
    		
    		CardView cardView = new CardView();
    		
    		// 글셋팅
    		PostEntity postData = iterator.next();
    		cardView.setPost(postData); 
    		
    		// 글쓴이 셋팅
    		UserEntity user = userBO.getUserEntityById(postData.getUserId());
    		
    		cardView.setUser(user); 
    		
    		// 하나의 게시글에 대응하는 댓글들을 셋팅한다
    	    List<CommentView> commentViewList = commentBO.generateCommentViewList(postData.getId());
    	    cardView.setCommentList(commentViewList);
    	   
			// 좋아요 갯수
    	    int likeCount = likeBO.countByPostId(postData.getId());
    	    cardView.setLikeCount(likeCount);
    	    
    		// 좋아요 여부 확인(로그인 또는 비로그인시 다 체크)
    	    boolean filledLike = false;
    	    filledLike = likeBO.getLikeByPostIdAndUserId(postData.getId(), userId);
    	
    	    cardView.setFilledLike(filledLike);
    	    
    	    //------------------------------//
    	    // 최종적으로 cardViewList에 담는다   //
    	    //------------------------------//
    	    cardViewList.add(cardView);
    	     
    	} 
    	
    	return cardViewList;
    }
    
    public void generateCardViewDelete(int postId, int userId){
    	
    	// post 삭제
    	postBO.deletePostByPostIdAndUserId(postId,userId);
    	// comment 삭제
    	commentBO.deleteCommentByPostId(postId);
    	// like 삭제
    	likeBO.deleteLikeByPostId(postId);
    	
    }
}