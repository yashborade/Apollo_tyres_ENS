package dao;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddMachBean;
import beans.ENS_SummaryBean;
import beans.ENS_UploadBean;
import beans.UserBean;

public interface ENS_SummaryDao {
	
	
	String insSummary(UserBean users,String sec, String nName, String mName, double diff, String dt);
	
	ArrayList<ENS_SummaryBean> genreport(UserBean users, HttpServletRequest request);
	
	String[][] genreportMaster(UserBean users, HttpServletRequest request);

	ArrayList<ENS_SummaryBean> getNode(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_SummaryBean> getDiff(UserBean users, HttpServletRequest request);
	
	String insGroup(UserBean users, HttpServletRequest request);
	
	ENS_SummaryBean getReadings(UserBean users, HttpServletRequest request);
	
	String editData(HttpServletRequest request);
	
	
	
}
