 import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}

class Guest {
    private String name;
    private String phoneNumber;

    public Guest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Reservation {
    private Room room;
    private Guest guest;

    public Reservation(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
        room.occupy();
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void checkOut() {
        room.vacate();
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public Hotel(int numberOfRooms) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public Room getAvailableRoom() {
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                return room;
            }
        }
        return null; // No available rooms
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                System.out.println("Room " + room.getRoomNumber());
            }
        }
    }

    public void makeReservation(Guest guest) {
        Room availableRoom = getAvailableRoom();
        if (availableRoom != null) {
            Reservation reservation = new Reservation(availableRoom, guest);
            reservations.add(reservation);
            System.out.println("Reservation successful. Room number: " + availableRoom.getRoomNumber());
        } else {
            System.out.println("Sorry, no available rooms.");
        }
    }

    public void displayGuests() {
        System.out.println("Guests:");
        for (Reservation reservation : reservations) {
            System.out.println("Guest: " + reservation.getGuest().getName() +
                    ", Room: " + reservation.getRoom().getRoomNumber());
        }
    }

    public void checkOut(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber) {
                reservation.checkOut();
                reservations.remove(reservation);
                System.out.println("Check-out successful. Room number: " + roomNumber);
                return;
            }
        }
        System.out.println("Room number " + roomNumber + " not found or not occupied.");
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Initialize the hotel with 10 rooms

        while (true) {
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Display Guests");
            System.out.println("4. Check Out");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter guest phone number: ");
                    String phoneNumber = scanner.next();
                    Guest guest = new Guest(guestName, phoneNumber);
                    hotel.makeReservation(guest);
                    break;
                case 3:
                    hotel.displayGuests();
                    break;
                case 4:
                    System.out.print("Enter room number to check out: ");
                    int roomNumber = scanner.nextInt();
                    hotel.checkOut(roomNumber);
                    break;
                case 5:
                    System.out.println("Exiting the Hotel Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

