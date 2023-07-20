package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create( 
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session)
	{
		
		// 세션에서 유저정보 받아옴
		int userId = (Integer)session.getAttribute("userId");
		
		//db insert
		String tmpFile = "image";
		int rowcnt = postBO.addPost(userId, subject, content, tmpFile);
		
		Map<String, Object> result = new HashMap<>();
		
		if (rowcnt > 0) {
			result.put("code", 1);
		}else {
			result.put("code", 500);
			result.put("errorMessage","글쓰기 저장중 오류발생");
		}
		
		return result;
	}

}