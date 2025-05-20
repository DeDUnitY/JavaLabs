package javalab4;

import java.util.List;

public class LibraryTest {
    public static void main(String[] args) {
        // Создаем библиотеку
        Library library = new Library();
        
        // Создаем тестовые книги
        Book book1 = new Book("Shadow Slave", "Guiltythree", 2022);
        Book book2 = new Book("Lord of the Mysteries", "Cuttlefish That Loves Diving", 2018);
        Book book3 = new Book("Ready Player One", "Ernest Cline", 2011);
        Book book4 = new Book("Идиот", "Fyodor Dostoevsky", 1869);
        Book book5 = new Book("1984", "George Orwell", 1949);

        System.out.println("=== Testing adding books ===");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.printAllBooks();

        // Тестируем методы класса Library
        System.out.println("\n Testing search by author ");
        System.out.println("Books by Guiltythree:");
        List<Book> tolstoyBooks = library.findBooksByAuthor("Guiltythree");
        tolstoyBooks.forEach(System.out::println);

        System.out.println("\n Testing search by year ");
        System.out.println("Books of 1869:");
        List<Book> books1869 = library.findBooksByYear(1869);
        books1869.forEach(System.out::println);

        System.out.println("\n Testing all authors ");
        library.printAllBooks();

        System.out.println("\n Testing unique authors ");
        library.printUniqueAuthors();

        System.out.println("\n Testing Author Statistics ");
        library.printAuthorStatistics();

        System.out.println("\n Testing book deletion ");
        System.out.println("Removing Dostoevsky's 'The Idiot'");
        library.removeBook(book4);
        library.printAllBooks();
        System.out.println("\nUpdated statistics:");
        library.printAuthorStatistics();

        // Тестируем краевые случаи
        System.out.println("\n Testing edge cases ");
        System.out.println("Search for a non-existent author:");
        List<Book> unknownAuthor = library.findBooksByAuthor("Alexander Pushkin");
        System.out.println("Books found: " + unknownAuthor.size());

        System.out.println("\nSearch for a non-existent year:");
        List<Book> unknownYear = library.findBooksByYear(2000);
        System.out.println("Books found: " + unknownYear.size());

        System.out.println("\nDeleting a non-existent book:");
        Book fakeBook = new Book("Non-existent book", "Unknown author", 3000);
        int initialCount = library.getAllBooks().size();
        System.out.println("Number of books before attempt to delete: " + library.getAllBooks().size());
        library.removeBook(fakeBook);
        System.out.println("Number of books after attempt to delete: " + library.getAllBooks().size());
        assert library.getAllBooks().size() == initialCount : "Количество книг изменилось!";
        System.out.println("Actual books in library:");
        library.printAllBooks();
    }
}
