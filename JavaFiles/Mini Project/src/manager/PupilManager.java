package manager;

import person.Pupil;

import java.util.ArrayList;


public class PupilManager {

    private ArrayList<Pupil> pupils;

    public PupilManager() {
        this.pupils = new ArrayList<>();
    }

    public int GetCount() {
        int count = 0;

        while (count < 10) {
            count += 1;
            if (count == 10) {
                count += 20;
            }
            else {
                count += 30;
            }
        }

        return count;
    }
}
