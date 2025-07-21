package smartcar;

public abstract class Car {

	protected String name;

	public Car(String name) {
		super();
		this.name = name;
	}
	
	public abstract void startEngine();
	
}
