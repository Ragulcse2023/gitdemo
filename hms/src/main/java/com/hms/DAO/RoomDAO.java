package com.hms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.Exception.RoomException;
import com.hms.Model.RoomModel;
import com.hms.Util.Db_Connection;

public class RoomDAO {

	private Connection connection;

	public RoomDAO() {
		this.connection = Db_Connection.getConnection();
	}

	public void addRoom(RoomModel room) throws RoomException {
		String sql = "INSERT INTO rooms (roomno, capacity, roomtype, isAvailable) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, room.getRoomno());
			pstmt.setInt(2, room.getCapacity());
			pstmt.setString(3, room.getRoomtype());
			pstmt.setBoolean(4, room.isAvailable());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Room Updated  Successfully!!!");
	}

	public List<String> displayRoom() throws RoomException {
		int no = 0;
		List<String> roomNumbers = new ArrayList<>();
		String sql = "SELECT * FROM rooms where isAvailable >='" + no + "'";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			roomNumbers.add("|------------|------------|-----------------|-----------------|");
			roomNumbers.add(String.format("| %-10s | %-10s | %-15s | %-14s |", "Room No", "Capacity", "Room Type",
					"Availablability"));
			roomNumbers.add("|------------|------------|-----------------|-----------------|");
			while (rs.next()) {
				String roomDetails = String.format("| %-10s | %-10s | %-15s | %-15s |", rs.getString("roomno"),
						rs.getString("capacity"), rs.getString("roomtype"), rs.getString("isAvailable"));
				roomNumbers.add(roomDetails);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return roomNumbers;
	}

	public List<String> availableRooms() throws RoomException {
		int no = 0;
		List<String> roomNumbers = new ArrayList<>();
		String sql = "SELECT * FROM rooms where capacity>'" + no + "' and isavailable > '" + no + "'";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			String table = "Room Number\tRoom Type\tRoom Capacity\n";
			while (rs.next()) {
				table += rs.getString("roomno") + "\t\t";
				table += rs.getString("roomtype") + "\t\t";
				table += rs.getString("capacity") + "\n";
			}
			roomNumbers.add(table);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return roomNumbers;
	}

	public int getRoomCapacity(int roomno) throws RoomException {
		int result = 0;
		String sql = "SELECT capacity FROM rooms where roomno='" + roomno + "'";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("capacity");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public void updateRoomCapacity(int roomNo, int capacity) throws RoomException {
		String sql = "UPDATE rooms SET capacity = ? WHERE roomno = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, capacity);
			pstmt.setInt(2, roomNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateRoomNo(int roomNo, boolean available) throws RoomException {
		String sql = "UPDATE rooms SET isavailable = ? WHERE roomno = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setBoolean(1, available);
			pstmt.setInt(2, roomNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean roomAvailable(int roomno) throws RoomException {
		int capacity = 0;
		String sql = "Select roomno FROM rooms WHERE roomno = '" + roomno + "' and capacity > '" + capacity + "' ";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			ResultSet rowaffected = pstmt.executeQuery();
			if (!rowaffected.next()) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public void updateRoom(String newValue, String attribute, int id) throws RoomException {
		try {
			String query = "UPDATE rooms SET " + attribute.toLowerCase() + " = ? WHERE roomno = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, newValue);
			pstmt.setInt(2, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("The Room details was updated successfully!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String> getAvailableStudent(String roomno) throws RoomException {
		List<String> studentDetails = new ArrayList<>();
		try {
			String sql = "SELECT * FROM students where roomno ='" + roomno + "'";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Student Available");
			} else {
				studentDetails.add("=============================");
				studentDetails.add(String.format("%-10s %-20s", "Field", "Value"));
				studentDetails.add("=============================");
				while (rs.next()) {
					studentDetails.add(String.format("%-10s %-20s", "StudentId", rs.getString("stuId")));
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
