import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

// Main class
public class EducationSystem {
    public static void main(String[] args) {
        System.out.println("\n\n");
        printBanner();
        
        EducationService service = new EducationService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        // Initialize sample data
        service.initializeSampleData();
        
        while (running) {
            printMainMenu();
            System.out.print("\nEnter your choice (0-9): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1 -> service.studentManagement();
                    case 2 -> service.courseManagement();
                    case 3 -> service.instructorManagement();
                    case 4 -> service.enrollmentManagement();
                    case 5 -> service.assignmentManagement();
                    case 6 -> service.gradeManagement();
                    case 7 -> service.searchManagement();
                    case 8 -> service.viewStatistics();
                    case 9 -> service.viewAllData();
                    case 0 -> {
                        System.out.println("\nExiting Education Automation System...");
                        System.out.println("Thank you for using the system!");
                        running = false;
                    }
                    default -> System.out.println("\nInvalid choice! Please enter 0-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPlease enter a valid number!");
            }
        }
        
        scanner.close();
        System.out.println("\nProgram terminated successfully.");
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void printBanner() {
        System.out.println("\u001B[36m");
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                      ║");
        System.out.println("║                     EDUCATION AUTOMATION SYSTEM - TERMINAL VERSION                                   ║");
        System.out.println("║                                                                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("\u001B[0m");
        System.out.println();
    }
    
    public static void printMainMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      MAIN MENU - EDUCATION SYSTEM                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                                      ║");
        System.out.println("║   1. 📝 Student Management                                           ║");
        System.out.println("║   2. 📚 Course Management                                            ║");
        System.out.println("║   3. 👨‍🏫 Instructor Management                                        ║");
        System.out.println("║   4. 🎓 Enrollment Management                                        ║");
        System.out.println("║   5. 📝 Assignment Management                                        ║");
        System.out.println("║   6. 📈 Grade Management                                             ║");
        System.out.println("║   7. 🔍 Search Management                                            ║");
        System.out.println("║   8. 📊 System Statistics                                            ║");
        System.out.println("║   9. 📋 View All Data                                                ║");
        System.out.println("║   0. 🚪 Exit System                                                  ║");
        System.out.println("║                                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
    }
    
    public static void printSectionTitle(String title) {
        int width = 70;
        int padding = (width - title.length() - 4) / 2;
        
        System.out.println("\n╔" + "═".repeat(width) + "╗");
        System.out.print("║");
        System.out.print(" ".repeat(padding));
        System.out.print(" " + title + " ");
        System.out.print(" ".repeat(width - padding - title.length() - 3));
        System.out.println("║");
        System.out.println("╚" + "═".repeat(width) + "╝");
    }
    
    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignore
        }
    }
}

// Model classes
class Student {
    private static int idCounter = 1;
    private int studentId;
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    
    public Student(String firstName, String lastName, String email, String phone) {
        this.studentId = idCounter++;
        this.studentNumber = "STU" + String.format("%04d", studentId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }
    
    public int getStudentId() { return studentId; }
    public String getStudentNumber() { return studentNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCreatedAt() { 
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-10s │ %-15s │ %-15s │ %-25s │ %-15s │ %-19s │",
            studentId, studentNumber, firstName, lastName, email, phone, getCreatedAt());
    }
    
    public static String getHeader() {
        return "┌────────┬────────────┬─────────────────┬─────────────────┬───────────────────────────┬─────────────────┬─────────────────────┐\n" +
               "│   ID   │ Student No │ First Name      │ Last Name       │ Email                    │ Phone           │ Created At          │\n" +
               "├────────┼────────────┼─────────────────┼─────────────────┼───────────────────────────┼─────────────────┼─────────────────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴────────────┴─────────────────┴─────────────────┴───────────────────────────┴─────────────────┴─────────────────────┘";
    }
}

class Course {
    private static int idCounter = 1;
    private int courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private int credits;
    private String department;
    private LocalDateTime createdAt;
    
    public Course(String courseCode, String courseName, String description, int credits, String department) {
        this.courseId = idCounter++;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.department = department;
        this.createdAt = LocalDateTime.now();
    }
    
    public int getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getDescription() { return description; }
    public int getCredits() { return credits; }
    public String getDepartment() { return department; }
    public String getCreatedAt() { 
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDescription(String description) { this.description = description; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setDepartment(String department) { this.department = department; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-12s │ %-25s │ %-35s │ %-7d │ %-15s │ %-19s │",
            courseId, courseCode, courseName, 
            description.length() > 35 ? description.substring(0, 32) + "..." : description,
            credits, department, getCreatedAt());
    }
    
    public static String getHeader() {
        return "┌────────┬──────────────┬───────────────────────────┬───────────────────────────────────────┬─────────┬─────────────────┬─────────────────────┐\n" +
               "│   ID   │ Course Code  │ Course Name               │ Description                          │ Credits │ Department      │ Created At          │\n" +
               "├────────┼──────────────┼───────────────────────────┼───────────────────────────────────────┼─────────┼─────────────────┼─────────────────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴──────────────┴───────────────────────────┴───────────────────────────────────────┴─────────┴─────────────────┴─────────────────────┘";
    }
}

class Instructor {
    private static int idCounter = 1;
    private int instructorId;
    private String identifier;
    private String name;
    private String status;
    private String location;
    private String contact;
    private String email;
    private String specialization;
    private LocalDateTime assignedSince;
    private int assignedCourseId = -1;
    
