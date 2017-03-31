package android.hcl.bean;

public class Dish {
	private int dishId;//��Ʒid
	private Business business;//�̼�id
	private String dishtype;//��Ʒ����
	private float price;//��Ʒ�۸�
	private String dishName;//��Ʒ��
	private String dishMessage;//��Ʒ��Ϣ

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
