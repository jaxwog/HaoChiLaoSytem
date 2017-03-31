package android.hcl.bean;

public class Dish {
	private int dishId;//菜品id
	private Business business;//商家id
	private String dishtype;//菜品类型
	private float price;//菜品价格
	private String dishName;//菜品名
	private String dishMessage;//菜品信息

	public Dish() {

	}

	public Dish(Business business, String dishtype,
			float price, String dishName, String dishMessage) {
		this.business = business;
		this.dishtype = dishtype;
		this.price = price;
		this.dishName = dishName;
		this.dishMessage = dishMessage;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getDishtype() {
		return dishtype;
	}

	public void setDishtype(String dishtype) {
		this.dishtype = dishtype;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishMessage() {
		return dishMessage;
	}

	public void setDishMessage(String dishMessage) {
		this.dishMessage = dishMessage;
	}

}
