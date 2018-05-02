import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.net.Socket;

public class SocketThread extends Thread {

  private static int count = 0;

  private String ip = "localhost";
  private String port = "8080";
  private int num;
  private long timeUsed;

  public SocketThread(String... args) {
    if (args.length>0) {
      ip = args[0];
      if (args.length>1)
        port = args[1];
    }
    num = count++;
  }

  @Override
  public void run() {
    Date t1 = new Date();
    try {
      Socket s = new Socket(ip, Integer.parseInt(port));
      PrintWriter out = 
        new PrintWriter(s.getOutputStream(), true);
      out.append("Thread "+num+" testing...");
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Date t2 = new Date();
    timeUsed = t2.getTime() - t1.getTime();
    //System.out.println("Time used= "+timeUsed);
  }

  public long getTimeUsed() {
    return timeUsed;
  }

  public void print() {
    if (timeUsed!=0)
      System.out.println("Thread "+num+" finished in "+timeUsed);
  }

  public static void main(String[] args) {
    new SocketThread().run();
  }
}
