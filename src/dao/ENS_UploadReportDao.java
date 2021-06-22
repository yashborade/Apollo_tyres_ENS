package dao;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import beans.UserBean;
import beans.ENS_UploadBean;

public interface ENS_UploadReportDao {

	
		ArrayList<ENS_UploadBean> genreport(UserBean users, HttpServletRequest request);

		ArrayList<ENS_UploadBean> getNode(UserBean users, HttpServletRequest request);
		
		ENS_UploadBean getReadings(UserBean users, String Node , String Mach , String dat);
		
		ENS_UploadBean getReadings(UserBean users, HttpServletRequest request);
		
		String editData(HttpServletRequest request);
		
		
}
