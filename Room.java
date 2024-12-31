import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private String type;
    private boolean isAvailable;
    private int bedCount;
    private float price;
    private ArrayList<String> features = new ArrayList<>();

    public Room(int roomNumber, String type, int bedCount, float price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.bedCount = bedCount;
        this.price = price;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }

    public float getPrice () {
        return price;
    }

    public int getRoomNumber () {
        return roomNumber;
    }

    public void addFeature(String feature) {
        features.add(feature);
    }

    public String getRoomInfo() {
        return "Room Number: " + roomNumber + ", Type: " + type + ", Price: " + price + ", Available: " + isAvailable;
    }
}
