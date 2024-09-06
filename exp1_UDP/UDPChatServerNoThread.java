import java.net.*;

public class UDPChatServerNoThread {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            InetAddress clientAddress = null;
            int clientPort = -1;

            while (true) {
                // Receive message from client
                socket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Get server message from input
                System.out.print("You: ");
                String serverMessage = new java.util.Scanner(System.in).nextLine();

                // Send response to client
                byte[] sendData = serverMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
