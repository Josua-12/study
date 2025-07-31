package thread;

public class InterruptTest extends Thread {

	@Override
	public void run() {
		int i;
		for(i = 0; i < 10; i++) {
			try {
				// 3초동안 잠들었다가 정수 한 개 출력
				Thread.sleep(3000);
				System.out.print(i + "\t");
			} catch (InterruptedException e) {
				//e.printStackTrace();
				System.out.println("깨어남");
			}
		}
		System.out.println("end");
	}
	
	public static void main(String[] args) throws InterruptedException {
		InterruptTest thread = new InterruptTest();
		thread.start();
		//스레드를 시작하고 3초 동안 일시정지
		Thread.sleep(3000);
		thread.interrupt();
		
	}
}
