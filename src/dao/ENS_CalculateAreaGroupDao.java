package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.UserBean;

public interface ENS_CalculateAreaGroupDao  {
	
	String[][] genereportMaster(UserBean users, HttpServletRequest request);
	
	ENS_CalAreaGroupBean getTotal (HttpServletRequest request);
	
	ArrayList<ENS_CalAreaGroupBean> genreport(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_AreaTargetBean> getMonth(UserBean users, HttpServletRequest request);

}
