package com.hms.view;

import java.util.List;

import com.hms.Exception.RoomException;

public class RoomView {
	public void getRoom(List<String> s) throws RoomException {
System.out.println("All Room Status:");
		for (String row : s) {
			System.out.println(row);
		}
		System.out.println("|------------|------------|-----------------|-----------------|");

	}
}
