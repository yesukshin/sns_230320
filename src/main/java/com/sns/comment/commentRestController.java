package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class commentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/create")
	public Map<String, Object> addComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		commentBO.addComment(postId, 12, content);
		
		Map<String, Object> result = new HashMap<>();
					
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}

}
