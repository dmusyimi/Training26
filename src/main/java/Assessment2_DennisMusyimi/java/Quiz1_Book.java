package Assessment2_DennisMusyimi.java;

import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private boolean isAvailable;


    public Book(String title, String author, String isbn, double price) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPrice(price);
        this.isAvailable = true;
    }


    public Book() {
        this.isAvailable = true;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.replaceAll("-", "").length() != 13 || !isbn.replaceAll("-", "").matches("\\d+")) {
            throw new IllegalArgumentException("ISBN must be exactly 13 digits");
        }
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book '" + title + "' has been borrowed successfully.");
            return true;
        } else {
            System.out.println("Book '" + title + "' is not available for borrowing.");
            return false;
        }
    }

    // returnBook method
    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Book '" + title + "' has been returned successfully.");
            return true;
        } else {
            System.out.println("Book '" + title + "' was not borrowed.");
            return false;
        }
    }


    @Override
    public String toString() {
        return "Book Details:\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "ISBN: " + isbn + "\n" +
                "Price: $" + String.format("%.2f", price) + "\n" +
                "Available: " + (isAvailable ? "Yes" : "No");
    }
}

public class Quiz1_Book {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Book book = new Book();

            System.out.println("Enter the title of the book: ");
            book.setTitle(scanner.nextLine());

            System.out.println("Enter the author of the book: ");
            book.setAuthor(scanner.nextLine());

            System.out.println("Enter the ISBN of the book (13 digits): ");
            book.setIsbn(scanner.nextLine());

            System.out.println("Enter the price of the book: ");
            book.setPrice(scanner.nextDouble());
            scanner.nextLine();

            System.out.println("\n" + book);

            // Demonstrate borrowBook and returnBook
            System.out.println("\n--- Testing Borrow/Return ---");
            book.borrowBook();
            System.out.println("\nCurrent Status:");
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));

            book.returnBook();
            System.out.println("\nCurrent Status:");
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}