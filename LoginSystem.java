import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSystem {
    private ArrayList<User> users;
    private static final String FILE_PATH = "C:\\My_Data\\Coding\\projectJKT48\\user_data.txt";

    // Konstruktor
    public LoginSystem() {
        this.users = loadUsersFromFile();
    }

    public void addUser(String username, String password) {
        if (isValidUsername(username) && isValidPassword(password)) {
            User newUser = new User(username, password);
            users.add(newUser);
            saveUsersToFile();
            System.out.println("Pengguna berhasil ditambahkan!");
        } else {
            System.out.println("Username harus memiliki minimal 8 karakter dan password minimal 6 karakter dengan mengandung huruf besar, huruf kecil, dan angka.");
        }
    }

        private boolean isValidUsername(String username) {
        return username.length() >= 8;
    }

    private boolean isValidPassword(String password) {
        // Memeriksa apakah password memiliki minimal 6 karakter, huruf besar, huruf kecil, dan angka
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

        private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
            System.out.println("Data pengguna berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data pengguna ke file: " + e.getMessage());
        }
    }

    private ArrayList<User> loadUsersFromFile() {
        ArrayList<User> loadedUsers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 2) {
                    String username = userData[0];
                    String password = userData[1];
                    loadedUsers.add(new User(username, password));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data pengguna dari file: " + e.getMessage());
        }
        return loadedUsers;
    }

    // Metode untuk memeriksa kredensial pengguna
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.checkCredentials(username, password)) {
                return true;
            }
        }
        return false;
    }
}