package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddGroupBean;
import beans.ENS_AddSpcGroupBean;
import beans.UserBean;

public interface ENS_AddSpcGroupDao {
	
	String insData(HttpServletRequest request);
	
	ArrayList<ENS_AddSpcGroupBean> genereport (HttpServletRequest request, UserBean users);
	
	ENS_AddSpcGroupBean getSpcgroup(HttpServletRequest request);
	
	String editData(HttpServletRequest request);

}
