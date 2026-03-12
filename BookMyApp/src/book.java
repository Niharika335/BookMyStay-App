/**
 * UseCase2RoomInitialization
 *
 * This class demonstrates basic room type initialization and static availability
 * for the Hotel Booking System (Version 2.1). It illustrates abstraction, inheritance,
 * and polymorphism in domain modeling.
 *
 * Version 2.1 (refactored from Use Case 1)
 * Author: OpenAI
 */

abstract class Room {
    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;
    protected double sizeSqMeters;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight, double sizeSqMeters) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.sizeSqMeters = sizeSqMeters;
    }

    // Abstract method to display room details
    public abstract void displayDetails();
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

// Application entry point
public class book {

    public static void main(String[] args) {

        System.out.println("*********************************************");
        System.out.println("        Welcome to Book My Stay App");
        System.out.println("                 Version 2.1");
        System.out.println("*********************************************\n");

        // Static availability variables
        int availableSingleRooms = 5;
        int availableDoubleRooms = 3;
        int availableSuites = 2;

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room details and availability
        System.out.println("Available Rooms:\n");

        singleRoom.displayDetails();
        System.out.println("Available: " + availableSingleRooms + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + availableDoubleRooms + "\n");

        suiteRoom.displayDetails();
        System.out.println("Available: " + availableSuites + "\n");

        System.out.println("End of Use Case 2 demonstration.");
    }
}