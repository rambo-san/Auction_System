public class Buyer extends User {

    public Buyer(int id, String username) {
        super(id, username, "Buyer");
    }

    @Override
    public void performAction() {
        System.out.println("Buyer " + username + " can view auctions, place bids, and view their bids.");
    }

    // Buyer-specific functionalities
    public void viewAuctions() {
        System.out.println("Viewing all active auctions...");
    }

    public void placeBid() {
        System.out.println("Placing a bid on an auction...");
    }

    public void viewMyBids() {
        System.out.println("Viewing my bids...");
    }
}
