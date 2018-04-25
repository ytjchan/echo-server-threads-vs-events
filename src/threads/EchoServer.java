package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable {
	
	private int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		System.out.println("Starting echo server on port "+port);
		
		try (ServerSocket serverSocket = new ServerSocket(port);) {
			while (true) {
				// Continuously waiting for HTTP request
				System.out.println("Waiting for connection...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connected from "+clientSocket.getInetAddress());
				// Create a thread to deal with the request, then get back to waiting
				new EchoThread(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EchoServer es = new EchoServer(args.length>0?Integer.parseInt(args[0]):8080);
		es.run();
	}

}
