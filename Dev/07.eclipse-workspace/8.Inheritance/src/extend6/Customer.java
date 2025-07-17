package extend6;

/*
 * 속성 : 고객 아이디, 고객 이름, 고객 등급, 포인트, 포인트 적립비율
 * 기능 : showInfo(),
 * 		 일반 고객은 물품 구매 시 1% 포인트 적립 
 */
public class Customer {
	protected String customerName;
	protected int customerID;
	protected String customerGrade;
	protected int bonusPoint;
	protected double bonusRatio;
	
	
	
	public Customer() {
		this.customerGrade = "SILVER";
		this.bonusRatio = 0.01;
		this.bonusPoint = 1000;
	}
	
	public int calcPrice(int price) {
		bonusPoint += (int)(price * bonusRatio);
		return price;
	}
	
	public String showCustomerInfo() {
		return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 "
				+ bonusPoint + "입니다.";
	}
	
	public String getCustomerName() {
	    return customerName;
	}

	public void setCustomerName(String customerName) {
	    this.customerName = customerName;
	}

	public int getCustomerID() {
	    return customerID;
	}
	
	public void setCustomerID(int customerID) {
	    this.customerID = customerID;
	}

	public String getCustomerGrade() {
	    return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
	    this.customerGrade = customerGrade;
	}

	public int getBonusPoint() {
	    return bonusPoint;
	}
	
	public void setBonusPoint(int bonusPoint) {
	    this.bonusPoint = bonusPoint;
	}

	public double getBonusRatio() {
	    return bonusRatio;
	}

	public void setBonusRatio(double bonusRatio) {
	    this.bonusRatio = bonusRatio;
	}
}




