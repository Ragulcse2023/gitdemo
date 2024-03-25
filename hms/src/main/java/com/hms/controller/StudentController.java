package com.hms.controller;

import java.util.List;
import java.util.Scanner;

// Importing necessary classes and exceptions
import com.hms.Exception.AdminException;
import com.hms.Exception.RoomException;
import com.hms.Exception.StudentException;
import com.hms.Model.StudentModel;
import com.hms.Service.FeedBackService;
import com.hms.Service.StudentService;
import com.hms.view.StudentView;

public class StudentController {
	// Scanner object for user input
	Scanner sc = new Scanner(System.in);
	StudentView stdview = new StudentView();
	// Method to control student operations

	public  void control() throws StudentException, RoomException, AdminException {
		// Creating a StudentService object
		StudentService stdservice = new StudentService();
		FeedBackService feedservice = new FeedBackService();
		boolean b = true;
		while (b) {
			// Displaying options to the user
			System.out.println(
					"Enter Your choice\n" + "1.Registration \n" + "2.Update Details\n" + "3.feedback\n" + "4.exit");
			// Validating user input
			while (!sc.hasNextInt()) {
				System.out.println("That's not a valid choice type! Try again:");
				sc.next();
			}
			int choice = sc.nextInt();
			switch (choice) {
			case (1): {
				// Inserting student details into DB
				stdservice.addStudent();
				break;
			}
			case (2): {
				// Updating Details of student
				stdservice.update();
				break;
			}
			case (3): {
				// Insert feedback details
				feedservice.createFeedBack();
				break;
			}
			case (4): {
				// Exit the application
				b = false;
				break;
			}
			default: {
				System.out.println("Please enter a Valid Choice!!!!");
				break;
			}
			}
		}
	}
	// Method to get student details
	public void getstdView(StudentModel studModel) {
		stdview.StudDetailView(studModel);
	}

	// Method to get old student details
	public void getoldstd(List<String> stdModel) {
		stdview.oldStudView(stdModel);
	}
	public void  availableStd(List<String> stdModel) {
		stdview.stdView(stdModel);
	}
	// Method to get available student details
	public void getavailablestd(List<String> stdModel) {
		stdview.stdDetailView(stdModel);
	}
}
