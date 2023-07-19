
  package com.CI.attendance.Service;
  
  import javax.mail.MessagingException;
  import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

  import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.UserRepository;

  @Service 
  public class EmailService {
	  @Autowired
	   private JavaMailSender mailSender;
	  
	  @Autowired private UserRepository repo;
	  
	  public void sendmail( String mail, String dp_password) throws MessagingException
	  {
	  	
	  	if(null!=mail)
	  	{
	  		String password = dp_password;
	  		
	  		System.out.println("Mailid : "+mail);
	  		System.out.println("Password : "+password);
	  		
	          SimpleMailMessage mailMessage = new SimpleMailMessage();
	          mailMessage.setTo(mail);
	          mailMessage.setSubject("Your Auto-Generated Password for CSL!");
	          mailMessage.setFrom("cslorgau@gmail.com");
	          mailMessage.setText("Your Password For CSL is: "
	            +password + ". Please do change your Password after login!");

	          // Send the email
	          mailSender.send(mailMessage);
	  	}
	  	
	  	
	  	
	  }
	  }
  
  