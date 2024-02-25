import java.util.Scanner;

public class AuctionSystemLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Auction System");

        System.out.print("Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Simple check for admin login (In real-world scenarios, validate against secure data source)
        if ("admin".equals(username) && "adminpass".equals(password)) {
            System.out.println("Admin login successful. Starting server...");
            AdminServer.startServer();  // AdminServer is a modified version of the server class which includes a static method to start the server.
        } else {
            System.out.println("Invalid admin credentials.");
        }

        scanner.close();
    }
}
