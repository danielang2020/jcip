package net.jcip.my;

import java.util.concurrent.TimeUnit;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-28 09:25
 **/
public class SyncBlock {
	public static synchronized void show()  {
		System.out.println("show start " + Thread.currentThread().getName());
		try {
			TimeUnit.HOURS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("show exception " + Thread.currentThread().getName());
		}
		System.out.println("show end " + Thread.currentThread().getName());
	}

	public static void main(String[] args) throws Exception{
		Thread thread = new Thread(SyncBlock::show);
		thread.start();

		Thread thread1 = new Thread(SyncBlock::show);
		thread1.start();
		TimeUnit.SECONDS.sleep(3);
		thread1.interrupt();
		System.out.println("interrupt " + thread1.getName() );
		TimeUnit.SECONDS.sleep(3);
		thread.interrupt();
		System.out.println("interrupt " + thread.getName() );
	}
}
