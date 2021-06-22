package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_UploadBean;
import beans.UserBean;

public interface ENS_UploadDao {
	
	//String getDetailsxls(HttpServletRequest request);

	String getDetailsxlsx(HttpServletRequest request, UserBean users);
	
	ArrayList<ENS_UploadBean> getNode(UserBean users, HttpServletRequest request);
	
}
