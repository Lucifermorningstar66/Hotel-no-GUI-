public class Payment {
    private int room;
    private float amount;
    private String paymentMethod;
    private String status;
    public Payment(int room, float amount, String paymentMethod) {
        this.room = room;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "Pending";
    }

    public void processPayment() {
        this.status = "Paid";
    }

    public void refundPayment() {
        this.status = "Refunded";
    }

    @Override
    public String toString () {
        return "Payment{" + "room=" + room + ", amount=" + amount + ", paymentMethod='" + paymentMethod + '\'' + '}';
    }

    public void applyDiscount(float discount) {
        this.amount -= this.amount * discount;
    }
}
