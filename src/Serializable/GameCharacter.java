package Serializable;

import java.io.Serializable;

public class GameCharacter implements Serializable {
    static final long serialVersionUID = 1;
    int power;
    String type;
    String[] weapons;

    public GameCharacter(int power, String type, String[] weapons) {
        this.power = power;
        this.type = type;
        this.weapons = weapons;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getWeapons() {
        String weaponList = "";
        for (int i = 0; i < weapons.length; i++) {
            weaponList += weapons[i] + " ";

        }
        return weaponList;
    }
}
