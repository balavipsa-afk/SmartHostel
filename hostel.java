import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * SmartHostel
 * A simple Java hostel management system.
 *
 * Concepts demonstrated:
 * - Classes and Objects
 * - Encapsulation
 * - Inheritance
 * - Method Overriding
 * - Polymorphism
 * - Constructors
 * - Collections
 * - Exception Handling
 * - Custom Exceptions
 * - Multithreading
 * - File Handling
 * - Java Swing
 */

public class SmartHostel extends JFrame {

    // =========================================================
    // DATA COLLECTIONS
    // =========================================================

    private final List<Student> students = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();
    private final List<Complaint> complaints = new ArrayList<>();

    private int nextStudentId = 1;
    private int nextPaymentId = 1;
    private int nextComplaintId = 1;

    private JTextArea outputArea;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SmartHostel() {

        setTitle("SmartHostel - Hostel Management System");

        setSize(950, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        initializeRooms();

        loadStudents();

        createGUI();
    }


    // =========================================================
    // ROOM INITIALIZATION
    // =========================================================

    private void initializeRooms() {

        // Creating 10 rooms with 2 beds each

        for (int i = 101; i <= 110; i++) {

            rooms.add(new Room(i, 2));
        }
    }


    // =========================================================
    // GUI
    // =========================================================

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );


        // ---------------- HEADER ----------------

        JLabel title = new JLabel(
                "SmartHostel",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        JLabel subtitle = new JLabel(
                "Java Hostel Management System",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );


        JPanel header = new JPanel(
                new GridLayout(2, 1)
        );

        header.add(title);
        header.add(subtitle);


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );


        // ---------------- BUTTONS ----------------

        JPanel buttonPanel = new JPanel(
                new GridLayout(3, 3, 10, 10)
        );

        JButton addStudent =
                new JButton("Add Student");

        JButton viewStudents =
                new JButton("View Students");

        JButton viewRooms =
                new JButton("View Rooms");

        JButton allocateRoom =
                new JButton("Allocate Room");

        JButton addPayment =
                new JButton("Add Payment");

        JButton viewPayments =
                new JButton("View Payments");

        JButton addComplaint =
                new JButton("Add Complaint");

        JButton processComplaint =
                new JButton("Process Complaint");

        JButton saveData =
                new JButton("Save Data");


        buttonPanel.add(addStudent);
        buttonPanel.add(viewStudents);
        buttonPanel.add(viewRooms);

        buttonPanel.add(allocateRoom);
        buttonPanel.add(addPayment);
        buttonPanel.add(viewPayments);

        buttonPanel.add(addComplaint);
        buttonPanel.add(processComplaint);
        buttonPanel.add(saveData);


        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        // ---------------- OUTPUT AREA ----------------

