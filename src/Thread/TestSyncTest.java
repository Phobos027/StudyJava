package Thread;
class TestSync implements Runnable{

    private int balance;

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            synchronized (this) {
                increment();
                System.out.println(Thread.currentThread().getName() + " Баланс равен " + balance);
            }
        }
    }

    public void increment() {
        int i = balance;
        balance = i + 1;
    }
}

public class TestSyncTest {
    public static void main(String[] args) {
        TestSync testSync = new TestSync();
        Thread one = new Thread(testSync);
        Thread two = new Thread(testSync);
        one.setName("one");
        two.setName("two");
        one.start();
        two.start();
    }
}
