package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
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
	//input
	//output : List<CardView>
    public List<CardView> generateCardViewList(){
    	
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
    		
    		// cardViewList에 담는다
    	    cardViewList.add(cardView);
    	    
    	} 
    	
    	return cardViewList;
    }
}
