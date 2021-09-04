package net.jcip.my;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-19 10:33
 **/
public class RunAndStartDemo {
	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " thread running ...");
		});
		thread.run();
		thread.run();

		Thread thread1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " thread starting ...");
		});
		thread1.start();

		FutureTask<String> voidFutureTask = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + " futuretask running ...");
			boolean interrupted = Thread.currentThread().isInterrupted();
			while (!interrupted) {
				break;
			}

			return "done...";
		});
		Thread thread2 = new Thread(voidFutureTask);
		thread2.start();
		TimeUnit.SECONDS.sleep(1);
		thread2.interrupt();
		System.out.println(voidFutureTask.get());
		System.out.println("thread2.isInterrupted()1:" + thread2.isInterrupted());
		System.out.println("thread2.isInterrupted()2:" + thread2.isInterrupted());

		//		System.out.println(voidFutureTask.get());
		//		System.out.println(voidFutureTask.get());
	}
}
