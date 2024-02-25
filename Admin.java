public class Admin extends User {

    public Admin(int id, String username) {
        super(id, username, "Admin");
    }

    @Override
    public void performAction() {
        System.out.println("Admin " + username + " can create users, start/end auctions, and view users.");
    }

    // Admin-specific functionalities
    public void createUser() {
        System.out.println("Creating a new user...");
    }

    public void viewUsers() {
        System.out.println("Viewing all users...");
    }

    public void startAuction() {
        System.out.println("Starting an auction...");
    }

    public void endAuction() {
        System.out.println("Ending an auction...");
    }
}
