package com.hms.Model;

import com.hms.DAO.FeedBackDAO;
import com.hms.Exception.FeedbackException;

public class FeedBackModel extends FeedBackDAO {

	private String studentName;
	private String email;
	private String feedback;

	public FeedBackModel() {
	}
	public FeedBackModel(String studentName, String email, String feedback) throws FeedbackException {
		this.studentName = studentName;
		this.email = email;
		this.feedback = feedback;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
