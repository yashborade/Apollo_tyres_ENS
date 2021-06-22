package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddSpcBean;
import beans.UserBean;

public interface ENS_AddSpcDao {
	
	String insSPC(HttpServletRequest request);
	
	ArrayList<ENS_AddSpcBean> genreport(UserBean users, HttpServletRequest request);

}
