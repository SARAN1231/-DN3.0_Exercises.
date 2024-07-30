import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"),
                new Book("B002", "1984", "George Orwell"),
                new Book("B003", "To Kill a Mockingbird", "Harper Lee"),
                new Book("B004", "Pride and Prejudice", "Jane Austen"),
                new Book("B005", "The Catcher in the Rye", "J.D. Salinger")
        );

        LibraryManagementSystem library = new LibraryManagementSystem(books);

        // Linear Search
        System.out.println("Linear Search for '1984':");
        System.out.println(library.linearSearchByTitle("1984"));

        // Binary Search (assuming the list is sorted by title)
        System.out.println("\nBinary Search for '1984':");
        System.out.println(library.binarySearchByTitle("1984"));
    }
}
