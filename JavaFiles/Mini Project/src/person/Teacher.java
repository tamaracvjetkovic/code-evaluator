package person;

import school.subject.Subject;

import java.util.ArrayList;


public class Teacher  {

    public String name;
    public double salary;
    public ArrayList<Subject> subjects;

    public Teacher(String name) {
        this.name = name;
        this.salary = 30000;
        this.subjects = new ArrayList<>();
    }

    public int calculateIQ() {
        int iq = 0;

        if (isSmart()) {
            iq += 20;
        }
        else {
            iq -= 20;
        }

        for (Subject subject : subjects) {
            if (subject.difficulty == 1) {
                iq += 10;
            }
            if (subject.difficulty == 2) {
                iq += 20;
            }
            if (subject.difficulty == 3) {
                iq += 30;
            }   
        }

        return iq;
    }

    public boolean isSmart() {
        if (name.equals("Michael")) {
            return true;
        }
        else {
            return false;
        }
    }
}

