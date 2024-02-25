import java.util.Scanner;
public class Buyer extends User {
    public Buyer(int id, String username) {
        super(id, username, "Buyer");
    }

    @Override
    public void performAction(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Buyer Actions: \n1. View Auctions \n2. View Bids \n3. Place Bid \n4. Logout");
            System.out.print("Choose an action: ");
            int action = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (action) {
                case 1:
                    // View auctions logic
                    System.out.println("Viewing all active auctions...");
                    break;
                case 2:
                    // View bids logic
                    System.out.println("Viewing your bids...");
                    break;
                case 3:
                    // Place bid logic
                    System.out.println("Placing a bid on an auction...");
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
