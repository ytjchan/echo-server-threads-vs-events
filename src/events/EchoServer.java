package events;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class EchoServer implements Runnable {
	
	private int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		System.out.println("Starting echo server on port "+port);
		
		try (
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				Selector selector = Selector.open();
				) {
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); 
			
			while (true) {
				// Wait until a channel is registered to this selector (blocking)
				System.out.println("Waiting for connection...");
				selector.select();
				
				// A channel is registered
				Set<SelectionKey> keys = selector.selectedKeys();
				
				for (SelectionKey key: keys) {
					// Accept a connection and prepare read channel
					if (key.isAcceptable()) {
						SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
						if (clientChannel!=null) {
							clientChannel.configureBlocking(false);
							clientChannel.register(selector, SelectionKey.OP_READ);
							System.out.println("Connected from "+clientChannel.socket().getInetAddress());
						}
					}
					// Read channel for input
					if (key.isReadable()) {
						SocketChannel clientChannel = (SocketChannel)key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						clientChannel.read(buffer);
						buffer.flip();
						System.out.println("echo:\n\n" + new String(buffer.array()));
						clientChannel.write(buffer);
						clientChannel.close();
						System.out.println("\nConnection closed");
					}
				}
				
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
