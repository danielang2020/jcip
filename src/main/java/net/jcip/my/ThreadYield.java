package net.jcip.my;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-24 14:55
 **/
public class ThreadYield {
	public static void main(String[] args) {
		Runnable r = () -> {
			int counter = 0;
			while (counter < 2) {
				System.out.println(Thread.currentThread()
						.getName() + "start...");
				counter++;
				Thread.yield();
				System.out.println(Thread.currentThread()
						.getName() + "end...");
			}
		};
		new Thread(r).start();
		new Thread(r).start();
	}
}
