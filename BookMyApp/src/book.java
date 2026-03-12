/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management using HashMap
 * for the Hotel Booking System (Version 3.1).
 *
 * Version 3.1 (refactored from Use Case 2)
 * Author: OpenAI
 */

import java.util.HashMap;
import java.util.Map;

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

    // Initialize room type with available count
    public void addRoomType(String roomType, int count) {
        availability.put(roomType, count);
    }

    // Get availability for a room type
    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    // Update availability for a room type
    public void updateAvailability(String roomType, int change) {
        int current = availability.getOrDefault(roomType, 0);
        int updated = current + change;
        if (updated < 0) updated = 0; // Ensure non-negative availability
        availability.put(roomType, updated);
    }

    // Display all inventory
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " available");
        }
        System.out.println();
    }
}

// Application entry point
public class book {

    public static void main(String[] args) {

        System.out.println("*********************************************");
        System.out.println("        Welcome to Book My Stay App");
        System.out.println("                 Version 3.1");
        System.out.println("*********************************************\n");

        // Initialize rooms
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(singleRoom.getRoomType(), 5);
        inventory.addRoomType(doubleRoom.getRoomType(), 3);
        inventory.addRoomType(suiteRoom.getRoomType(), 2);

        // Display room details
        System.out.println("Room Details:\n");
        singleRoom.displayDetails();
        System.out.println();
        doubleRoom.displayDetails();
        System.out.println();
        suiteRoom.displayDetails();
        System.out.println();

        // Display centralized inventory
        inventory.displayInventory();

        // Example updates
        System.out.println("Booking 1 Single Room...");
        inventory.updateAvailability(singleRoom.getRoomType(), -1);

        System.out.println("Booking 1 Suite Room...");
        inventory.updateAvailability(suiteRoom.getRoomType(), -1);

        inventory.displayInventory();

        System.out.println("End of Use Case 3 demonstration.");
    }
}