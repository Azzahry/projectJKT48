public class Member {

    private String name;
    private String born;
    private String bloodType;
    private String horoskop;
    private int height;
    private String surname;

    public Member(String name, String born, String bloodType, String horoskop, int height, String surname){
        this.name = name;
        this.born = born;
        this.bloodType = bloodType;
        this.horoskop = horoskop;
        this.height = height;
        this.surname = surname;
    }

    public String getName(){
        return name;
    }

    public String getBorn(){
        return born;
    }

    public String getBloodType(){
        return bloodType;
    }

    public String getHoroskop(){
        return horoskop;
    }

    public int getHeight(){
        return height;
    }

    public String getSurname(){
        return surname;
    }
}
