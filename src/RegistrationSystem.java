import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RegistrationSystem {
    private static final Logger logger = Logger.getLogger(RegistrationSystem.class.getName());
    private final List<User> validUsers = new ArrayList<>();

    public void processRegistrationFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    User user = parseAndValidate(line);
                    validUsers.add(user);
                    logger.info("User registered: " + user);
                } catch (RegistrationException | NumberFormatException e) {
                    logger.warning("Skipped line [" + line + "] -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.severe("Critical file error: " + e.getMessage());
        } finally {
            logger.info("Process completed. Total records saved: " + validUsers.size());
        }
    }
    private User parseAndValidate(String line) throws RegistrationException {
        String[] parts = line.split(",");
        if (parts.length < 3) {
            throw new RegistrationException("Invalid line format: missing fields.");
        }
        int id = Integer.parseInt(parts[0].trim());
        String email = validateEmail(parts[1]);
        int age;
        try {
            age = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new RegistrationException("Age is not a valid number.");
        }
        if (age < 18) {
            throw new InvalidAgeException("Age restriction: must be 18 or older.");
        }
        return new User(id, email, age);
    }

    private String validateEmail(String rawEmail) throws InvalidEmailException {
        String email = rawEmail.trim();
        if (email.isEmpty()) throw new InvalidEmailException("Email cannot be empty.");
        if (!email.contains("@")) throw new InvalidEmailException("Email must contain @.");
        if (!email.endsWith(".com") && !email.endsWith(".net") && !email.endsWith(".org")) {
            throw new InvalidEmailException("Invalid email domain.");
        }
        return email;
    }
}