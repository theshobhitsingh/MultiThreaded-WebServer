package ThreadPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
     private final ExecutorService threadPool;
     private volatile boolean running = true;

     public Server(int poolSize) {
          this.threadPool = Executors.newFixedThreadPool(poolSize);
     }

     public void handleClient(Socket clientSocket) {
          try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
               toSocket.println("Hello from server " + clientSocket.getInetAddress());
          } catch (IOException ex) {
               System.err.println("Error handling client connection: " + ex.getMessage());
          } finally {
               try {
                    clientSocket.close();
               } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
               }
          }
     }

     public void stop() {
          running = false;
          threadPool.shutdown();
     }

     public static void main(String[] args) {
          int port = 8010;
          int poolSize = 10;
          Server server = new Server(poolSize);

          try (ServerSocket serverSocket = new ServerSocket(port)) {
               serverSocket.setSoTimeout(70000);
               System.out.println("Server is listening on port " + port);

               while (server.running) {
                    try {
                         Socket clientSocket = serverSocket.accept();
                         server.threadPool.execute(() -> server.handleClient(clientSocket));
                    } catch (IOException ex) {
                         if (server.running) {
                              System.err.println("Error accepting connection: " + ex.getMessage());
                         } else {
                              System.out.println("Server is shutting down.");
                         }
                    }
               }
          } catch (IOException ex) {
               System.err.println("Error starting server: " + ex.getMessage());
          } finally {
               server.stop();
          }
     }
}
