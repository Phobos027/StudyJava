package NewGame;

import java.util.ArrayList;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> locationCells){
        this.locationCells = locationCells;
    }

    public void setName(String name){
        this.name = name;
    }

    public String checkYourself(String userInput){
        String result = "����";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);


            if (locationCells.isEmpty()) {
                result = "�������";
                System.out.println("��! �� �������� " + name + " =(");
            } else result = "�����";
        }
        return result;
    }
}
