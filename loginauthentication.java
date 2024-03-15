import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class loginauthentication {
    // Database to store user credentials
    private static HashMap<String, String> users = new HashMap<>();

    // Function to register a new user
    private static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = hashPassword(password);
        users.put(username, hashedPassword);
        System.out.println("Registration successful!");
    }

    // Function to hash the password using SHA-256 algorithm
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to login
    private static boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("Username does not exist. Please register.");
            return false;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String hashedPassword = users.get(username);
        String inputHashedPassword = hashPassword(password);
        if (hashedPassword.equals(inputHashedPassword)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }

    // Function to access secured page
    private static void securedPage() {
        System.out.println("Welcome to the secured page!");
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    if (loginUser()) {
                        securedPage();
                    }
                    break;
                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
