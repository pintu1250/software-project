package com.test.example;

import java.sql.*;
import java.util.*;

public class HospitalManagementSystem1 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/hospitaldb";
    static final String USER = "root";
    static final String PASS = "Pintu@4321";

    Connection conn;
    Scanner scanner = new Scanner(System.in);

    public HospitalManagementSystem1() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            runConsole();
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    void runConsole() {
        while (true) {
            System.out.println("\nHospital Management System Console");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Generate Bill");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addPatientInput(); break;
                case 2: viewAllPatients(); break;
                case 3: addDoctorInput(); break;
                case 4: bookAppointmentInput(); break;
                case 5: viewAppointments(); break;
                case 6: generateBillInput(); break;
                case 7: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    void addPatientInput() {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Age: "); int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Gender: "); String gender = scanner.nextLine();
        System.out.print("Address: "); String address = scanner.nextLine();
        System.out.print("Contact: "); String contact = scanner.nextLine();
        addPatient(name, age, gender, address, contact);
    }

    void addDoctorInput() {
        System.out.print("Doctor Name: "); String name = scanner.nextLine();
        System.out.print("Specialization: "); String spec = scanner.nextLine();
        System.out.print("Contact: "); String contact = scanner.nextLine();
        addDoctor(name, spec, contact);
    }

    void bookAppointmentInput() {
        System.out.print("Patient ID: "); int pid = scanner.nextInt();
        System.out.print("Doctor ID: "); int did = scanner.nextInt(); scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = scanner.nextLine();
        System.out.print("Time (HH:MM): "); String time = scanner.nextLine();
        bookAppointment(pid, did, date, time);
    }

    void generateBillInput() {
        System.out.print("Patient ID: "); int pid = scanner.nextInt();
        System.out.print("Amount: "); double amt = scanner.nextDouble();
        generateBill(pid, amt);
    }

    // Parameterized business methods (40+ methods)

    void addPatient(String name, int age, String gender, String address, String contact) {
        executeUpdate("INSERT INTO patients (name, age, gender, address, contact) VALUES (?, ?, ?, ?, ?)",
                name, age, gender, address, contact);
    }

    void viewAllPatients() {
        executeQuery("SELECT * FROM patients");
    }

    void addDoctor(String name, String specialization, String contact) {
        executeUpdate("INSERT INTO doctors (name, specialization, contact) VALUES (?, ?, ?)",
                name, specialization, contact);
    }

    void bookAppointment(int patientId, int doctorId, String date, String time) {
        executeUpdate("INSERT INTO appointments (patient_id, doctor_id, date, time) VALUES (?, ?, ?, ?)",
                patientId, doctorId, date, time);
    }

    void viewAppointments() {
        executeQuery("SELECT * FROM appointments");
    }

    void generateBill(int patientId, double amount) {
        executeUpdate("INSERT INTO bills (patient_id, total, status) VALUES (?, ?, 'Unpaid')",
                patientId, amount);
    }

    void updatePatientContact(int id, String contact) {
        executeUpdate("UPDATE patients SET contact = ? WHERE id = ?", contact, id);
    }

    void deletePatient(int id) {
        executeUpdate("DELETE FROM patients WHERE id = ?", id);
    }

    void deleteDoctor(int id) {
        executeUpdate("DELETE FROM doctors WHERE id = ?", id);
    }

    void deleteAppointment(int id) {
        executeUpdate("DELETE FROM appointments WHERE id = ?", id);
    }

    void updateDoctorSpecialization(int id, String spec) {
        executeUpdate("UPDATE doctors SET specialization = ? WHERE id = ?", spec, id);
    }

    void updateBillStatus(int billId, String status) {
        executeUpdate("UPDATE bills SET status = ? WHERE id = ?", status, billId);
    }

    void getBillByPatientId(int id) {
        executeQuery("SELECT * FROM bills WHERE patient_id = ?", id);
    }

    void getAppointmentsByDoctorId(int id) {
        executeQuery("SELECT * FROM appointments WHERE doctor_id = ?", id);
    }

    void getAppointmentsByPatientId(int id) {
        executeQuery("SELECT * FROM appointments WHERE patient_id = ?", id);
    }

    void updateAppointmentTime(int id, String time) {
        executeUpdate("UPDATE appointments SET time = ? WHERE id = ?", time, id);
    }

    void getPatientById(int id) {
        executeQuery("SELECT * FROM patients WHERE id = ?", id);
    }

    void getDoctorById(int id) {
        executeQuery("SELECT * FROM doctors WHERE id = ?", id);
    }

    void searchPatientsByName(String name) {
        executeQuery("SELECT * FROM patients WHERE name LIKE ?", "%" + name + "%");
    }

    void searchDoctorsBySpecialization(String spec) {
        executeQuery("SELECT * FROM doctors WHERE specialization LIKE ?", "%" + spec + "%");
    }

    void executeUpdate(String sql, Object... params) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) stmt.setObject(i + 1, params[i]);
            stmt.executeUpdate();
            System.out.println("Update executed successfully.");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    void executeQuery(String sql, Object... params) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) stmt.setObject(i + 1, params[i]);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new HospitalManagementSystem1();
    }
}
