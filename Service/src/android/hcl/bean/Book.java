package android.hcl.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Book {
	private int bookId;//订单id
	private String username;//用户名
	private String phone;//用户手机号
	private String address;//用户的地址
	private String busunessname;//商家名
	private String dishName;//菜名
	private int number;//所定餐品的份数
	private float price;//价格
	private float totalconsumption;//消费总金额
	private Timestamp bookTime;//订单提交时间
	private String bookFinish;//订单完成进度

	public Book() {

	}

	public Book(String username, String phone, String address,
			String busunessname, String dishName, int number, float price,
			float totalconsumption, Timestamp bookTime, String bookFinish) {
		this.username = username;
		this.phone = phone;
		this.address = address;
		this.busunessname = busunessname;
		this.dishName = dishName;
		this.number = number;
		this.price = price;
		this.totalconsumption = totalconsumption;
		this.bookTime = bookTime;
		this.bookFinish = bookFinish;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBusunessname() {
		return busunessname;
	}

	public void setBusunessname(String busunessname) {
		this.busunessname = busunessname;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public float getTotalconsumption() {
		return totalconsumption;
	}

	public void setTotalconsumption(float totalconsumption) {
		this.totalconsumption = totalconsumption;
	}

	public Timestamp getBookTime() {
		return bookTime;
	}

	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}

	public String getBookFinish() {
		return bookFinish;
	}

	public void setBookFinish(String bookFinish) {
		this.bookFinish = bookFinish;
	}

}
