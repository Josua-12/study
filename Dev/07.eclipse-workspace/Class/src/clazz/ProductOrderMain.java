package clazz;

public class ProductOrderMain {

	public static void main(String[] args) {
		ProductOrder[] Products = new ProductOrder[3];
				
		ProductOrder Tofu = new ProductOrder();
		Tofu.name = "두부";
		Tofu.price = 2000;
		Tofu.count = 2;
		Products[0] = Tofu;
		
		ProductOrder Kimchi = new ProductOrder();
		Kimchi.name = "김치";
		Kimchi.price = 5000;
		Kimchi.count = 1;
		Products[1] = Kimchi;
		
		ProductOrder Cola = new ProductOrder();
		Cola.name = "콜라";
		Cola.price = 1500;
		Cola.count = 2;
		Products[2] = Cola;
		
		int totalPrice = (Tofu.price*Tofu.count) +
				 (Kimchi.price*Kimchi.count) +
				 (Cola.price*Cola.count);
		
		for(ProductOrder Product : Products) {
			System.out.println("상품명: " + Product.name +
							   ", 가격: " + Product.price + "원"+
							   ", 수량: " + Product.count+ "개");
		}
		System.out.println("총 결제 금액: " + totalPrice + "원");
	}
}
