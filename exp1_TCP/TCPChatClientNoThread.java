import java.io.*;
import java.net.*;

public class TCPChatClientNoThread {
    private static final String SERVER_IP = "127.0.0.1"; // localhost
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT)) {
            System.out.println("Connected to the server.");

            // Streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                // Send message to server
                System.out.print("You: ");
                clientMessage = userInput.readLine();
                out.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive message from server
                serverMessage = in.readLine();
                if (serverMessage == null || serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println("Server: " + serverMessage);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
