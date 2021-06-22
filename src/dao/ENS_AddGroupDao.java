package dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddGroupBean;
import beans.ENS_SummaryBean;
import beans.UserBean;

public interface ENS_AddGroupDao {
	
	String insData(HttpServletRequest request);
	
	String editData(HttpServletRequest request);
	
	ENS_AddGroupBean deletedata(HttpServletRequest request);
	
	ENS_AddGroupBean getEquip(HttpServletRequest request);
	
	ArrayList<ENS_AddGroupBean> genreport(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_AddGroupBean> getMixer (UserBean userBean, HttpServletRequest request);
	
	ArrayList<ENS_AddGroupBean> getMixer1(UserBean userBean, HttpServletRequest request);
	
	
	
	
	
}
