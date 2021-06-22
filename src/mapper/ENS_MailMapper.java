package mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.UserBean;
import utility.ENS_hibernateFactory;

public class ENS_MailMapper implements dao.ENS_MailDao{
	
	public String msg="";
	
	
	 public ENS_MailMapper() {
		// TODO Auto-generated constructor stub
		
		ENS_hibernateFactory.buildIfNeeded();
	}

	@Override
	public boolean sendMail(String mailSubject, String mailText, List<String> mailTO, String mailFrom, Map<String, String> mapInlineImages) {
		// TODO Auto-generated method stub
		
		boolean val=false;
		
		String host = "corpmail01.apollotyres.com";
		//String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host",host);
		javax.mail.Session session2 = javax.mail.Session.getDefaultInstance(properties);
		
		System.out.println("inside send mail Mapper");
		try
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session2);
			
			// creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        // Now set the actual message
	     	messageBodyPart.setContent(mailText,"text/html");
	        
			 // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
										
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(mailFrom));
						
			//prepare the list in which all the email id is store
			for(String EmailID:mailTO )
			{				
				System.out.println("mail is send to this person : -- >  "+EmailID);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(EmailID));
			}
			// Set Subject: header field
			message.setSubject(mailSubject);
			
			// adds inline image attachments
	        if (mapInlineImages != null && mapInlineImages.size() > 0) {
	            Set<String> setImageID = mapInlineImages.keySet();
	             
	            for (String contentId : setImageID) {
	                MimeBodyPart imagePart = new MimeBodyPart();
	                imagePart.setHeader("Content-ID", "<" + contentId + ">");
	                imagePart.setDisposition(MimeBodyPart.INLINE);
	                 
	                String imageFilePath = mapInlineImages.get(contentId);
	                try {
	                    imagePart.attachFile(imageFilePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	               multipart.addBodyPart(imagePart);
	                
	            }
	        }
			message.setContent(multipart);
							
			// Send message
			Transport.send(message);
						
			//System.out.println("mail is send");
			val=true;
			
		}
		catch(Exception ex)
		{
			val=false;
			System.out.println("mail not send");
			ex.printStackTrace();	
		}
		return val;
	}

	@Override
	public ArrayList<ENS_CalSpcGroupBean> getMail(UserBean users, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session = null;
		ArrayList<ENS_CalSpcGroupBean> lstspc = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_CalSpcGroupBean "
					+ " where PLT ="+users.getPlt()+" "
					+ "and SPC_DATE ='"+request.getAttribute("spc_date")+"' "
					+ "");
			lstspc = (ArrayList<ENS_CalSpcGroupBean>) query.list();
					
		}
		catch(Exception ex)
		{
			System.out.println("Calculate spc mail Method --> Exception" + ex.getMessage());
			ex.printStackTrace();
		}
		finally 
		{
			try
			{
				ENS_hibernateFactory.close(session);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		return lstspc;
	}

	@Override
	public List<String> getMailID(int plt, String sec, String application) {
		// TODO Auto-generated method stub
		
		Session session = null;
		List<String> lst = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			Query query = session.createQuery("select EMAIL from EMP_INFO where "
					+ "PLT ='"+plt+"'"
					+ "and SECT = '"+sec+"'"
					+ "and EMP_ID in( select EMP_ID from AuthorizatnBean where PLT = '"+plt+"'"
									+ "and APPL = '"+application+"')");
			lst = (List<String>) query.list();
			System.out.println("Size :"+lst.size());	
		}
		catch(Exception ex)
		{
			System.out.println("get Mail ID List --> Exception" + ex.getMessage());
			ex.printStackTrace();	
		}
		finally
		{
			try
			{
				ENS_hibernateFactory.close(session);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return lst;
	}

	@Override
	public ArrayList<ENS_CalAreaGroupBean> getArea(UserBean users, HttpServletRequest request) {

		Session session = null;
		ArrayList<ENS_CalAreaGroupBean> lstarea = null;
		
		try
		{
			session = ENS_hibernateFactory.openSession();
			int plt = users.getPlt();

			Query query = session.createQuery("from ENS_CalAreaGroupBean "
					+ " where PLT ="+users.getPlt()+" "
					+ "and AREA_DATE ='"+request.getAttribute("spc_date")+"' "
					+ "");
			lstarea = (ArrayList<ENS_CalAreaGroupBean>) query.list();
			
		}
		catch(Exception ex)
		{
			System.out.println("Calculate area mail Method --> Exception" + ex.getMessage());
			ex.printStackTrace();
		}
		finally 
		{
			try
			{
				ENS_hibernateFactory.close(session);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		return lstarea;
	}   
}
