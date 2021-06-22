package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_CalSpcGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SpcTargetBean;
import beans.UserBean;

public interface ENS_CalSpcGroupDao {
	
	String insData(HttpServletRequest request);
	
	ArrayList<ENS_CalSpcGroupBean> genreport(UserBean users, HttpServletRequest request);
	
	String[][] genereportMaster(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_SpcTargetBean> getMonth(UserBean users, HttpServletRequest request);

}
