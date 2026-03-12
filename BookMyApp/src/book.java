/**
 * UseCase4RoomSearch
 *
 * Demonstrates read-only room search using centralized inventory for
 * the Hotel Booking System (Version 4.0).
 *
 * Version 4.0
 * Author: OpenAI
 */

import java.util.*;

// Abstract Room class
abstract class Room {
    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;
    protected double sizeSqMeters;

    public Room(String roomType, int numberOfBeds, double pricePerNight, double sizeSqMeters) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.sizeSqMeters = sizeSqMeters;
    }

    public abstract void displayDetails();

    public String getRoomType() {
        return roomType;
    }
}

// Concrete room types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0, 20.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + sizeSqMeters + " sqm");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0, 35.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + sizeSqMeters + " sqm");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 200.0, 60.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + sizeSqMeters + " sqm");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}

// Centralized inventory management
class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        availability.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = availability.getOrDefault(roomType, 0);
        int updated = current + change;
        if (updated < 0) updated = 0;
        availability.put(roomType, updated);
    }

    public Map<String, Integer> getInventorySnapshot() {
        // Return a shallow copy to prevent modifications
        return new HashMap<>(availability);
    }
}

// Read-only search service
class SearchService {

    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    // Display only available rooms
    public void searchAvailableRooms() {
        System.out.println("Available Rooms for Booking:\n");
        for (Room room : rooms) {
            int availableCount = inventory.getAvailability(room.getRoomType());
            if (availableCount > 0) {
                room.displayDetails();
                System.out.println("Available: " + availableCount + "\n");
            }
        }
    }
}

// Application entry point
public class book {

    public static void main(String[] args) {

        System.out.println("*********************************************");
        System.out.println("        Welcome to Book My Stay App");
        System.out.println("                 Version 4.0");
        System.out.println("*********************************************\n");

        // Initialize rooms
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        List<Room> rooms = Arrays.asList(singleRoom, doubleRoom, suiteRoom);

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(singleRoom.getRoomType(), 5);
        inventory.addRoomType(doubleRoom.getRoomType(), 3);
        inventory.addRoomType(suiteRoom.getRoomType(), 0); // Suite unavailable

        // Search service
        SearchService searchService = new SearchService(inventory, rooms);

        // Perform room search (read-only)
        searchService.searchAvailableRooms();

        System.out.println("End of Use Case 4 demonstration.");
    }
}
