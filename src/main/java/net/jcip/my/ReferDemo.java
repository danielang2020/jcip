package net.jcip.my;

import java.util.ArrayList;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-12 11:55
 **/
public class ReferDemo {
	private int i;
	private String s;
	private ArrayList<String> al = new ArrayList<>();
	private String[] l = new String[]{"1L","2L","3L"};

	public String[] getL() {
		return l;
	}

	public void setL(String[] l) {
		this.l = l;
	}

	public static void main(String[] args) {
		ReferDemo referDemo = new ReferDemo();
		referDemo.setI(1);
		referDemo.setS("ok");
//		ArrayList<String> objects = new ArrayList<>();
//		objects.add("a");
//		objects.add("b");
//		referDemo.setAl(objects);
//		referDemo.setL(new String[]{"1L","2L","3L"});



		int i = referDemo.getI();
		i = 2;
		String s = referDemo.getS();
		s = "no";
		ArrayList al = referDemo.getAl();
		al.add("c");
		al.add("d");
		referDemo.getL()[0] = "100";
//		String s1 = l[0];
		System.out.println(referDemo.getI() + " " + referDemo.getS() + " " + referDemo.getAl());

		for (String a : referDemo.getL()) {
			System.out.print("array:" + a + "  ");
		}

		ArrayList<String> objects1 = new ArrayList<>();
		objects1.add("aa");
		objects1.add("bb");
		referDemo.getAl().clear();

		System.out.println(referDemo.getI() + " " + referDemo.getS() + " " + referDemo.getAl());
	}
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public ArrayList getAl() {
		return al;
	}

	public void setAl(ArrayList al) {
		this.al = al;
	}
}
