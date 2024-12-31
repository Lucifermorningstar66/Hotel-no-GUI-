import java.util.ArrayList;

public class Hotel {
    private String name;
    private String location;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();
    private float rating;
    private ArrayList<Review> reviews = new ArrayList<>();

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName () {
        return name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    public ArrayList<Staff> getStaff () {
        return staff;
    }

    public void calculateOverallRating() {
        float totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        this.rating = totalRating / reviews.size();
    }

    @Override
    public String toString () {
        return "Hotel{" + "name='" + name + ", location='" + location + '}';
    }

    public String getHotelInfo() {
        return "Hotel Name: " + name + ", Location: " + location + ", Rating: " + rating;
    }
}


