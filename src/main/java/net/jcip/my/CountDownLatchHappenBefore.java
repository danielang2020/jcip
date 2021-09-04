package net.jcip.my;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-31 15:20
 **/
public class CountDownLatchHappenBefore {
	public static void main(String[] args) throws Exception{
		int n = 10;
		CountDownLatch c = new CountDownLatch(n);
		CDTask cdTask = new CDTask(c);
		for (int i = 0; i < n; i++) {
			new Thread(cdTask).start();
		}

		System.out.println(cdTask.i);
		System.out.println(cdTask.a);
	}
}

class CDTask implements Runnable{
	CountDownLatch cdl;
	public CDTask(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	public int i = 0;
	public int a = 0;
	@Override
	public void run() {
		i += 10;
//		cdl.countDown();
		a+=10;
	}
}

