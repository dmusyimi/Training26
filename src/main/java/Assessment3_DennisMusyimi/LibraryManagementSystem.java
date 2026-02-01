package Assessment3_DennisMusyimi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Base class LibraryItem
abstract class LibraryItem {
    protected String itemId;
    protected String title;
    protected String author;
    protected boolean isAvailable;

    // Constructor
    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Items are available by default
    }

    // Method to checkout an item
    public boolean checkout() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Item '" + title + "' has been checked out successfully.");
            return true;
        } else {
            System.out.println("Item '" + title + "' is not available for checkout.");
            return false;
        }
    }

    // Method to return an item
    public boolean returnItem() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Item '" + title + "' has been returned successfully.");
            return true;
        } else {
            System.out.println("Item '" + title + "' was not checked out.");
            return false;
        }
    }

    // Abstract method to display details (to be implemented by subclasses)
    public abstract void displayDetails();

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

// Book class extending LibraryItem
class Book extends LibraryItem {
    private String isbn;
    private String genre;

    // Constructor
    public Book(String itemId, String title, String author, String isbn, String genre) {
        super(itemId, title, author);
        this.isbn = isbn;
        this.genre = genre;
    }

    @Override
    public void displayDetails() {
        System.out.println("=== BOOK DETAILS ===");
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("==================");
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }
}

// DVD class extending LibraryItem
class DVD extends LibraryItem {
    private int duration; // Duration in minutes
    private String rating;

    // Constructor
    public DVD(String itemId, String title, String author, int duration, String rating) {
        super(itemId, title, author);
        this.duration = duration;
        this.rating = rating;
    }

    @Override
    public void displayDetails() {
        System.out.println("=== DVD DETAILS ===");
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Director: " + author);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Rating: " + rating);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("==================");
    }

    // Getters
    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }
}

// Magazine class extending LibraryItem
class Magazine extends LibraryItem {
    private int issueNumber;
    private LocalDate publicationDate;

    // Constructor
    public Magazine(String itemId, String title, String author, int issueNumber, LocalDate publicationDate) {
        super(itemId, title, author);
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
    }

    @Override
    public void displayDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        System.out.println("=== MAGAZINE DETAILS ===");
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Publisher: " + author);
        System.out.println("Issue Number: " + issueNumber);
        System.out.println("Publication Date: " + publicationDate.format(formatter));
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("=======================");
    }

    // Getters
    public int getIssueNumber() {
        return issueNumber;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM DEMO ===\n");

        // Create library items
        Book book1 = new Book("B001", "To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", "Fiction");
        DVD dvd1 = new DVD("D001", "The Shawshank Redemption", "Frank Darabont", 142, "R");
        Magazine magazine1 = new Magazine("M001", "National Geographic", "National Geographic Society", 
                                        12, LocalDate.of(2023, 12, 1));

        // Display details of all items
        book1.displayDetails();
        System.out.println();
        dvd1.displayDetails();
        System.out.println();
        magazine1.displayDetails();
        System.out.println();

        // Demonstrate checkout and return functionality
        System.out.println("=== CHECKOUT/RETURN OPERATIONS ===");

        // Checkout items
        book1.checkout();
        dvd1.checkout();
        magazine1.checkout();
        System.out.println();

        // Try to checkout already checked out items
        book1.checkout();
        System.out.println();

        // Return items
        book1.returnItem();
        dvd1.returnItem();
        System.out.println();

        // Try to return already returned item
        book1.returnItem();
        System.out.println();

        // Display updated details
        System.out.println("=== UPDATED ITEM STATUS ===");
        book1.displayDetails();
        System.out.println();
        dvd1.displayDetails();
        System.out.println();
        magazine1.displayDetails();

        // Demonstrate polymorphism
        System.out.println("\n=== POLYMORPHISM DEMO ===");
        LibraryItem[] libraryItems = {book1, dvd1, magazine1};

        for (LibraryItem item : libraryItems) {
            System.out.println("Processing item: " + item.getTitle());
            item.displayDetails();
            System.out.println();
        }
    }
}
