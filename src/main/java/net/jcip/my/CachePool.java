package net.jcip.my;

import java.util.concurrent.*;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-22 22:09
 **/
public class CachePool {
	public static void main(String[] args) throws Exception{
		ExecutorService executorService = new ThreadPoolExecutor(1, 1,
				60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

		executorService.submit(new Task());
		executorService.submit(new Task());
		TimeUnit.SECONDS.sleep(3);
	}
}

class Task implements Runnable {
	@Override
	public void run() {
		System.out.println("start..." + Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done..." + Thread.currentThread().getName());
	}
}
