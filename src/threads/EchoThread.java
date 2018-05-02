package threads;

import java.io.IOException;
import java.net.Socket;

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
	
}
