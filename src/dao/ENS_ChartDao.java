package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AreaTargetBean;
import beans.ENS_CalAreaGroupBean;
import beans.ENS_CalSpcGroupBean;
import beans.ENS_CalculateGroupBean;
import beans.ENS_SpcTargetBean;
import beans.UserBean;

public interface ENS_ChartDao {
	
	ArrayList<ENS_CalSpcGroupBean> genereport(HttpServletRequest request, UserBean users);
	
	ArrayList<ENS_CalculateGroupBean> getEquip (HttpServletRequest request, UserBean users);
	
	ArrayList<ENS_AreaTargetBean> gettarget(HttpServletRequest request, UserBean users);
	
	ArrayList<ENS_SpcTargetBean> getspctarget(HttpServletRequest request, UserBean users);
	
	ArrayList<ENS_CalAreaGroupBean> getArea(HttpServletRequest request, UserBean users);

}
