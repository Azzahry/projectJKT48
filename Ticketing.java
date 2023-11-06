import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ticketing {
    private static Scanner in = new Scanner(System.in);
    private List<Ticket> tickets;

    public Ticketing(){
        tickets =loadTicketsFromFile("C:\\\\My_Data\\\\Coding\\\\projectJKT48\\\\ticket_data.txt"); //Sesuaikan dengan file path anda 
    }

    public List<Ticket> getAvailableTickets(){
        return tickets;
    }

    public Ticket purchaseTicket(int index, int quantity){
        Ticket ticket = tickets.get(index);
        if (ticket.getAvailableTickets() >= quantity){
            boolean won = performLottery();

            if(won){
                System.out.println("Selamat anda mendapatkan tiket dan berhasil verifikasi!");
                ticket.decreaseAvailTikcets(quantity);
                updateTicketsFile();
            }else{
                System.out.println("Maaf, anda tidak memenangkan tiket " + ticket.getEventName());
            }
            return ticket;
        } else {
            System.out.println("Tiket sudah habis");
            return null;
        }
    }

    private List<Ticket> loadTicketsFromFile(String ticket_data){
        List<Ticket> loadedTickets = new ArrayList<>();

        try{
            List<String> lines = Files.readAllLines(Paths.get("C:\\My_Data\\Coding\\projectJKT48\\ticket_data.txt"));   //Sesuaikan dengan file path anda 
            for (String line : lines){
                String[] parts = line.split(",");
                String eventName = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                int availableTickets = Integer.parseInt(parts[2].trim());
                loadedTickets.add(new Ticket(eventName, price, availableTickets));
            }
        } catch (IOException | NumberFormatException e){
            System.out.println("Error: " + e.getMessage());
        }
        return loadedTickets;
    }

    public void updateTicketsFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\My_Data\\Coding\\projectJKT48\\ticket_data.txt"))) {    //Sesuaikan dengan file path anda 
            for (Ticket ticket : tickets) {
                writer.write(ticket.getEventName() + "," + ticket.getPrice() + "," + ticket.getAvailableTickets());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void ticketSystem(){
    
        List<Ticket> availableTickets = getAvailableTickets();
        for (int i = 0; i < availableTickets.size(); i++){
            Ticket ticket = availableTickets.get(i);
            System.out.println(i + ". "+ ticket.getEventName() + "- Rp. " + ticket.getPrice());

        }

        // Meminta pengguna memilih tiket
        System.out.print("Pilih nomor tiket yang ingin dibeli: ");
        int ticketIndex = in.nextInt();

        System.out.print("Masukkan jumlah tiket yang ingin dibeli: ");
        int quantity = in.nextInt();

        Ticket purchasedTicket = purchaseTicket(ticketIndex, quantity);
        if(purchasedTicket != null){
            System.out.println("Total harga: Rp. " + purchasedTicket.getPrice() * quantity);
        }else{
            System.out.println("Pembelian gagal. Coba lagi");
        }
    }

    private boolean performLottery() {
        Random random = new Random();
        int winningNumber = random.nextInt(2); // Angka acak antara 0 dan 1
        return winningNumber == 1;
    }

}
