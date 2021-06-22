package beans;

import java.io.Serializable;
import java.util.Date;

public class Mail_EmailMasterBean implements Serializable{
	
	private int PLT;
	private String EMAIL;
	private String EMAIL_FOR;
	private String DEPT_NAME;
	private String VEN_CODE;
	private String VEN_ID;
	private String VEN_NM;
	private String FLG;
	private Date UP_BY;
	private String UP_WHO;
	private String USER_DISP;
	private String FILE_NAME;
	public int getPLT() {
		return PLT;
	}
	public void setPLT(int pLT) {
		PLT = pLT;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getEMAIL_FOR() {
		return EMAIL_FOR;
	}
	public void setEMAIL_FOR(String eMAIL_FOR) {
		EMAIL_FOR = eMAIL_FOR;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public String getVEN_CODE() {
		return VEN_CODE;
	}
	public void setVEN_CODE(String vEN_CODE) {
		VEN_CODE = vEN_CODE;
	}
	public String getVEN_ID() {
		return VEN_ID;
	}
	public void setVEN_ID(String vEN_ID) {
		VEN_ID = vEN_ID;
	}
	public String getVEN_NM() {
		return VEN_NM;
	}
	public void setVEN_NM(String vEN_NM) {
		VEN_NM = vEN_NM;
	}
	public String getFLG() {
		return FLG;
	}
	public void setFLG(String fLG) {
		FLG = fLG;
	}
	public Date getUP_BY() {
		return UP_BY;
	}
	public void setUP_BY(Date uP_BY) {
		UP_BY = uP_BY;
	}
	public String getUP_WHO() {
		return UP_WHO;
	}
	public void setUP_WHO(String uP_WHO) {
		UP_WHO = uP_WHO;
	}
	public String getUSER_DISP() {
		return USER_DISP;
	}
	public void setUSER_DISP(String uSER_DISP) {
		USER_DISP = uSER_DISP;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	
	

}
