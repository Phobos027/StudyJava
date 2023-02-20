package Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SortMountains {

    LinkedList<Mountain> mtn = new LinkedList<Mountain>();

    class NameCompare implements Comparator<Mountain>{

        @Override
        public int compare(Mountain o1, Mountain o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    class HeightCompare implements Comparator<Mountain>{

        @Override
        public int compare(Mountain o1, Mountain o2) {
            return (o2.getHeights() - o1.getHeights());
        }
    }

    public static void main(String[] args) {
        new SortMountains().go();
    }

    private void go() {
        mtn.add(new Mountain("����-������", 14255));
        mtn.add(new Mountain("�������", 14433));
        mtn.add(new Mountain("�����", 14156));
        mtn.add(new Mountain("����", 14265));

        System.out.println("� ������� ����������:\n" + mtn);

        NameCompare nc = new NameCompare();
        Collections.sort(mtn, nc);
        System.out.println("�� ��������:\n" + mtn);

        HeightCompare hc = new HeightCompare();
        Collections.sort(mtn, hc);
        System.out.println("�� ������:\n" + mtn);
    }
}
