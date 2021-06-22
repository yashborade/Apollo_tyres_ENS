package beans;

import java.io.Serializable;
import java.util.Date;

public class ENS_AreaGroupBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private String AREA_NAME;
	private String EQUIP_NAME;
	private String EQUIP_NAME_COR;
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
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public String getEQUIP_NAME() {
		return EQUIP_NAME;
	}
	public void setEQUIP_NAME(String eQUIP_NAME) {
		EQUIP_NAME = eQUIP_NAME;
	}
	public String getEQUIP_NAME_COR() {
		return EQUIP_NAME_COR;
	}
	public void setEQUIP_NAME_COR(String eQUIP_NAME_COR) {
		EQUIP_NAME_COR = eQUIP_NAME_COR;
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
