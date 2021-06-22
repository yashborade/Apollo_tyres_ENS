package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AreaTargetBean;
import beans.UserBean;

public interface ENS_AddAreaTargetDao {
	
	String insAreaTarget(HttpServletRequest request);
	
	ArrayList<ENS_AreaTargetBean> genereport (HttpServletRequest request, UserBean users);

}
