package Magnet;

import java.io.*;

class DungeonGame implements Serializable {
    public int x = 3;
    transient long y = 4L;
    private short z = 5;

    int getX() {
        return x;
    }

    long getY() {
        return y;
    }

    short getZ() {
        return z;
    }
}
class DungeonTest {
    public static void main(String[] args) {
        DungeonGame d = new DungeonGame();
        try {
            FileOutputStream fos = new FileOutputStream("dg.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(d);
            oos.close();
            System.out.println(d.getX() + d.getZ() + d.getY());

            FileInputStream fis = new FileInputStream("dg.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            d = (DungeonGame) ois.readObject();
            ois.close();
            System.out.println(d.getX() + d.getZ() + d.getY());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}