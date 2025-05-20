package javalab3;

public class Hall {
    private String hallName;
    private int numberOfRows;
    private int seatsPerRow;
    private Cinema cinema; // Ссылка на кинотеатр

    public Hall(String hallName, int numberOfRows, int seatsPerRow, Cinema cinema) {
        this.hallName = hallName;
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
        this.cinema = cinema;
    }

    // Геттер для названия зала
    public String getName() {
        return hallName;
    }

    // Геттер для количества рядов
    public int getRows() {
        return numberOfRows;
    }

    // Геттер для количества мест в ряду
    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    // Метод для расчета вместимости зала
    public int getCapacity() {
        return numberOfRows * seatsPerRow;
    }

    // Геттер для кинотеатра, к которому принадлежит зал
    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public String toString() {
        return "Hall: " + hallName + ", Rows: " + numberOfRows + ", Seats in a row: " + seatsPerRow + ", Capacity: " + getCapacity() + " seat";
    }
}