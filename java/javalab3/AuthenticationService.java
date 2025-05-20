package javalab3;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> userList;
    private List<Cinema> cinemaList;

   

    public AuthenticationService() {
        userList = new ArrayList<>();
        cinemaList = new ArrayList<>();
    
        // Добавляем тестовых пользователей
        userList.add(new User("admin", "admin_pass", true));
        userList.add(new User("user", "user_pass", false));
    
        // Добавляем тестовые кинотеатры и залы
        Cinema cinema1 = new Cinema("Cinema Park", "Europe Mall, Professor Baranova St., 40");
        cinema1.addHall("Main Hall", 12, 28); // 12 рядов по 28 мест в ряду
        cinema1.addHall("Small Hall", 4, 10);  // 4 ряда по 10 мест в ряду
        cinemaList.add(cinema1);
    
        Cinema cinema2 = new Cinema("Epicenter", "Professor Baranova St., 30");
        cinema2.addHall("Hall 1", 10, 21); // 10 рядов по 21 мест в ряду
        cinema2.addHall("Hall 2", 14, 25); // 14 рядов по 25 мест в ряду
        cinemaList.add(cinema2);
    
        // Добавляем тестовые сеансы
        cinema1.addSession("The Prophet. The Story of Alexander Pushkin", "17:30", 137, cinema1.getHalls().get(0)); // Main Hall
        cinema1.addSession("Castle in the Sky", "13:00", 124, cinema1.getHalls().get(1)); // Small Hall
        cinema2.addSession("Catherine the Great", "19:00", 180, cinema2.getHalls().get(0)); // Hall 1
    }
    

    // Метод для поиска ближайшего сеанса с свободными местами
    public Session findNearestSessionWithAvailableSeats(String movieName) {
        Session nearestSession = null;
        for (Cinema cinema : cinemaList) {
            for (Session session : cinema.getSessions()) {
                if (session.getMovieName().trim().equalsIgnoreCase(movieName.trim())) {
                    // Проверяем, есть ли свободные места
                    long occupiedSeats = cinema.getTickets().stream().filter(t -> t.getSession().equals(session)).count();
                    if (occupiedSeats < session.getHall().getCapacity()) {
                        if (nearestSession == null || session.getStartTime().compareTo(nearestSession.getStartTime()) < 0) {
                            nearestSession = session;
                        }
                    }
                }
            }
        }
        return nearestSession;
    }

    // Метод для входа пользователя
    public User login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Пользователь найден
            }
        }
        return null; // Пользователь не найден
    }

    // Метод для регистрации нового пользователя
    public boolean register(String username, String password, boolean isAdmin) {
        // Проверяем, существует ли пользователь с таким логином
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return false; // Пользователь уже существует
            }
        }
        // Добавляем нового пользователя
        userList.add(new User(username, password, isAdmin));
        return true;
    }

    // Метод для добавления нового кинотеатра
    public void addCinema(String name, String address) {
        cinemaList.add(new Cinema(name, address));
    }

    // Метод для получения списка кинотеатров
    public List<Cinema> getCinemas() {
        return cinemaList;
    }

    // Метод для поиска кинотеатра по названию
    public Cinema findCinemaByName(String name) {
        String trimmedName = name.trim();
        for (Cinema cinema : cinemaList) {
            if (cinema.getName().trim().equalsIgnoreCase(trimmedName)) {
                return cinema;
            }
        }
        return null;
    }
}