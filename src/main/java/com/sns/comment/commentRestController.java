package com.sns.comment;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.entity.CommentEntity;

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
	
}