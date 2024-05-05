package manager;

import person.Teacher;

import java.util.ArrayList;


public class TeacherManager {

    private ArrayList<Teacher> teachers;

    public TeacherManager() {
        this.teachers = new ArrayList<>();
    }

    public int getCount() {
        int count = 0;
        for (Teacher teacher : teachers) {
            if (teacher.isSmart()) {
                count += 2;
            }
            count += 1;
        }
        return count;
    }
}
