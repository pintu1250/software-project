package com.test.example;

import java.util.*;

import static java.lang.System.out;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private String accountType;
    private double balance;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String loanType;
    private double loanAmount;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder, String accountType, double balance,
            String address, String phone, String email, String password) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.loanType = "None";
        this.loanAmount = 0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + balance);
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | New Balance: " + balance);
            out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount + " | New Balance: " + balance);
            out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else {
            out.println("Insufficient funds or invalid amount.");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            receiver.deposit(amount);
            transactionHistory.add("Transferred: " + amount + " to " + receiver.getAccountHolder());
            out.println("Transfer successful: " + amount + " to " + receiver.getAccountHolder());
        } else {
            out.println("Transfer failed due to insufficient balance.");
        }
    }

    public void applyLoan(String loanType, double amount) {
        if (amount > 0) {
            this.loanType = loanType;
            this.loanAmount = amount;
            transactionHistory.add("Loan Applied: " + loanType + " | Amount: " + amount);
            out.println("Loan applied successfully: " + loanType + " | Amount: " + amount);
        } else {
            out.println("Invalid loan amount.");
        }
    }

    public void repayLoan(double amount) {
        if (amount > 0 && amount <= loanAmount) {
            loanAmount -= amount;
            transactionHistory.add("Loan Repayment: " + amount + " | Remaining Loan: " + loanAmount);
            out.println("Loan repaid: " + amount + " | Remaining Loan: " + loanAmount);
        } else {
            out.println("Invalid repayment amount.");
        }
    }

    public void calculateInterest(double rate) {
        if (accountType.equalsIgnoreCase("Savings")) {
            double interest = balance * (rate / 100);
            balance += interest;
            transactionHistory.add("Interest Added: " + interest + " | New Balance: " + balance);
            out.println("Interest added: " + interest + " | New Balance: " + balance);
        } else {
            out.println("Interest only applicable for Savings accounts.");
        }
    }

    public void updateInfo(String address, String phone, String email) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        out.println("Information updated successfully.");
    }

    public void displayUserInfo() {
        out.println("\n===== Account Details =====");
        out.println("Account Number: " + accountNumber);
        out.println("Account Holder: " + accountHolder);
        out.println("Account Type: " + accountType);
        out.println("Balance: " + balance);
        out.println("Address: " + address);
        out.println("Phone: " + phone);
        out.println("Email: " + email);
        out.println("Loan Type: " + loanType);
        out.println("Loan Amount: " + loanAmount);
    }

    public void showTransactionHistory() {
        out.println("\n===== Transaction History =====");
        for (String transaction : transactionHistory) {
            out.println(transaction);
        }
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public double getLoanAmount() {
        return

        loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

public class BankingSystem1 {
    public static Map<String, BankAccount> accounts = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);

    public BankingSystem1(Map<String, BankAccount> accounts, Scanner scanner) {
    }

    public static void createAccount() {
        out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        out.print("Enter Account Holder Name: ");
        String accountHolder = scanner.next();
        out.print("Enter Account Type (Savings/Checking): ");
        String accountType = scanner.next();
        out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        out.print("Enter Address: ");
        String address = scanner.next();
        out.print("Enter Phone Number: ");
        String phone = scanner.next();
        out.print("Enter Email: ");
        String email = scanner.next();
        out.print("Set Account Password: ");
        String password = scanner.next();

        BankAccount newAccount = new BankAccount(accountNumber, accountHolder, accountType, balance, address, phone,
                email, password);
        accounts.put(accountNumber, newAccount);
        out.println("Account created successfully!");
    }

    public static void userLogin() {
        out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        if (BankingSystem1.accounts.containsKey(accountNumber)) {
            out.print("Enter Password: ");
            String password = scanner.next();
            BankAccount account = BankingSystem1.accounts.get(accountNumber);
            if (account.verifyPassword(password)) {
                out.println("Login Successful!");
                userMenu(account);
            } else {
                out.println("Incorrect password.");
            }
        } else {
            out.println("Account not found.");
        }
    }

    public static void userMenu(BankAccount account) {
        while (true) {
            out.println(
                    "\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Check Balance\n5. Apply for Loan\n6. Repay Loan\n7. Show Transactions\n8. Update Info\n9. Logout");
            out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    out.print("Enter deposit amount: ");
                    account.deposit(scanner.nextDouble());
                    break;

                case 2:
                    out.print("Enter withdrawal amount: ");
                    account.withdraw(scanner.nextDouble());
                    break;

                case 3:
                    out.print("Enter recipient account number: ");
                    String receiverNumber = scanner.next();
                    if (accounts.containsKey(receiverNumber)) {
                        out.print("Enter transfer amount: ");
                        account.transfer(accounts.get(receiverNumber), scanner.nextDouble());
                    } else {
                        out.println("Recipient account not found.");
                    }
                    break;

                case 4:
                    account.displayUserInfo();
                    break;

                case 5:
                    out.print("Enter loan type: ");
                    String loanType = scanner.next();
                    out.print("Enter loan amount: ");
                    account.applyLoan(loanType, scanner.nextDouble());
                    break;

                case 6:
                    out.print("Enter repayment amount: ");
                    account.repayLoan(scanner.nextDouble());
                    break;

                case 7:
                    account.showTransactionHistory();
                    break;

                case 8:
                    out.print("Enter new address: ");
                    String newAddress = scanner.next();
                    String newPhone = scanner.next();
                    String newEmail = scanner.next();
                    account.updateInfo(newAddress, newPhone, newEmail);
                    break;

                case 9:
                    out.println("Logged out.");
                    return; // Exit the method or loop

                default:
                    out.println("Invalid choice.");
                    break;
            }

            /*
             * switch (choice) {
             * case 1 -> {
             * out.print("Enter deposit amount: ");
             * account.deposit(scanner.nextDouble());
             * }
             * case 2 -> {
             * out.print("Enter withdrawal amount: ");
             * account.withdraw(scanner.nextDouble());
             * }
             * case 3 -> {
             * out.print("Enter recipient account number: ");
             * String receiverNumber = scanner.next();
             * if (accounts.containsKey(receiverNumber)) {
             * out.print("Enter transfer amount: ");
             * account.transfer(accounts.get(receiverNumber), scanner.nextDouble());
             * } else {
             * out.println("Recipient account not found.");
             * }
             * }
             * case 4 -> account.displayUserInfo();
             * case 5 -> {
             * out.print("Enter loan type: ");
             * String loanType = scanner.next();
             * out.print("Enter loan amount: ");
             * account.applyLoan(loanType, scanner.nextDouble());
             * }
             * case 6 -> {
             * out.print("Enter repayment amount: ");
             * account.repayLoan(scanner.nextDouble());
             * }
             * case 7 -> account.showTransactionHistory();
             * case 8 -> {
             * out.print("Enter new address: ");
             * account.updateInfo(scanner.next(), scanner.next(), scanner.next());
             * }
             * case 9 -> {
             * out.println("Logged out.");
             * return;
             * }
             * default -> out.println("Invalid choice.");
             * }
             */
        }
    }

    public static void main(String[] args) {
        while (true) {
            out.println("\n1. Create Account\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            if (choice == 1)
                createAccount();
            else if (choice == 2)
                userLogin();
            else
                break;
        }
    }
}
