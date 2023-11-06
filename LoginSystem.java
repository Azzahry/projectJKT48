import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class LoginSystem {
    private ArrayList<User> users;
    private static Scanner in = new Scanner(System.in);
    private static Ticketing ticketing = new Ticketing();
    private static MemberBio memberBio = new MemberBio();
    private static final String FILE_PATH = "C:\\My_Data\\Coding\\projectJKT48\\user_data.txt"; //Sesuaikan dengan file path anda 

    // Konstruktor
    public LoginSystem() {
        this.users = loadUsersFromFile();
    }

    public void addUser(String userId, String username, String password) {
        if (isValidUserId(userId) && isValidUsername(username) && isValidPassword(password)) {
            User newUser = new User(userId, username, password);
            users.add(newUser);
            saveUsersToFile();
            System.out.println("Pengguna berhasil ditambahkan!");
        } else {
            System.out.println("Username harus memiliki minimal 8 karakter dan password minimal 6 karakter dengan mengandung huruf besar, huruf kecil, dan angka.");
        }
    }

    private boolean isValidUserId(String userId){
        return userId.length() >= 4;
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
                writer.write(user.getUserId() + "," + user.getUsername() + "," + user.getPassword());
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
                if (userData.length == 3) {
                    String userId = userData[0];
                    String username = userData[1];
                    String password = userData[2];
                    loadedUsers.add(new User(userId, username, password));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data pengguna dari file: " + e.getMessage());
        }
        return loadedUsers;
    }

    // Metode untuk memeriksa kredensial pengguna
    public boolean authenticateUser(String userId, String password) {
        for (User user : users) {
            if (user.checkCredentials(userId, password)) {
                return true;
            }
        }
        return false;
    }

    public void login() {

        System.out.print("Masukkan User Id: ");
        String userId = in.nextLine();
        
        System.out.print("Masukkan Password: ");
        String password = in.nextLine();

        if(authenticateUser(userId, password)){
            System.out.println("Login berhasil!! Selamat Datang, " + userId);
            menuLogin();
        }else{
            System.out.println("Login gagal. Silahkan Cek kembali username dan password anda");
        }

    }

    public void menuLogin(){
        while(true){
            System.out.println("Pilih opsi : ");
            System.out.println("1. Biografi Member\n2. Beli Ticket\n3. Log Out");
            System.out.print("Option : ");
            int menu = in.nextInt();
            in.nextLine(); // Membersihkan newline karakter dari buffer
            System.out.println("----------------------------------");

            switch (menu) {
                case 1:
                    memberBio.biografi();
                    break;
                case 2:
                    ticketing.ticketSystem();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi JKT 48. Sampai jumpa lagi!");
                    return; // Keluar dari program
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    public void signUp() {

        System.out.println("Ingin menambahkan pengguna baru? (ya/tidak): ");
        String choose = in.nextLine();
        if(choose.equalsIgnoreCase("ya")){
            System.out.print("Masukkan user id baru: ");
            String newUserId = in.nextLine();
            System.out.print("Masukkan username baru: ");
            String newUsername = in.nextLine();
            System.out.print("Masukkan password baru: ");
            String newPassword = in.nextLine();
            addUser(newUserId, newUsername, newPassword); 
        }
    }
}