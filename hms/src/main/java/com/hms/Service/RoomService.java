package com.hms.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.hms.DAO.RoomDAO;
import com.hms.Exception.RoomException;
import com.hms.Model.RoomModel;
import com.hms.controller.RoomController;
import com.hms.controller.StudentController;
import com.hms.view.StudentView;

public class RoomService {
	RoomDAO roomdao = new RoomDAO();
	RoomController roomcontroller = new RoomController();
	StudentView studview = new StudentView();
	StudentController studcontroller = new StudentController();
	Scanner scanner = new Scanner(System.in);

	public void addRoom() throws RoomException {
		System.out.println("Enter room number:");
		while (!scanner.hasNextInt()) {
			System.out.println("That's not a valid room number! Try again:");
			scanner.next();
		}
		int roomno = scanner.nextInt();

		System.out.println("Enter capacity:");
		while (!scanner.hasNextInt()) {
			System.out.println("That's not a valid capacity! Try again:");
			scanner.next();
		}
		int capacity = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter room type:");
		String roomtype = scanner.nextLine();
		System.out.println("Is the room available? (true/false):");
		while (!scanner.hasNextBoolean()) {
			System.out.println("That's not a valid input! Enter true or false:");
			scanner.next();
		}
		boolean isAvailable = scanner.nextBoolean();
		RoomModel roommodel = new RoomModel(roomno, capacity, roomtype, isAvailable);
		roomdao.addRoom(roommodel);

	}

	public void updateRoom() throws RoomException {

		String[] list = { "roomno", "capacity", "roomtype", "isavailable" };
		List<String> attr = Arrays.asList(list);
		System.out.println("Enter room number:");
		while (!scanner.hasNextInt()) {
			System.out.println("That's not a valid room number! Try again:");
			scanner.next();
		}
		int roomno = scanner.nextInt();
		String attribute = "";
		while (true) {
			System.out.println("Enter attribute type:(roomno,capacity,roomtype,isavailable) ");
			attribute = scanner.next();
			if (attr.contains(attribute)) {
				break;
			} else {
				System.out.println("Invalid Attribute Please Enter Valid attribute as Mentioned");
			}
		}
		System.out.println("Enter new value:");
		String newvalue = scanner.next();
		roomdao.updateRoom(newvalue, attribute, roomno);
	}

	public void getRoomDetails() throws RoomException {
		roomcontroller.getAllRoom(roomdao.displayRoom());
	}

	public void getStudDetails() throws RoomException {

				
				System.out.println("Enter roomno");
				while (!scanner.hasNextInt()) {
					System.out.println("That's not a room number! Try again:");
					scanner.next();
				}
				int roomno = scanner.nextInt();
				
				scanner.nextLine();
				if (roomdao.roomAvailable(roomno)) {
				studcontroller.getavailablestd(roomdao.getAvailableStudent(String.valueOf(roomno)));

				}
		}

	}
