package beans;


import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


public class UserBean implements java.io.Serializable {
 
  private static final long serialVersionUID = 1L;
  
  private String emp_name1;
  
  private String pwd;
  
  private int uid;
  
  // private String admin;
  private int plt;
  
  private String fin_yr;
  
  private String sysdate;
  
  private String pltname;
  
  private String type;
  
  private String inch = "";
  
  private String patrn = "";
  
  private String cat = "";
  
  private String sec = "";
  
  private String email = "";
  
  private String cust_auth="";
  
  private String selectCust="";
  private String selCustNam="";
  
  
  
  public static String getDate(Date sdate)
 {
  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
  String dt = dateFormat.format(sdate);
  return dt;
 }

  
  
  public String getSelCustNam() {
    return selCustNam;
  }
  
  public void setSelCustNam(String selCustNam) {
    this.selCustNam = selCustNam;
  }
  
  
  public String getSelectCust() {
    return selectCust;
  }
  
  
  public void setSelectCust(String selectCust) {
    this.selectCust = selectCust;
  }
  
  
  public String getCust_auth() {
    return cust_auth;
  }
  
  
  public void setCust_auth(String custAuth) {
    cust_auth = custAuth;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String pword) {
    email = pword;
  }
  
  public void setSec(String p) {
    sec = p.trim();
  }
  
  public String getSec() {
    return sec.trim();
  }
  
  public void settype(String p) {
    type = p;
  }
  
  public String gettype() {
    return type;
  }
  
  public void setsysdate(String d) {
    sysdate = d;
  }
  
  public String getsysdate() {
    return sysdate;
  }
  
  public String getUsername() {
    return emp_name1;
  }
  
  public int getUid() {
    return uid;
  }
  
  public String getPassword() {
    return pwd;
  }
  
  public int getPlt() {
     return plt;
  }
  
  public void setUsername(String uname) {
    emp_name1 = uname;
  }
  
  public void setFinyear(String fy) {
    fin_yr = fy;
  }
  
  public String getFinyear() {
    return fin_yr;
  }
  
  public void setUid(int id) {
    uid = id;
  }
  
  public void setPlantName(String name) {
    pltname = name;
  }
  
  public String getPlantName() {
    return pltname;
  }
  
  public void setPassword(String pword) {
    pwd = pword;
  }
  
  public void setPlt(int pltcd) {
    plt = pltcd;
  }
  
  public void setinch(String pinch) {
    inch = pinch;
  }
  
  public String getinch() {
    
    return inch;
  }
  
  public void setpatrn(String ppatrn) {
    patrn = ppatrn;
  }
  
  public String getpatrn() {
    // System.out.println("patrn :"+patrn);
    return patrn;
  }
  
  public void setcat(String pcat) {
    cat = pcat;
  }
  
  public String getcat() {
    // System.out.println("cat :"+cat);
    return cat;
  }

  
}