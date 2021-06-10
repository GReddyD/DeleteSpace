package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

	private static final int SERVER_PORT = 44444;
	private static final String HOST = "127.0.0.1";
	private static final int SLEEP_TIME = 1000;

	protected void startClient() throws IOException {
		InetSocketAddress socketAddress = new InetSocketAddress(HOST, SERVER_PORT);
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(socketAddress);
		System.out.print("Клиент стартовал");
		try (Scanner scanner = new Scanner(System.in)) {
			ByteBuffer inputBufClient = ByteBuffer.allocate(2 << 10);
			String msg;
			while (true) {
				System.out.print("\nВведите строку с пробелами для отправки на сервер: ");
				msg = scanner.nextLine();
				if ("end".equals(msg)) {
					break;
				}
				socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
				Thread.sleep(SLEEP_TIME);
				int bytesCount = socketChannel.read(inputBufClient);
				System.out.println(new String(inputBufClient.array(), 0, bytesCount, StandardCharsets.UTF_8).trim());
				inputBufClient.clear();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}