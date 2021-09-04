package net.jcip.my;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-21 22:24
 **/
public class IoEchoServer {
	public static void main(String[] args) throws Exception {
//		close();
		interrupt();
	}

	public static void close() throws Exception{
		ServerSocket serverSocket = new ServerSocket(7000);
		Socket socket = serverSocket.accept(); // blocking
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


		}).start();
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		int read;
		byte[] bytes = new byte[1024];
		while ((read = is.read(bytes)) != -1) { // blocking
			os.write(bytes, 0, read); // blocking
		}
		socket.close();

		serverSocket.close();
	}

	public static void interrupt() throws Exception{
		Thread thread = new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(7000);
				Socket socket = serverSocket.accept(); // blocking
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				int read;
				byte[] bytes = new byte[1024];
				while ((read = is.read(bytes)) != -1) { // blocking
					os.write(bytes, 0, read); // blocking
				}
				socket.close();

				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		thread.start();

		TimeUnit.SECONDS.sleep(1);
		thread.interrupt();
		System.out.println("interrupt...");
	}
}
