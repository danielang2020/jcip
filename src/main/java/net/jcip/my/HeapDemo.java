package net.jcip.my;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-28 10:39
 **/
public class HeapDemo {
	public static void main(String[] args) {
		showHeap();
		ArrayList<String> objects = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			objects.add(i + "");
		}

		System.gc();
		System.out.println(objects.size());
		showHeap();
		Iterator<String> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		System.gc();
		showHeap();
	}

	public static void showHeap() {
		// Get current size of heap in bytes
		long heapSize = Runtime.getRuntime().totalMemory();

		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		long heapMaxSize = Runtime.getRuntime().maxMemory();

		// Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		long heapFreeSize = Runtime.getRuntime().freeMemory();
		System.out.println("heapSize=" + heapSize + " heapMaxSize=" + heapMaxSize + " heapFreeSize=" + heapFreeSize);
	}
}
