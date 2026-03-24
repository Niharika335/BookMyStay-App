import java.util.*;

// Reservation class (confirmed booking)
class Reservation {
    private String reservationId;
    private String customerName;
    private String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "ReservationID: " + reservationId +
                ", Customer: " + customerName +
                ", RoomType: " + roomType;
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
        System.out.println("Added to history: " + reservation);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Booking Report Service
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(List<Reservation> history) {
        System.out.println("\n--- Booking History ---");
        for (Reservation r : history) {
            System.out.println(r);
        }
    }

    // Generate summary report
    public void generateSummary(List<Reservation> history) {
        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : history) {
            roomCount.put(
                    r.getRoomType(),
                    roomCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\n--- Booking Summary Report ---");
        for (Map.Entry<String, Integer> entry : roomCount.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Booked: " + entry.getValue());
        }
    }
}

// Main class
public class book {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings (from Use Case 6)
        history.addReservation(new Reservation("SI-1001", "Niharika", "Single"));
        history.addReservation(new Reservation("DO-1002", "Rahul", "Double"));
        history.addReservation(new Reservation("SI-1003", "Ananya", "Single"));
        history.addReservation(new Reservation("SU-1004", "Karan", "Suite"));

        // Admin views all bookings
        reportService.displayAllBookings(history.getAllReservations());

        // Admin generates summary
        reportService.generateSummary(history.getAllReservations());
    }
}