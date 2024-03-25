package com.hms.view;

import java.util.List;

import com.hms.Model.StudentModel;

public class StudentView {
	public void StudDetailView(StudentModel stdmodel) {
		try {
			System.out.println(stdmodel.toString());
		} catch (NullPointerException e) {
			System.out.println("Invalid Student Id\n");
		}
	}

	public void oldStudView(List<String> s) {
		for (String row : s) {
			System.out.println(row);
		}
	}

	public void stdDetailView(List<String> s) {
		for (String row : s) {
			System.out.println(row);
		}
	}

	public void stdView(List<String> s) {

		for (String row : s) {
			System.out.println(row);
		
}
}
	}