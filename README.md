User Registration System

Project Overview

This project is a Java-based registration system that processes user data from a text file. It demonstrates professional error handling and logging practices as taught in Module 10 (Logging) and Module 11 (Exceptions).


Key Features

1. Robust Exception Handling (Module 11)
   
The system is designed to "never crash." It uses a dual-layer try-catch strategy:

File Level: Uses Try-with-resources to ensure the BufferedReader is closed automatically, preventing memory leaks.

Row Level: Each line is processed in a separate try-catch block. If one user has a bad email or age, the system logs a WARNING and moves to the next line instead of terminating.

2. Custom Exception Hierarchy

We implemented a structured hierarchy to categorize errors:

RegistrationException (Base Checked Exception)

InvalidEmailException (For missing '@', empty fields, or bad domains)

InvalidAgeException (For users under 18)

3. Professional Logging (Module 10)

Instead of using System.out.println, we use java.util.logging.Logger with specific levels:

INFO: Logged for successful registrations and process completion.

WARNING: Logged when a specific line is skipped due to validation errors (avoids "Swallowing Exceptions").

SEVERE: Logged for critical failures, such as the input file not being found.


Project Structure

Main.java: The entry point.

RegistrationSystem.java: Contains the logic for file reading and the parseAndValidate method.

User.java: The data model.

RegistrationException.java: Custom exception classes.


How to Run

Ensure you have a users.txt file in the root directory.

The file format should be: ID, Email, Age (e.g., 1, test@example.com, 25).

Compile and run the Main class.

Observe the console for timestamped logs indicating the success or failure of each record.
