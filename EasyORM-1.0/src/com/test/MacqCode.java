package com.test;

import com.org.annotations.Entity;

@Entity(tableName = "smp_macqcode")
public class MacqCode {
	
	private String CODE;
	private String LGNTYPE;
	private String STAT;
	private String MEMO1;
	private String UTIME;
	private String DESCRIPTION;
	
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getLGNTYPE() {
		return LGNTYPE;
	}
	public void setLGNTYPE(String lGNTYPE) {
		LGNTYPE = lGNTYPE;
	}
	public String getSTAT() {
		return STAT;
	}
	public void setSTAT(String sTAT) {
		STAT = sTAT;
	}
	public String getMEMO1() {
		return MEMO1;
	}
	public void setMEMO1(String mEMO1) {
		MEMO1 = mEMO1;
	}
	public String getUTIME() {
		return UTIME;
	}
	public void setUTIME(String uTIME) {
		UTIME = uTIME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
}
