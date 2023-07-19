package com.sns.timeline;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	//PostBO
	private PostBO postBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
	
		List<PostEntity> postList = postBO.getPostList();
		
		model.addAttribute("result", postList);
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}