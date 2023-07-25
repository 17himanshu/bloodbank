package bloodissuedview;

import java.sql.Date;

public class IssuedBeans
{
	String nname;
	String mobile;
	String hospital;
	String reason;
	Date doi;
	String bgroup;
	
	public IssuedBeans() {}
	
	public IssuedBeans(String nname, String mobile, String hospital, String reason, Date doi, String bgroup) {
		super();
		this.nname = nname;
		this.mobile = mobile;
		this.hospital = hospital;
		this.reason = reason;
		this.doi = doi;
		this.bgroup = bgroup;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDoi() {
		return doi;
	}
	public void setDoi(Date doi) {
		this.doi = doi;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	
	
}
