package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
     public Consumer<Socket> getConsumer() {
          return (clientSocket) -> {
               try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    toSocket.println("Hello from server " + clientSocket.getInetAddress());
               } catch (IOException ex) {
                    System.err.println("Error communicating with client: " + ex.getMessage());
               } finally {
                    try {
                         clientSocket.close();
                    } catch (IOException e) {
                         System.err.println("Error closing client socket: " + e.getMessage());
                    }
               }
          };
     }

     public static void main(String[] args) {
          int port = 8010;
          Server server = new Server();

          try (ServerSocket serverSocket = new ServerSocket(port)) {
               serverSocket.setSoTimeout(70000);
               System.out.println("Server is listening on port " + port);
               while (true) {
                    try {
                         Socket clientSocket = serverSocket.accept();
                         System.out.println("Connected to " + clientSocket.getRemoteSocketAddress());

                         Thread thread = new Thread(() -> server.getConsumer().accept(clientSocket));
                         thread.start();
                    } catch (IOException ex) {
                         System.err.println("Error accepting connection: " + ex.getMessage());
                    }
               }
          } catch (IOException ex) {
               System.err.println("Error starting server: " + ex.getMessage());
          }
     }
}
