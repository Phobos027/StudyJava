package Serializable;

import java.io.FileWriter;
import java.io.IOException;

public class WriteAFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("Foo.txt");
            writer.write("������, ��!");
            writer.write("\n");
            writer.write("Hello, world!");
            writer.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
