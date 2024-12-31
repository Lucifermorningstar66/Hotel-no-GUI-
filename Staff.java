public class Staff {
    private String name;
    private String position;
    private int staffID;

    public Staff(String name, String position, int staffID) {
        this.name = name;
        this.position = position;
        this.staffID = staffID;
    }

    public void viewStaffDetails() {
        System.out.println("Staff Name: " + name + ", Position: " + position + ", ID: " + staffID);
    }

    public int getStaffID () {
        return staffID;
    }
}


