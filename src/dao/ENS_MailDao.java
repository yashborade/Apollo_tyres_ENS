package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddMachBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.UserBean;

public interface ENS_MailDao {
	
	public boolean sendMail(String mailSubject, String mailText, List<String> mailTO, String mailFrom, Map<String, String> mapInlineImages);
	
	ArrayList<ENS_CalSpcGroupBean> getMail(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_CalAreaGroupBean> getArea(UserBean users, HttpServletRequest request);
	
	List<String> getMailID(int plt, String sec, String application);

}
