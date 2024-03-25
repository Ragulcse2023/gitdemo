package com.hms.controller;

import java.util.List;

import com.hms.view.FeedBackView;

public class FeedbackController {
	static FeedBackView feedview = new FeedBackView();

	public void displayFeedback(List<String> feedbacklist){
		feedview.viewFeedback(feedbacklist);
	}
}
