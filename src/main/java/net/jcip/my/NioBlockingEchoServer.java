package net.jcip.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-21 22:35
 **/
public class NioBlockingEchoServer {
	public static void main(String[] args)  throws Exception{
		interrupt(false);
//		close();
	}

	public static void close(boolean block) throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress("localhost", 7000));
		serverSocketChannel.configureBlocking(block);
		SocketChannel socketChannel = serverSocketChannel.accept(); // blocking
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				socketChannel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


		}).start();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			buffer.clear();
			int read = socketChannel.read(buffer); // blocking
			if (read < 0) {
				break;
			}
			buffer.flip();
			socketChannel.write(buffer); // blocking
		}
		socketChannel.close();
		serverSocketChannel.close();
	}

	public static void interrupt(boolean block) throws Exception{
		Thread localhost = new Thread(() -> {
			try {
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				serverSocketChannel.bind(new InetSocketAddress("localhost", 7000));
				serverSocketChannel.configureBlocking(block);
				SocketChannel socketChannel = serverSocketChannel.accept(); // blocking
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				while (true) {
					buffer.clear();
					int read = socketChannel.read(buffer); // blocking
					if (read < 0) {
						break;
					}
					buffer.flip();
					socketChannel.write(buffer); // blocking
				}
				socketChannel.close();
				serverSocketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		localhost.start();

		TimeUnit.SECONDS.sleep(1);
		localhost.interrupt();
	}
}
