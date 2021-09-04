package net.jcip.my;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-23 17:02
 **/
public class DeadlockExample {
	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);

	public static void main(String[] args)  {
		DeadlockExample deadlock = new DeadlockExample();
		new Thread(() -> {
			try {
				deadlock.operation1();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "T1").start();
		new Thread(() -> {
			try {
				deadlock.operation2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "T2").start();
		System.out.println("done...");
	}

	public void operation1() throws Exception {
		lock1.lock();
		System.out.println("lock1 acquired, waiting to acquire lock2.");
		TimeUnit.MILLISECONDS.sleep(50);

		lock2.lock();
		System.out.println("lock2 acquired");

		System.out.println("executing first operation.");

		lock2.unlock();
		lock1.unlock();
	}

	public void operation2() throws Exception {
		lock2.lock();
		System.out.println("lock2 acquired, waiting to acquire lock1.");
		TimeUnit.MILLISECONDS.sleep(50);

		lock1.lock();
		System.out.println("lock1 acquired");

		System.out.println("executing second operation.");

		lock1.unlock();
		lock2.unlock();
	}
}
