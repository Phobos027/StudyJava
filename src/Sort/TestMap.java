package Sort;

import java.util.HashMap;

public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("����", 42);
        scores.put("����", 343);
        scores.put("�������", 420);

        System.out.println(scores);
        System.out.println(scores.get("����"));
    }
}
