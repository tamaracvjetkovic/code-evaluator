import school.School;


public class Main {

    public static void main(String[] args) {
        School school = new School("Ludwig van Beethoven", "Elementary");

        if  (school.name.equals("Ludwig van Beethoven")) {
            System.out.println("All right!");
        } 
    }
}