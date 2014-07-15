package dejanpe.zadatak1.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {
	
	
	private static final String ADDRESS = "localhost";

	private static final int PORT = 1234;
	
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
	@Override
	public void run() {
		System.out.println("Write command for server:");
		while (true) {
			String command = this.consoleIn.nextLine();
			sendCommandToServer(command);
			System.out.println(getResult());
			System.out.println("Write your next command:");
		}
	}
	
	private String getResult() {
		// TODO: what if server doesn't send anything... then fuck... 
		return this.serverIn.nextLine();		
	}
	
	private void sendCommandToServer(String command) {
		this.serverOut.print(command + "\n");
		this.serverOut.flush();
	}
	
	public static void main(String[] args) {
		new Client().start();
	}
}
