package extend6;
/*
 * 	* 멤버십 프로그램을 구현하시오.
 * 		- 회사에서 고객 정보를 활용한 맞춤 서비스를 제공하고자 합니다.
 * 		- 일반 고객(Customer)과 우수고객(VIPCustomer)에 따른 서비스를 제공하고자 합니다.
 * 		- 물품을 구매할 때 적용되는 할인율과 적립되는 포인트의 비율이 다릅니다.
 * 		- 멤버십에 대한 구현을 클래스 상속을 활용하여 구현하시오.
 */
public class CustomerMain {

	public static void main(String[] args) {
		Customer cus1 = new Customer("이순신");
		VIPCustomer cus2 = new VIPCustomer("신사임당");
		
		cus1.showInfo();
		cus2.showInfo();
	}
}
