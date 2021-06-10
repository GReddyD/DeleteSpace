package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {

	private static final int SERVER_PORT = 44444;
	private static final String HOST = "127.0.0.1";

	protected void startServer() throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.bind(new InetSocketAddress(HOST, SERVER_PORT));
		System.out.println("Сервер стартовал");

		while (true) {
			try (SocketChannel socketChannel = serverChannel.accept()) {
				final ByteBuffer inputBufServer = ByteBuffer.allocate(2 << 10);
				String text;
				while (socketChannel.isConnected()) {
					int cntBytes = socketChannel.read(inputBufServer);
					if (cntBytes == -1) break;
					final String msg = new String(inputBufServer.array(), 0, cntBytes, StandardCharsets.UTF_8);
					if(msg.equals("end")) {
						System.out.println("Сервер выключается");
						break;
					}
					text = msg.replaceAll("\\s", "");
					inputBufServer.clear();
					socketChannel.write(ByteBuffer.wrap(("\nОтвет от сервера: \n" + text).getBytes(StandardCharsets.UTF_8)));
				}
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}
}
