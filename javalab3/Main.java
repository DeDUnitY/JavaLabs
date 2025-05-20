package javalab3;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu(); // Вывод главного меню
            System.out.print("Select an option: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine()); // Используем nextLine и парсим в int

                switch (choice) {
                    case 1:
                        handleLogin(authService, scanner); // Обработка входа
                        break;

                    case 2:
                        handleRegistration(authService, scanner); // Обработка регистрации
                        break;

                    case 3:
                        System.out.println("Exit the program.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Incorrect choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number.");
            }

            // Пауза перед следующим шагом
            System.out.println("\nPress Enter to continue.");
            scanner.nextLine();
        }
    }

    // Метод для вывода главного меню
    private static void printMainMenu() {
        System.out.println("------------------------------------");
        System.out.println("Cinema Authorization System");
        System.out.println("1. Entrance");
        System.out.println("2. Registration");
        System.out.println("3. Exit");
        System.out.println("------------------------------------");
    }

    // Метод для обработки входа
    private static void handleLogin(AuthenticationService authService, Scanner scanner) {
        System.out.print("Enter your login: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);
        if (user != null) {
            System.out.println("Successful login!");
            if (user.isAdmin()) {
                handleAdminMenu(authService, scanner); // Меню для администратора
            } else {
                handleUserMenu(authService, scanner); // Меню для пользователя
            }
        } else {
            System.out.println("Incorrect login or password.");
        }
    }

    // Метод для обработки регистрации
    private static void handleRegistration(AuthenticationService  authService, Scanner scanner) {
        System.out.print("Enter a new login: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Are you an administrator? (true/false): ");
        try {
            boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());

            if (authService.register(username, password, isAdmin)) {
                System.out.println("Registration was successful!");
            } else {
                System.out.println("A user with this login already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error: Enter true or false.");
        }
    }

    // Меню для администратора
    private static void handleAdminMenu(AuthenticationService  authService, Scanner scanner) {
        while (true) {
            System.out.println("\n-------------------------------------");
            System.out.println("Administrator menu");
            System.out.println("1. Add a cinema");
            System.out.println("2. View list of cinemas");
            System.out.println("3. Add a hall to a cinema");
            System.out.println("4. Create session");
            System.out.println("5. Exit to the main menu");
            System.out.println("-------------------------------------");
            System.out.print("Select an option: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name of the cinema: ");
                        String cinemaName = scanner.nextLine();
                        System.out.print("Enter the address of the cinema: ");
                        String cinemaAddress = scanner.nextLine();
                        authService.addCinema(cinemaName, cinemaAddress);
                        System.out.println("Cinema successfully added!");
                        break;

                    case 2:
                        List<Cinema> cinemas = authService.getCinemas();
                        if (cinemas.isEmpty()) {
                            System.out.println("The list of cinemas is empty.");
                        } else {
                            System.out.println("List of cinemas:");
                            for (Cinema cinema : cinemas) {
                                System.out.println(cinema);
                                List<Hall> halls = cinema.getHalls();
                                if (!halls.isEmpty()) {
                                    System.out.println("Halls:");
                                    for (Hall hall : halls) {
                                        System.out.println("  " + hall);
                                    }
                                }
                                List<Session> sessions = cinema.getSessions();
                                if (!sessions.isEmpty()) {
                                    System.out.println("Sessions:");
                                    for (Session session : sessions) {
                                        System.out.println("  " + session);
                                    }
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter the name of the cinema: ");
                        String cinemaNameForHall = scanner.nextLine();
                        Cinema cinemaForHall = authService.findCinemaByName(cinemaNameForHall);
                        if (cinemaForHall != null) {
                            System.out.print("Enter the name of the hall: ");
                            String hallName = scanner.nextLine();
                            System.out.print("Enter the number of rows: ");
                            int rows = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the number of seats per row: ");
                            int seatsPerRow = Integer.parseInt(scanner.nextLine());
                            cinemaForHall.addHall(hallName, rows, seatsPerRow);
                            System.out.println("Hall added successfully!");
                        } else {
                            System.out.println("Cinema not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter the name of the cinema: ");
                        String cinemaNameForSession = scanner.nextLine();
                        Cinema cinemaForSession = authService.findCinemaByName(cinemaNameForSession);
                        if (cinemaForSession != null) {
                            System.out.print("Enter movie title: ");
                            String movieName = scanner.nextLine();
                            System.out.print("Enter the session start time (HH:MM): ");
                            String startTime = scanner.nextLine();
                            System.out.print("Enter the length of the movie (in minutes): ");
                            int durationMinutes = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the name of the hall: ");
                            String hallNameForSession = scanner.nextLine();
                            Hall hallForSession = null;
                            for (Hall hall : cinemaForSession.getHalls()) {
                                if (hall.getName().equalsIgnoreCase(hallNameForSession)) {
                                    hallForSession = hall;
                                    break;
                                }
                            }
                            if (hallForSession != null) {
                                cinemaForSession.addSession(movieName, startTime, durationMinutes, hallForSession);
                                System.out.println("Session successfully created!");
                            } else {
                                System.out.println("Hall not found.");
                            }
                        } else {
                            System.out.println("Cinema not found.");
                        }
                        break;

                    case 5:
                        return; // Выход в главное меню

                    default:
                        System.out.println("Incorrect choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a number.");
            }

            // Пауза перед следующим шагом
            System.out.println("\nPress Enter to continue");
            scanner.nextLine();
        }
    }

    // Меню для пользователя
    private static void handleUserMenu(AuthenticationService  authService, Scanner scanner) {
        while (true) {
            System.out.println("\n----------------------------------");
            System.out.println("User menu");
            System.out.println("1. View list of cinemas");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Find the nearest show with available seats");
            System.out.println("4. Exit to the main menu");
            System.out.println("----------------------------------");
            System.out.print("Select an option: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
    
                switch (choice) {
                    case 1:
                        List<Cinema> cinemas = authService.getCinemas();
                        if (cinemas.isEmpty()) {
                            System.out.println("The list of cinemas is empty.");
                        } else {
                            System.out.println("List of cinemas:");
                            for (Cinema cinema : cinemas) {
                                System.out.println(cinema);
                                List<Session> sessions = cinema.getSessions();
                                if (!sessions.isEmpty()) {
                                    System.out.println("Sessions:");
                                    for (Session session : sessions) {
                                        System.out.println("  " + session);
                                        cinema.printHallPlan(session); // План зала
                                    }
                                }
                            }
                        }
                        break;
    
                    case 2:
                        System.out.print("Enter the name of the cinema: ");
                        String cinemaName = scanner.nextLine().trim(); // Убираем лишние пробелы
                        Cinema cinema = authService.findCinemaByName(cinemaName);
                        if (cinema != null) {
                            System.out.println("Cinema found: " + cinema.getName());
                            List<Session> sessions = cinema.getSessions();
                            if (sessions.isEmpty()) {
                                System.out.println("No sessions available.");
                            } else {
                                System.out.println("Available sessions:");
                                for (int i = 0; i < sessions.size(); i++) {
                                    System.out.println((i + 1) + ". " + sessions.get(i)); // Информация о сеансе
                                }
                                System.out.print("Select session: ");
                                int sessionIndex = Integer.parseInt(scanner.nextLine()) - 1;
                                if (sessionIndex >= 0 && sessionIndex < sessions.size()) {
                                    Session session = sessions.get(sessionIndex);
                                    System.out.println("Information about the hall:");
                                    System.out.println("  " + session); // Параметры зала
                                    cinema.printHallPlan(session); // План зала
    
                                    System.out.print("Enter row number: ");
                                    int row = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Enter seat number: ");
                                    int seat = Integer.parseInt(scanner.nextLine());
                                    if (cinema.buyTicket(session, row, seat)) {
                                        System.out.println("Ticket purchased successfully!");
                                    } else {
                                        System.out.println("Failed to purchase a ticket. The seat may already be taken or may not exist.");
                                    }
                                } else {
                                    System.out.println("Invalid session selection.");
                                }
                            }
                        } else {
                            System.out.println("Cinema not found.");
                        }
                        break;
    
                    case 3:
                        System.out.print("Enter movie title: ");
                        String movieName = scanner.nextLine().trim(); // Убираем лишние пробелы
                        Session nearestSession = authService.findNearestSessionWithAvailableSeats(movieName);
                        if (nearestSession != null) {
                            System.out.println("The nearest session with available seats:");
                            System.out.println("  Movie: " + nearestSession.getMovieName());
                            System.out.println("  Start: " + nearestSession.getStartTime());
                            System.out.println("  Duration: " + nearestSession.getDurationMinutes() + " min.");
                            System.out.println("  Cinema: " + nearestSession.getHall().getCinema().getName());
                            System.out.println("  Hall: " + nearestSession.getHall().getName());
                            System.out.println("  Hall plan:");
                            nearestSession.getHall().getCinema().printHallPlan(nearestSession);
                        } else {
                            System.out.println("There are no available screenings for the selected movie.");
                        }
                        break;
    
                    case 4:
                        return; // Выход в главное меню
    
                    default:
                        System.out.println("Incorrect choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a number.");
            }
    
            // Пауза перед следующим шагом
            System.out.println("\nPress Enter to continue.");
            scanner.nextLine();
        }
    }
}