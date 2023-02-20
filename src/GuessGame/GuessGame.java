package GuessGame;

public class GuessGame {
    Player p1;
    Player p2;
    Player p3;

    public void startGame() {
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessP1 = 0;
        int guessP2 = 0;
        int guessP3 = 0;

        boolean p1IsRight = false;
        boolean p2IsRight = false;
        boolean p3IsRight = false;

        int targetNumber = (int) (Math.random() * 10);
        System.out.println("� ��������� ����� �� 0 �� 9...");

        while (true) {
            System.out.println("�����, ������ ����� �������, - " + targetNumber);
            p1.guess();
            p2.guess();
            p3.guess();

            guessP1 = p1.number;
            System.out.println("������ ����� ������, ��� ��� " + guessP1);

            guessP2 = p2.number;
            System.out.println("������ ����� ������, ��� ��� " + guessP2);

            guessP3 = p3.number;
            System.out.println("������ ����� ������, ��� ��� " + guessP3);

            if (guessP1 == targetNumber) {
                p1IsRight = true;
            }
            if (guessP2 == targetNumber) {
                p2IsRight = true;
            }
            if (guessP3 == targetNumber) {
                p3IsRight = true;
            }


            if (p1IsRight || p2IsRight || p3IsRight) {
                System.out.println("� ��� ���� ����������!");
                System.out.println("������ ������ ������?" + p1IsRight);
                System.out.println("������ ����� ������?" + p2IsRight);
                System.out.println("������ ����� ������?" + p3IsRight);
                System.out.println("����� ����!");
                break;
            } else {
                System.out.println("������ ������ ����������� ��� ���");
            }
        }
    }
}