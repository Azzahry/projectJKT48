public class Ticket {
    private String eventName;
    private double price;
    private int availableTickets;

    public Ticket(String eventName, double price, int availableTickets){
        this.eventName = eventName;
        this.price = price;
        this.availableTickets = availableTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void decreaseAvailTikcets(int quantity){
        availableTickets -= quantity;
    }
}
