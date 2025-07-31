package thread2;

class Bank {

	private int money = 10000;
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public synchronized void saveMoney(int save) {
		int money = this.getMoney();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setMoney(money + save);
	}
	
	public synchronized void minusMoney(int minus) {
		int money = this.getMoney();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setMoney(money - minus); 
	}
}

class Lee extends Thread {
	@Override
	public void run() {
		System.out.println("예금 작업을 시작합니다.");
		SyncTest.myBank.saveMoney(3000);
		System.out.println("saveMoney(3000) 작업이 진행되어 잔액이 " + SyncTest.myBank.getMoney() +"원 입니다.");
	}
}

class Shin extends Thread {
	
	@Override
	public void run() {
		System.out.println("출금 작업을 시작합니다.");
		SyncTest.myBank.minusMoney(1000);
		System.out.println("minusMoney(1000) 작업이 진행되어 잔액이 " + SyncTest.myBank.getMoney() + "원 입니다.");
	}
}

public class SyncTest {
	public static Bank myBank = new Bank();
	
	public static void main(String[] args) throws InterruptedException {
		Lee lee = new Lee();
		lee.start();
		
		Thread.sleep(200);
		
		Shin shin = new Shin();
		shin.start();
	}
}
