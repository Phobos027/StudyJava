package Thread;

class BankAccount{
    private int balance = 100;

    public int getBalance(){
        return balance;
    }

    public void withdraw(int amount){
        balance -= amount;
    }
}

public class RyanAndMonicaJob implements Runnable{

    private BankAccount account = new BankAccount();
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            makeWithdrawal(10);
            if (account.getBalance() < 0) System.out.println("���������� ������");
        }
    }

    public static void main(String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("�����");
        two.setName("������");
        one.start();
        two.start();
    }

    private synchronized void makeWithdrawal(int amount){
        if (account.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " ���������� ����� ������");
            try {
                System.out.println(Thread.currentThread().getName() + " ���� ���������");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " �����������");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " ����������� ����������");
            System.out.println("�������: " + account.getBalance());
        } else {
            System.out.println("��������, ��� ������� " + Thread.currentThread().getName() + " ������������ �������");
        }
    }
}
