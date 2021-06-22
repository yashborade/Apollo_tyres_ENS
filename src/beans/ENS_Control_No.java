package beans;

import java.io.Serializable;

public class ENS_Control_No implements Serializable{
	
	private int PLT;
	private String FIN_YR;
	private String CTRLNO_DOCUMENT;  
	private int CTRLNO_NEXT_NO;
	private String CTRLNO_NEXT_NUM;
	
	
	
	public ENS_Control_No() {

		PLT = 0;
		FIN_YR = "";
		CTRLNO_DOCUMENT = "";
		CTRLNO_NEXT_NO = 0;
		CTRLNO_NEXT_NUM = "0";

	}
	
	public int getPLT() {
		return PLT;
	}
	
	public void setPLT(int pLT) {
		PLT = pLT;
	}
	
	public String getFIN_YR() {
		return FIN_YR;
	}
	
	public void setFIN_YR(String fIN_YR) {
		FIN_YR = fIN_YR;
	}
	
	public String getCTRLNO_DOCUMENT() {
		return CTRLNO_DOCUMENT;
	}
	
	public void setCTRLNO_DOCUMENT(String cTRLNO_DOCUMENT) {
		CTRLNO_DOCUMENT = cTRLNO_DOCUMENT;
	}
	
	public int getCTRLNO_NEXT_NO() {
		return CTRLNO_NEXT_NO;
	}
	
	public void setCTRLNO_NEXT_NO(int cTRLNO_NEXT_NO) {
		CTRLNO_NEXT_NO = cTRLNO_NEXT_NO;
	}
	
	public String getCTRLNO_NEXT_NUM() {
		return CTRLNO_NEXT_NUM;
	}
	
	public void setCTRLNO_NEXT_NUM(String cTRLNO_NEXT_NUM) {
		CTRLNO_NEXT_NUM = cTRLNO_NEXT_NUM;
	}
	

}
