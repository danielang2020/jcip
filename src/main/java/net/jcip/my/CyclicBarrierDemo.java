package net.jcip.my;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-17 17:50
 **/
public class CyclicBarrierDemo {
	public static void main(String[] args) {

		//3 threads are part of the barrier, ServiceOne, ServiceTwo and this main thread calling them.
		final CyclicBarrier barrier = new CyclicBarrier(3);

		Thread serviceOneThread = new Thread(new ServiceOne(barrier));
		Thread serviceTwoThread = new Thread(new ServiceTwo(barrier));

		System.out.println("Starting both the services at" + new Date());

		serviceOneThread.start();
		serviceTwoThread.start();

		try {
			barrier.await();
		} catch (InterruptedException e) {
			System.out.println("Main Thread interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Main Thread interrupted!");
			e.printStackTrace();
		}
		System.out.println("Ending both the services phase 1 at" + new Date());
		barrier.reset();

		try {
			barrier.await();
		} catch (InterruptedException e) {
			System.out.println("Main Thread interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Main Thread interrupted!");
			e.printStackTrace();
		}
		System.out.println("Ending both the services phase 2 at" + new Date());
	}
}

class ServiceOne implements Runnable {

	private final CyclicBarrier cyclicBarrier;

	public ServiceOne(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		System.out.println("Starting service One phase 1...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Service One has finished its phase 1 ork... waiting for others...");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		}

		System.out.println("Starting service One phase 2...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Service One has finished its phase 2 work... waiting for others...");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		}
		System.out.println("The wait is over, lets complete Service One!");

	}

}

class ServiceTwo implements Runnable {

	private final CyclicBarrier cyclicBarrier;

	public ServiceTwo(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		System.out.println("Starting service Two phase 1....");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Service Two has finished its phase 1 work.. waiting for others...");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		}

		System.out.println("Starting service Two phase 2....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Service Two has finished its phase 2 work.. waiting for others...");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println("Service one interrupted!");
			e.printStackTrace();
		}

		System.out.println("The wait is over, lets complete Service two!");

	}

}