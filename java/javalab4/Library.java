package javalab4;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private Set<String> uniqueAuthors;
    private Map<String, Integer> authorCounts;

    public Library() {
        books = new ArrayList<>();
        uniqueAuthors = new HashSet<>();
        authorCounts = new HashMap<>();
    }

    // Метод для добавления книги в библиотеку
    public void addBook(Book book) {
        books.add(book);
        uniqueAuthors.add(book.getAuthor());
        authorCounts.put(book.getAuthor(), 
                        authorCounts.getOrDefault(book.getAuthor(), 0) + 1);
    }

    // Метод для удаления книги из библиотеки
    public void removeBook(Book book) {
        if (books.remove(book)) {
            // Уменьшаем счетчик книг автора
            int count = authorCounts.getOrDefault(book.getAuthor(), 0);
            if (count > 1) {
                authorCounts.put(book.getAuthor(), count - 1);
            } else {
                authorCounts.remove(book.getAuthor());
                uniqueAuthors.remove(book.getAuthor());
            }
        }
    }

    //  Метод для нахождения количества книг
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(new ArrayList<>(books));
    }

    // Метод для нахождения всех книг определенного автора
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                   .filter(book -> book.getAuthor().equals(author))
                   .collect(Collectors.toList());
    }

    // Метод для нахождения всех книг, изданных в определенный год
    public List<Book> findBooksByYear(int year) {
        return books.stream()
                   .filter(book -> book.getYear() == year)
                   .collect(Collectors.toList());
    }

    // Метод для вывода списка всех книг в библиотеке
    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("There are no books in the library.");
            return;
        }
        System.out.println("List of all books in the library:");
        books.forEach(System.out::println);
    }

    // Метод для вывода списка уникальных авторов
    public void printUniqueAuthors() {
        if (uniqueAuthors.isEmpty()) {
            System.out.println("There are no authors in the library.");
            return;
        }
        System.out.println("Unique authors in the library:");
        uniqueAuthors.forEach(System.out::println);
    }

    // Метод для вывода статистики по количеству книг каждого автора
    public void printAuthorStatistics() {
        if (authorCounts.isEmpty()) {
            System.out.println("No data for statistics.");
            return;
        }
        System.out.println("Statistics by Authors:");
        authorCounts.forEach((author, count) -> 
            System.out.println(author + ": " + count + " book(s)"));
    }
}
