package beans;

import java.io.Serializable;
import java.util.Date;

public class ENS_AddSpcGroupBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private String SPC_GRP;
	private String SPC_GRP_CR;
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
	public String getSPC_GRP() {
		return SPC_GRP;
	}
	public void setSPC_GRP(String sPC_GRP) {
		SPC_GRP = sPC_GRP;
	}
	public String getSPC_GRP_CR() {
		return SPC_GRP_CR;
	}
	public void setSPC_GRP_CR(String sPC_GRP_CR) {
		SPC_GRP_CR = sPC_GRP_CR;
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
