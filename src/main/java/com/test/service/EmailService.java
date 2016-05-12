package com.test.service;

import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendSimpleMail(String recepientName, String recpientEmail, Locale locale, String subject,
			String confirmationUrl, String template) throws Exception {
		final Context ctx= new Context(locale);
		
		ctx.setVariable("name", recepientName);
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("confirmationUrl", confirmationUrl);
		
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		try {
			message.setSubject(subject);
			message.setFrom("bicyclerentaljava@gmail.com");
			message.setTo(recpientEmail);
			
			
			final String htmlContent =this.templateEngine.process(template, ctx);
			message.setText(htmlContent,true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.mailSender.send(mimeMessage);


	}

}
