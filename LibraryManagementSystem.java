package com.test.example;
import java.util.*;

public class LibraryManagementSystem {

    // Book class to hold book details
    static class Book {
        String title;
        String author;
        String isbn;
        boolean isBorrowed;

        Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isBorrowed = false;
        }
    }

    // User class to hold user details
    static class User {
        String userId;
        String name;
        List<String> borrowedBooks;

        User(String userId, String name) {
            this.userId = userId;
            this.name = name;
            this.borrowedBooks = new ArrayList<>();
        }
    }

    Map<String, Book> books = new HashMap<>();
    Map<String, User> users = new HashMap<>();

    public void addBook(String title, String author, String isbn) {
        books.put(isbn, new Book(title, author, isbn));
        System.out.println("Book added successfully!");
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
        System.out.println("Book removed.");
    }

    public void registerUser(String userId, String name) {
        users.put(userId, new User(userId, name));
        System.out.println("User registered.");
    }

    public void borrowBook(String userId, String isbn) {
        if (users.containsKey(userId) && books.containsKey(isbn)) {
            Book book = books.get(isbn);
            if (!book.isBorrowed) {
                book.isBorrowed = true;
                users.get(userId).borrowedBooks.add(isbn);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Invalid user ID or ISBN.");
        }
    }

    public void returnBook(String userId, String isbn) {
        if (users.containsKey(userId)) {
            Book book = books.get(isbn);
            if (book != null && book.isBorrowed) {
                book.isBorrowed = false;
                users.get(userId).borrowedBooks.remove(isbn);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("This book is not currently borrowed.");
            }
        } else {
            System.out.println("Invalid user ID.");
        }
    }

    public void viewAvailableBooks() {
        for (Book book : books.values()) {
            if (!book.isBorrowed) {
                System.out.println(book.title + " by " + book.author + " [ISBN: " + book.isbn + "]");
            }
        }
    }

    public void viewBorrowedBooks(String userId) {
        User user = users.get(userId);
        if (user != null) {
            for (String isbn : user.borrowedBooks) {
                Book book = books.get(isbn);
                System.out.println(book.title + " by " + book.author);
            }
        }
    }

    public void searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + book.title + " - " + book.isbn);
            }
        }
    }

    public void searchBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + book.title + " - " + book.isbn);
            }
        }
    }

    public void searchBookByISBN(String isbn) {
        Book book = books.get(isbn);
        if (book != null) {
            System.out.println("Found: " + book.title + " by " + book.author);
        } else {
            System.out.println("Book not found.");
        }
    }

    public void displayAllUsers() {
        for (User user : users.values()) {
            System.out.println(user.userId + " - " + user.name);
        }
    }

    public void updateBookTitle(String isbn, String newTitle) {
        Book book = books.get(isbn);
        if (book != null) {
            book.title = newTitle;
            System.out.println("Book title updated.");
        }
    }

    public void updateBookAuthor(String isbn, String newAuthor) {
        Book book = books.get(isbn);
        if (book != null) {
            book.author = newAuthor;
            System.out.println("Book author updated.");
        }
    }

    public void updateUser(String userId, String newName) {
        User user = users.get(userId);
        if (user != null) {
            user.name = newName;
            System.out.println("User updated.");
        }
    }

    public void deleteUser(String userId) {
        users.remove(userId);
        System.out.println("User deleted.");
    }

    public int countAvailableBooks() {
        int count = 0;
        for (Book book : books.values()) {
            if (!book.isBorrowed) count++;
        }
        return count;
    }

    public int countBorrowedBooks(String userId) {
        User user = users.get(userId);
        return (user != null) ? user.borrowedBooks.size() : 0;
    }

    public List<String> getAllBorrowedBooks() {
        List<String> borrowed = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isBorrowed) {
                borrowed.add(book.title);
            }
        }
        return borrowed;
    }

    public void printBookDetails(String isbn) {
        Book book = books.get(isbn);
        if (book != null) {
            System.out.println("Title: " + book.title + ", Author: " + book.author + ", Borrowed: " + book.isBorrowed);
        }
    }

    public void printUserDetails(String userId) {
        User user = users.get(userId);
        if (user != null) {
            System.out.println("User: " + user.name + ", Borrowed books: " + user.borrowedBooks.size());
        }
    }

    public void clearAllData() {
        books.clear();
        users.clear();
        System.out.println("All data cleared.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Available Books");
            System.out.println("6. View Borrowed Books");
            System.out.println("7. Search Book by Title");
            System.out.println("8. Search Book by Author");
            System.out.println("9. Search Book by ISBN");
            System.out.println("10. Display All Users");
            System.out.println("11. Update Book Title");
            System.out.println("12. Update Book Author");
            System.out.println("13. Update User Name");
            System.out.println("14. Delete User");
            System.out.println("15. Count Available Books");
            System.out.println("16. Count Borrowed Books");
            System.out.println("17. Print Book Details");
            System.out.println("18. Print User Details");
            System.out.println("19. Clear All Data");
            System.out.println("20. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    lib.addBook(title, author, isbn);
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    lib.registerUser(userId, name);
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    lib.borrowBook(userId, isbn);
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    lib.returnBook(userId, isbn);
                    break;

                case 5:
                    lib.viewAvailableBooks();
                    break;

                case 6:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    lib.viewBorrowedBooks(userId);
                    break;

                case 7:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    lib.searchBookByTitle(title);
                    break;

                case 8:
                    System.out.print("Enter Author: ");
                    author = sc.nextLine();
                    lib.searchBookByAuthor(author);
                    break;

                case 9:
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    lib.searchBookByISBN(isbn);
                    break;

                case 10:
                    lib.displayAllUsers();
                    break;

                case 11:
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    System.out.print("Enter New Title: ");
                    title = sc.nextLine();
                    lib.updateBookTitle(isbn, title);
                    break;

                case 12:
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    System.out.print("Enter New Author: ");
                    author = sc.nextLine();
                    lib.updateBookAuthor(isbn, author);
                    break;

                case 13:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    System.out.print("Enter New Name: ");
                    name = sc.nextLine();
                    lib.updateUser(userId, name);
                    break;

                case 14:
                    System.out.print("Enter User ID to Delete: ");
                    userId = sc.nextLine();
                    lib.deleteUser(userId);
                    break;

                case 15:
                    System.out.println("Available Books: " + lib.countAvailableBooks());
                    break;

                case 16:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    System.out.println("Borrowed Books: " + lib.countBorrowedBooks(userId));
                    break;

                case 17:
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    lib.printBookDetails(isbn);
                    break;

                case 18:
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    lib.printUserDetails(userId);
                    break;

                case 19:
                    lib.clearAllData();
                    break;

                case 20:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
