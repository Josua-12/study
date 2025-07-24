package generics3;

public class Pointer<T,V> {
   T x;      // 참조변수 x와 y
   V y;
   
   public Pointer(T x, V y) {
      //super();
      this.x = x;
      this.y = y;
   }

   public T getX() {
      return x;
   }

   public V getY() {
      return y;
   }
   
   
   
}


// 