package android.hcl.bean;

public class Business {
	private int busunessid; // �̼�id
	private String bususername;// �̼��û���
	private String buspassword;// �̼�����
	private String busunessname;// �̼���
	private String busunessPhone;// �绰
	private String busunessAddress;// �û���ַ

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
