package com.hms.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.hms.DAO.RoomDAO;
import com.hms.DAO.StudentDAO;
import com.hms.Exception.AdminException;
import com.hms.Exception.RoomException;
import com.hms.Exception.StudentException;
import com.hms.Model.StudentModel;
import com.hms.Util.Email;
import com.hms.controller.StudentController;

public class StudentService {
	private StudentDAO stddao = new StudentDAO();
	StudentController stdcontrol = new StudentController();
	static Scanner scanner = new Scanner(System.in);

	public void addStudent() throws StudentException, RoomException {
		System.out.println("Enter the Details to Register in Hostel Database");

		boolean b = true;
		while (b) {
			RoomDAO roomdao = new RoomDAO();
			int roomno = 0;
			if ((roomdao.availableRooms()).size() > 0) {
				System.out.println(roomdao.availableRooms());
				System.out.println("Enter roomno");
				while (!scanner.hasNextInt()) {
					System.out.println("That's not a room number! Try again:");
					scanner.next();
				}
				roomno = scanner.nextInt();
				scanner.nextLine();
				if (roomdao.roomAvailable(roomno)) {
					b = false;
				}
			}

			if (b == false) {
				System.out.println("Enter User Name");
				while (!scanner.hasNext("[a-zA-Z]+")) {
					System.out.println("That's not a valid name! Try again:");
					scanner.nextLine();
				}
				String name = scanner.nextLine();

				System.out.println("Enter User Age");
				while (!scanner.hasNextInt()) {
					System.out.println("That's not a valid age! Try again:");
					scanner.next();
				}
				int age = scanner.nextInt();

				System.out.println("Enter User gender");
				while (!scanner.hasNext("[a-zA-Z]+")) {
					System.out.println("That's not a valid gender! Try again:");
					scanner.next();
				}
				String gender = scanner.next();

				System.out.println("Enter User DOB (in the format YYYY-MM-DD)");
				while (!scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
					System.out.println("That's not a valid date of birth! Try again:");
					scanner.next();
				}
				String dob = scanner.next();
				scanner.nextLine();

				System.out.println("Enter User Address");
				String address = scanner.nextLine();

				System.out.println("Enter User Mobile Number");
				while (!scanner.hasNext("\\d{10}")) {
					System.out.println("That's not a valid mobile number! Try again:");
					scanner.next();
				}
				long mobile = scanner.nextLong();

				System.out.println("Enter User email");
				while (!scanner.hasNext("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
					System.out.println("That's not a valid email! Try again:");
					scanner.next();
				}
				String email = scanner.next();

				System.out.println("Enter User Aadhar");
				while (!scanner.hasNext("\\d{12}")) {
					System.out.println("That's not a valid Aadhar number! Try again:");
					scanner.next();
				}
				long aadhar = scanner.nextLong();

				System.out.println("Enter Guardian Name");
				while (!scanner.hasNext("[a-zA-Z]+")) {
					System.out.println("That's not a valid name! Try again:");
					scanner.next();
				}
				String gname = scanner.next();

				System.out.println("Enter Guardian Mobile");
				while (!scanner.hasNext("\\d{10}")) {
					System.out.println("That's not a valid mobile number! Try again:");
					scanner.next();
				}
				long gmobile = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Enter Guardian Address");
				String gaddress = scanner.nextLine();
				LocalDate dat = LocalDate.now();
				Date doj = java.sql.Date.valueOf(dat);

				int capacity = roomdao.getRoomCapacity(roomno);
				if (capacity > 0) {
					insertstd(name, age, gender, dob, address, mobile, email, aadhar, gname, gmobile, gaddress, roomno,
							doj);
					int result = capacity - 1;
					roomdao.updateRoomCapacity(roomno, result);
					if (result <= 0) {
						roomdao.updateRoomNo(roomno,false);
					}

				} else {
					System.out.println("No Rooms Available");
					System.out.println("Please contact Warden for more Information");
				}

			} else {
				System.out.println("Give Correct Room No");

			}
		}
	}	
	public void update() throws StudentException {
	    String[] list = { "name", "address", "mobile", "email", "gname", "gmobile", "gaddress" };
	    List<String> attr = Arrays.asList(list);

	    System.out.println("Enter the ID of the Student to update:");
	    String id = scanner.next();

	    String attribute = "";
	    while (true) {
	        System.out.println("Enter the attribute you want to update (Name, age, localDate, address, mobile, email, gname, gmobile, gaddress):");
	        attribute = scanner.next();
	        if (attr.contains(attribute.toLowerCase())) {
	            break;
	        } else {
	            System.out.println("Invalid Attribute Please Enter Valid attribute as Mentioned");
	        }
	    }

	    System.out.println("Enter the new value for " + attribute + ":");
	    String newValue = scanner.next();

	    updatestd(attribute, id, newValue);
	}

	

	public void insertstd(String name, int age, String gender, String dob, String address, long mobile, String email,
			long aadhar, String gname, long gmobile, String gaddress, int roomno, Date doj) throws StudentException {
		StudentModel student = new StudentModel(name, age, gender, LocalDate.parse(dob), address, mobile, email, aadhar,
				gname, gmobile, gaddress, roomno, doj);
		stddao.addStudent(student);
		Email.mail(student);
	}

	public void updatestd(String attribute, String id, String newValue) throws StudentException {
		stddao.updateStudent(attribute, id, newValue);
	}

	public void getStdDetail() throws StudentException, AdminException {
		System.out.println("Enter a Student Id");
		String stdid = scanner.next();
		StudentModel s1 = stddao.getStudent(stdid);
		stdcontrol.getstdView(s1);

	}

	public void deleteStud() throws StudentException, AdminException, RoomException {
		System.out.println("Enter Student Id to delete Student Details");
		String stdid = scanner.next();
		try {
			stddao.oldStudent(stdid);
		} catch (StudentException e) {
			throw new StudentException("Invalid Student Id");
		}
	}

	public void oldStdDetail() throws RoomException {
		stdcontrol.getoldstd(stddao.getOldStudent());
	}
	public void currentStdDetail() throws RoomException {
		stdcontrol.getavailablestd(stddao.availableStudent());
	}


	
}
