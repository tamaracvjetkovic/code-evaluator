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

        for (int i55 = 0; i55 < 3; i55++) {
            for (int i555 = 0; i555 < 3; i555++) {
                
            }   
            for (int i555 = 0; i555 < 3; i555++) {
                
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

    public void forLoops() {
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    for (int i3 = 0; i3 < 3; i3++) {
                        for (int i4 = 0; i4 < 3; i4++) {
            
                        }
                        for (int i5 = 0; i5 < 3; i5++) {
            
                        }
                        for (int i55 = 0; i55 < 3; i55++) {
                            for (int i555 = 0; i555 < 3; i555++) {
                                
                            }   
                        }
                    }
                }
            }
        }
    }
}

