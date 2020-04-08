import javax.swing.text.html.StyleSheet;

public class Thread_Create extends Thread {

    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread_Create t1 = new Thread_Create();
        t1.start();

    }
}
