package com.example.UserFeedback.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserFeedback.entities.Feedback;
import com.example.UserFeedback.repositories.FeedbackRepository;


@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Iterable<Feedback> getAllFeedback() {
		return feedbackRepository.findAll();
	}
	
	public void addFeedback(Feedback userFeedback) {
    	feedbackRepository.save(userFeedback);
    }
	
	public Feedback getFeedbackById(int id)
	{
		Optional<Feedback> foundFeedback = feedbackRepository.findById(id);
		
		return foundFeedback.get();
	}
	
	public boolean validateEmptyInput(String user)
	{
		if(user.isEmpty() || user.trim().isEmpty())
		{
			return false;
		}
		return true;
	}
}
