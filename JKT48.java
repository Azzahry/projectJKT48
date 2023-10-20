import java.util.Scanner;

public class JKT48 {
    private static Scanner in = new Scanner(System.in);
    private static LoginSystem loginSystem = new LoginSystem();

    public static void login() {

        System.out.print("Masukkan Username: ");
        String username = in.nextLine();
        
        System.out.print("Masukkan Password: ");
        String password = in.nextLine();

        if(loginSystem.authenticateUser(username, password)){
            System.out.println("Login berhasil!! Selamat Datang, " +username);
        }else{
            System.out.println("Login gagal. Silahkan Cek kembali username dan password anda");
        }

    }

    public static void signUp() {

        System.out.println("Ingin menambahkan pengguna baru? (ya/tidak): ");
        String choose = in.nextLine();
        if(choose.equalsIgnoreCase("ya")){
            System.out.print("Masukkan username baru: ");
            String newUsername = in.nextLine();
            System.out.print("Masukkan password baru: ");
            String newPassword = in.nextLine();
            loginSystem.addUser(newUsername, newPassword); 
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("==========================");
            System.out.println("Selamat Datang di JKT 48");
            System.out.println("Pilih opsi : ");
            System.out.println("1. Login\n2. Sign Up\n3. Exit");
            System.out.print("Option : ");
            int menu = in.nextInt();
            in.nextLine(); // Membersihkan newline karakter dari buffer

            switch (menu) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
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
}
