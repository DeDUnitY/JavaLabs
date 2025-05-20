package javalab3;

public class Session {
    private String movieName;
    private String startTime; // Время начала сеанса в формате "HH:MM"
    private int durationMinutes; // Длительность фильма в минутах
    private Hall hallDetails;

    public Session(String movieName, String startTime, int durationMinutes, Hall hallDetails) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.hallDetails = hallDetails;
    }

    // Геттер для названия фильма
    public String getMovieName() {
        return movieName;
    }

    // Геттер для времени начала сеанса
    public String getStartTime() {
        return startTime;
    }

    // Геттер для длительности фильма
    public int getDurationMinutes() {
        return durationMinutes;
    }

    // Геттер для информации о зале
    public Hall getHall() {
        return hallDetails;
    }

    @Override
    public String toString() {
        return "Movie: " + movieName + ", Start: " + startTime + ", Duration: " + durationMinutes + " min., " +
                "Hall: " + hallDetails.getName() + " (Rows: " + hallDetails.getRows() + ", Seats in row: " + hallDetails.getSeatsPerRow() + ")";
    }
}