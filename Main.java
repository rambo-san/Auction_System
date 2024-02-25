import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Welcome to the Auction System");

            while (true) {
                System.out.println("\n1. Login\n2. Sign Up\n3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        handleLogin(scanner);
                        break;
                    case 2:
                        handleSignUp(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting the Auction System.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = User.login(username, password);
        if (user == null) {
            System.out.println("Login failed. Please check your credentials.");
        } else {
            System.out.println("Login successful. Welcome, " + user.username + "!");
            user.performAction(scanner);
        }
    }

    private static void handleSignUp(Scanner scanner) {
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
    }
}
