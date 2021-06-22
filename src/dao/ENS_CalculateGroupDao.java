package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SummaryBean;
import beans.UserBean;

public interface ENS_CalculateGroupDao {
	
	ArrayList<ENS_CalculateGroupBean> genreport(UserBean users, HttpServletRequest request);
	
	String[][] genereportMaster(UserBean users, HttpServletRequest request);
	
	ENS_CalculateGroupBean getReadings(HttpServletRequest request);
	
	String insAreagroup(UserBean users, HttpServletRequest request);
	
}
