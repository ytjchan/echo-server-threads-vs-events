import java.io.IOException;
import java.util.Date;

public class CurlThread extends Thread {

  private static int count = 0;

  private String ip = "143.89.104.188";
  private String port = "8080";
  private int num;
  private long timeUsed;

  public CurlThread(String... args) {
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
      Runtime.getRuntime().exec("curl "+ip+":"+port);
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

}
