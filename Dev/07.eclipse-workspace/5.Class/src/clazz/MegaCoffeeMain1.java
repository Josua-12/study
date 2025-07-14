package clazz;

public class MegaCoffeeMain1 {

	public static void main(String[] args) {
		MegaCoffeeItem[] famousemenus = new MegaCoffeeItem[3];
		System.out.println("=== ☕ 메가 커피 인기 메뉴 ===");
				
		MegaCoffeeItem Americano = new MegaCoffeeItem();
		Americano.menu = "아메리카노";
		Americano.price = 4500;
		Americano.category = "커피";
		Americano.temp = true;
		famousemenus[0] = Americano;
		
		MegaCoffeeItem Latte = new MegaCoffeeItem();
		Latte.menu = "카페라떼";
		Latte.price = 5500;
		Latte.category = "커피";
		Latte.temp = true;
		famousemenus[1] = Latte;
		
		MegaCoffeeItem Strawberry = new MegaCoffeeItem();
		Strawberry.menu = "딸기 에이드";
		Strawberry.price = 6000;
		Strawberry.category = "음료";
		Strawberry.temp = false;
		famousemenus[2] = Strawberry;
		
		for(MegaCoffeeItem famousemenu : famousemenus) {
			String tempText = famousemenu.temp ? "HOT 🔥" : "COLD ❄️";
			
			System.out.println("메뉴: " + famousemenu.menu 
							 + " | 가격: " + famousemenu.price
							 + " | 분류: " + famousemenu.category
							 + " | 온도: " + tempText);
		}	
	}
}
