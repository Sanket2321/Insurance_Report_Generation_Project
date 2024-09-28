package com.report.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

//i want to make this class is spring bean thats why i used componant
@Component
public class EmailUtil {
	// I inject one predefine class provide by springboot provide by springboot
	// which is JavaMailSender
	@Autowired
	private JavaMailSender mailSender;// javamailsneder is interface provided by in spring mail dependencies

	public boolean sendEmail(String subject, String body, String to, File f) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();// i used this because i want to send some file in
																		// attachment suppose we want to send simple
																		// message go for simple message instead of mine
																		// msg

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);// here i am passing mimemessage object to
																			// minmessagehepler constructor so we can
																			// say that it constructor injection
			helper.setSubject(subject);
			helper.setText(body, true);// suppose in body are you using any html tag then after you make it true
			helper.setTo(to);
			helper.addAttachment("plans_info.xlsx", f);
			helper.addAttachment("plans.pdf", f);
			mailSender.send(mimeMessage);
		} catch (Exception e) {

		}
		return true;

	}

}
