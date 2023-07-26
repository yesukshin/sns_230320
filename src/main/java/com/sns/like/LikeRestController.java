package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;
import com.sns.like.domain.LIKE;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	//GET : /like?postId=13  @RequestParam("postId") 
	//GET : /like/13         @PathVariable
	@RequestMapping("/like/{postId}")  //{postId} 매번바뀜, 와일드카드라고 함
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
        Map<String, Object> result = new HashMap<>();
		
		Integer loginId = (Integer)session.getAttribute("userId");
		
		if (loginId == null) {
			result.put("code", 300);
		    result.put("errorMessage", "로그인이 되지 않은 사용자입니다.");
		    
		    return result;
		}
		
		//BO호출(like 테이블 채우거나 빼거나->로직은 BO에서 처리해야함)
		//likeToggle(int postId, int userId)
		likeBO.likeToggle(postId, loginId);
		result.put("code", 1);
	    result.put("result", "성공");
		
		//응답 내림
	    return result;
	}
	
}
