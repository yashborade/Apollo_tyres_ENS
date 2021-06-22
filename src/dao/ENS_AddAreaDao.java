package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddAreaBean;
import beans.UserBean;

public interface ENS_AddAreaDao {
	
	String insArea(HttpServletRequest request);
	
	ArrayList<ENS_AddAreaBean> genreport(UserBean users, HttpServletRequest request);
	
	
	
}
