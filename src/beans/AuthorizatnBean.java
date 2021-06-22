package beans;

import java.io.Serializable;

public class AuthorizatnBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int PLT;
	private int EMP_ID;
	private String APPL;
	private String UTYPE;
	private String EMAIL;
	
	
	public int getPLT() {
		return PLT;
	}
	public void setPLT(int pLT) {
		PLT = pLT;
	}
	public int getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public String getAPPL() {
		return APPL;
	}
	public void setAPPL(String aPPL) {
		APPL = aPPL;
	}
	public String getUTYPE() {
		return UTYPE;
	}
	public void setUTYPE(String uTYPE) {
		UTYPE = uTYPE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
}
