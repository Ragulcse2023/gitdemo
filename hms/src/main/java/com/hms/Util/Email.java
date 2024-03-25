package com.hms.Util;

import java.util.Properties;

//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class Email {
//
//    public static void main(String[] args) {
//System.out.println("USer name is processed");
//        final String username = "risingragul@gmail.com";
//        final String password = "bnrkwpqjgzbbqszq";
//        System.out.println("property is processed");
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        System.out.println("session is processed");
//        Session session = Session.getInstance(props,
//          new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//          });
//
//        try {
//        	System.out.println("Inside try");
//            Message message = new MimeMessage(session);
//            System.out.println("session created");
//            message.setFrom(new InternetAddress("risingragul@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse("harshank05@gmail.com"));
//            message.setSubject("Testing Subject");
//            message.setText("Dear Mail Crawler,"
//                + "\n\n No spam to my email, please!");
//            System.out.println("message is set processed");
//            Transport.send(message);
//
//            System.out.println("Done");
//            System.out.println("message sent processed");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.hms.Model.StudentModel;

public class Email {

	public static void mail(StudentModel student) {
		final String username = "risingragul@gmail.com";
		final String password = "bnrkwpqjgzbbqszq";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
			message.setSubject("Registeration Details Email");
			String regdet = "<!DOCTYPE html><html><head><title>Registration Successful</title></head><body><h1>Registration Successful!</h1><p>Congratulations, you have successfully registered as a student. Here are your details:</p><ul><li>Name: "
					+ student.getName() + "</li><li>Age: " + student.getAge() + "</li><li>Gender: "
					+ student.getGender() + "</li><li>Date of Birth: " + student.getDob() + "</li><li>Address: "
					+ student.getAddress() + "</li><li>Mobile: " + student.getMobile() + "</li><li>Email: "
					+ student.getEmail() + "</li><li>Aadhar: " + student.getAadhar() + "</li><li>Guardian Name: "
					+ student.getGname() + "</li><li>Guardian Mobile: " + student.getGmobile()
					+ "</li><li>Guardian Address: " + student.getGaddress() + "</li><li>Student Id: "
					+ student.getStuId() + "</li></ul><p>Thank you for registering with us.</p></body></html>";
			message.setContent(regdet, "text/html");
			Transport.send(message);
			System.out.println("Email Sent Successfully!");
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}

}
