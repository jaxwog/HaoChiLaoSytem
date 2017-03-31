package android.hcl.bean;

public class Business {
	private int busunessid; // 商家id
	private String bususername;// 商家用户名
	private String buspassword;// 商家密码
	private String busunessname;// 商家名
	private String busunessPhone;// 电话
	private String busunessAddress;// 用户地址

	public Business() {

	}

	public Business(String bususername, String buspassword,
			String busunessname, String busunessPhone, String busunessAddress) {
		this.bususername = bususername;
		this.busunessname = busunessname;
		this.buspassword = buspassword;
		this.busunessPhone = busunessPhone;
		this.busunessAddress = busunessAddress;
	}

	public int getBusunessid() {
		return busunessid;
	}

	public void setBusunessid(int busunessid) {
		this.busunessid = busunessid;
	}

	public String getBusunessname() {
		return busunessname;
	}

	public void setBusunessname(String busunessname) {
		this.busunessname = busunessname;
	}

	public String getBuspassword() {
		return buspassword;
	}

	public void setBuspassword(String buspassword) {
		this.buspassword = buspassword;
	}

	public String getBusunessPhone() {
		return busunessPhone;
	}

	public void setBusunessPhone(String busunessPhone) {
		this.busunessPhone = busunessPhone;
	}

	public String getBusunessAddress() {
		return busunessAddress;
	}

	public void setBusunessAddress(String busunessAddress) {
		this.busunessAddress = busunessAddress;
	}

	public String getBususername() {
		return bususername;
	}

	public void setBususername(String bususername) {
		this.bususername = bususername;
	}

}
