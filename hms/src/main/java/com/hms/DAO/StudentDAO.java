package com.hms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.Exception.AdminException;
import com.hms.Exception.RoomException;
import com.hms.Exception.StudentException;
import com.hms.Model.StudentModel;
import com.hms.Util.Db_Connection;

public class StudentDAO {

	RoomDAO roomdao = new RoomDAO();
	private Connection connection;

	public StudentDAO() {
		this.connection = Db_Connection.getConnection();
	}

	public void addStudent(StudentModel student) throws StudentException {
		String sql = "INSERT INTO Students (stuId, name, age, gender, dob, address, mobile, email, aadhar, gname, gmobile, gaddress,roomno,doj) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getStuId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setDate(5, java.sql.Date.valueOf(student.getDob()));
			preparedStatement.setString(6, student.getAddress());
			preparedStatement.setLong(7, student.getMobile());
			preparedStatement.setString(8, student.getEmail());
			preparedStatement.setLong(9, student.getAadhar());
			preparedStatement.setString(10, student.getGname());
			preparedStatement.setLong(11, student.getGmobile());
			preparedStatement.setString(12, student.getGaddress());
			preparedStatement.setInt(13, student.getRoomno());
			preparedStatement.setDate(14, student.getDoj());
			preparedStatement.executeUpdate();

			System.out.println("Student Details Registered Successfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void oldStudent(String stdid) throws StudentException, RoomException {
		try {
			String sql = "INSERT INTO oldstudent select * from students where stuid='" + stdid + "'";
			String s = "select roomno from students where stuid='" + stdid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(s);
			int roomno = 0;
			while (rs.next()) {
				roomno = rs.getInt("roomno");
			}
			int capacity = roomdao.getRoomCapacity(roomno);
			int result = capacity + 1;
			roomdao.updateRoomCapacity(roomno, result);
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected > 0) {
				try {
					String sq = "DELETE FROM Students WHERE stuId = ?";
					PreparedStatement prepared = connection.prepareStatement(sq);
					prepared.setString(1, stdid);
					int affectedrow = prepared.executeUpdate();
					if (affectedrow > 0) {
						System.out.println("Student Details Deleted Successfully");
					}
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			} else {
				throw new StudentException("Invalid Student Id");

			}
		} catch (SQLException | StudentException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public void updateStudent(String attribute, String id, String newValue) throws StudentException {
		
		try {
			String query = "UPDATE students SET " + attribute + " = ? WHERE stuId = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, newValue);
			pstmt.setString(2, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("The Student details was updated successfully!");
			} else {
				throw new StudentException("Invalid Student id");
			}
		} catch (SQLException | StudentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public StudentModel getStudent(String stuId) throws StudentException, AdminException {
		StudentModel student = null;
		try {
			String sql = "SELECT * FROM Students WHERE stuId = '" + stuId + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				student = new StudentModel();
				student.setStuId(resultSet.getString("stuId"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
				student.setGender(resultSet.getString("gender"));
				student.setDob(resultSet.getDate("dob").toLocalDate());
				student.setAddress(resultSet.getString("address"));
				student.setMobile(resultSet.getLong("mobile"));
				student.setEmail(resultSet.getString("email"));
				student.setAadhar(resultSet.getLong("aadhar"));
				student.setGname(resultSet.getString("gname"));
				student.setGmobile(resultSet.getLong("gmobile"));
				student.setGaddress(resultSet.getString("gaddress"));
				student.setRoomno(resultSet.getInt("roomno"));
				student.setDoj(resultSet.getDate("doj"));
			} else {
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return student;
	}
	

	public List<String> getOldStudent() throws RoomException {
		List<String> studentDetails = new ArrayList<>();
		try {
			String sql = "SELECT * FROM oldstudent";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Student Available");
			} else {
			studentDetails.add("=============================");
			studentDetails.add(String.format("%-10s %-20s", "Field", "Value"));
			studentDetails.add("=============================");
			while (rs.next()) {
				studentDetails.add(String.format("%-10s %-20s", "stuId", rs.getString("stuid")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Name", rs.getString("name")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Email", rs.getString("email")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Mobile", rs.getString("mobile")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Address", rs.getString("address")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian Name", rs.getString("gname")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian mobile", rs.getString("gmobile")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian Address", rs.getString("gaddress")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "RoomNo", rs.getString("roomno")));
				studentDetails.add("-----------------------------");
				studentDetails.add("*********************************************************************");
			}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return studentDetails;

	}
	public List<String> availableStudent() throws RoomException {
		List<String> studentDetails = new ArrayList<>();
		try {
			String sql = "SELECT * FROM students";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Student Available");
			} else {
			studentDetails.add("=============================");
			studentDetails.add(String.format("%-10s %-20s", "Field", "Value"));
			studentDetails.add("=============================");
			while (rs.next()) {
				studentDetails.add(String.format("%-10s %-20s", "stuId", rs.getString("stuid")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Name", rs.getString("name")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Email", rs.getString("email")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Mobile", rs.getString("mobile")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Address", rs.getString("address")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian Name", rs.getString("gname")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian mobile", rs.getString("gmobile")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "Gaurdian Address", rs.getString("gaddress")));
				studentDetails.add("-----------------------------");
				studentDetails.add(String.format("%-10s %-20s", "RoomNo", rs.getString("roomno")));
				studentDetails.add("-----------------------------");
				studentDetails.add("*********************************************************************");
			}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return studentDetails;

	}
}