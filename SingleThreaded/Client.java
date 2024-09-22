import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

     public void run() {
          int port = 8010;
          try (Socket socket = new Socket(InetAddress.getByName("localhost"), port);
                    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

               String message = "Hello World from socket " + socket.getLocalSocketAddress();
               toSocket.println(message);

               String response = fromSocket.readLine();
               System.out.println("Received from server: " + response);
          } catch (UnknownHostException e) {
               System.err.println("Unknown host: " + e.getMessage());
          } catch (IOException e) {
               System.err.println("I/O error: " + e.getMessage());
          }
     }

     public static void main(String[] args) {
          Client client = new Client();
          client.run();
     }
}
