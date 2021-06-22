package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ENS_AddProductBean implements Serializable{

	private int SRNO;
	private int PLT;
	private String SEC;
	private Date BTCH_DATE;
	private String EQUIP_NAM;
	private double EQUIP_TOTAL;
	private double TOT_BTCH;
	private double BIAS_BTCH;
	private double RADIAL_BTCH;
	private double BIAS_CONSUP;
	private double RADIAL_CONSUP;
	private double TOTAL;
	private String FLAG;
	private Date UPD_ON;
	private int UPD_BY;
	
	public double getEQUIP_TOTAL() {
		return EQUIP_TOTAL;
	}
	public void setEQUIP_TOTAL(double eQUIP_TOTAL) {
		EQUIP_TOTAL = eQUIP_TOTAL;
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
	public Date getBTCH_DATE() {
		return BTCH_DATE;
	}
	public void setBTCH_DATE(Date bTCH_DATE) {
		BTCH_DATE = bTCH_DATE;
	}
	public String getEQUIP_NAM() {
		return EQUIP_NAM;
	}
	public void setEQUIP_NAM(String eQUIP_NAM) {
		EQUIP_NAM = eQUIP_NAM;
	}
	public double getTOT_BTCH() {
		return TOT_BTCH;
	}
	public void setTOT_BTCH(double tOT_BTCH) {
		TOT_BTCH = tOT_BTCH;
	}
	public double getBIAS_BTCH() {
		return BIAS_BTCH;
	}
	public void setBIAS_BTCH(double bIAS_BTCH) {
		BIAS_BTCH = bIAS_BTCH;
	}
	public double getRADIAL_BTCH() {
		return RADIAL_BTCH;
	}
	public void setRADIAL_BTCH(double rADIAL_BTCH) {
		RADIAL_BTCH = rADIAL_BTCH;
	}
	public double getBIAS_CONSUP() {
		return BIAS_CONSUP;
	}
	public void setBIAS_CONSUP(double bIAS_CONSUP) {
		BIAS_CONSUP = bIAS_CONSUP;
	}
	public double getRADIAL_CONSUP() {
		return RADIAL_CONSUP;
	}
	public void setRADIAL_CONSUP(double rADIAL_CONSUP) {
		RADIAL_CONSUP = rADIAL_CONSUP;
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
