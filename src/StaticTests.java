class StaticSuper {
    static {
        System.out.println("Родительский статический блок");
    }

    public StaticSuper() {
        System.out.println("Родительский коструктор");
    }
}

public class StaticTests extends StaticSuper {
    static int rand;

    static {
        rand = (int) (Math.random() * 6);
        System.out.println("Статический блок"  + rand);
    }

    public StaticTests() {
        System.out.println("Конструктор");
    }

    public static void main(String[] args) {
        System.out.println("Внутри main");
        StaticTests st = new StaticTests();
    }
}


