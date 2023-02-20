package Game;

import java.util.ArrayList;

public class DotCom {
    private ArrayList <String> locationCells;

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String checkYourself(String userGuess){
        String result = "Мимо";
        int index = locationCells.indexOf(userGuess);

        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) result = "Потопил";
            else result = "Попал";
        }
        System.out.println(result);
        return result;
    }
}
