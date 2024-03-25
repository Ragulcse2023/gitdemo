package com.hms.controller;

import java.util.List;

import com.hms.Exception.RoomException;
import com.hms.view.RoomView;

public class RoomController {
	RoomView roomview = new RoomView();

	public void getAllRoom(List<String> roomlist) throws RoomException {
		roomview.getRoom(roomlist);
	}
}
