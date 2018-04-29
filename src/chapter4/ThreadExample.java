package chapter4;

import java.util.concurrent.TimeUnit;

public class ThreadExample implements Runnable {

	private String greeting;	// 打印到控制台的信息
	
	public ThreadExample(String greeting) {
		this.greeting = greeting;
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " : " + greeting);
			try {
				// 睡眠 0 到 100 毫秒
				TimeUnit.MICROSECONDS.sleep(((long) Math.random() * 1000));
			} catch (InterruptedException e) {
				// 不应该执行
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new ThreadExample("Hello")).start();
		new Thread(new ThreadExample("Aloha")).start();
		new Thread(new ThreadExample("Ciao")).start();
	}
}
