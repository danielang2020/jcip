package net.jcip.my;

import java.util.Random;
import java.util.concurrent.*;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-22 18:32
 **/
public class SingleThreadDeadLock {
	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Callable<String> callable = new Callable<>() {
			@Override
			public String call() throws Exception {
				System.out.println("callable start...");
				TimeUnit.SECONDS.sleep(new Random().nextInt(100));
				System.out.println("callable end...");
				return "callable";
			}
		};

		Callable<String> callable1 = new Callable<>() {
			@Override
			public String call() throws Exception {
				System.out.println("callable 1 start...");
				TimeUnit.SECONDS.sleep(new Random().nextInt(100));
				System.out.println("callable 1 end...");
				return "callable_1";
			}
		};

		Future<String> submit = executorService.submit(callable);
		Future<String> submit1 = executorService.submit(callable1);
		System.out.println(submit.get() + "_" + submit1.get());
		System.out.println("done...");

		executorService.shutdownNow();
	}
}
