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
		Customer c1 = new Customer();
        c1.setCustomerName("이순신");
        c1.setCustomerID(10010);

        VIPCustomer c2 = new VIPCustomer();
        c2.setCustomerName("신사임당");
        c2.setCustomerID(10020);
        c2.setAgentID(12345);

        // === 일반 고객 ===
        System.out.println("=== 일반 고객 정보 ===");
        System.out.println(c1.showCustomerInfo());
        System.out.println("상품 가격: 10000원");
        int c1Payment = c1.calcPrice(10000);
        System.out.println("지불 금액: " + c1Payment + "원");
        System.out.println("구매 후 정보: " + c1.showCustomerInfo());
        System.out.println();

        // === VIP 고객 ===
        System.out.println("=== VIP 고객 정보 ===");
        System.out.println(c2.showCustomerInfo());
        System.out.println("상품 가격: 10000원");
        int c2Payment = c2.calcPrice(10000);
        System.out.println("할인 적용 지불 금액: " + c2Payment + "원 (10% 할인)");
        System.out.println("구매 후 정보: " + c2.showCustomerInfo());
        System.out.println();

        // === 혜택 비교 ===
        System.out.println("=== 혜택 비교 ===");
        System.out.println("일반 고객 지불액: " + c1Payment + "원");
        System.out.println("VIP 고객 지불액: " + c2Payment + "원");
        System.out.println("VIP 할인 혜택: " + (10000 - c2Payment) + "원");
	}
}
