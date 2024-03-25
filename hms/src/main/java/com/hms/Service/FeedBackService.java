package com.hms.Service;

import java.util.Scanner;

import com.hms.DAO.FeedBackDAO;
import com.hms.Model.FeedBackModel;
import com.hms.controller.FeedbackController;

public class FeedBackService extends FeedBackDAO {
	FeedbackController fc = new FeedbackController();

	public void createFeedBack() {
		FeedBackModel feedmodel = new FeedBackModel();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Name");
		while (!s.hasNext("[a-zA-Z]+")) {
			System.out.println("That's not a valid name! Try again:");
			s.next();
		}
		String name = s.next();
		System.out.println("Enter email");
		while (!s.hasNext("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
			System.out.println("That's not a valid email! Try again:");
			s.next();
		}
		String email = s.next();
		s.nextLine();
		System.out.println("Enter feedback");
		String feedback = s.nextLine();
		feedmodel.insertFeedback(name, email, feedback);
	}

	public void displayFeedback() {
		fc.displayFeedback(ViewFeedback());
	}
}
