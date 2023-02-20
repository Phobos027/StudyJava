package Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Эльф", new String [] {"Лук", "Меч", "Кастет"});
        GameCharacter two = new GameCharacter(200, "Троль", new String[] {"голые руки", "большой топор"});
        GameCharacter three = new GameCharacter(120, "маг", new String[]{"заклинания", "невидимость"});

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        one = null;
        two = null;
        three = null;

        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("Type one: " + oneRestore.getType() + " " + oneRestore.getWeapons());
            System.out.println("Type two: " + twoRestore.getType() + " " + twoRestore.getWeapons());
            System.out.println("Type three: " + threeRestore.getType() + " " + threeRestore.getWeapons());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
