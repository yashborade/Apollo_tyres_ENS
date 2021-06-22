package beans;

import java.io.Serializable;
import java.util.Date;

public class ENS_AddGroupBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private String EQUIP_NAM;
	private String FIDD_NAM_PLUS;
	private String FIDD_NAM_MINUS;
	private String NAM_PLUS;
	private String NAM_MINUS;
	private String FLAG;
	private Date UPD_ON;
	private int UPD_BY;
	
	public String getNAM_PLUS() {
		return NAM_PLUS;
	}
	public void setNAM_PLUS(String nAM_PLUS) {
		NAM_PLUS = nAM_PLUS;
	}
	
	public String getNAM_MINUS() {
		return NAM_MINUS;
	}
	
	public void setNAM_MINUS(String nAM_MINUS) {
		NAM_MINUS = nAM_MINUS;
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
	
	public String getEQUIP_NAM() {
		return EQUIP_NAM;
	}
	public void setEQUIP_NAM(String eQUIP_NAM) {
		EQUIP_NAM = eQUIP_NAM;
	}
	
	public String getFIDD_NAM_PLUS() {
		return FIDD_NAM_PLUS;
	}
	public void setFIDD_NAM_PLUS(String fIDD_NAM_PLUS) {
		FIDD_NAM_PLUS = fIDD_NAM_PLUS;
	}
	
	public String getFIDD_NAM_MINUS() {
		return FIDD_NAM_MINUS;
	}
	public void setFIDD_NAM_MINUS(String fIDD_NAM_MINUS) {
		FIDD_NAM_MINUS = fIDD_NAM_MINUS;
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
