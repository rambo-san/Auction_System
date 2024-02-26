import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BuyerClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public BuyerClient(String address, int port) throws IOException {
        socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Login\n2. Sign Up");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Handle login
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            out.println("LOGIN:" + username + ":" + password);
        } else if (choice == 2) {
            // Handle signup
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Role (Seller/Buyer): ");
            String role = scanner.nextLine();
            out.println("SIGNUP:" + username + ":" + password + ":" + role);
        }

        // Read response from server
        String response = in.readLine();
        System.out.println(response);

        scanner.close();
    }
    
    public static void main(String[] args) throws IOException {
        BuyerClient buyer = new BuyerClient("127.0.0.1", 12345);
        buyer.start();
    }
}
