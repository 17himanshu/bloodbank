package bloodcollectiontblview;

import java.sql.Date;

public class DonationsBean 
{
	String mobile;
	String bgroup;
	Date dateofdonation;
	
	public DonationsBean() {}
	
	public DonationsBean(String mobile, String bgroup, Date dateofdonation)
	{
		super();
		this.mobile = mobile;
		this.bgroup = bgroup;
		this.dateofdonation = dateofdonation;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public Date getDateofdonation() {
		return dateofdonation;
	}
	public void setDateofdonation(Date dateofdonation) {
		this.dateofdonation = dateofdonation;
	}
}
