package clazz.step3;

import java.util.Scanner;

public class ProductOrderMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("입력할 주문의 개수를 입력하세요: ");
        int n = scanner.nextInt();  //정수값 입력받음
        scanner.nextLine();  // nextInt() 후 버퍼에 남아있는 줄바꿈 문자(\n) 제거하기 위함
        
        //여러 상품의 주문 정보를 담는 배열 생성
        ProductOrder[] orders = new ProductOrder[n];
        for(int i = 0; i < orders.length; i++) {
        	System.out.println((i + 1) + "번째 주문 정보를 입력하세요.");
        	System.out.print("상품명 : ");
        	String productName = scanner.nextLine();
        	
        	System.out.print("가격 : ");
        	int price = scanner.nextInt();
        	
        	System.out.print("수량 : ");
        	int quantity = scanner.nextInt();
        	scanner.nextLine();
        	
        	orders[i] = createOrder(productName, price, quantity);
        }
    
    
		printOrder(orders);
		//getTotalAmount()를 사용해서 총 결제 금액 계산
		int totalAmount = getTotalAmount(orders);
		System.out.println("총 결제 금액 : " + totalAmount);

		scanner.close();
    }
    
	public static int getTotalAmount(ProductOrder[] orders) {
		int totalAmount = 0;
		for(ProductOrder order : orders) {
			totalAmount += order.price * order.quantity;
		}
		return totalAmount;
	}

	public static void printOrder(ProductOrder[] orders) {
		for(ProductOrder order : orders) {
			System.out.println("상품명: " + order.productName + ", 가격: " + order.price
								+ ", 수량: " + order.quantity);		
		}
		
	}

	public static ProductOrder createOrder(String productName, int price, int quantity) {
		ProductOrder order = new ProductOrder();
		order.productName = productName;
		order.price = price;
		order.quantity = quantity;
		
		return order;
	}
}
