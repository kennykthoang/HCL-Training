package com.example.UserFeedback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.UserFeedback.services.FeedbackService;
import com.example.UserFeedback.entities.Feedback;

@Controller
public class TestFormController {

	@Autowired
	FeedbackService feedbackService;
	
	 @GetMapping(value="/")
     public String showIndexPage()
     {
		 return "index";
     }
	
	@GetMapping("/addfeedback")
    public String showFeedbackPage(){  
		 return "addfeedback";
    }
	
	// TODO: implement form submission
	// TODO: add view JSP
	// TODO: Call RestTemplate and make POST json request to localhost:8090/feedback
	@PostMapping("/addfeedback")
	public ModelAndView submitFeedback(@RequestParam String user, @RequestParam int rating, 
			@RequestParam String comments)
	{
		Feedback fb = new Feedback();
		fb.setUser(user);
		fb.setRating(rating);
		fb.setComments(comments);
//		feedbackService.addFeedback(fb);
		RestTemplate restTemplate = new RestTemplate();
		String feedbackAsJson = restTemplate.postForObject("http://localhost:8090/feedback", fb, String.class);
		
//		return "redirect:/feedback";
		return new ModelAndView("view", "viewjson", feedbackAsJson);
	}
	
	@GetMapping("/view")
	public String showJson()
	{
		return "view";
	}
}
