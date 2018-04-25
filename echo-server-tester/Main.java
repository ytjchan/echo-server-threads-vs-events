import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    int threadCount = 1;
    String ip = "143.89.104.188";
    String port = "8080";
    if (args.length>0)
      threadCount = Integer.parseInt(args[0]);
    if(args.length>1) 
      ip = args[1];
    if (args.length>2)
      port = args[2];

    // Preparing threads
    ArrayList<CurlThread> threads = new ArrayList<>();
    for (int i=0; i<threadCount; i++)
      threads.add(new CurlThread(ip,port));
    //Starting threads
    for (Thread thread: threads)
      thread.start();
    // Wait for all threads to end
    int i=0;
    for (Thread thread: threads) {
      System.out.println("Waiting on thread: "+i);
      thread.join();
      i++;
    }
    // Results
    long totalTime = 0;
    long max = 0;
    for (CurlThread thread: threads) {
      totalTime += thread.getTimeUsed();
      thread.print();
      if (thread.getTimeUsed() > max)
        max = thread.getTimeUsed();
    }
    System.out.println("Summation of time used: "+totalTime);
    System.out.println("Max time used by a thread: "+max);

  }

}
