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
}