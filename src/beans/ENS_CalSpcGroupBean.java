package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ENS_CalSpcGroupBean implements Serializable{

	private int SRNO;
	private int PLT;
	private String SEC;
	private Date SPC_DATE;
	private String AREA_NAME; 
	private double TOTAL_TON;
	private double TOTAL_SPC;
	private String FLAG;
	private Date UPD_ON;
	private int UPD_BY;
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
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
	public Date getSPC_DATE() {
		return SPC_DATE;
	}
	public void setSPC_DATE(Date sPC_DATE) {
		SPC_DATE = sPC_DATE;
	}
	public double getTOTAL_TON() {
		return TOTAL_TON;
	}
	public void setTOTAL_TON(double tOTAL_TON) {
		TOTAL_TON = tOTAL_TON;
	}
	public double getTOTAL_SPC() {
		return TOTAL_SPC;
	}
	public void setTOTAL_SPC(double tOTAL_SPC) {
		TOTAL_SPC = tOTAL_SPC;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
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
