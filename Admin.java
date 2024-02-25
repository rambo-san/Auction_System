import java.util.Scanner;

public class Admin extends User {
    public Admin(int id, String username) {
        super(id, username, "Admin");
    }

    @Override
    public void performAction(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Admin Actions: \n1. Create User \n2. View Users \n3. Start Auction \n4. End Auction \n5. Logout");
            System.out.print("Choose an action: ");
            int action = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (action) {
                case 1:
                    // Create user logic
                    System.out.println("Creating a new user...");
                    break;
                case 2:
                    // View users logic
                    System.out.println("Listing all users...");
                    break;
                case 3:
                    // Start auction logic
                    System.out.println("Starting an auction...");
                    break;
                case 4:
                    // End auction logic
                    System.out.println("Ending an auction...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}

