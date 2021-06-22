package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddProductBean;
import beans.UserBean;

public interface ENS_AddProductDao {
	
	String insProduct(HttpServletRequest request);
	
	ArrayList<ENS_AddProductBean> genreport(UserBean users, HttpServletRequest request);

}
