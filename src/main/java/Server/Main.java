package Server;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		Server server = new Server();

		try {
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
