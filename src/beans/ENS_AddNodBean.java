package beans;

import java.io.Serializable;
import java.util.Date;

public class ENS_AddNodBean implements Serializable{
	
	private int SRNO;
	private int PLT;
	private String SEC;
	private String NOD_NAM;
	private String FLAG;
	private Date UPD_ON;
	private int UPD_BY;
	private String USERDISP;
	
	
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
	public String getNOD_NAM() {
		return NOD_NAM;
	}
	public void setNOD_NAM(String nOD_NAM) {
		NOD_NAM = nOD_NAM;
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
	public String getUSERDISP() {
		return USERDISP;
	}
	public void setUSERDISP(String uSERDISP) {
		USERDISP = uSERDISP;
	}
	
	

}
