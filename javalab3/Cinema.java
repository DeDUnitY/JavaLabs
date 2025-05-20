package javalab3;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String cinemaName;
    private String cinemaAddress;
    private List<Hall> hallList;
    private List<Session> sessionList;
    private List<Ticket> ticketList;

    public Cinema(String cinemaName, String cinemaAddress) {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.hallList = new ArrayList<>();
        this.sessionList = new ArrayList<>();
        this.ticketList = new ArrayList<>();
    }

    // Геттер для названия кинотеатра
    public String getName() {
        return cinemaName;
    }

    // Геттер для адреса кинотеатра
    public String getAddress() {
        return cinemaAddress;
    }

    // Метод для добавления зала
    public void addHall(String hallName, int numberOfRows, int seatsPerRow) {
        hallList.add(new Hall(hallName, numberOfRows, seatsPerRow, this)); // Передаем текущий кинотеатр
    }

    // Геттер для списка залов
    public List<Hall> getHalls() {
        return hallList;
    }

    // Метод для добавления сеанса
    public void addSession(String movieName, String startTime, int durationMinutes, Hall hall) {
        sessionList.add(new Session(movieName, startTime, durationMinutes, hall));
    }

    // Геттер для списка сеансов
    public List<Session> getSessions() {
        return sessionList;
    }

    // Метод для покупки билета
    public boolean buyTicket(Session session, int rowNumber, int seatNumber) {
        if (rowNumber < 1 || rowNumber > session.getHall().getRows() || seatNumber < 1 || seatNumber > session.getHall().getSeatsPerRow()) {
            return false; // Место не существует
        }
        for (Ticket ticket : ticketList) {
            if (ticket.getSession().equals(session) && ticket.getRow() == rowNumber && ticket.getSeat() == seatNumber) {
                return false; // Место уже занято
            }
        }
        ticketList.add(new Ticket(session, rowNumber, seatNumber));
        return true;
    }

    // Геттер для списка билетов
    public List<Ticket> getTickets() {
        return ticketList;
    }

    // Метод для печати плана зала
    public void printHallPlan(Session session) {
        Hall hall = session.getHall();
        int numberOfRows = hall.getRows();
        int seatsPerRow = hall.getSeatsPerRow();

        System.out.println("Hall plan '" + hall.getName() + "':");
        System.out.print("   ");
        for (int seat = 1; seat <= seatsPerRow; seat++) {
            System.out.printf("%2d ", seat); // Номера мест
        }
        System.out.println();

        for (int row = 1; row <= numberOfRows; row++) {
            System.out.printf("%2d ", row); // Номер ряда
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                boolean isOccupied = false;
                for (Ticket ticket : ticketList) {
                    if (ticket.getSession().equals(session) && ticket.getRow() == row && ticket.getSeat() == seat) {
                        isOccupied = true;
                        break;
                    }
                }
                System.out.print(isOccupied ? " X " : " O "); // X - занято, O - свободно
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Cinema: " + cinemaName + ", Address: " + cinemaAddress;
    }
}