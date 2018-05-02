# Echo Server Threads vs. Events
A comparison between echo server using threads and events when dealing with simultaneous client connections.

## Usage
To start threads version echo server, go to [bin/](bin/) and
``` shell
java threads.EchoServer <port>
```
To start events version echo server, go to [bin/](bin/) and 
``` shell
java events.EchoServer <port>
```

## Tools of evaluation
To test the performance of the two echo servers, first start the subject server, then go to [echo-server-tester/](echo-server-tester/) and 
```shell
java Main <ip> <port> <num of connections#1> <num of connections#2> <...>
```
Each time ```<num of connections#n>``` will start that number of threads to connect to the server in the same time, after all finishes, show an evaluation report. Then after 5 seconds, ```<num of connevtions#n+1>``` will start.

To check memory and CPU usage of the server, use ```jconsole``` and connect to the ```EchoServer``` process.
