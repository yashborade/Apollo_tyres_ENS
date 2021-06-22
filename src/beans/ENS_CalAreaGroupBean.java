package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ENS_CalAreaGroupBean implements Serializable {
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private Date AREA_DATE;
	private String AREA_NAM;
	private double TOTAL;
	private String FLAG;
	private Date UPD_ON;
	private int UPD_BY;
	
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
	
	public Date getAREA_DATE() {
		return AREA_DATE;
	}
	public void setAREA_DATE(Date aREA_DATE) {
		AREA_DATE = aREA_DATE;
	}
	
	public String getAREA_NAM() {
		return AREA_NAM;
	}
	public void setAREA_NAM(String aREA_NAM) {
		AREA_NAM = aREA_NAM;
	}
	
	public double getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(double tOTAL) {
		TOTAL = tOTAL;
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
