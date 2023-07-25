package exploredonors;

public class DonorsBean 
{
	public String mobile;
	public String name;
	public String gender;
	public String address;
	public String bgroup;
	public String age;
	
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public DonorsBean() {}
	public DonorsBean(String mobile, String name, String gender, String address, String bgroup, String age)
	{
		super();
		this.mobile = mobile;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.bgroup = bgroup;
		this.age = age;
	}
	
	
	
}
