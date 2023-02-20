package Magnet;

import java.util.ArrayList;

public class ArrayListMagnet {
    public static void printAl(ArrayList<String> al){
        for (String element : al) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList <String> a = new ArrayList<String>();
        a.add(0, "����");
        a.add(1, "����");
        a.add(2, "���");
        a.add(3, "���");
        printAl(a);

        a.remove(2);
        if (a.contains("���")){
            a.add("������");
        }
        printAl(a);

        if (a.indexOf("������") != 4){
            a.add(4, "4.2");
        }
        printAl(a);

        if (a.contains("���")){
            a.add("2.2");
        }
        printAl(a);
    }
}
