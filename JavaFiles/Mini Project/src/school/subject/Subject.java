package school.subject;


public class Subject {

    public String name;
    public int difficulty;
    public int score;

    public Subject(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.score = 0;
    }

    public void calculateScore() {
        if (name == "A") {
            score = 3;
        }
        
        int count = 0;
        while (count < 5) {
            count++;
        }

        while (count < 10) {
            count++;
        }
    }
}
