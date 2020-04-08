import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class test implements Runnable {
    private String greeting;
    private JPanel panel;

    public test(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":" + greeting);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new test("hello")).start();
        new Thread(new test("Tom")).start();
        new Thread(new test("123")).start();
        new Thread(new test("456")).start();
        new Thread(new test("789")).start();
        new Thread(new test("101")).start();
        new Thread(new test("102")).start();
        new Thread(new test("103")).start();
        new Thread(new test("104")).start();
        new Thread(new test("105")).start();
    }
}
