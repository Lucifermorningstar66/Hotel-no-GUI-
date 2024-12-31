import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Hotel> hotels = new ArrayList<>();
    public static File hot = new File("Hotel\\Hotels.txt");
    private static ArrayList<Customer> customers = new ArrayList<>();
    public static File cus = new File("Hotel\\Customers.txt");
    private static ArrayList<Booking> bookings = new ArrayList<>();
    public static File bok = new File("Hotel\\Bookings.txt");
    private static ArrayList<Payment> payments = new ArrayList<>();
    public static File pay = new File("Hotel\\Payments.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*** Hotel Booking System ***");
            System.out.println("1. Add Hotel");
            System.out.println("2. Add Room");
            System.out.println("3. Add Staff");
            System.out.println("4. Add Customer");

            System.out.println("5. Book a Room");

            System.out.println("6. Show Hotels");
            System.out.println("7. Show Customers");
            System.out.println("8. Show Bookings");
            System.out.println("9. Show Payments");

            System.out.println("10. Remove Hotel");
            System.out.println("11. Remove Room");
            System.out.println("12. Remove Staff");
            System.out.println("13. Remove Customer");
            System.out.println("14. Remove Booking");

            System.out.println("15. Open Edit Menu");
            System.out.println("16. EXIT");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    //اضافه کردن هتل به داده های پروژه
                    System.out.print("Enter Hotel Name: ");
                    String hotelName = scanner.nextLine();
                    System.out.print("Enter Hotel Location: ");
                    String hotelLocation = scanner.nextLine();
                    Hotel hote = new Hotel(hotelName, hotelLocation);
                    hotels.add(hote);


                    try {
                        FileWriter writer = new FileWriter(hot.getAbsolutePath(), true);
                        if (!hot.exists()) {
                            hot.createNewFile();
                        }

                        writer.write("\n" + hote.toString());
                        writer.close();

                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }

                    System.out.println("Hotel added successfully!");
                    break;

                case 2:
                    //اضافه کردن اتاق به هتل که البته باید هتل از قبل در داده هایی که به صورت اضافه کردیم به پروژه وجود داشته باشه وگرنه فرایند انجام نمیشه
                    System.out.print("Enter Hotel Name to Add Room: ");
                    String targetHotel = scanner.nextLine();
                    Hotel hotel = findHotelByName(targetHotel);
                    if (hotel != null) {
                        System.out.print("Enter Room Number: ");
                        int roomNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Room Type: ");
                        String roomType = scanner.nextLine();
                        System.out.print("Enter Number of Beds: ");
                        int bedCount = scanner.nextInt();
                        System.out.print("Enter Room Price: ");
                        float price = scanner.nextFloat();
                        scanner.nextLine();
                        hotel.addRoom(new Room(roomNumber, roomType, bedCount, price));
                        System.out.println("Room added successfully!");
                    } else {
                        System.out.println("Hotel not found!");
                    }
                    break;

                case 3:
                    //برای ثبت کارکنان و ثبت اطلاعات اولیه اونها برای ساخت شی staff و نوشتن اون داخل فایل
                    System.out.print("Enter Hotel Name to Add Staff: ");
                    String hotelForStaff = scanner.nextLine();
                    Hotel staffHotel = findHotelByName(hotelForStaff);
                    if (staffHotel != null) {
                        System.out.print("Enter Staff Name: ");
                        String staffName = scanner.nextLine();
                        System.out.print("Enter Staff Position: ");
                        String position = scanner.nextLine();
                        System.out.print("Enter Staff ID: ");
                        int staffID = scanner.nextInt();
                        scanner.nextLine();
                        staffHotel.addStaff(new Staff(staffName, position, staffID));
                        System.out.println("Staff added successfully!");
                    } else {
                        System.out.println("Hotel not found!");
                    }
                    break;

                case 4:
                    //برای ایجاد و ثبت مشتری و گرفتن اطلاعات اولیه و نوشتن اون توی فایل
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Customer Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Customer Phone: ");
                    String phone = scanner.nextLine();
                    Customer cust = new Customer(customerName, email, phone);
                    customers.add(cust);

                    try {
                        FileWriter writer = new FileWriter(cus.getAbsolutePath(), true);
                        if (!cus.exists()) {
                            cus.createNewFile();
                        }

                        writer.write("\n" + cust.toString());
                        writer.close();

                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }

                    System.out.println("Customer added successfully!");
                    break;

                case 5:
                    //اطلاعات اولیه مشتری مثل اسم و اسم هتل و چک کردن که ایا اصن هتل اتاق خالی داره یا نع و سپس شماره اتاق مورد نیاز رو انتخاب میکنی تا رزرو کامل بشه
                    //در اخر هم مبلغ کلی محاسبه میشه و به فایل اضافه میشه
                    System.out.print("Enter Customer Name: ");
                    String bookingCustomer = scanner.nextLine();
                    Customer customer = findCustomerByName(bookingCustomer);
                    if (customer != null) {
                        System.out.print("Enter Hotel Name: ");
                        String bookingHotelName = scanner.nextLine();
                        Hotel bookingHotel = findHotelByName(bookingHotelName);
                        if (bookingHotel != null) {
                            List<Room> availableRooms = bookingHotel.getAvailableRooms();
                            if (!availableRooms.isEmpty()) {
                                System.out.println("Available Rooms:");
                                for (Room room : availableRooms) {
                                    System.out.println(room.getRoomInfo());
                                }
                                System.out.print("Enter Room Number to Book: ");
                                int roomNumberToBook = scanner.nextInt();
                                scanner.nextLine();
                                for (Room room : availableRooms) {
                                    if (room.getRoomNumber() == roomNumberToBook) {
                                        System.out.print("Enter Check-In Date: ");
                                        String checkIn = scanner.nextLine();
                                        System.out.print("Enter Check-Out Date: ");
                                        String checkOut = scanner.nextLine();
                                        room.reserve();
                                        Booking booking = new Booking(customer, room, checkIn, checkOut);
                                        Payment payment = new Payment(room.getRoomNumber(),booking.calculateTotalCost(),"Card to Card");
                                        customer.addBooking(booking);

                                        try {
                                            FileWriter writer = new FileWriter(bok.getAbsolutePath(), true);
                                            if (!bok.exists()) {
                                                bok.createNewFile();
                                            }

                                            writer.write("\n" + booking.toString());
                                            writer.close();

                                        } catch (IOException e2) {
                                            throw new RuntimeException(e2);
                                        }
                                        try {
                                            FileWriter writer1 = new FileWriter(pay.getAbsolutePath(), true);
                                            if (!pay.exists()) {
                                                pay.createNewFile();
                                            }

                                            writer1.write("\n" + payment.toString());
                                            writer1.close();

                                        } catch (IOException e2) {
                                            throw new RuntimeException(e2);
                                        }

                                        System.out.println("Room booked successfully!");
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("No rooms available!");
                            }
                        } else {
                            System.out.println("Hotel not found!");
                        }
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 6:
                    try {
                        Scanner scanner2 = new Scanner(hot);
                        while (scanner2.hasNextLine()) {
                            String line = scanner2.nextLine().trim();

                            if (line.startsWith("Hotel{name=") && line.contains(", location=")) {
                                int nameStart = line.indexOf("name=") + 6; // طول "name='"
                                int nameEnd = line.indexOf(", location=");
                                String hotelNa = line.substring(nameStart, nameEnd).trim();

                                int locationStart = line.indexOf("location=") + 10; // طول "location='"
                                int locationEnd = line.lastIndexOf("}");
                                String hotelLoc = line.substring(locationStart, locationEnd).trim();

                                System.out.println("Hotel Name: " + hotelNa);
                                System.out.println("Hotel Location: " + hotelLoc);
                                System.out.println("--------------------------");
                            } else {
                                System.out.println("Invalid line format: " + line);
                            }
                        }
                        scanner2.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }

                    break;

                case 7:
                    try {
                        Scanner scanner2 = new Scanner(cus);
                        while (scanner2.hasNextLine()) {
                            String line = scanner2.nextLine().trim();

                            // بررسی فرمت خط برای اطمینان از وجود اطلاعات معتبر
                            if (line.startsWith("Customer{") && line.contains(", email=") && line.contains(", phone=")) {
                                // استخراج نام مشتری
                                int nameStart = line.indexOf("name='") + 6; // طول "name='"
                                int nameEnd = line.indexOf(", email=");
                                String customerNam = line.substring(nameStart, nameEnd).trim();

                                // استخراج ایمیل مشتری
                                int emailStart = line.indexOf("email='") + 7; // طول "email='"
                                int emailEnd = line.indexOf(", phone=");
                                String customerEmail = line.substring(emailStart, emailEnd).trim();

                                // استخراج شماره تماس مشتری
                                int phoneStart = line.indexOf("phone='") + 7; // طول "phone='"
                                int phoneEnd = line.lastIndexOf("}");
                                String customerPhone = line.substring(phoneStart, phoneEnd).trim();

                                // چاپ اطلاعات مشتری
                                System.out.println("Customer Name: " + customerNam);
                                System.out.println("Customer Email: " + customerEmail);
                                System.out.println("Customer Phone: " + customerPhone);
                                System.out.println("--------------------------");
                            } else {
                                System.out.println("Invalid line format: " + line);
                            }
                        }
                        scanner2.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }

                    break;

                case 8:
                    try {
                        Scanner scanner2 = new Scanner(bok); // فایل رزروها
                        while (scanner2.hasNextLine()) {
                            String line = scanner2.nextLine().trim();

                            // بررسی قالب خط برای اطمینان از وجود اطلاعات معتبر
                            if (line.startsWith("Booking{") && line.contains(", customer=") && line.contains(", room=")
                                    && line.contains(", checkInDate='") && line.contains(", checkOutDate='")) {
                                // استخراج اطلاعات مشتری
                                int customerStart = line.indexOf("customer=") + 9; // طول "customer="
                                int customerEnd = line.indexOf(", room=");
                                String custome = line.substring(customerStart, customerEnd).trim();

                                // استخراج اطلاعات اتاق
                                int roomStart = line.indexOf("room=") + 5; // طول "room="
                                int roomEnd = line.indexOf(", checkInDate='");
                                String room = line.substring(roomStart, roomEnd).trim();

                                // استخراج تاریخ ورود
                                int checkInStart = line.indexOf("checkInDate='") + 14; // طول "checkInDate='"
                                int checkInEnd = line.indexOf("', checkOutDate='");
                                String checkInDate = line.substring(checkInStart, checkInEnd).trim();

                                // استخراج تاریخ خروج
                                int checkOutStart = line.indexOf("checkOutDate='") + 15; // طول "checkOutDate='"
                                int checkOutEnd = line.lastIndexOf("}");
                                String checkOutDate = line.substring(checkOutStart, checkOutEnd).trim();

                                // چاپ اطلاعات رزرو
                                System.out.println("Customer: " + custome);
                                System.out.println("Room: " + room);
                                System.out.println("Check-in Date: " + checkInDate);
                                System.out.println("Check-out Date: " + checkOutDate);
                                System.out.println("--------------------------");
                            } else {
                                System.out.println("Invalid line format: " + line);
                            }
                        }
                        scanner2.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }

                    break;

                case 9:
                    try {
                        Scanner scanner2 = new Scanner(pay);

                        while (scanner2.hasNextLine()) {
                            String line = scanner2.nextLine().trim();

                            if (line.startsWith("Payment{") && line.contains(", amount=") && line.contains(", paymentMethod=")) {
                                // استخراج اطلاعات room
                                int roomStart = line.indexOf("room=") + 5; // طول "room="
                                int roomEnd = line.indexOf(", amount=");
                                String roooom = line.substring(roomStart, roomEnd).trim();
                                int rooom = Integer.parseInt(roooom);

                                // استخراج اطلاعات amount
                                int amountStart = line.indexOf("amount=") + 7; // طول "amount="
                                int amountEnd = line.indexOf(", paymentMethod=");
                                float amount = Float.parseFloat((line.substring(amountStart, amountEnd).trim()));

                                // استخراج اطلاعات paymentMethod
                                int methodStart = line.indexOf("paymentMethod='") + 15; // طول "paymentMethod='"
                                int methodEnd = line.lastIndexOf("'");
                                String paymentMethod = line.substring(methodStart, methodEnd).trim();

                                // ایجاد شیء Payment و چاپ آن
                                Payment payment = new Payment(rooom, amount, paymentMethod);
                                System.out.println(payment);
                            } else {
                                System.out.println("Invalid line format: " + line);
                            }
                        }
                        scanner2.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }

                    break;

                case 10:
                    System.out.print("Enter Hotel Name to Remove: ");
                    String hotelToRemove = scanner.nextLine();
                    Hotel hotelFound = findHotelByName(hotelToRemove);
                    if (hotelFound != null) {
                        hotels.remove(hotelFound);
                        System.out.println("Hotel removed successfully!");
                    } else {
                        System.out.println("Hotel not found!");
                    }
                    break;

                case 11:
                    System.out.print("Enter Hotel Name to Remove Room From: ");
                    String hotelForRoomRemoval = scanner.nextLine();
                    Hotel roomHotel = findHotelByName(hotelForRoomRemoval);
                    if (roomHotel != null) {
                        System.out.print("Enter Room Number to Remove: ");
                        int roomNumberToRemove = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        boolean roomRemoved = false;
                        for (Room room : roomHotel.getAvailableRooms()) {
                            if (room.getRoomNumber() == roomNumberToRemove) {
                                roomHotel.getAvailableRooms().remove(room);
                                roomRemoved = true;
                                break;
                            }
                        }
                        if (roomRemoved) {
                            System.out.println("Room removed successfully!");
                        } else {
                            System.out.println("Room not found or not available!");
                        }
                    } else {
                        System.out.println("Hotel not found!");
                    }
                    break;

                case 12:
                    System.out.print("Enter Hotel Name to Remove Staff From: ");
                    String hotelForStaffRemoval = scanner.nextLine();
                    Hotel staffRemovalHotel = findHotelByName(hotelForStaffRemoval);
                    if (staffRemovalHotel != null) {
                        System.out.print("Enter Staff ID to Remove: ");
                        int staffIDToRemove = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        boolean staffRemoved = false;
                        for (Staff staff : staffRemovalHotel.getStaff()) {
                            if (staff.getStaffID() == staffIDToRemove) {
                                staffRemovalHotel.getStaff().remove(staff);
                                staffRemoved = true;
                                break;
                            }
                        }
                        if (staffRemoved) {
                            System.out.println("Staff removed successfully!");
                        } else {
                            System.out.println("Staff not found!");
                        }
                    } else {
                        System.out.println("Hotel not found!");
                    }
                    break;

                case 13:
                    System.out.print("Enter Customer Name to Remove: ");
                    String customerToRemove = scanner.nextLine();
                    Customer customerFound = findCustomerByName(customerToRemove);
                    if (customerFound != null) {
                        customers.remove(customerFound);
                        System.out.println("Customer removed successfully!");
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 14:
                    System.out.print("Enter Customer Name to Remove Booking: ");
                    String customerForBookingRemoval = scanner.nextLine();
                    Customer customerForBooking = findCustomerByName(customerForBookingRemoval);
                    if (customerForBooking != null) {
                        System.out.print("Enter Room Number for Booking to Remove: ");
                        int roomForBookingRemoval = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        boolean bookingRemoved = false;
                        for (Booking booking : customerForBooking.getBookingHistory()) {
                            if (booking.getRoom().getRoomNumber() == roomForBookingRemoval) {
                                customerForBooking.getBookingHistory().remove(booking);
                                booking.getRoom().release();
                                bookingRemoved = true;
                                break;
                            }
                        }
                        if (bookingRemoved) {
                            System.out.println("Booking removed successfully!");
                        } else {
                            System.out.println("Booking not found!");
                        }
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;


                case 16:
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 16);

        scanner.close();
    }

    private static Hotel findHotelByName(String name) {

        try {
            Scanner scanner3 = new Scanner(hot);
            while (scanner3.hasNextLine()) {
                String line = scanner3.nextLine().trim();

                if (line.startsWith("Hotel{name=") && line.contains(", location=")) {
                    int nameStart = line.indexOf("name=") + 6; // طول "name='"
                    int nameEnd = line.indexOf(", location=");
                    String hotelNa = line.substring(nameStart, nameEnd).trim();

                    int locationStart = line.indexOf("location=") + 10; // طول "location='"
                    int locationEnd = line.lastIndexOf("}");
                    String hotelLoc = line.substring(locationStart, locationEnd).trim();

                    Hotel hotel = new Hotel(hotelNa,hotelLoc);
                    hotels.add(hotel);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            scanner3.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        for (Hotel hotel : hotels) {
            if (hotel.getName().contains(name)) {
                return hotel;
            }
        }
        return null;
    }

    private static Customer findCustomerByName(String name) {

        try {
            Scanner scanner3 = new Scanner(cus);
            while (scanner3.hasNextLine()) {
                String line = scanner3.nextLine().trim();

                // بررسی فرمت خط برای اطمینان از وجود اطلاعات معتبر
                if (line.startsWith("Customer{") && line.contains(", email=") && line.contains(", phone=")) {
                    // استخراج نام مشتری
                    int nameStart = line.indexOf("name='") + 6; // طول "name='"
                    int nameEnd = line.indexOf(", email=");
                    String customerNam = line.substring(nameStart, nameEnd).trim();

                    // استخراج ایمیل مشتری
                    int emailStart = line.indexOf("email='") + 7; // طول "email='"
                    int emailEnd = line.indexOf(", phone=");
                    String customerEmail = line.substring(emailStart, emailEnd).trim();

                    // استخراج شماره تماس مشتری
                    int phoneStart = line.indexOf("phone='") + 7; // طول "phone='"
                    int phoneEnd = line.lastIndexOf("}");
                    String customerPhone = line.substring(phoneStart, phoneEnd).trim();

                    Customer customer = new Customer(customerNam, customerEmail, customerPhone);
                    customers.add(customer);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            scanner3.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        for (Customer customer : customers) {
            if (customer.getName().contains(name)) {
                return customer;
            }
        }
        return null;
    }
}
