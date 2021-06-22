package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ENS_CalculateGroupBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private Date GRP_DATE;
	private String EQUIP_NAM;
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
	public Date getGRP_DATE() {
		return GRP_DATE;
	}
	public void setGRP_DATE(Date gRP_DATE) {
		GRP_DATE = gRP_DATE;
	}
	public String getEQUIP_NAM() {
		return EQUIP_NAM;
	}
	public void setEQUIP_NAM(String eQUIP_NAM) {
		EQUIP_NAM = eQUIP_NAM;
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
