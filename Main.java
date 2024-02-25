import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Auction System");

        System.out.println("1. Login\n2. Sign Up");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();

                User user = User.login(loginUsername, loginPassword);
                if (user == null) {
                    System.out.println("Login failed. Please check your credentials.");
                } else {
                    System.out.println("Login successful. Welcome, " + user.username + "!");
                    user.performAction();
                }
                break;
            case 2:
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter role (Seller/Buyer): ");
                String role = scanner.nextLine();

                boolean success = User.signUp(username, password, role);
                if (success) {
                    System.out.println("Registration successful. You can now log in.");
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        scanner.close();
    }
}
