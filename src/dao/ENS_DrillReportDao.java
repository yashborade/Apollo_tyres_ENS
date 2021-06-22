package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddAreaBean;
import beans.ENS_AddGroupBean;
import beans.ENS_SummaryBean;
import beans.UserBean;

public interface ENS_DrillReportDao {
	
	ArrayList<ENS_AddGroupBean> genereport (HttpServletRequest request, UserBean users);
	
	String[][] genreportMaster(UserBean users, HttpServletRequest request);
	
	String[][] genreportArea(UserBean users, HttpServletRequest request);
	
	String[][] genreportSpc(UserBean users, HttpServletRequest request);


}
