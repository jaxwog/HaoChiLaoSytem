package android.hcl.bean;

public class Customer {
	private int userid; // �û�id
	private String username;// �û���
	private String password;// ����
	private String userEmail;//�Ñ�����
	private String userPhone;// �绰
	private String userAddress;// �û���ַ

	public Customer() {

	}

	public Customer(String username, String password, String userEmail,String userPhone,
			String userAddress) {
		this.password = password;
		this.username = username;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
