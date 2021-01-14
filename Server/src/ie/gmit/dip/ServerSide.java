package ie.gmit.dip;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class ServerSide {

	public static void main(String[] args) throws IOException {

		int portNumber;

		portNumber = Integer.parseInt(args[0]);

		System.out.println("+++++++++++++++++++++++++");
		System.out.println("Welcome to the chat ");
		System.out.println("To quit press: /q");
		System.out.println("Please enter some text: ");

		System.out.println("+++++++++++++++++++++++++");

		ServerSocket serverSocket;
		Socket socket = null;
		PrintStream ostream;
		DataInputStream istream;

		serverSocket = new ServerSocket(portNumber);

		Scanner scanner = new Scanner(System.in);

		try {
			socket = serverSocket.accept(); // will be stuck there until client connects to same socket.
			ostream = new PrintStream(socket.getOutputStream());
			istream = new DataInputStream(socket.getInputStream()); // coming from client.
			while (true) {

				String outputMessage = scanner.nextLine();
				if (outputMessage.equals("/q")) {
					System.out.println("You have exited the chat.");

					break;
				}

				ostream.println(outputMessage); // going to client
				String inputMessage = istream.readLine();

				if (inputMessage == null) {
					System.out.println("Client disconnected.");
					break;
				}
				System.out.println(inputMessage);

			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			serverSocket.close();
			socket.close();
			scanner.close();
		}

	}

}
