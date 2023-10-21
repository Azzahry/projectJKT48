import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ticketing {
    private static Scanner in = new Scanner(System.in);
    private List<Ticket> tickets;
    // private static final String FILE_PATH = ("C:\\My_Data\\Coding\\projectJKT48\\ticket_data.txt");

    public Ticketing(){
        tickets =loadTicketsFromFile("C:\\\\My_Data\\\\Coding\\\\projectJKT48\\\\ticket_data.txt");
    }

    public List<Ticket> getAvailableTickets(){
        return tickets;
    }

    public Ticket purchaseTicket(int index, int quantity){
        Ticket ticket = tickets.get(index);
        if (ticket.getAvailableTickets() >= quantity){
            ticket.decreaseAvailTikcets(quantity);
            return ticket;
        } else {
            System.out.println("Tiket sudah habis");
            return null;
        }
    }

    private List<Ticket> loadTicketsFromFile(String ticket_data){
        List<Ticket> loadedTickets = new ArrayList<>();

        try{
            List<String> lines = Files.readAllLines(Paths.get("C:\\My_Data\\Coding\\projectJKT48\\ticket_data.txt"));
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
            System.out.println("Pembelian berhasil!");
            System.out.println("Total harga: Rp. " + purchasedTicket.getPrice() * quantity);
        }else{
            System.out.println("Pembelian gagal. Coba lagi");
        }
    }

}
