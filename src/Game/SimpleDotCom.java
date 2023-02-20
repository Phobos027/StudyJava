package Game;

public class SimpleDotCom {
    int [] locationCells;
    int numOfHits;

    public void setLocationCells(int [] locationCells){
        this.locationCells = locationCells;
    }

    public String checkYourself(String userGuess){
        int guess = Integer.parseInt(userGuess);
        String result = "Мимо";
        for (int i = 0; i < locationCells.length; i++) {
            if (locationCells[i] == guess){
                result = "Попал";
                locationCells[i] = -1;
                numOfHits++;
                break;
            }
        }
        setLocationCells(locationCells);
        if (numOfHits == locationCells.length){
            result = "Потопил";
        }
        System.out.println(result);
        return result;
    }
}
