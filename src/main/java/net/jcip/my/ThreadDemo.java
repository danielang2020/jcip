package net.jcip.my;

import java.util.concurrent.TimeUnit;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-18 11:35
 **/
public class ThreadDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.setDaemon(false);
		thread.start();

	}
}
