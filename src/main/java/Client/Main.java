package Client;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		Client client = new Client();

		try {
			client.startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
