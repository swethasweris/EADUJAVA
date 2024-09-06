import java.net.*;

public class UDPChatClientNoThread {
    private static final String SERVER_IP = "127.0.0.1"; // localhost
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                // Get client message from input
                System.out.print("You: ");
                String clientMessage = new java.util.Scanner(System.in).nextLine();

                // Send message to server
                byte[] sendData = clientMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                socket.send(sendPacket);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive message from server
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }

                System.out.println("Server: " + serverMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
