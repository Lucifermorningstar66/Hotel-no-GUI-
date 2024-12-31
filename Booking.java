public class Booking {
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private String paymentStatus;

    public Booking(Customer customer, Room room, String checkInDate, String checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentStatus = "Pending";
    }

    public float calculateTotalCost() {
        return room.getPrice() * getNumberOfDays();
    }

    public void cancelBooking() {
        room.release();
        paymentStatus = "Cancelled";
    }

    public Room getRoom () {
        return room;
    }

    @Override
    public String toString () {
        return "Booking{" + "customer=" + customer + ", room=" + room + ", checkInDate='" + checkInDate + '\'' + ", checkOutDate='" + checkOutDate + '\'' + '}';
    }

    private int getNumberOfDays() {
        // Logic to calculate number of days (not implemented for simplicity)
        return 1; // Placeholder
    }
}
