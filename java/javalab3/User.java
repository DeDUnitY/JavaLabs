package javalab3;

public class User {
    private String username;
    private String password;
    private boolean isAdminUser;

    public User(String username, String password, boolean isAdminUser) {
        this.username = username;
        this.password = password;
        this.isAdminUser = isAdminUser;
    }

    // Геттер для имени пользователя
    public String getUsername() {
        return username;
    }

    // Геттер для пароля
    public String getPassword() {
        return password;
    }

    // Метод для проверки, является ли пользователь администратором
    public boolean isAdmin() {
        return isAdminUser;
    }
}