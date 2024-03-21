# Course Enrollment and Dropping System

This project is a simple command-line application that allows students to enroll in and drop courses. It provides functionalities such as enrolling in multiple courses at once, dropping courses, and validating student IDs.

## Features

- **Enroll Student:** Allows students to enroll in courses by entering their student ID, name, and selecting the courses they want to enroll in.
- **Drop Course:** Allows students to drop courses they are enrolled in by entering their student ID and selecting the course they want to drop.
- **Course Listing:** Displays available courses along with their capacities and the number of enrolled students.
- **Student Validation:** Validates student IDs to ensure they match the required format.
- **Error Handling:** Provides error messages for invalid inputs and ensures proper handling of edge cases.

## Usage

1. **Enroll:** Choose option 1 to enroll in courses. Enter the student ID, name, and course numbers separated by spaces or newlines when prompted.
2. **Drop:** Choose option 2 to drop courses. Enter the student ID when prompted, and the enrolled courses will be displayed with corresponding numbers. Enter the course number to drop.
3. **Exit:** Choose option 3 to exit the program.

## Student ID Format

The student ID must be of the format VUYYBBBBRRRRRRR (Coz this is the student ID format used in our college), where:
- VU: University code (fixed characters)
- YY: Year (2 digits)
- BBBB: Branch code (4 uppercase letters)
- RRRRRRR: Roll number (7 digits)

Example: VU21CSEN0100057

## Requirements

- Java Development Kit (JDK) installed
- Command-line interface (Terminal or Command Prompt)

## How to Run

1. Clone or download the repository to your local machine.
2. Open a command-line interface and navigate to the project directory.
3. Compile the Java files using the following command:
```
javac Course_Main.java
```
4. Run the compiled program using the following command:
```
java Course_Main
```
5. Follow the on-screen prompts to enroll in or drop courses.

## Notes

- Adjust the course listings and capacities as needed in the `Course_Main.java` file.
- Modify the student ID validation logic in the `isValidStudentId` method as per your requirements.
