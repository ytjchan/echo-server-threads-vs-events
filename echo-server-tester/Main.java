import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ArrayList<Integer> threadNum = new ArrayList<Integer>();
    String ip = "localhost";
    String port = "8080";
    if(args.length>0) 
      ip = args[0];
    if (args.length>1)
      port = args[1];
    if (args.length>2)
      for (String s: Arrays.copyOfRange(args, 2, args.length))
        threadNum.add(Integer.parseInt(s));
    if (threadNum.isEmpty())
      threadNum.add(1);

    for (int k: threadNum) {
      // Preparing threads
      ArrayList<SocketThread> threads = new ArrayList<>();
      for (int i=0; i<k; i++)
        threads.add(new SocketThread(ip,port));
      //Starting threads
      for (Thread thread: threads)
        thread.start();
      // Wait for all threads to end
      for (Thread thread: threads)
        thread.join();
      // Results
      long totalTime = 0;
      long max = 0;
      long min = Long.MAX_VALUE;
      for (SocketThread thread: threads) {
        totalTime += thread.getTimeUsed();
        //thread.print();
        if (thread.getTimeUsed() > max)
          max = thread.getTimeUsed();
        if (thread.getTimeUsed() < min)
          min = thread.getTimeUsed();
      }
      System.out.println("====="+k+" threads results=====");
      System.out.println("Summation of time used: "+totalTime);
      System.out.println("Max time used by a thread: "+max);
      System.out.println("Min time used by a thread: "+min);
      Thread.sleep(5*1000);
    }

  }

}
