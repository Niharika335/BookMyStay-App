import java.util.*;

// Reservation class (represents guest request)
class Reservation {
    private String customerName;
    private String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation{Customer='" + customerName + "', RoomType='" + roomType + "'}";
    }
}

// Booking Request Queue Service
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request to queue (FIFO)
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added: " + reservation);
    }

    // View next request (without removing)
    public void peekRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            System.out.println("Next request in queue: " + requestQueue.peek());
        }
    }

    // Process request (remove from queue)
    public void processRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No requests to process.");
        } else {
            Reservation processed = requestQueue.poll();
            System.out.println("Processing request: " + processed);
        }
    }

    // Display all queued requests
    public void displayQueue() {
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("\nCurrent Booking Queue (FIFO Order):");
            for (Reservation r : requestQueue) {
                System.out.println(r);
            }
        }
    }
}

// Main Class
public class book {
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Adding booking requests (FIFO order)
        bookingQueue.addRequest(new Reservation("Niharika", "Single"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double"));
        bookingQueue.addRequest(new Reservation("Ananya", "Suite"));
        bookingQueue.addRequest(new Reservation("Karan", "Single"));

        // Display queue
        bookingQueue.displayQueue();

        // Peek next request
        bookingQueue.peekRequest();

        // Process requests one by one
        bookingQueue.processRequest();
        bookingQueue.processRequest();

        // Display remaining queue
        bookingQueue.displayQueue();
    }
}