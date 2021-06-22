package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddNodBean;
import beans.UserBean;

public interface ENS_AddNode {
	
	String insMachine(HttpServletRequest request);

	ArrayList<ENS_AddNodBean> genreport(UserBean users, HttpServletRequest request);

	ArrayList<ENS_AddNodBean> getNode(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_AddNodBean> gExcel (UserBean users, HttpServletRequest request);
	
	

}
