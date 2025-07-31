package thread;

// 0부터 200까지 출력

class MyThread extends Thread{
	
	@Override
	public void run() {
		
		int i;
		for(i = 0; i <= 200; i++) {
			System.out.print(Thread.currentThread().getName() + ":" + i + "\t");
		}
	}
}

public class ThreadTest {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread());
		
		MyThread thread1 = new MyThread();		// 첫번째 스레드 생성
		thread1.start();		// start() 호출하면 Thread 클래스의 run()이 수행됨
		
		MyThread thread2 = new MyThread();  	// 두번째 스레드 생성
		thread2.start();
		
		System.out.println("end");	// 스레드가 끝나면 end 출력됨
	}
}
