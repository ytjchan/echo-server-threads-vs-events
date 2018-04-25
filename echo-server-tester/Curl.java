import java.io.IOException;
import java.util.Date;

public class Curl {

  public static void main(String[] args) throws IOException {
    String ip = "143.89.104.188";
    String port = "8080";
    if (args.length>0) {
      ip = args[0];
      if (args.length>1)
        port = args[1];
    }
    
    Date t1 = new Date();
    Runtime.getRuntime().exec("curl "+ip+":"+port);
    Date t2 = new Date();
    System.out.println("Time used= "+(t2.getTime()-t1.getTime()));
  }

}
