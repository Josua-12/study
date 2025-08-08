package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable {
	//버전 관리를 위한 정보
	private static final long serialVersionID = 1583932237292393L;
	String name;
	String job;
	
	public Person() {}
	
	public Person(String name, String job) {
		//super();
		this.name = name;
		this.job = job;
	}

	@Override
	public String toString() {
		return name +", "+ job;
	}
	
	
}

public class SerializationTest {
	
	public static void main(String[] args) {
		Person personLee = new Person("이순신", "대표이사");
		Person personShin = new Person("신사임당", "상무이사");
		
		try(FileOutputStream fos = new FileOutputStream("serial.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(personLee);		// personLee과 personShin의 값을 파일에 씀 
			oos.writeObject(personShin);    // (직렬화)
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		try(FileInputStream fis = new FileInputStream("serial.out");
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			Person p1 = (Person) ois.readObject(); //personLee과 personShin의 값을
			Person p2 = (Person) ois.readObject(); //파일에서 읽어 들임(역직렬화) 
			
			System.out.println(p1);
			System.out.println(p2);
			
		} catch (IOException | ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}









