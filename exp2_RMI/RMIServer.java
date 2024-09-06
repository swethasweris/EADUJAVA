import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the implementation
            AddNumbersImpl obj = new AddNumbersImpl();

            // Bind the remote object in the registry with a specific port (2000 in this example)
            Registry registry = LocateRegistry.createRegistry(2000); 
            registry.rebind("AddNumbersService", obj);

            System.out.println("Server started and AddNumbersService bound to registry on port 2000.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
