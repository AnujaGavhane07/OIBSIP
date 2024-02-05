import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(String trainNumber, String trainName, String classType,
                       String dateOfJourney, String from, String to) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    // Getter methods...

    @Override
    public String toString() {
        return "Train Number: " + trainNumber +
                "\nTrain Name: " + trainName +
                "\nClass Type: " + classType +
                "\nDate of Journey: " + dateOfJourney +
                "\nFrom: " + from +
                "\nTo: " + to;
    }
}

public class OnlineReservationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers();
        login();
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));
        // Add more users as needed
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            showMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
            login();
        }
        
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Reservation");
        System.out.println("2. Cancellation");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                makeReservation();
                break;
            case 2:
                cancelReservation();
                break;
            case 3:
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showMenu();
        }
        
    }

    private static void makeReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        // Similar input for other reservation details

        Reservation reservation = new Reservation(trainNumber, "Sample Train", "AC", "2024-01-25", "CityA", "CityB");
        reservations.put("PNR123", reservation);

        System.out.println("Reservation successful!");
        System.out.println(reservation);

        showMenu();
        
    }
    
    private static void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PNR number: ");
        String pnr = scanner.nextLine();

        Reservation reservation = reservations.get(pnr);

        if (reservation != null) {
            System.out.println("Reservation details:");
            System.out.println(reservation);

            System.out.print("Confirm cancellation (OK/Cancel): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) {
                reservations.remove(pnr);
                System.out.println("Cancellation successful!");
            } else {
                System.out.println("Cancellation canceled.");
            }
        } else {
            System.out.println("Invalid PNR number. Please try again.");
            cancelReservation();
        }

        showMenu();
        scanner.close();
    }
}
