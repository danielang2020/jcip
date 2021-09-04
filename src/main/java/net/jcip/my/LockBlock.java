package net.jcip.my;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-28 09:25
 **/
public class LockBlock {
	static Lock lock = new ReentrantLock();
	public static void show()  {
		lock.lock();
		System.out.println("show start " + Thread.currentThread().getName());
		try {
			TimeUnit.HOURS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("show exception " + Thread.currentThread().getName());
		}
		System.out.println("show end " + Thread.currentThread().getName());
		lock.unlock();
	}

	public static void main(String[] args) throws Exception{
		Thread thread = new Thread(LockBlock::show);
		thread.start();

		Thread thread1 = new Thread(LockBlock::show);
		thread1.start();
		TimeUnit.SECONDS.sleep(3);
		thread1.interrupt();
		System.out.println("interrupt " + thread1.getName() );
		TimeUnit.SECONDS.sleep(3);
		thread.interrupt();
		System.out.println("interrupt " + thread.getName() );
	}
}
