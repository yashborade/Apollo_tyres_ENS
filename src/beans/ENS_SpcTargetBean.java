package beans;

import java.io.Serializable;
import java.util.Date;

public class ENS_SpcTargetBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private String AREA_NAM;
	private double SPC_TARGET;
	private String MONTH;
	private String FLG;
	private Date UPD_ON;
	private int UPD_BY;
	private String FINYR;

	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getFINYR() {
		return FINYR;
	}
	public void setFINYR(String fINYR) {
		FINYR = fINYR;
	}
	public int getSRNO() {
		return SRNO;
	}
	public void setSRNO(int sRNO) {
		SRNO = sRNO;
	}
	public int getPLT() {
		return PLT;
	}
	public void setPLT(int pLT) {
		PLT = pLT;
	}
	public String getSEC() {
		return SEC;
	}
	public void setSEC(String sEC) {
		SEC = sEC;
	}
	public String getAREA_NAM() {
		return AREA_NAM;
	}
	public void setAREA_NAM(String aREA_NAM) {
		AREA_NAM = aREA_NAM;
	}
	public double getSPC_TARGET() {
		return SPC_TARGET;
	}
	public void setSPC_TARGET(double sPC_TARGET) {
		SPC_TARGET = sPC_TARGET;
	}
	public String getFLG() {
		return FLG;
	}
	public void setFLG(String fLG) {
		FLG = fLG;
	}
	public Date getUPD_ON() {
		return UPD_ON;
	}
	public void setUPD_ON(Date uPD_ON) {
		UPD_ON = uPD_ON;
	}
	public int getUPD_BY() {
		return UPD_BY;
	}
	public void setUPD_BY(int uPD_BY) {
		UPD_BY = uPD_BY;
	}
}
