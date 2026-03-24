import java.util.*;

// Reservation (from Use Case 5)
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
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("Inventory: " + inventory);
    }
}

// Booking Service
class BookingService {
    private Queue<Reservation> queue = new LinkedList<>();
    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> allocationMap = new HashMap<>();
    private InventoryService inventoryService;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Add request (FIFO)
    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    // Generate unique room ID
    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 2).toUpperCase() + "-" +
                    UUID.randomUUID().toString().substring(0, 4);
        } while (allocatedRoomIds.contains(id));
        return id;
    }

    // Process queue
    public void processBookings() {
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            System.out.println("\nProcessing: " + r.customerName);

            if (!inventoryService.isAvailable(r.roomType)) {
                System.out.println("No rooms available for " + r.roomType);
                continue;
            }

            // Atomic-like allocation
            String roomId = generateRoomId(r.roomType);

            allocatedRoomIds.add(roomId);

            allocationMap
                    .computeIfAbsent(r.roomType, k -> new HashSet<>())
                    .add(roomId);

            inventoryService.decrement(r.roomType);

            System.out.println("Reservation Confirmed!");
            System.out.println("Customer: " + r.customerName);
            System.out.println("Room Type: " + r.roomType);
            System.out.println("Room ID: " + roomId);
        }
    }

    public void displayAllocations() {
        System.out.println("\nAllocations: " + allocationMap);
    }
}

// Main class
public class book {
    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        // Simulating queue from Use Case 5
        bookingService.addRequest(new Reservation("Niharika", "Single"));
        bookingService.addRequest(new Reservation("Rahul", "Double"));
        bookingService.addRequest(new Reservation("Ananya", "Single"));
        bookingService.addRequest(new Reservation("Karan", "Suite"));
        bookingService.addRequest(new Reservation("Meera", "Single"));

        // Process bookings
        bookingService.processBookings();

        // Final state
        inventory.displayInventory();
        bookingService.displayAllocations();
    }
}