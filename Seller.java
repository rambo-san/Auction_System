import java.util.Scanner;
public class Seller extends User {
    public Seller(int id, String username) {
        super(id, username, "Seller");
    }

    @Override
    public void performAction(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Seller Actions: \n1. Add Product \n2. View Products \n3. View Bids \n4. Logout");
            System.out.print("Choose an action: ");
            int action = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (action) {
                case 1:
                    System.out.println("Adding a product...");
                    break;
                case 2:
                    // View products logic
                    System.out.println("Listing your products...");
                    break;
                case 3:
                    // View bids logic
                    System.out.println("Viewing bids on your products...");
                    break;
                case 4:
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
