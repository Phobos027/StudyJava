package Serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Box implements Serializable {

    private int height;

    private int width;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) {
        Box myBox = new Box();
        myBox.setHeight(20);
        myBox.setWidth(50);

        try{
            FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
