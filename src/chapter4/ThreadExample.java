package chapter4;

import java.util.concurrent.TimeUnit;

public class ThreadExample implements Runnable {

	private String greeting;	// ��ӡ������̨����Ϣ
	
	public ThreadExample(String greeting) {
		this.greeting = greeting;
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " : " + greeting);
			try {
				// ˯�� 0 �� 100 ����
				TimeUnit.MICROSECONDS.sleep(((long) Math.random() * 1000));
			} catch (InterruptedException e) {
				// ��Ӧ��ִ��
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new ThreadExample("Hello")).start();
		new Thread(new ThreadExample("Aloha")).start();
		new Thread(new ThreadExample("Ciao")).start();
	}
}