    public Instructor(String name, String contact, String email, String specialization) {
        this.instructorId = idCounter++;
        this.identifier = "INS" + String.format("%04d", instructorId);
        this.name = name;
        this.status = "ACTIVE";
        this.location = "Not Specified";
        this.contact = contact;
        this.email = email;
        this.specialization = specialization;
        this.assignedSince = LocalDateTime.now();
    }
    
    public int getInstructorId() { return instructorId; }
    public String getIdentifier() { return identifier; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getSpecialization() { return specialization; }
    public String getAssignedSince() { 
        return assignedSince.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    public int getAssignedCourseId() { return assignedCourseId; }
    
    public void setName(String name) { this.name = name; }
    public void setStatus(String status) { this.status = status; }
    public void setLocation(String location) { this.location = location; }
    public void setContact(String contact) { this.contact = contact; }
    public void setEmail(String email) { this.email = email; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setAssignedCourseId(int assignedCourseId) { this.assignedCourseId = assignedCourseId; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-10s │ %-25s │ %-10s │ %-15s │ %-15s │ %-25s │ %-15s │ %-19s │",
            instructorId, identifier, name, status, location, contact, email, specialization, getAssignedSince());
    }
    
    public static String getHeader() {
        return "┌────────┬────────────┬───────────────────────────┬────────────┬─────────────────┬─────────────────┬───────────────────────────┬─────────────────┬─────────────────────┐\n" +
               "│   ID   │ Identifier │ Name                      │ Status     │ Location        │ Contact         │ Email                    │ Specialization  │ Assigned Since      │\n" +
               "├────────┼────────────┼───────────────────────────┼────────────┼─────────────────┼─────────────────┼───────────────────────────┼─────────────────┼─────────────────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴────────────┴───────────────────────────┴────────────┴─────────────────┴─────────────────┴───────────────────────────┴─────────────────┴─────────────────────┘";
    }
}

class Enrollment {
    private static int idCounter = 1;
    private int enrollmentId;
    private String referenceId;
    private String description;
    private LocalDateTime date;
    private String status;
    private String remarks;
    private int studentId;
    private int courseId;
    
    public Enrollment(int studentId, int courseId, String description) {
        this.enrollmentId = idCounter++;
        this.referenceId = "ENR" + String.format("%04d", enrollmentId);
        this.studentId = studentId;
        this.courseId = courseId;
        this.description = description;
        this.date = LocalDateTime.now();
        this.status = "PENDING";
        this.remarks = "Awaiting approval";
    }
    
    public int getEnrollmentId() { return enrollmentId; }
    public String getReferenceId() { return referenceId; }
    public String getDescription() { return description; }
    public String getDate() { 
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    public String getStatus() { return status; }
    public String getRemarks() { return remarks; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-10s │ %-30s │ %-19s │ %-10s │ %-30s │ %-8d │ %-8d │",
            enrollmentId, referenceId, 
            description.length() > 30 ? description.substring(0, 27) + "..." : description,
            getDate(), status,
            remarks.length() > 30 ? remarks.substring(0, 27) + "..." : remarks,
            studentId, courseId);
    }
    
    public static String getHeader() {
        return "┌────────┬────────────┬────────────────────────────────┬─────────────────────┬────────────┬────────────────────────────────┬──────────┬──────────┐\n" +
               "│   ID   │ Reference  │ Description                   │ Date                │ Status     │ Remarks                        │ Student  │ Course   │\n" +
               "│        │            │                               │                     │            │                                │   ID     │   ID     │\n" +
               "├────────┼────────────┼────────────────────────────────┼─────────────────────┼────────────┼────────────────────────────────┼──────────┼──────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴────────────┴────────────────────────────────┴─────────────────────┴────────────┴────────────────────────────────┴──────────┴──────────┘";
    }
}

class Assignment {
    private static int idCounter = 1;
    private int assignmentId;
    private String referenceId;
    private String title;
    private String description;
    private LocalDateTime date;
    private LocalDateTime dueDate;
    private String status;
    private String remarks;
    private int enrollmentId;
    
    public Assignment(int enrollmentId, String title, String description, LocalDateTime dueDate) {
        this.assignmentId = idCounter++;
        this.referenceId = "ASS" + String.format("%04d", assignmentId);
        this.enrollmentId = enrollmentId;
        this.title = title;
        this.description = description;
        this.date = LocalDateTime.now();
        this.dueDate = dueDate;
        this.status = "PENDING";
        this.remarks = "Not submitted";
    }
    
    public int getAssignmentId() { return assignmentId; }
    public String getReferenceId() { return referenceId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { 
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    public String getDueDate() { 
        return dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    public String getStatus() { return status; }
    public String getRemarks() { return remarks; }
    public int getEnrollmentId() { return enrollmentId; }
    
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public void setStatus(String status) { this.status = status; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-10s │ %-25s │ %-35s │ %-19s │ %-19s │ %-10s │ %-25s │ %-8d │",
            assignmentId, referenceId, 
            title.length() > 25 ? title.substring(0, 22) + "..." : title,
            description.length() > 35 ? description.substring(0, 32) + "..." : description,
            getDate(), getDueDate(), status,
            remarks.length() > 25 ? remarks.substring(0, 22) + "..." : remarks,
            enrollmentId);
    }
    
    public static String getHeader() {
        return "┌────────┬────────────┬───────────────────────────┬───────────────────────────────────────┬─────────────────────┬─────────────────────┬────────────┬───────────────────────────┬──────────┐\n" +
               "│   ID   │ Reference  │ Title                     │ Description                          │ Created Date       │ Due Date           │ Status     │ Remarks                   │ Enrollment│\n" +
               "│        │            │                           │                                       │                    │                    │            │                           │   ID     │\n" +
               "├────────┼────────────┼───────────────────────────┼───────────────────────────────────────┼─────────────────────┼─────────────────────┼────────────┼───────────────────────────┼──────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴────────────┴───────────────────────────┴───────────────────────────────────────┴─────────────────────┴─────────────────────┴────────────┴───────────────────────────┴──────────┘";
    }
}

class Grade {
    private static int idCounter = 1;
    private int gradeId;
    private double score;
    private String grade;
    private String comments;
    private LocalDateTime createdAt;
    private int assignmentId;
    private int studentId;
    
    public Grade(int studentId, int assignmentId, double score, String comments) {
        this.gradeId = idCounter++;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.score = score;
        this.comments = comments;
        this.createdAt = LocalDateTime.now();
        calculateGrade();
    }
    
    private void calculateGrade() {
        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else if (score >= 60) grade = "D";
        else grade = "F";
    }
    
    public int getGradeId() { return gradeId; }
    public double getScore() { return score; }
    public String getGrade() { return grade; }
    public String getComments() { return comments; }
    public String getCreatedAt() { 
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
    }
    public int getAssignmentId() { return assignmentId; }
    public int getStudentId() { return studentId; }
    
    public void setScore(double score) { 
        this.score = score; 
        calculateGrade();
    }
    public void setComments(String comments) { this.comments = comments; }
    
    @Override
    public String toString() {
        return String.format("│ %-6d │ %-8.2f │ %-5s │ %-40s │ %-19s │ %-8d │ %-8d │",
            gradeId, score, grade, 
            comments.length() > 40 ? comments.substring(0, 37) + "..." : comments,
            getCreatedAt(), assignmentId, studentId);
    }
    
    public static String getHeader() {
        return "┌────────┬──────────┬───────┬──────────────────────────────────────────┬─────────────────────┬──────────┬──────────┐\n" +
               "│   ID   │ Score    │ Grade │ Comments                                 │ Created At          │ Assign.  │ Student  │\n" +
               "│        │          │       │                                          │                     │   ID     │   ID     │\n" +
               "├────────┼──────────┼───────┼──────────────────────────────────────────┼─────────────────────┼──────────┼──────────┤";
    }
    
    public static String getFooter() {
        return "└────────┴──────────┴───────┴──────────────────────────────────────────┴─────────────────────┴──────────┴──────────┘";
    }
}

// Service class
class EducationService {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    
    private Scanner scanner = new Scanner(System.in);
    
    // Initialize sample data
    public void initializeSampleData() {
        System.out.println("\nInitializing sample data...");
        
        // Create sample students
        students.add(new Student("John", "Doe", "john.doe@email.com", "123-456-7890"));
        students.add(new Student("Jane", "Smith", "jane.smith@email.com", "987-654-3210"));
        students.add(new Student("Bob", "Johnson", "bob.johnson@email.com", "555-123-4567"));
        students.add(new Student("Alice", "Williams", "alice.williams@email.com", "444-555-6666"));
        students.add(new Student("Charlie", "Brown", "charlie.brown@email.com", "777-888-9999"));
        
        // Create sample courses
        courses.add(new Course("CS101", "Introduction to Programming", 
            "Basic programming concepts and algorithms", 3, "Computer Science"));
        courses.add(new Course("CS201", "Database Systems", 
            "Database design, implementation, and management", 4, "Computer Science"));
        courses.add(new Course("CS301", "Web Development", 
            "Full-stack web development with modern frameworks", 4, "Computer Science"));
        courses.add(new Course("MATH101", "Calculus I", 
            "Limits, derivatives, and integrals", 4, "Mathematics"));
        courses.add(new Course("ENG101", "English Composition", 
            "Academic writing and research skills", 3, "English"));
        
        // Create sample instructors
        instructors.add(new Instructor("Dr. Alice Brown", "555-111-2222", 
            "alice.brown@university.edu", "Programming"));
        instructors.add(new Instructor("Prof. David Wilson", "555-333-4444", 
            "david.wilson@university.edu", "Databases"));
        instructors.add(new Instructor("Dr. Sarah Johnson", "555-555-6666", 
            "sarah.johnson@university.edu", "Web Technologies"));
        instructors.add(new Instructor("Prof. Michael Chen", "555-777-8888", 
            "michael.chen@university.edu", "Mathematics"));
        
        // Assign instructors to courses
        instructors.get(0).setAssignedCourseId(1);
        instructors.get(1).setAssignedCourseId(2);
        instructors.get(2).setAssignedCourseId(3);
        instructors.get(3).setAssignedCourseId(4);
        
        // Create sample enrollments
        enrollments.add(new Enrollment(1, 1, "Fall 2024 Enrollment"));
        enrollments.get(0).setStatus("APPROVED");
        enrollments.add(new Enrollment(2, 1, "Fall 2024 Enrollment"));
        enrollments.get(1).setStatus("APPROVED");
        enrollments.add(new Enrollment(3, 2, "Fall 2024 Enrollment"));
        enrollments.get(2).setStatus("APPROVED");
        enrollments.add(new Enrollment(4, 3, "Fall 2024 Enrollment"));
        enrollments.get(3).setStatus("APPROVED");
        enrollments.add(new Enrollment(5, 4, "Fall 2024 Enrollment"));
        enrollments.get(4).setStatus("APPROVED");
        
        // Create sample assignments
        LocalDateTime dueDate1 = LocalDateTime.now().plusDays(14);
        LocalDateTime dueDate2 = LocalDateTime.now().plusDays(21);
        
        assignments.add(new Assignment(1, "Programming Assignment 1", 
            "Write a Java program to calculate factorial", dueDate1));
        assignments.add(new Assignment(2, "Database Design Project", 
            "Design a database schema for library system", dueDate2));
        assignments.add(new Assignment(3, "Web Development Project", 
            "Create a responsive website using HTML/CSS/JavaScript", dueDate1));
        
        // Create sample grades
        grades.add(new Grade(1, 1, 92.5, "Excellent work!"));
        grades.add(new Grade(2, 1, 85.0, "Good effort"));
        grades.add(new Grade(3, 2, 78.5, "Needs improvement in design"));
        
        System.out.println("✅ Sample data initialized successfully!");
        System.out.println("   - Students: " + students.size());
        System.out.println("   - Courses: " + courses.size());
        System.out.println("   - Instructors: " + instructors.size());
        System.out.println("   - Enrollments: " + enrollments.size());
        System.out.println("   - Assignments: " + assignments.size());
        System.out.println("   - Grades: " + grades.size());
        EducationSystem.pressEnterToContinue();
    }
    
    // Student Management
    public void studentManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("STUDENT MANAGEMENT");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addStudent() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ADD NEW STUDENT");
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        Student student = new Student(firstName, lastName, email, phone);
        students.add(student);
        
        System.out.println("\n✅ Student added successfully!");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Student Number: " + student.getStudentNumber());
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllStudents() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL STUDENTS");
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println(Student.getHeader());
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println(Student.getFooter());
            System.out.println("\nTotal Students: " + students.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void searchStudent() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("SEARCH STUDENT");
        
        System.out.print("Enter search term (name, email, or phone): ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        List<Student> results = students.stream()
            .filter(s -> s.getFirstName().toLowerCase().contains(searchTerm) ||
                        s.getLastName().toLowerCase().contains(searchTerm) ||
                        s.getEmail().toLowerCase().contains(searchTerm) ||
                        s.getPhone().contains(searchTerm))
            .collect(Collectors.toList());
        
        if (results.isEmpty()) {
            System.out.println("\nNo students found matching: " + searchTerm);
        } else {
            System.out.println("\nSearch Results (" + results.size() + " found):");
            System.out.println(Student.getHeader());
            for (Student student : results) {
                System.out.println(student);
            }
            System.out.println(Student.getFooter());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void updateStudent() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("UPDATE STUDENT");
        
        System.out.print("Enter Student ID to update: ");
        int id = getIntInput();
        
        Optional<Student> studentOpt = students.stream()
            .filter(s -> s.getStudentId() == id)
            .findFirst();
        
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            System.out.println("\nCurrent Information:");
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhone());
            
            System.out.println("\nEnter new information (press Enter to keep current):");
            
            System.out.print("First Name [" + student.getFirstName() + "]: ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) student.setFirstName(firstName);
            
            System.out.print("Last Name [" + student.getLastName() + "]: ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) student.setLastName(lastName);
            
            System.out.print("Email [" + student.getEmail() + "]: ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) student.setEmail(email);
            
            System.out.print("Phone [" + student.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) student.setPhone(phone);
            
            System.out.println("\n✅ Student updated successfully!");
        } else {
            System.out.println("Student not found with ID: " + id);
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Course Management
    public void courseManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("COURSE MANAGEMENT");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> viewAllCourses();
                case 3 -> searchCourse();
                case 4 -> updateCourse();
                case 5 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addCourse() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ADD NEW COURSE");
        
        System.out.print("Course Code: ");
        String code = scanner.nextLine();
        
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Credits: ");
        int credits = getIntInput();
        
        System.out.print("Department: ");
        String department = scanner.nextLine();
        
        Course course = new Course(code, name, description, credits, department);
        courses.add(course);
        
        System.out.println("\n✅ Course added successfully!");
        System.out.println("Course ID: " + course.getCourseId());
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllCourses() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL COURSES");
        
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println(Course.getHeader());
            for (Course course : courses) {
                System.out.println(course);
            }
            System.out.println(Course.getFooter());
            System.out.println("\nTotal Courses: " + courses.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void searchCourse() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("SEARCH COURSE");
        
        System.out.print("Enter search term (name, code, or department): ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        List<Course> results = courses.stream()
            .filter(c -> c.getCourseName().toLowerCase().contains(searchTerm) ||
                        c.getCourseCode().toLowerCase().contains(searchTerm) ||
                        c.getDepartment().toLowerCase().contains(searchTerm))
            .collect(Collectors.toList());
        
        if (results.isEmpty()) {
            System.out.println("\nNo courses found matching: " + searchTerm);
        } else {
            System.out.println("\nSearch Results (" + results.size() + " found):");
            System.out.println(Course.getHeader());
            for (Course course : results) {
                System.out.println(course);
            }
            System.out.println(Course.getFooter());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void updateCourse() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("UPDATE COURSE");
        
        System.out.print("Enter Course ID to update: ");
        int id = getIntInput();
        
        Optional<Course> courseOpt = courses.stream()
            .filter(c -> c.getCourseId() == id)
            .findFirst();
        
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            System.out.println("\nCurrent Information:");
            System.out.println("Code: " + course.getCourseCode());
            System.out.println("Name: " + course.getCourseName());
            System.out.println("Credits: " + course.getCredits());
            System.out.println("Department: " + course.getDepartment());
            
            System.out.println("\nEnter new information (press Enter to keep current):");
            
            System.out.print("Course Name [" + course.getCourseName() + "]: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) course.setCourseName(name);
            
            System.out.print("Description [" + course.getDescription() + "]: ");
            String desc = scanner.nextLine();
            if (!desc.isEmpty()) course.setDescription(desc);
            
            System.out.print("Credits [" + course.getCredits() + "]: ");
            String creditsStr = scanner.nextLine();
            if (!creditsStr.isEmpty()) course.setCredits(Integer.parseInt(creditsStr));
            
            System.out.print("Department [" + course.getDepartment() + "]: ");
            String dept = scanner.nextLine();
            if (!dept.isEmpty()) course.setDepartment(dept);
            
            System.out.println("\n✅ Course updated successfully!");
        } else {
            System.out.println("Course not found with ID: " + id);
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Instructor Management
    public void instructorManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("INSTRUCTOR MANAGEMENT");
            System.out.println("1. Add New Instructor");
            System.out.println("2. View All Instructors");
            System.out.println("3. Assign Instructor to Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addInstructor();
                case 2 -> viewAllInstructors();
                case 3 -> assignInstructorToCourse();
                case 4 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addInstructor() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ADD NEW INSTRUCTOR");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Contact: ");
        String contact = scanner.nextLine();
        
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        
        System.out.print("Location: ");
        String location = scanner.nextLine();
        
        Instructor instructor = new Instructor(name, contact, email, specialization);
        instructor.setLocation(location);
        instructors.add(instructor);
        
        System.out.println("\n✅ Instructor added successfully!");
        System.out.println("Instructor ID: " + instructor.getInstructorId());
        System.out.println("Identifier: " + instructor.getIdentifier());
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllInstructors() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL INSTRUCTORS");
        
        if (instructors.isEmpty()) {
            System.out.println("No instructors found.");
        } else {
            System.out.println(Instructor.getHeader());
            for (Instructor instructor : instructors) {
                System.out.println(instructor);
            }
            System.out.println(Instructor.getFooter());
            System.out.println("\nTotal Instructors: " + instructors.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void assignInstructorToCourse() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ASSIGN INSTRUCTOR TO COURSE");
        
        System.out.println("Available Instructors:");
        System.out.println(Instructor.getHeader());
        for (Instructor instructor : instructors) {
            System.out.println(instructor);
        }
        System.out.println(Instructor.getFooter());
        
        System.out.print("\nEnter Instructor ID to assign: ");
        int instructorId = getIntInput();
        
        System.out.println("\nAvailable Courses:");
        System.out.println(Course.getHeader());
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println(Course.getFooter());
        
        System.out.print("\nEnter Course ID to assign: ");
        int courseId = getIntInput();
        
        Optional<Instructor> instructorOpt = instructors.stream()
            .filter(i -> i.getInstructorId() == instructorId)
            .findFirst();
        
        Optional<Course> courseOpt = courses.stream()
            .filter(c -> c.getCourseId() == courseId)
            .findFirst();
        
        if (instructorOpt.isPresent() && courseOpt.isPresent()) {
            Instructor instructor = instructorOpt.get();
            instructor.setAssignedCourseId(courseId);
            System.out.println("\n✅ Instructor " + instructor.getName() + 
                             " assigned to course " + courseOpt.get().getCourseName());
        } else {
            System.out.println("Invalid instructor or course ID!");
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Enrollment Management
    public void enrollmentManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("ENROLLMENT MANAGEMENT");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View All Enrollments");
            System.out.println("3. Update Enrollment Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> enrollStudent();
                case 2 -> viewAllEnrollments();
                case 3 -> updateEnrollmentStatus();
                case 4 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void enrollStudent() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ENROLL STUDENT IN COURSE");
        
        System.out.println("Available Students:");
        System.out.println(Student.getHeader());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println(Student.getFooter());
        
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        System.out.println("\nAvailable Courses:");
        System.out.println(Course.getHeader());
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println(Course.getFooter());
        
        System.out.print("\nEnter Course ID: ");
        int courseId = getIntInput();
        
        System.out.print("Enrollment Description: ");
        String description = scanner.nextLine();
        
        // Check if already enrolled
        boolean alreadyEnrolled = enrollments.stream()
            .anyMatch(e -> e.getStudentId() == studentId && e.getCourseId() == courseId);
        
        if (alreadyEnrolled) {
            System.out.println("\n⚠️ Student is already enrolled in this course!");
        } else {
            Enrollment enrollment = new Enrollment(studentId, courseId, description);
            enrollments.add(enrollment);
            System.out.println("\n✅ Enrollment created successfully!");
            System.out.println("Enrollment ID: " + enrollment.getEnrollmentId());
            System.out.println("Reference: " + enrollment.getReferenceId());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllEnrollments() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL ENROLLMENTS");
        
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
        } else {
            System.out.println(Enrollment.getHeader());
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
            System.out.println(Enrollment.getFooter());
            System.out.println("\nTotal Enrollments: " + enrollments.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void updateEnrollmentStatus() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("UPDATE ENROLLMENT STATUS");
        
        System.out.print("Enter Enrollment ID: ");
        int id = getIntInput();
        
        Optional<Enrollment> enrollmentOpt = enrollments.stream()
            .filter(e -> e.getEnrollmentId() == id)
            .findFirst();
        
        if (enrollmentOpt.isPresent()) {
            Enrollment enrollment = enrollmentOpt.get();
            System.out.println("\nCurrent Status: " + enrollment.getStatus());
            System.out.println("\nAvailable Statuses:");
            System.out.println("1. PENDING");
            System.out.println("2. APPROVED");
            System.out.println("3. REJECTED");
            System.out.println("4. COMPLETED");
            System.out.print("\nSelect status (1-4): ");
            
            int statusChoice = getIntInput();
            String newStatus = switch (statusChoice) {
                case 1 -> "PENDING";
                case 2 -> "APPROVED";
                case 3 -> "REJECTED";
                case 4 -> "COMPLETED";
                default -> enrollment.getStatus();
            };
            
            enrollment.setStatus(newStatus);
            System.out.print("Remarks: ");
            String remarks = scanner.nextLine();
            enrollment.setRemarks(remarks);
            
            System.out.println("\n✅ Enrollment status updated to: " + newStatus);
        } else {
            System.out.println("Enrollment not found!");
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Assignment Management
    public void assignmentManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("ASSIGNMENT MANAGEMENT");
            System.out.println("1. Create Assignment");
            System.out.println("2. View All Assignments");
            System.out.println("3. Update Assignment Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> createAssignment();
                case 2 -> viewAllAssignments();
                case 3 -> updateAssignmentStatus();
                case 4 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void createAssignment() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("CREATE ASSIGNMENT");
        
        System.out.println("Available Enrollments:");
        System.out.println(Enrollment.getHeader());
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
        System.out.println(Enrollment.getFooter());
        
        System.out.print("\nEnter Enrollment ID: ");
        int enrollmentId = getIntInput();
        
        System.out.print("Assignment Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Due Date (YYYY-MM-DD): ");
        String dueDateStr = scanner.nextLine();
        
        try {
            LocalDateTime dueDate = LocalDateTime.parse(dueDateStr + "T23:59:59");
            Assignment assignment = new Assignment(enrollmentId, title, description, dueDate);
            assignments.add(assignment);
            
            System.out.println("\n✅ Assignment created successfully!");
            System.out.println("Assignment ID: " + assignment.getAssignmentId());
            System.out.println("Reference: " + assignment.getReferenceId());
            System.out.println("Due Date: " + assignment.getDueDate());
        } catch (DateTimeParseException e) {
            System.out.println("\n❌ Invalid date format! Please use YYYY-MM-DD");
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllAssignments() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL ASSIGNMENTS");
        
        if (assignments.isEmpty()) {
            System.out.println("No assignments found.");
        } else {
            System.out.println(Assignment.getHeader());
            for (Assignment assignment : assignments) {
                System.out.println(assignment);
            }
            System.out.println(Assignment.getFooter());
            System.out.println("\nTotal Assignments: " + assignments.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void updateAssignmentStatus() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("UPDATE ASSIGNMENT STATUS");
        
        System.out.print("Enter Assignment ID: ");
        int id = getIntInput();
        
        Optional<Assignment> assignmentOpt = assignments.stream()
            .filter(a -> a.getAssignmentId() == id)
            .findFirst();
        
        if (assignmentOpt.isPresent()) {
            Assignment assignment = assignmentOpt.get();
            System.out.println("\nCurrent Status: " + assignment.getStatus());
            System.out.println("\nAvailable Statuses:");
            System.out.println("1. PENDING");
            System.out.println("2. SUBMITTED");
            System.out.println("3. GRADED");
            System.out.println("4. OVERDUE");
            System.out.print("\nSelect status (1-4): ");
            
            int statusChoice = getIntInput();
            String newStatus = switch (statusChoice) {
                case 1 -> "PENDING";
                case 2 -> "SUBMITTED";
                case 3 -> "GRADED";
                case 4 -> "OVERDUE";
                default -> assignment.getStatus();
            };
            
            assignment.setStatus(newStatus);
            System.out.print("Remarks: ");
            String remarks = scanner.nextLine();
            assignment.setRemarks(remarks);
            
            System.out.println("\n✅ Assignment status updated to: " + newStatus);
        } else {
            System.out.println("Assignment not found!");
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Grade Management
    public void gradeManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("GRADE MANAGEMENT");
            System.out.println("1. Add Grade");
            System.out.println("2. View All Grades");
            System.out.println("3. Update Grade");
            System.out.println("4. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addGrade();
                case 2 -> viewAllGrades();
                case 3 -> updateGrade();
                case 4 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addGrade() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ADD GRADE");
        
        System.out.println("Available Students:");
        System.out.println(Student.getHeader());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println(Student.getFooter());
        
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        System.out.println("\nAvailable Assignments:");
        System.out.println(Assignment.getHeader());
        for (Assignment assignment : assignments) {
            System.out.println(assignment);
        }
        System.out.println(Assignment.getFooter());
        
        System.out.print("\nEnter Assignment ID: ");
        int assignmentId = getIntInput();
        
        System.out.print("Score (0-100): ");
        double score = getDoubleInput();
        
        System.out.print("Comments: ");
        String comments = scanner.nextLine();
        
        Grade grade = new Grade(studentId, assignmentId, score, comments);
        grades.add(grade);
        
        System.out.println("\n✅ Grade added successfully!");
        System.out.println("Grade ID: " + grade.getGradeId());
        System.out.println("Score: " + grade.getScore());
        System.out.println("Letter Grade: " + grade.getGrade());
        EducationSystem.pressEnterToContinue();
    }
    
    private void viewAllGrades() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("ALL GRADES");
        
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            System.out.println(Grade.getHeader());
            for (Grade grade : grades) {
                System.out.println(grade);
            }
            System.out.println(Grade.getFooter());
            System.out.println("\nTotal Grades: " + grades.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void updateGrade() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("UPDATE GRADE");
        
        System.out.print("Enter Grade ID: ");
        int id = getIntInput();
        
        Optional<Grade> gradeOpt = grades.stream()
            .filter(g -> g.getGradeId() == id)
            .findFirst();
        
        if (gradeOpt.isPresent()) {
            Grade grade = gradeOpt.get();
            System.out.println("\nCurrent Information:");
            System.out.println("Score: " + grade.getScore());
            System.out.println("Grade: " + grade.getGrade());
            System.out.println("Comments: " + grade.getComments());
            
            System.out.print("\nNew Score (press Enter to keep current): ");
            String scoreStr = scanner.nextLine();
            if (!scoreStr.isEmpty()) {
                double newScore = Double.parseDouble(scoreStr);
                grade.setScore(newScore);
            }
            
            System.out.print("New Comments [" + grade.getComments() + "]: ");
            String comments = scanner.nextLine();
            if (!comments.isEmpty()) grade.setComments(comments);
            
            System.out.println("\n✅ Grade updated successfully!");
        } else {
            System.out.println("Grade not found!");
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Search Management
    public void searchManagement() {
        boolean back = false;
        while (!back) {
            EducationSystem.clearScreen();
            EducationSystem.printSectionTitle("SEARCH MANAGEMENT");
            System.out.println("1. Search Students");
            System.out.println("2. Search Courses");
            System.out.println("3. Search Enrollments by Student");
            System.out.println("4. Search Assignments by Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter choice: ");
            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> searchStudent();
                case 2 -> searchCourse();
                case 3 -> searchEnrollmentsByStudent();
                case 4 -> searchAssignmentsByStatus();
                case 5 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    private void searchEnrollmentsByStudent() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("SEARCH ENROLLMENTS BY STUDENT");
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().toLowerCase();
        
        List<Student> foundStudents = students.stream()
            .filter(s -> s.getFirstName().toLowerCase().contains(name) ||
                        s.getLastName().toLowerCase().contains(name))
            .collect(Collectors.toList());
        
        if (foundStudents.isEmpty()) {
            System.out.println("No students found with name: " + name);
        } else {
            for (Student student : foundStudents) {
                System.out.println("\nEnrollments for " + student.getFirstName() + " " + student.getLastName() + ":");
                List<Enrollment> studentEnrollments = enrollments.stream()
                    .filter(e -> e.getStudentId() == student.getStudentId())
                    .collect(Collectors.toList());
                
                if (studentEnrollments.isEmpty()) {
                    System.out.println("No enrollments found.");
                } else {
                    System.out.println(Enrollment.getHeader());
                    for (Enrollment enrollment : studentEnrollments) {
                        System.out.println(enrollment);
                    }
                    System.out.println(Enrollment.getFooter());
                }
            }
        }
        EducationSystem.pressEnterToContinue();
    }
    
    private void searchAssignmentsByStatus() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("SEARCH ASSIGNMENTS BY STATUS");
        
        System.out.println("Select Status:");
        System.out.println("1. PENDING");
        System.out.println("2. SUBMITTED");
        System.out.println("3. GRADED");
        System.out.println("4. OVERDUE");
        System.out.print("\nChoice: ");
        
        int choice = getIntInput();
        String status = switch (choice) {
            case 1 -> "PENDING";
            case 2 -> "SUBMITTED";
            case 3 -> "GRADED";
            case 4 -> "OVERDUE";
            default -> "PENDING";
        };
        
        List<Assignment> results = assignments.stream()
            .filter(a -> a.getStatus().equals(status))
            .collect(Collectors.toList());
        
        if (results.isEmpty()) {
            System.out.println("\nNo assignments found with status: " + status);
        } else {
            System.out.println("\nAssignments with status: " + status);
            System.out.println(Assignment.getHeader());
            for (Assignment assignment : results) {
                System.out.println(assignment);
            }
            System.out.println(Assignment.getFooter());
            System.out.println("Total: " + results.size());
        }
        EducationSystem.pressEnterToContinue();
    }
    
    // Statistics
    public void viewStatistics() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("SYSTEM STATISTICS");
        
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│                 SYSTEM OVERVIEW                     │");
        System.out.println("├─────────────────────────────────────────────────────┤");
        System.out.printf("│ Students:        %-35d │\n", students.size());
        System.out.printf("│ Courses:         %-35d │\n", courses.size());
        System.out.printf("│ Instructors:     %-35d │\n", instructors.size());
        System.out.printf("│ Enrollments:     %-35d │\n", enrollments.size());
        System.out.printf("│ Assignments:     %-35d │\n", assignments.size());
        System.out.printf("│ Grades:          %-35d │\n", grades.size());
        System.out.println("├─────────────────────────────────────────────────────┤");
        
        // Enrollment statistics
        long pendingEnrollments = enrollments.stream()
            .filter(e -> e.getStatus().equals("PENDING"))
            .count();
        long approvedEnrollments = enrollments.stream()
            .filter(e -> e.getStatus().equals("APPROVED"))
            .count();
        
        System.out.printf("│ Pending Enrollments:   %-30d │\n", pendingEnrollments);
        System.out.printf("│ Approved Enrollments:  %-30d │\n", approvedEnrollments);
        
        // Assignment statistics
        long pendingAssignments = assignments.stream()
            .filter(a -> a.getStatus().equals("PENDING"))
            .count();
        long submittedAssignments = assignments.stream()
            .filter(a -> a.getStatus().equals("SUBMITTED"))
            .count();
        long gradedAssignments = assignments.stream()
            .filter(a -> a.getStatus().equals("GRADED"))
            .count();
        
        System.out.printf("│ Pending Assignments:   %-30d │\n", pendingAssignments);
        System.out.printf("│ Submitted Assignments: %-30d │\n", submittedAssignments);
        System.out.printf("│ Graded Assignments:    %-30d │\n", gradedAssignments);
        
        // Grade statistics
        if (!grades.isEmpty()) {
            double avgScore = grades.stream()
                .mapToDouble(Grade::getScore)
                .average()
                .orElse(0.0);
            
            long aGrades = grades.stream()
                .filter(g -> g.getGrade().equals("A"))
                .count();
            long bGrades = grades.stream()
                .filter(g -> g.getGrade().equals("B"))
                .count();
            long cGrades = grades.stream()
                .filter(g -> g.getGrade().equals("C"))
                .count();
            long dGrades = grades.stream()
                .filter(g -> g.getGrade().equals("D"))
                .count();
            long fGrades = grades.stream()
                .filter(g -> g.getGrade().equals("F"))
                .count();
            
            System.out.println("├─────────────────────────────────────────────────────┤");
            System.out.printf("│ Average Score:        %-30.2f │\n", avgScore);
            System.out.printf("│ A Grades:             %-30d │\n", aGrades);
            System.out.printf("│ B Grades:             %-30d │\n", bGrades);
            System.out.printf("│ C Grades:             %-30d │\n", cGrades);
            System.out.printf("│ D Grades:             %-30d │\n", dGrades);
            System.out.printf("│ F Grades:             %-30d │\n", fGrades);
        }
        
        System.out.println("└─────────────────────────────────────────────────────┘");
        EducationSystem.pressEnterToContinue();
    }
    
    // View All Data
    public void viewAllData() {
        EducationSystem.clearScreen();
        EducationSystem.printSectionTitle("COMPLETE SYSTEM DATA");
        
        System.out.println("\n📊 STUDENTS:");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println(Student.getHeader());
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println(Student.getFooter());
        }
        
        System.out.println("\n\n📚 COURSES:");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println(Course.getHeader());
            for (Course course : courses) {
                System.out.println(course);
            }
            System.out.println(Course.getFooter());
        }
        
        System.out.println("\n\n👨‍🏫 INSTRUCTORS:");
        if (instructors.isEmpty()) {
            System.out.println("No instructors found.");
        } else {
            System.out.println(Instructor.getHeader());
            for (Instructor instructor : instructors) {
                System.out.println(instructor);
            }
            System.out.println(Instructor.getFooter());
        }
        
        System.out.println("\n\n🎓 ENROLLMENTS:");
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
        } else {
            System.out.println(Enrollment.getHeader());
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
            System.out.println(Enrollment.getFooter());
        }
        
        System.out.println("\n\n📝 ASSIGNMENTS:");
        if (assignments.isEmpty()) {
            System.out.println("No assignments found.");
        } else {
            System.out.println(Assignment.getHeader());
            for (Assignment assignment : assignments) {
                System.out.println(assignment);
            }
            System.out.println(Assignment.getFooter());
        }
        
        System.out.println("\n\n📈 GRADES:");
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            System.out.println(Grade.getHeader());
            for (Grade grade : grades) {
                System.out.println(grade);
            }
            System.out.println(Grade.getFooter());
        }
        
        System.out.println("\n\n✅ Data display complete!");
        EducationSystem.pressEnterToContinue();
    }
    
    // Helper methods
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
    
    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
}
