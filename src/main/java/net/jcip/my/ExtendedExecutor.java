package net.jcip.my;

import java.util.concurrent.*;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-22 16:30
 **/
public class ExtendedExecutor extends ThreadPoolExecutor {
	public ExtendedExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if (t == null
				&& r instanceof Future<?>
				&& ((Future<?>)r).isDone()) {
			try {
				Object result = ((Future<?>) r).get();
				System.out.println("after execute result:" + result);
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				// ignore/reset
				Thread.currentThread().interrupt();
			}
		}
		if (t != null)
			t.printStackTrace();
	}

	public static void main(String[] args) throws Exception{
		ExtendedExecutor extendedExecutor = new ExtendedExecutor(1, 1,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());


		Callable<String> callable = new Callable<>() {
			@Override
			public String call() throws Exception {

				return "1";
			}
		};
		FutureTask<String> stringFutureTask = new FutureTask<>(callable);
		Future<String> submit = extendedExecutor.submit(callable);
//		extendedExecutor.submit(stringFutureTask);
				System.out.println("result:" + submit.get());
//		System.out.println("result:" + stringFutureTask.get());

		TimeUnit.SECONDS.sleep(1);
		extendedExecutor.shutdownNow();
	}
}