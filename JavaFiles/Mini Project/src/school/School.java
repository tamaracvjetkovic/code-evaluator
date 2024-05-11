package school;

import person.Pupil;
import person.Teacher;

import java.util.ArrayList;


public class School {
    public String name;
    public String type;
    public ArrayList<Pupil> pupils;
    public ArrayList<Teacher> teachers;
    public double money;

    public School(String name, String type) {
        this.name = name;
        this.type = type;
        this.pupils = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.money = 99999.99;
    }

    public int GetDifficulty() {
        switch (type) {
            default : {
                return 4;
            }
        }
    }
}