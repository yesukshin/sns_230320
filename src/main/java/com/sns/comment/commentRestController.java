package com.sns.comment;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/create")
	public Map<String, Object> addComment(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String content,
			HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		commentBO.addComment(postId, userId, content);
		
		Map<String, Object> result = new HashMap<>();
					
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
	
	@DeleteMapping("/delete")	
	public Map<String, Object> delComment(
			@RequestParam("userId") int userId,
			@RequestParam("commentId") int commentId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer loginId = (Integer)session.getAttribute("userId");
		
		if (loginId == null) {
			result.put("code", 500);
		    result.put("errorMessage", "로그인이 되지 않은 사용자입니다");
		    
		    return result;
		}
		
		
		// 화면에서 로긴한 사용자의 댓글만 x가 보이게 해놨으므로 아래 비교는 사실 의미없음
		if (userId == loginId) {
		
			commentBO.delComment(commentId);
					
		    result.put("code", 1);
		    result.put("result", "성공");
		    
	    }
		
		return result;
	}
	
}