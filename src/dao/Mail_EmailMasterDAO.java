package dao;

import javax.servlet.http.HttpServletRequest;

import beans.UserBean;

public interface Mail_EmailMasterDAO {
	
	String getDetailsxlsx(HttpServletRequest request, UserBean users);

}
