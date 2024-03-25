package com.hms.Main;

import java.util.Scanner;

import com.hms.Exception.AdminException;
import com.hms.Exception.RoomException;
import com.hms.Exception.StudentException;
import com.hms.controller.AdminController;
import com.hms.controller.StudentController;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	String message = "";

	public static void main(String[] args) throws StudentException, AdminException, RoomException {
		StudentController stdcontrol = new StudentController();
		AdminController admincontrol = new AdminController();
		boolean a = true;
		while (a) {
			System.out.println(
					"===========================================\n"+
				    "          Welcome to ARC Hostel !!!          " + 
					"\n=========================================\n");

			System.out.println("Your choice\n"

					+ "1.Admin\n"

					+ "2.User\n"

					+ "3.Exit");

			while (!sc.hasNextInt()) {
				System.out.println("That's not a valid Choice type! Try again:");
				sc.next();
			}

			int i = sc.nextInt();
			switch (i) {
			case (1): {
				admincontrol.control();
				break;
			}
			case (2): {
				stdcontrol.control();
				break;
			}
			case (3): {
				System.out.println("Thank You For Visiting Us !!! ");
				a = false;
				break;
			}
			default: {
				System.out.println("Invalid Choice Please enter Valid Choice");
				break;
			}
			}

		}
	}

}

