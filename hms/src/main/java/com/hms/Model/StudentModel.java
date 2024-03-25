package com.hms.Model;

import java.sql.Date;
import java.time.LocalDate;

public class StudentModel {
	private String stuId;
	private String name;
	private int age;
	private String gender;
	private LocalDate dob;
	private String address;
	private long mobile;
	private String email;
	private long aadhar;
	private String gname;
	private long gmobile;
	private String gaddress;
	private int roomno;
	private Date doj;

	public int getRoomno() {
		return roomno;
	}

	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

	public String getStuId() {
		return stuId;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public long getGmobile() {
		return gmobile;
	}

	public void setGmobile(long gmobile) {
		this.gmobile = gmobile;
	}

	public String getGaddress() {
		return gaddress;
	}

	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public long getAadhar() {
		return aadhar;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StudentModel() {
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public StudentModel(String name, int age, String gender, LocalDate localDate, String address, long mobile,
			String email, long aadhar, String gname, long gmobile, String gaddress, int roomno, Date doj) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = localDate;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.aadhar = aadhar;
		this.gname = gname;
		this.gmobile = gmobile;
		this.gaddress = gaddress;
		this.roomno = roomno;
		this.doj = doj;
		this.setStuId((name.substring(0, 2) + String.valueOf(Integer.parseInt(String.valueOf(aadhar).substring(0, 3))
				+ Integer.parseInt(String.valueOf(mobile).substring(0, 3)))));
	}

	@Override
	public String toString() {
		return String.format(
				"*******************************\n" + "%-10s %-20s\n" + "*******************************\n"
						+ "%-10s %-20s\n" + "--------------------------------\n" + "%-10s %-20s\n"
						+ "--------------------------------\n" + "%-10s %-20s\n" + "--------------------------------\n"
						+ "%-10s %-20s\n" + "--------------------------------\n" + "%-10s %-20s\n"
						+ "--------------------------------\n" + "%-10s %-20s\n" + "--------------------------------\n"
						+ "%-10s %-20s\n" + "-------------------------------\n" + "%-10s %-20s\n"
						+ "-------------------------------\n" + "%-10s %-20s\n" + "-------------------------------\n"
						+ "%-10s %-20s\n" + "-------------------------------\n" + "%-10s %-20s\n"
						+ "-------------------------------\n" + "%-10s %-20s\n" + "-------------------------------\n"
						+ "%-10s %-20s\n" + "-------------------------------\n",
				"Details", "Value", "stuId", stuId, "name", name, "age", age, "gender", gender, "dob", dob, "address",
				address, "mobile", mobile, "email", email, "aadhar", aadhar, "gname", gname, "gmobile", gmobile,
				"gaddress", gaddress, "roomno", roomno, "doj", doj);
	}
}
