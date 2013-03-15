package com.sohu.sur.util.gift;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * 下发邮件
 * @author Administrator
 *
 */
public class SendHTMLEmail
{
	   
   public static void send(String to,String subject, String scontent){

	   // Sender's email ID needs to be mentioned
	      String from = "sohuscoregift@sohu.com";

	      // Assuming you are sending email from localhost
	      String host = "192.168.95.47";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subject, "GBK");

	         // Send the actual HTML message, as big as you like
	         message.setContent(scontent,"text/html;charset=GBK" );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   
   }

   public static void main(String [] args)
   {
	   send("zhaoyangtim@qq.com","1","2");
   }
}