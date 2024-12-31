import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private String phone;
    private ArrayList<Booking> bookingHistory = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    public String getName () {
        return name;
    }

    public ArrayList<Booking> getBookingHistory () {
        return bookingHistory;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public String toString () {
        return "Customer{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + '}';
    }

    public void viewBookingHistory() {
        for (Booking booking : bookingHistory) {
            System.out.println(booking);
        }
    }
}
