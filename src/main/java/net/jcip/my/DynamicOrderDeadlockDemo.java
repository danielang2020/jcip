package net.jcip.my;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-24 10:28
 **/
public class DynamicOrderDeadlockDemo {
	public static void main(String[] args) {
		Account my = new Account();
		Account your = new Account();
		new Thread(() -> {
			try {
				transferMoney(my,your,10,1,2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				transferMoney(your,my,20,3,4);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}
	public static void transferMoney(Account fromAccount, Account toAccount, int amount, int from_index, int to_index)
			throws Exception {
		System.out.println("account" + from_index + "~and account ~" + to_index + "~request lock");

		synchronized (fromAccount) {
			System.out.println("Account >>" + from_index + "<Get Lock");
			synchronized (toAccount) {
				System.out.println("Account" + from_index + "&" + to_index + " locks are acquired");
				if (fromAccount.compareTo(amount) < 0) {
					throw new Exception();
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}
	}

	static class Account {
		private int balance = 100000; // Let's assume the initial money in each person's account.
		private static final AtomicInteger sequence = new AtomicInteger();
		private final int accNo;


		public Account() {
			accNo = sequence.incrementAndGet();
		}

		void debit(int m) throws InterruptedException {
			Thread.sleep(5);//Simulated operation time
			balance = balance + m;
		}

		void credit(int m) throws InterruptedException {
			Thread.sleep(5);//Simulated operation time
			balance = balance - m;
		}

		int getBalance() {
			return balance;
		}

		int getAccNo() {
			return accNo;
		}

		public int compareTo(int money) {
			if (balance > money) {
				return 1;
			} else if (balance < money) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