        outputArea = new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);


        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        scrollPane.setPreferredSize(
                new Dimension(900, 280)
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        addStudent.addActionListener(
                e -> addStudent()
        );

        viewStudents.addActionListener(
                e -> viewStudents()
        );

        viewRooms.addActionListener(
                e -> viewRooms()
        );

        allocateRoom.addActionListener(
                e -> allocateRoom()
        );

        addPayment.addActionListener(
                e -> addPayment()
        );

        viewPayments.addActionListener(
                e -> viewPayments()
        );

        addComplaint.addActionListener(
                e -> addComplaint()
        );

        processComplaint.addActionListener(
                e -> processComplaint()
        );

        saveData.addActionListener(
                e -> saveStudents()
        );


        setContentPane(mainPanel);


        outputArea.setText(
                "Welcome to SmartHostel!\n\n"
                        + "This application manages students, rooms,\n"
                        + "payments and hostel complaints.\n\n"
                        + "Select an option above to get started."
        );
    }


    // =========================================================
    // ADD STUDENT
    // =========================================================

    private void addStudent() {

        String name = JOptionPane.showInputDialog(
                this,
                "Enter student name:"
        );

        if (!isValidText(name)) {

            showError(
                    "Please enter a valid student name."
            );

            return;
        }


        String email = JOptionPane.showInputDialog(
                this,
                "Enter student email:"
        );

        if (!isValidEmail(email)) {

            showError(
                    "Please enter a valid email."
            );

            return;
        }


        String course = JOptionPane.showInputDialog(
                this,
                "Enter course:"
        );

        if (!isValidText(course)) {

            showError(
                    "Please enter a valid course."
            );

            return;
        }


        Student student = new Student(
                nextStudentId++,
                name.trim(),
                email.trim(),
                course.trim()
        );


        students.add(student);


        outputArea.setText(
                "Student added successfully!\n\n"
                        + student
        );
    }


    // =========================================================
    // VIEW STUDENTS
    // =========================================================

    private void viewStudents() {

        if (students.isEmpty()) {

            outputArea.setText(
                    "No students have been registered yet."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();

        result.append(
                "================ STUDENTS ================\n\n"
        );


        for (Student student : students) {

            result.append(student)
                    .append("\n\n");
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =========================================================
    // VIEW ROOMS
    // =========================================================

    private void viewRooms() {

        StringBuilder result =
                new StringBuilder();

        result.append(
                "================ ROOMS ================\n\n"
        );


        for (Room room : rooms) {

            result.append(room)
                    .append("\n");
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =========================================================
    // ROOM ALLOCATION
    // =========================================================

    private void allocateRoom() {

        if (students.isEmpty()) {

            showError(
                    "Please add a student first."
            );

            return;
        }


        String idText = JOptionPane.showInputDialog(
                this,
                "Enter student ID:"
        );


        try {

            int studentId =
                    Integer.parseInt(idText);


            Student student =
                    findStudent(studentId);


            if (student == null) {

                showError(
                        "Student not found."
                );

                return;
            }


            if (student.getRoomNumber() != -1) {

                showError(
                        "This student already has a room."
                );

                return;
            }


            allocateStudentRoom(student);


        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid student ID."
            );
        }
    }


    private void allocateStudentRoom(
            Student student
    ) throws RoomNotAvailableException {

        for (Room room : rooms) {

            if (room.hasSpace()) {

                room.allocateBed();

                student.setRoomNumber(
                        room.getRoomNumber()
                );


                outputArea.setText(
                        "Room allocated successfully!\n\n"
                                + "Student: "
                                + student.getName()
                                + "\nRoom: "
                                + room.getRoomNumber()
                );

                return;
            }
        }


        throw new RoomNotAvailableException(
                "No rooms are currently available."
        );
    }


    // =========================================================
    // ADD PAYMENT
    // =========================================================

    private void addPayment() {

        String studentText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter student ID:"
                );


        try {

            int studentId =
                    Integer.parseInt(studentText);


            Student student =
                    findStudent(studentId);


            if (student == null) {

                showError(
                        "Student not found."
                );

                return;
            }


            String amountText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter payment amount:"
                    );


            double amount =
                    Double.parseDouble(amountText);


            if (amount <= 0) {

                throw new InvalidPaymentException(
                        "Payment amount must be greater than zero."
                );
            }


            String date =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter payment date:"
                    );


            Payment payment = new Payment(
                    nextPaymentId++,
                    studentId,
                    amount,
                    date
            );


            payments.add(payment);


            outputArea.setText(
                    "Payment added successfully!\n\n"
                            + payment
            );


        } catch (NumberFormatException e) {

            showError(
                    "Please enter valid numbers."
            );


        } catch (InvalidPaymentException e) {

            showError(
                    e.getMessage()
            );
        }
    }


    // =========================================================
    // VIEW PAYMENTS
    // =========================================================

    private void viewPayments() {

        if (payments.isEmpty()) {

            outputArea.setText(
                    "No payments have been recorded yet."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "================ PAYMENTS ================\n\n"
        );


        for (Payment payment : payments) {

            result.append(payment)
                    .append("\n");
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =========================================================
    // ADD COMPLAINT
    // =========================================================

    private void addComplaint() {

        String studentText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter student ID:"
                );


        try {

            int studentId =
                    Integer.parseInt(studentText);


            Student student =
                    findStudent(studentId);


            if (student == null) {

                showError(
                        "Student not found."
                );

                return;
            }


            String description =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter complaint:"
                    );


            if (!isValidText(description)) {

                showError(
                        "Complaint cannot be empty."
                );

                return;
            }


            Complaint complaint =
                    new Complaint(
                            nextComplaintId++,
                            studentId,
                            description
                    );


            complaints.add(complaint);


            outputArea.setText(
                    "Complaint registered!\n\n"
                            + complaint
            );


        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid student ID."
            );
        }
    }


    // =========================================================
    // PROCESS COMPLAINT
    // =========================================================

    private void processComplaint() {

        if (complaints.isEmpty()) {

            showError(
                    "There are no complaints."
            );

            return;
        }


        String idText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter complaint ID:"
                );


        try {

            int complaintId =
                    Integer.parseInt(idText);


            Complaint complaint =
                    findComplaint(complaintId);


            if (complaint == null) {

                showError(
                        "Complaint not found."
                );

                return;
            }


            if (complaint.getStatus()
                    .equals("Resolved")) {

                showError(
                        "Complaint is already resolved."
                );

                return;
            }


            ComplaintThread thread =
                    new ComplaintThread(
                            complaint
                    );


            thread.start();


            outputArea.setText(
                    "Complaint processing started.\n\n"
                            + "Complaint ID: "
                            + complaint.getComplaintId()
                            + "\nStatus: Processing..."
            );


        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid complaint ID."
            );
        }
    }


    // =========================================================
    // SAVE STUDENTS
    // =========================================================

    private void saveStudents() {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        "students.txt"
                                )
                        )
        ) {

            for (Student student : students) {

                writer.write(
                        student.getId()
                                + "|"
                                + student.getName()
                                + "|"
                                + student.getEmail()
                                + "|"
                                + student.getCourse()
                                + "|"
                                + student.getRoomNumber()
                );

                writer.newLine();
            }


            outputArea.setText(
                    "Student data saved successfully!\n\n"
                            + "File: students.txt"
            );


        } catch (IOException e) {

            showError(
                    "Could not save data: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    private void loadStudents() {

        File file =
                new File("students.txt");


        if (!file.exists()) {
            return;
        }


        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;


            while (
                    (line = reader.readLine())
                            != null
            ) {

                String[] data =
                        line.split("\\|");


                if (data.length >= 5) {

                    int id =
                            Integer.parseInt(data[0]);


                    Student student =
                            new Student(
                                    id,
                                    data[1],
                                    data[2],
                                    data[3]
                            );


                    int room =
                            Integer.parseInt(data[4]);


                    student.setRoomNumber(room);


                    students.add(student);


                    if (id >= nextStudentId) {

                        nextStudentId =
                                id + 1;
                    }
                }
            }


        } catch (
                IOException |
                NumberFormatException e
        ) {

            System.out.println(
                    "Could not load students: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // SEARCH STUDENT
    // =========================================================

    private Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                return student;
            }
        }


        return null;
    }


    // =========================================================
    // SEARCH COMPLAINT
    // =========================================================

    private Complaint findComplaint(int id) {

        for (Complaint complaint : complaints) {

            if (complaint.getComplaintId() == id) {

                return complaint;
            }
        }


        return null;
    }


    // =========================================================
    // VALIDATION
    // =========================================================

    private boolean isValidText(String text) {

        return text != null
                && !text.trim().isEmpty();
    }


    private boolean isValidEmail(String email) {

        return email != null
                && email.contains("@")
                && email.contains(".");
    }


    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "SmartHostel",
                JOptionPane.ERROR_MESSAGE
        );
    }


    // =========================================================
    // STUDENT CLASS
    // =========================================================

    static class Student extends User {

        private String course;
        private int roomNumber;


        public Student(
                int id,
                String name,
                String email,
                String course
        ) {

            super(id, name, email);

            this.course = course;

            this.roomNumber = -1;
        }


        public String getCourse() {

            return course;
        }


        public int getRoomNumber() {

            return roomNumber;
        }


        public void setRoomNumber(
                int roomNumber
        ) {

            this.roomNumber = roomNumber;
        }


        @Override
        public String getUserType() {

            return "Student";
        }


        @Override
        public String toString() {

            String room =
                    roomNumber == -1
                            ? "Not Allocated"
                            : String.valueOf(
                                    roomNumber
                            );


            return "Student ID: "
                    + getId()
                    + "\nName: "
                    + getName()
                    + "\nEmail: "
                    + getEmail()
                    + "\nCourse: "
                    + course
                    + "\nRoom: "
                    + room;
        }
    }


    // =========================================================
    // USER ABSTRACT CLASS
    // =========================================================

    static abstract class User {

        private int id;
        private String name;
        private String email;


        public User(
                int id,
                String name,
                String email
        ) {

            this.id = id;

            this.name = name;

            this.email = email;
        }


        public int getId() {

            return id;
        }


        public String getName() {

            return name;
        }


        public String getEmail() {

            return email;
        }


        public abstract String getUserType();
    }


    // =========================================================
    // ROOM CLASS
    // =========================================================

    static class Room {

        private int roomNumber;
        private int capacity;
        private int occupiedBeds;


        public Room(
                int roomNumber,
                int capacity
        ) {

            this.roomNumber = roomNumber;

            this.capacity = capacity;

            this.occupiedBeds = 0;
        }


        public int getRoomNumber() {

            return roomNumber;
        }


        public boolean hasSpace() {

            return occupiedBeds < capacity;
        }


        public void allocateBed() {

            if (hasSpace()) {

                occupiedBeds++;
            }
        }


        public int getAvailableBeds() {

            return capacity - occupiedBeds;
        }


        @Override
        public String toString() {

            return "Room "
                    + roomNumber
                    + " | Capacity: "
                    + capacity
                    + " | Occupied: "
                    + occupiedBeds
                    + " | Available: "
                    + getAvailableBeds();
        }
    }


    // =========================================================
    // PAYMENT CLASS
    // =========================================================

    static class Payment {

        private int paymentId;
        private int studentId;
        private double amount;
        private String date;


        public Payment(
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

            return "Payment ID: "
                    + paymentId
                    + " | Student ID: "
                    + studentId
                    + " | Amount: ₹"
                    + String.format(
                            "%.2f",
                            amount
                    )
                    + " | Date: "
                    + date;
        }
    }


    // =========================================================
    // COMPLAINT CLASS
    // =========================================================

    static class Complaint {

        private int complaintId;
        private int studentId;
        private String description;
        private String status;


        public Complaint(
                int complaintId,
                int studentId,
                String description
        ) {

            this.complaintId = complaintId;

            this.studentId = studentId;

            this.description = description;

            this.status = "Pending";
        }


        public int getComplaintId() {

            return complaintId;
        }


        public String getStatus() {

            return status;
        }


        public void setStatus(
                String status
        ) {

            this.status = status;
        }


        @Override
        public String toString() {

            return "Complaint ID: "
                    + complaintId
                    + " | Student ID: "
                    + studentId
                    + " | Complaint: "
                    + description
                    + " | Status: "
                    + status;
        }
    }


    // =========================================================
    // CUSTOM EXCEPTION
    // =========================================================

    static class RoomNotAvailableException
            extends Exception {

        public RoomNotAvailableException(
                String message
        ) {

            super(message);
        }
    }


    // =========================================================
    // CUSTOM PAYMENT EXCEPTION
    // =========================================================

    static class InvalidPaymentException
            extends Exception {

        public InvalidPaymentException(
                String message
        ) {

            super(message);
        }
    }


    // =========================================================
    // MULTITHREADING
    // =========================================================

    static class ComplaintThread
            extends Thread {

        private Complaint complaint;


        public ComplaintThread(
                Complaint complaint
        ) {

            this.complaint = complaint;
        }


        @Override
        public void run() {

            try {

                complaint.setStatus(
                        "Processing"
                );


                Thread.sleep(2000);


                complaint.setStatus(
                        "Resolved"
                );


                System.out.println(
                        "Complaint "
                                + complaint.getComplaintId()
                                + " resolved."
                );


            } catch (
                    InterruptedException e
            ) {

                complaint.setStatus(
                        "Interrupted"
                );


                Thread.currentThread()
                        .interrupt();
            }
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    SmartHostel app =
                            new SmartHostel();

                    app.setVisible(true);
                }
        );
    }
}
