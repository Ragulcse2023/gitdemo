package com.hms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.Util.Db_Connection;

public class FeedBackDAO {

	private Connection connection;

	public FeedBackDAO() {
		this.connection = Db_Connection.getConnection();
	}

	public void insertFeedback(String studentName, String email, String feedback) {
		try {
			String sql = "INSERT INTO feedback(name,email, feedback) VALUES(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, studentName);
			pstmt.setString(2, email);
			pstmt.setString(3, feedback);
			pstmt.executeUpdate();
			System.out.println("FeedBack Submitted Successfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String> ViewFeedback() {
		String sql = "SELECT * FROM feedback";
		List<String> feedList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			feedList.add(
					"|--------------------------------|--------------------------------|--------------------------------|");

			feedList.add(String.format("| %-30s | %-30s | %-30s |", "Name", "Email", "Feedback"));
			feedList.add(
					"|--------------------------------|--------------------------------|--------------------------------|");
			while (resultSet.next()) {
				String row = String.format("| %-30s | %-30s | %-30s |", resultSet.getString("name"),
						resultSet.getString("email"), resultSet.getString("feedback"));
				feedList.add(row);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return feedList;
	}
}