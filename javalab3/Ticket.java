package javalab3;

public class Ticket {
    private Session sessionDetails;
    private int rowNumber;
    private int seatNumber;

    public Ticket(Session sessionDetails, int rowNumber, int seatNumber) {
        this.sessionDetails = sessionDetails;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    // Геттер для информации о сеансе
    public Session getSession() {
        return sessionDetails;
    }

    // Геттер для номера ряда
    public int getRow() {
        return rowNumber;
    }

    // Геттер для номера места
    public int getSeat() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Movie ticket: " + sessionDetails.getMovieName() + ", Hall: " + sessionDetails.getHall().getName() +
                ", Row: " + rowNumber + ", Seat: " + seatNumber + ", Time: " + sessionDetails.getStartTime();
    }
}