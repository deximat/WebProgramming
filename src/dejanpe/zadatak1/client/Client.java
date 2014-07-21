package dejanpe.zadatak1.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {

	private static final String ADDRESS = "localhost";

	private static final int PORT = 1234;

	private static final Object EXIT_COMMAND = "QUIT";

	public static void main(final String[] args) {
		new Client().start();
	}

	private Scanner consoleIn;

	private Socket server;

	private Scanner serverIn;

	private PrintWriter serverOut;

	public Client() {
		this.consoleIn = new Scanner(System.in);
		try {
			this.server = new Socket(ADDRESS, PORT);
			this.serverIn = new Scanner(this.server.getInputStream());
			this.serverOut = new PrintWriter(this.server.getOutputStream());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getResult() {
		// TODO: what if server doesn't send anything... then fuck...
		return this.serverIn.nextLine();
	}

	@Override
	public void run() {

		System.out.println("Welcome to GALAXY system, please do your work!");
		while (true) {
			String command = this.consoleIn.nextLine();
			if (command.equals(EXIT_COMMAND)) {
				System.out.println("Thanks for using GALAXY system!");
				return;
			}
			sendCommandToServer(command);
			System.out.println(getResult());
			System.out.println("What more, master?");
		}
	}

	private void sendCommandToServer(final String command) {
		this.serverOut.print(command + "\n");
		this.serverOut.flush();
	}
}
