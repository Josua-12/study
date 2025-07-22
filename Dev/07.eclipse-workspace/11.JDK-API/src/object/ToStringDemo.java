package object;

class Book {
   int bookNumber;
   String bookTitle;
   public Book(int bookNumber, String bookTitle) {
      //super();
      this.bookNumber = bookNumber;
      this.bookTitle = bookTitle;
   }
   
   // 메서드 재정의
   @Override
   public String toString() {
	
	return bookNumber + ", " + bookTitle;
   }
   
   
}
public class ToStringDemo {
   public static void main(String[] args) {
      Book book = new Book(2025, "LLM의 전성기");
      
      System.out.println(book);
      System.out.println(book.toString());
      
      String str = new String("TEST");
      System.out.println(str);
      System.out.println(str.toString());
      
      Integer integer = new Integer(100); 
      System.out.println(integer);
      System.out.println(integer.toString());
   }
}
