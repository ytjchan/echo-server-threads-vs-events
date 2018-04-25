package threads;

import java.io.IOException;
import java.net.Socket;

import events.EchoServer;

public class EchoThread extends Thread {
	
	Socket socket;

	public EchoThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// Read bytes received and write back
			byte[] buffer = new byte[1024];
			socket.getInputStream().read(buffer);
			System.out.println("echo:\n\n" + new String(buffer));
			socket.getOutputStream().write(buffer);
			socket.close();
			System.out.println("\nConnection closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EchoServer es = new EchoServer(args.length>0?Integer.parseInt(args[0]):8080);
		es.run();
	}
	
}
