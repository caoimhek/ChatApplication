package ie.gmit.dip;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		String hostname;
		int portNumber;

		portNumber = Integer.parseInt(args[0]);

		hostname = (args[1]);

		System.out.println("+++++++++++++++++++++++++");
		System.out.println("Welcome to the chat ");
		System.out.println("To quit press: /q");
		System.out.println("Please enter some text: ");

		System.out.println("+++++++++++++++++++++++++");

		Scanner scanner = new Scanner(System.in);

		Socket socket = null;

		DataInputStream istream;

		PrintStream ostream;

		try {
			socket = new Socket(hostname, portNumber);
			istream = new DataInputStream(socket.getInputStream()); // from server
			ostream = new PrintStream(socket.getOutputStream()); // to server
			while (true) {

				String inputMessage = istream.readLine();

				if (inputMessage == null) {
					System.out.println("Lost connection to server.");
					break;

				}

				System.out.println(inputMessage); // coming from server.

				String outputMessage = scanner.nextLine();
				if (outputMessage.equals("/q")) {
					System.out.println("You have exited the chat.");

					break;
				}

				ostream.println(outputMessage);

			}

		} // end try
		catch (UnknownHostException e) {
			System.err.println(e);
		} catch (ConnectException e) {
			System.out.println("Failed to connect to chat");
		}

		catch (IOException e) {
			System.err.println(e);
		} finally {
			if (socket != null) {
				socket.close();
			}
			if (scanner != null) {
				scanner.close();

			}
		}
	}

}
