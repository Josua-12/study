package thread;

public class SleepTest extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.print(i + "\t");
			
		}
	}
	public static void main(String[] args) {
		SleepTest thread = new SleepTest();
		thread.start();
	}
}
