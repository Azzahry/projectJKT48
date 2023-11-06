import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MemberBio {

    public void biografi(){
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\My_Data\\Coding\\projectJKT48\\Member_bio.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String born = data[1].trim();
                String bloodType = data[2].trim();
                String horoskop = data[3].trim();
                int height = Integer.parseInt(data[4].trim());
                String surname = data[5].trim();

                displayMemberInfo(name, born, bloodType, horoskop, height, surname);
            }
            
        }catch (FileNotFoundException e) {
            System.out.println("File not found: "+e.getMessage());
        }catch (IOException e){
            System.out.println("Error readig file: "+e.getMessage());
        }
    }

    public void displayMemberInfo(String name, String born, String bloodType, String horoskop, int height, String surname){
        System.out.println("Name            : " +name);
        System.out.println("Tanggal Lahir   : " +born);
        System.out.println("Golongan Darah  : " +bloodType);
        System.out.println("Horoskop        : " +horoskop);
        System.out.println("Tinggi badan    : " +height+"cm");
        System.out.println("Nama Panggilan  : " +surname);
        System.out.println("----------------------------------");
    }
}
