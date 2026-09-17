import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class SmartHostel extends JFrame {

    // Lists used to store hostel data
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Payment> payments = new ArrayList<>();
    ArrayList<Complaint> complaints = new ArrayList<>();

    int studentId = 1;
    int paymentId = 1;
    int complaintId = 1;

    JTextArea display;

    // Constructor
    SmartHostel() {

        setTitle("SmartHostel - Hostel Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createRooms();
        loadStudents();
        createWindow();
    }

    // Creating hostel rooms
    void createRooms() {

        for (int i = 101; i <= 110; i++) {
            rooms.add(new Room(i, 2));
        }
    }

    // Creating the main GUI
    void createWindow() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel heading = new JLabel(
                "SmartHostel",
                SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subHeading = new JLabel(
                "Hostel Management System",
                SwingConstants.CENTER
        );
        subHeading.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(heading);
        top.add(subHeading);

        mainPanel.add(top, BorderLayout.NORTH);

        JPanel buttons = new JPanel(
                new GridLayout(3, 3, 10, 10)
        );

        JButton addStudent = new JButton("Add Student");
        JButton showStudents = new JButton("View Students");
        JButton showRooms = new JButton("View Rooms");
        JButton allocateRoom = new JButton("Allocate Room");
        JButton addPayment = new JButton("Add Payment");
        JButton showPayments = new JButton("View Payments");
        JButton addComplaint = new JButton("Add Complaint");
        JButton processComplaint = new JButton("Process Complaint");
        JButton saveData = new JButton("Save Data");

        buttons.add(addStudent);
        buttons.add(showStudents);
        buttons.add(showRooms);
        buttons.add(allocateRoom);
        buttons.add(addPayment);
        buttons.add(showPayments);
        buttons.add(addComplaint);
        buttons.add(processComplaint);
        buttons.add(saveData);

        mainPanel.add(buttons, BorderLayout.CENTER);

        display = new JTextArea();
        display.setEditable(false);
        display.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scroll = new JScrollPane(display);
        scroll.setPreferredSize(
                new Dimension(850, 250)
        );

        mainPanel.add(scroll, BorderLayout.SOUTH);

        // Button actions
        addStudent.addActionListener(e -> addStudent());
        showStudents.addActionListener(e -> showStudents());
        showRooms.addActionListener(e -> showRooms());
        allocateRoom.addActionListener(e -> allocateRoom());
        addPayment.addActionListener(e -> addPayment());
        showPayments.addActionListener(e -> showPayments());
        addComplaint.addActionListener(e -> addComplaint());
        processComplaint.addActionListener(e -> processComplaint());
        saveData.addActionListener(e -> saveStudents());

        setContentPane(mainPanel);

        display.setText(
                "Welcome to SmartHostel!\n\n"
                + "Use the buttons above to manage students,\n"
                + "rooms, payments and complaints."
        );
    }

    // Add a new student
    void addStudent() {

        String name = JOptionPane.showInputDialog(
                this,
                "Enter student name:"
        );

        if (name == null || name.trim().isEmpty()) {
            showError("Student name cannot be empty.");
            return;
        }

        String email = JOptionPane.showInputDialog(
                this,
                "Enter student email:"
        );

        if (email == null ||
                !email.contains("@") ||
                !email.contains(".")) {

            showError("Enter a valid email.");
            return;
        }

        String course = JOptionPane.showInputDialog(
                this,
                "Enter course:"
        );

        if (course == null || course.trim().isEmpty()) {
            showError("Course cannot be empty.");
            return;
        }

        Student s = new Student(
                studentId++,
                name.trim(),
                email.trim(),
                course.trim()
        );

        students.add(s);

        display.setText(
                "Student added successfully!\n\n" + s
        );
    }

    // Display all students
    void showStudents() {

        if (students.isEmpty()) {
            display.setText(
                    "No students have been added yet."
            );
            return;
        }

        String result =
                "============= STUDENTS =============\n\n";

        for (Student s : students) {
            result += s + "\n\n";
        }

        display.setText(result);
    }

    // Display room information
    void showRooms() {

        String result =
                "============== ROOMS ==============\n\n";

        for (Room r : rooms) {
            result += r + "\n";
        }

        display.setText(result);
    }

    // Allocate a room to a student
    void allocateRoom() {

        if (students.isEmpty()) {
            showError("Please add a student first.");
            return;
        }

        String input = JOptionPane.showInputDialog(
                this,
                "Enter student ID:"
        );

        try {

            int id = Integer.parseInt(input);
            Student s = findStudent(id);

            if (s == null) {
                showError("Student not found.");
                return;
            }

            if (s.roomNumber != -1) {
                showError(
                        "This student already has a room."
                );
                return;
            }

            giveRoom(s);

        } catch (NumberFormatException e) {

            showError("Enter a valid student ID.");

        } catch (RoomNotAvailableException e) {

            showError(e.getMessage());
        }
    }

    // Find an empty bed and assign it
    void giveRoom(Student student)
            throws RoomNotAvailableException {

        for (Room r : rooms) {

            if (r.hasSpace()) {

                r.allocateBed();
                student.roomNumber = r.roomNumber;

                display.setText(
                        "Room allocated successfully!\n\n"
                        + "Student: "
                        + student.name
                        + "\nRoom Number: "
                        + r.roomNumber
                );

                return;
            }
        }

        throw new RoomNotAvailableException(
                "No rooms are available right now."
        );
    }

    // Add payment details
    void addPayment() {

        String input = JOptionPane.showInputDialog(
                this,
                "Enter student ID:"
        );

        try {

            int id = Integer.parseInt(input);
            Student s = findStudent(id);

            if (s == null) {
                showError("Student not found.");
                return;
            }

            String amountInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter payment amount:"
                    );

            double amount =
                    Double.parseDouble(amountInput);

            if (amount <= 0) {
                throw new InvalidPaymentException(
                        "Payment amount should be greater than zero."
                );
            }

            String date =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter payment date:"
                    );

            Payment p = new Payment(
                    paymentId++,
                    id,
                    amount,
                    date
            );

            payments.add(p);

            display.setText(
                    "Payment added successfully!\n\n"
                    + p
            );

        } catch (NumberFormatException e) {

            showError("Enter valid numbers.");

        } catch (InvalidPaymentException e) {

            showError(e.getMessage());
        }
    }

    // Show all payments
    void showPayments() {

        if (payments.isEmpty()) {
            display.setText(
                    "No payments have been recorded."
            );
            return;
        }

        String result =
                "============= PAYMENTS =============\n\n";

        for (Payment p : payments) {
            result += p + "\n";
        }

        display.setText(result);
    }

    // Register a complaint
    void addComplaint() {

        String input = JOptionPane.showInputDialog(
                this,
                "Enter student ID:"
        );

        try {

            int id = Integer.parseInt(input);
            Student s = findStudent(id);

            if (s == null) {
                showError("Student not found.");
                return;
            }

            String text =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter your complaint:"
                    );

            if (text == null || text.trim().isEmpty()) {
                showError(
                        "Complaint cannot be empty."
                );
                return;
            }

            Complaint c = new Complaint(
                    complaintId++,
                    id,
                    text.trim()
            );

            complaints.add(c);

            display.setText(
                    "Complaint registered successfully!\n\n"
                    + c
            );

        } catch (NumberFormatException e) {

            showError("Enter a valid student ID.");
        }
    }

    // Process a complaint using a separate thread
    void processComplaint() {

        if (complaints.isEmpty()) {
            showError("There are no complaints.");
            return;
        }

        String input = JOptionPane.showInputDialog(
                this,
                "Enter complaint ID:"
        );

        try {

            int id = Integer.parseInt(input);
            Complaint c = findComplaint(id);

            if (c == null) {
                showError("Complaint not found.");
                return;
            }

            if (c.status.equals("Resolved")) {
                showError(
                        "This complaint is already resolved."
                );
                return;
            }

            ComplaintThread thread =
                    new ComplaintThread(c);

            thread.start();

            display.setText(
                    "Complaint processing started.\n\n"
                    + "Complaint ID: " + id
                    + "\nStatus: Processing..."
            );

        } catch (NumberFormatException e) {

            showError("Enter a valid complaint ID.");
        }
    }

    // Save student data into a text file
    void saveStudents() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("students.txt")
                    );

            for (Student s : students) {

                writer.write(
                        s.id + "|"
                        + s.name + "|"
                        + s.email + "|"
                        + s.course + "|"
                        + s.roomNumber
                );

                writer.newLine();
            }

            writer.close();

            display.setText(
                    "Student data saved successfully!\n\n"
                    + "File created: students.txt"
            );

        } catch (IOException e) {

            showError(
                    "Error while saving data."
            );
        }
    }

    // Load previously saved students
    void loadStudents() {

        File file = new File("students.txt");

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                if (data.length >= 5) {

                    int id =
                            Integer.parseInt(data[0]);

                    Student s = new Student(
                            id,
                            data[1],
                            data[2],
                            data[3]
                    );

                    s.roomNumber =
                            Integer.parseInt(data[4]);

                    students.add(s);

                    if (id >= studentId) {
                        studentId = id + 1;
                    }
                }
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "Could not load old student data."
            );
        }
    }

    // Search student using ID
    Student findStudent(int id) {

        for (Student s : students) {

            if (s.id == id) {
                return s;
            }
        }

        return null;
    }

    // Search complaint using ID
    Complaint findComplaint(int id) {

        for (Complaint c : complaints) {

            if (c.complaintId == id) {
                return c;
            }
        }

        return null;
    }

    void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "SmartHostel",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Parent class
    static abstract class User {

        int id;
        String name;
        String email;

        User(
                int id,
                String name,
                String email
        ) {

            this.id = id;
            this.name = name;
            this.email = email;
        }

        abstract String getUserType();
    }

    // Student class inherits User
    static class Student extends User {

        String course;
        int roomNumber = -1;

        Student(
                int id,
                String name,
                String email,
                String course
        ) {

            super(id, name, email);
            this.course = course;
        }

        @Override
        String getUserType() {

            return "Student";
        }

        @Override
        public String toString() {

            String room =
                    roomNumber == -1
                            ? "Not Allocated"
                            : String.valueOf(roomNumber);

            return "Student ID: " + id
                    + "\nName: " + name
                    + "\nEmail: " + email
                    + "\nCourse: " + course
                    + "\nRoom: " + room;
        }
    }

    // Room class
    static class Room {

        int roomNumber;
        int capacity;
        int occupied;

        Room(int roomNumber, int capacity) {

            this.roomNumber = roomNumber;
            this.capacity = capacity;
            occupied = 0;
        }

        boolean hasSpace() {

            return occupied < capacity;
        }

        void allocateBed() {

            if (hasSpace()) {
                occupied++;
            }
        }

        @Override
        public String toString() {

            return "Room " + roomNumber
                    + " | Capacity: " + capacity
                    + " | Occupied: " + occupied
                    + " | Available: "
                    + (capacity - occupied);
        }
    }

    // Payment class
    static class Payment {

        int paymentId;
        int studentId;
        double amount;
        String date;

        Payment(
                int paymentId,
                int studentId,
                double amount,
                String date
        ) {

            this.paymentId = paymentId;
            this.studentId = studentId;
            this.amount = amount;
            this.date = date;
        }

        @Override
        public String toString() {

            return "Payment ID: " + paymentId
                    + " | Student ID: " + studentId
                    + " | Amount: Rs. "
                    + String.format("%.2f", amount)
                    + " | Date: " + date;
        }
    }

    // Complaint class
    static class Complaint {

        int complaintId;
        int studentId;
        String description;
        String status = "Pending";

        Complaint(
                int complaintId,
                int studentId,
                String description
        ) {

            this.complaintId = complaintId;
            this.studentId = studentId;
            this.description = description;
        }

        @Override
        public String toString() {

            return "Complaint ID: "
                    + complaintId
                    + " | Student ID: "
                    + studentId
                    + "\nComplaint: "
                    + description
                    + "\nStatus: "
                    + status;
        }
    }

    // Custom exception for room allocation
    static class RoomNotAvailableException
            extends Exception {

        RoomNotAvailableException(String message) {

            super(message);
        }
    }

    // Custom exception for payment
    static class InvalidPaymentException
            extends Exception {

        InvalidPaymentException(String message) {

            super(message);
        }
    }

    // Thread used to process complaints
    static class ComplaintThread extends Thread {

        Complaint complaint;

        ComplaintThread(Complaint complaint) {

            this.complaint = complaint;
        }

        @Override
        public void run() {

            try {

                complaint.status = "Processing";

                // Simulating complaint processing
                Thread.sleep(2000);

                complaint.status = "Resolved";

                System.out.println(
                        "Complaint "
                        + complaint.complaintId
                        + " resolved."
                );

            } catch (InterruptedException e) {

                complaint.status = "Interrupted";
                Thread.currentThread().interrupt();
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            SmartHostel app =
                    new SmartHostel();

            app.setVisible(true);
        });
    }
}
