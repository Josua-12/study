package interface5;

public class Customer implements Buyer, Seller {

	@Override
	public void sell() {
		System.out.println("판매하기");
	}

	@Override
	public void buy() {
		System.out.println("구매하기");
	}

	@Override
	public void order() {
		System.out.println("customer order");
	}

	
}
