package dejanpe.zadatak1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	public static void main(final String[] args) {
		Server server = new Server();
		server.start();
	}

	private final int PORT = 1234;

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(this.PORT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Server galaxy started!");
		boolean turnOff = false;
		while (!turnOff) {
			try {
				System.out.println("Waiting for new client to arive:");
				Socket client = serverSocket.accept();
				System.out.println("Client(" + client + ") arrived!");
				new Worker(client).start();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occured while accepting client.");
			} 
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
