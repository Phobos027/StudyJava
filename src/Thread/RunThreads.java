package Thread;

public class RunThreads implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("������ ��������: " + threadName);
        }
    }

    public static void main(String[] args) {
        RunThreads runner = new RunThreads();
        Thread alfa = new Thread(runner);
        Thread beta = new Thread(runner);
        alfa.setName("����� �����");
        beta.setName("����� ����");
        alfa.start();
        beta.start();
    }
}
