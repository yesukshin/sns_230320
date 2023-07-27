package com.sns.timeline;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.timeline.bo.TimelineBO;

@RestController
@RequestMapping("/timeline")
public class TimelineRestController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@DeleteMapping("/delete/{postId}")
	public Map<String, Object> delete(
			@PathVariable("postId") int postId,
			HttpSession session)
	{
		int userId = (int)session.getAttribute("userId");
		
		timelineBO.generateCardViewDelete(postId,userId);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;		
		
	}
}

