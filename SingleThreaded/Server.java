import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

     public void run() throws IOException {
          int port = 8010;
          try (ServerSocket serverSocket = new ServerSocket(port)) {
               serverSocket.setSoTimeout(20000);
               System.out.println("Server is listening on port: " + port);

               while (true) {
                    try {
                         Socket acceptedConnection = serverSocket.accept();
                         System.out.println("Connected to " + acceptedConnection.getRemoteSocketAddress());
                         new Thread(new ClientHandler(acceptedConnection)).start();
                    } catch (IOException e) {
                         System.out.println("Error accepting connection: " + e.getMessage());
                    }
               }
          }
     }

     private static class ClientHandler implements Runnable {
          private final Socket clientSocket;

          public ClientHandler(Socket socket) {
               this.clientSocket = socket;
          }

          @Override
          public void run() {
               try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader fromClient = new BufferedReader(
                                   new InputStreamReader(clientSocket.getInputStream()))) {

                    toClient.println("Hello World from the server!");

                    String message;
                    while ((message = fromClient.readLine()) != null) {
                         System.out.println("Received from client: " + message);
                         toClient.println("Echo: " + message);
                    }
               } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
               } finally {
                    try {
                         clientSocket.close();
                    } catch (IOException e) {
                         System.out.println("Error closing socket: " + e.getMessage());
                    }
               }
          }
     }

     public static void main(String[] args) {
          Server server = new Server();
          try {
               server.run();
          } catch (IOException ex) {
               ex.printStackTrace();
          }
     }
}
