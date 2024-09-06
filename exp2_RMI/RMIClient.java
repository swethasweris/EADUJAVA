import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            // Connect to the registry on port 2000 (same as where the registry is running)
            Registry registry = LocateRegistry.getRegistry("localhost", 2000);

            // Look up the remote object
            AddNumbers stub = (AddNumbers) registry.lookup("AddNumbersService");

            // Get input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter the second number: ");
            int num2 = scanner.nextInt();

            // Call the add method and print the result
            int result = stub.add(num1, num2);
            System.out.println("Sum of " + num1 + " and " + num2 + " is: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
