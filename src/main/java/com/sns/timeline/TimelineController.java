package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
	    
		List<CardView> cardViewList = timelineBO.generateCardViewList(); 
		
		model.addAttribute("cardViewList", cardViewList);
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}