package com.hms.Model;

public class RoomModel  {

	private int roomno;
	private int capacity;
	private String roomtype;
	private boolean isAvailable;

	public int getRoomno() {
		return roomno;
	}

	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Room [roomno=" + roomno + ", room=" + capacity + ", roomtype=" + roomtype + ", isAvailable="
				+ isAvailable + "]";

	}

	public RoomModel(int roomno, int capacity, String roomtype, boolean isAvailable) {
		super();
		this.roomno = roomno;
		this.capacity = capacity;
		this.roomtype = roomtype;
		this.isAvailable = isAvailable;
	}

	public RoomModel() {
	}

}
