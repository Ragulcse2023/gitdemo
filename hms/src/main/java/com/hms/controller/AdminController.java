package com.hms.controller;

import java.util.Scanner;

// Importing necessary classes and exceptions
import com.hms.Exception.AdminException;
import com.hms.Exception.RoomException;
import com.hms.Exception.StudentException;
import com.hms.Service.FeedBackService;
import com.hms.Service.RoomService;
import com.hms.Service.StudentService;

public class AdminController {
	static Scanner sc = new Scanner(System.in);
	public void control() throws AdminException, StudentException, RoomException {
		while (true) {
			System.out.print("Enter the password: ");
			while (!sc.hasNext("[a-zA-Z0-9]+")) {
				System.out.println("That's not a valid password! Try again:");
				sc.next();
			}
			String pwd = sc.next();
			if (pwd.equals("admin")) {
				System.out.println("Login Successful.\n");
				mainview();
				break;
			} else {
				System.out.println("Wrong Password.\n");
			}
		}
	}

	public static void mainview() throws StudentException, AdminException, RoomException {
		RoomService roomservice = new RoomService();
		StudentService studentservice = new StudentService();
		FeedBackService feedbackservice = new FeedBackService();

		boolean b = true;
		while (b) {
			System.out.println(
					"Enter Your choice\n" + 
					"1.Get Student \n" + 
					"2.Delete Student\n" + 
					"3.Add Room\n"+ 
					"4.Update Room\n" +
					"5.Get Room\n" +
					"6.Available Student in Room\n" + 
					"7.Display Feedback\n"+ 
					"8.Old Students\n"+
					"9.Available Student\n" + 
					"10.LogOut");
			
			while (!sc.hasNextInt()) {
				System.out.println("That's not a valid Choice type try again with digits");
				sc.next();
			}
			int ch = sc.nextInt();
			switch (ch) {
			case (1): {
				// Student service for getting details
				studentservice.getStdDetail();
				break;
			}
			case (2): {
				// Delete student from DB
				studentservice.deleteStud();
				break;
			}
			case (3): {
				// Insert room into DB
				roomservice.addRoom();
				break;
			}
			case (4): {
				// Update room status
				roomservice.updateRoom();
				break;
			}
			case (5): {
				// Get Room Details from DB
				roomservice.getRoomDetails();
				break;
			}
			case (6): {
				// Get Student Details who Stay in particular Room using Roomno
				roomservice.getStudDetails();
				break;
			}
			case (7): {
				// Display Feedback of all users
				feedbackservice.displayFeedback();
				break;
			}
			case (8): {
				// Display of all old students
				studentservice.oldStdDetail();
				break;
			}
			case (9): {
				// Display of all old students
				studentservice.currentStdDetail();
				break;
			}
			case (10): {
				b = false;
				break;
			}
			default: {
				System.out.println("Please Enter a Correct Choice");
				break;
			}
			}
		}
	}
}
