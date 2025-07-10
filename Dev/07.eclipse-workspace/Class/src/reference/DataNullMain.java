package reference;

public class DataNullMain {
	public static void main(String[] args) {
		Data data = null;
		System.out.println("data = " + data);
		
		data = new Data();
		System.out.println("2.data = " + data);
		

		data = null;
		System.out.println("3.data = "+ data);
	}
}

