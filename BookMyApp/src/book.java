import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    String customerName;
    String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) throws InvalidBookingException {
        int count = inventory.get(roomType);
        if (count <= 0) {
            throw new InvalidBookingException("Inventory cannot go negative for " + roomType);
        }
        inventory.put(roomType, count - 1);
    }
}

// Validator
class BookingValidator {

    public static void validate(Reservation r, InventoryService inventory)
            throws InvalidBookingException {

        if (r.customerName == null || r.customerName.trim().isEmpty()) {
            throw new InvalidBookingException("Customer name cannot be empty");
        }

        if (!inventory.isValidRoomType(r.roomType)) {
            throw new InvalidBookingException("Invalid room type: " + r.roomType);
        }

        if (!inventory.isAvailable(r.roomType)) {
            throw new InvalidBookingException("No rooms available for " + r.roomType);
        }
    }
}

// Booking Service
class BookingService {

    private InventoryService inventory;

    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void confirmBooking(Reservation r) {
        try {
            // Validation (fail-fast)
            BookingValidator.validate(r, inventory);

            // If valid → proceed
            inventory.decrement(r.roomType);

            System.out.println("Booking confirmed for " + r.customerName +
                    " (" + r.roomType + ")");

        } catch (InvalidBookingException e) {
            // Graceful error handling
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

// Main class
public class book {
    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        BookingService service = new BookingService(inventory);

        // Valid booking
        service.confirmBooking(new Reservation("Niharika", "Single"));

        // Invalid room type
        service.confirmBooking(new Reservation("Rahul", "Deluxe"));

        // No availability case
        service.confirmBooking(new Reservation("Ananya", "Suite"));
        service.confirmBooking(new Reservation("Karan", "Suite")); // should fail

        // Empty name
        service.confirmBooking(new Reservation("", "Double"));
    }
}