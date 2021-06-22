package beans;

import java.io.Serializable;

public class EMP_INFO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int EMP_ID;
	private int PLT;
	private String EMP_NAME;
	private String SECT;
	private String EMAIL;
	private String DEPTNAM;
	private String FGSDEVDEPT;
	
	
	public int getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public int getPLT() {
		return PLT;
	}
	public void setPLT(int pLT) {
		PLT = pLT;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public String getSECT() {
		return SECT;
	}
	public void setSECT(String sECT) {
		SECT = sECT;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getDEPTNAM() {
		return DEPTNAM;
	}
	public void setDEPTNAM(String dEPTNAM) {
		DEPTNAM = dEPTNAM;
	}
	public String getFGSDEVDEPT() {
		return FGSDEVDEPT;
	}
	public void setFGSDEVDEPT(String fGSDEVDEPT) {
		FGSDEVDEPT = fGSDEVDEPT;
	}
}
