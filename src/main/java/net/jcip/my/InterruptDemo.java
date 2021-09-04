package net.jcip.my;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-17 15:50
 **/
public class InterruptDemo {
	public static void main(String[] args) throws Exception{
		Thread thread = new Thread(() -> {

			// some dummy queue
			TransferQueue<String> queue = new LinkedTransferQueue<>();

			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("For 3 seconds the thread "
							+ Thread.currentThread().getName()
							+ " will try to poll an element from queue ...");

					queue.poll(3000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException ex) {
					System.out.println("InterruptedException! The thread "
							+ Thread.currentThread().getName() + " was interrupted! " + Thread.currentThread().isInterrupted());

					Thread.currentThread().interrupt();
				}
			}

			System.out.println("The execution was stopped!");
		});

		thread.start();
		Thread.sleep(1500);
		thread.interrupt();
	}
}
