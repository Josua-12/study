package interface5;

public interface Seller {
   void sell();
   
   default void order() {
	   System.out.println("구매 주문");
   }
}