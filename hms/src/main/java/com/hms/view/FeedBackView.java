package com.hms.view;

import java.util.List;

public class FeedBackView{

public void viewFeedback(List<String> s) {
	System.out.println("FeedBack of All Students:");
	for (String row : s) {
	    System.out.println(row);
	}
System.out.println("|--------------------------------|--------------------------------|--------------------------------|");

}
}
