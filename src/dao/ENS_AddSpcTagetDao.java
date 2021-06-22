package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_SpcTargetBean;
import beans.UserBean;

public interface ENS_AddSpcTagetDao {
	
	String insSpcTraget(HttpServletRequest request);
	
	ArrayList<ENS_SpcTargetBean> genereport (HttpServletRequest request, UserBean users);

}
