import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean dropStudent(Student student) {
        return enrolledStudents.remove(student);
    }
}

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class Course_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        courses.add(new Course("CS101", "Java Programming", 20));
        courses.add(new Course("CS102", "Web Development", 15));
        courses.add(new Course("CS103", "Machine Learning", 10));
        courses.add(new Course("CS104", "Cyber Security", 12));
        courses.add(new Course("CS105", "VLSI Design", 8));

        while (true) {
            System.out.println("\n1. Enroll\n2. Drop\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enrollStudent(students, courses, scanner);
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records available.");
                    } else {
                        dropCourse(students, courses, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void enrollStudent(List<Student> students, List<Course> courses, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        if (!isValidStudentId(studentId)) {
            System.out.println("Invalid Student ID format! The Student ID must be of format VUYYBBBBRRRRRRR.");
            return;
        }
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentId, studentName);
        students.add(student);

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getTitle());
        }
        System.out.print("Enter course numbers to enroll (separated by spaces or newlines): ");
        String courseInput = scanner.nextLine();
        String[] courseNumbers = courseInput.split("[\\s]+");
        for (String courseNumberStr : courseNumbers) {
            try {
                int courseNumber = Integer.parseInt(courseNumberStr);
                if (courseNumber > 0 && courseNumber <= courses.size()) {
                    Course selectedCourse = courses.get(courseNumber - 1);
                    if (selectedCourse.enrollStudent(student)) {
                        System.out.println(student.getName() + " enrolled in " + selectedCourse.getTitle());
                    } else {
                        System.out.println("Failed to enroll " + student.getName() + " in " + selectedCourse.getTitle() + " (Course Full)");
                    }
                } else {
                    System.out.println("Invalid course number: " + courseNumber);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + courseNumberStr);
            }
        }
    }


    public static void dropCourse(List<Student> students, List<Course> courses, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(students, studentId);
        if (student != null) {
            System.out.println("Courses enrolled by " + student.getName() + ":");
            List<Course> enrolledCourses = new ArrayList<>();
            for (Course course : courses) {
                if (course.getEnrolledStudents().contains(student)) {
                    enrolledCourses.add(course);
                    System.out.println("- " + (enrolledCourses.size()) + ". " + course.getTitle());
                }
            }

            if (enrolledCourses.isEmpty()) {
                System.out.println("No courses enrolled by " + student.getName());
            } else {
                System.out.print("Enter course number to drop: ");
                int dropCourseNumber = scanner.nextInt();
                scanner.nextLine();
                if (dropCourseNumber > 0 && dropCourseNumber <= enrolledCourses.size()) {
                    Course dropCourse = enrolledCourses.get(dropCourseNumber - 1);
                    if (dropCourse.dropStudent(student)) {
                        System.out.println(student.getName() + " dropped from " + dropCourse.getTitle());
                    } else {
                        System.out.println(student.getName() + " is not enrolled in " + dropCourse.getTitle());
                    }
                } else {
                    System.out.println("Invalid course number.");
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }


    public static boolean isValidStudentId(String studentId) {
        // Check if the student ID matches the format VUYYBBBBRRRRRRR
        return studentId.matches("VU\\d{2}[A-Z]{4}\\d{7}");
    }

    public static Student findStudentById(List<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public static boolean studentHasCourses(Student student, List<Course> courses) {
        for (Course course : courses) {
            if (course.getEnrolledStudents().contains(student)) {
                return true;
            }
        }
        return false;
    }
}
