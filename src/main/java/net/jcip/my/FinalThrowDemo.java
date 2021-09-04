package net.jcip.my;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-21 20:17
 **/
public class FinalThrowDemo {
	public static int show() {
		try {
			Integer.parseInt("1");
		} finally {
			System.out.println("finally");
		}

		return 1;
	}

	public static void main(String[] args) {
		System.out.println(show());
	}
}
