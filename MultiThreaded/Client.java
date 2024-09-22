package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
// import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

     public Runnable getRunnable() {
          return () -> {
               int port = 8010;
               try {
                    InetAddress address = InetAddress.getByName("localhost");
                    try (Socket socket = new Socket(address, port);
                              PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                              BufferedReader fromSocket = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()))) {

                         String message = "Hello from Client " + socket.getLocalSocketAddress();
                         toSocket.println(message);
                         String response = fromSocket.readLine();
                         System.out.println("Response from Server: " + response);
                    } catch (IOException e) {
                         System.err.println("Error communicating with server: " + e.getMessage());
                    }
               } catch (IOException e) {
                    System.err.println("Unable to connect to server: " + e.getMessage());
               }
          };
     }

     public static void main(String[] args) {
          Client client = new Client();
          ExecutorService executor = Executors.newFixedThreadPool(10);

          for (int i = 0; i < 100; i++) {
               executor.execute(client.getRunnable());
          }

          executor.shutdown();
          while (!executor.isTerminated()) {
          }

          System.out.println("All clients finished.");
     }
}
