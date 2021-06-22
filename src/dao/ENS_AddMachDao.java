package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddMachBean;
import beans.UserBean;

public interface ENS_AddMachDao {
	
	String insMachine(HttpServletRequest request);

	ArrayList<ENS_AddMachBean> genreport(UserBean users, HttpServletRequest request);
	
	ArrayList<ENS_AddMachBean> getMach(UserBean users, HttpServletRequest request);

	

}
