package net.jcip.my;

import java.util.concurrent.*;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-17 14:33
 **/
public class SynQueueDemo {
	public static void main(String[] args) throws Exception{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		Runnable producer = () -> {
			Integer producedElement = ThreadLocalRandom
					.current()
					.nextInt();
			try {
				System.out.println("put:" + producedElement);
				queue.put(producedElement);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		};

		Runnable consumer = () -> {
			try {
				Integer consumedElement = queue.take();
				System.out.println("take:" + consumedElement);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		};

		executor.execute(producer);
		executor.execute(consumer);

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
	}
}
