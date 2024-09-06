import java.io.*;
import java.net.*;

public class TCPChatServerNoThread {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;
            while (true) {
                // Receive message from client
                clientMessage = in.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Send message to client
                System.out.print("You: ");
                serverMessage = userInput.readLine();
                out.println(serverMessage);
                if (serverMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
