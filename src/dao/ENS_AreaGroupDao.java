package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddGroupBean;
import beans.ENS_AreaGroupBean;
import beans.UserBean;

public interface ENS_AreaGroupDao {
	
	String insData(HttpServletRequest request);
	
	ArrayList<ENS_AreaGroupBean> genereport (UserBean users, HttpServletRequest request);
	
	ENS_AreaGroupBean getArea(HttpServletRequest request);
	
	String editData(HttpServletRequest request);
	
	ENS_AreaGroupBean deletedata(HttpServletRequest request);

}
