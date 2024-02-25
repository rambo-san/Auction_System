public class Seller extends User {

    public Seller(int id, String username) {
        super(id, username, "Seller");
    }

    @Override
    public void performAction() {
        System.out.println("Seller " + username + " can add/view products and view bids on their products.");
    }

    // Seller-specific functionalities
    public void addProduct() {
        System.out.println("Adding a new product...");
    }

    public void viewProducts() {
        System.out.println("Viewing all products...");
    }

    public void viewBids() {
        System.out.println("Viewing bids on products...");
    }
}
