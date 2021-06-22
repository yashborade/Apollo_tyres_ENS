package dao;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import beans.ENS_AddGroupBean;
import beans.ENS_AddMachBean;
import beans.UserBean;

public interface ENS_AutoCompleteDao {
	
	List<String> getNode(HttpServletRequest request, UserBean users);
	
	List<String> getEquip(HttpServletRequest request, UserBean users);
	
	List<String> getMach(HttpServletRequest request, UserBean users);
	
	List<String> getS_Node(HttpServletRequest request, UserBean users);
	
	List<String> getGroup(HttpServletRequest request, UserBean users);
	
	List<String> getGroup_mach(HttpServletRequest request, UserBean users);
	
	List<String> getEquip1(HttpServletRequest request, UserBean users);
	
	List<String> getArea(HttpServletRequest request, UserBean users);
	
	List<String> getArea1(HttpServletRequest request, UserBean users);
	
	List<String> getAreaEquip(HttpServletRequest request, UserBean users);
	
	List<String> getCalEquip(HttpServletRequest request, UserBean users);
	
	List<String> callNode(HttpServletRequest request, UserBean users);
	
	List<String> getm_node(HttpServletRequest request, UserBean users);
	
	List<String> getAreaTarget(HttpServletRequest request, UserBean users);
	
	List<String> getSpcTarget(HttpServletRequest request, UserBean users);

}
