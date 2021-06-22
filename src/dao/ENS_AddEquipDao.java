package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ENS_AddEquipBean;
import beans.ENS_AddMachBean;
import beans.UserBean;

public interface ENS_AddEquipDao {
	
	String insEquip(HttpServletRequest request);
	
	ArrayList<ENS_AddEquipBean> genreport(UserBean users, HttpServletRequest request);

}
