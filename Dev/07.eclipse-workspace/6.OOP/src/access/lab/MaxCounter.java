package access.lab;

public class MaxCounter {
	private int count;
	private int max;


	public MaxCounter(int max) {
		this.max = max;
		this.count = 0;
	}

	private boolean canIncrement() {
		// TODO Auto-generated method stub
		return count < max;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	public void increment() {
		// TODO Auto-generated method stub
		if(canIncrement()) {
			count++;
		} else {
			System.out.println("최대값을 초과할 수 없습니다.");
		}
	}
	
}
