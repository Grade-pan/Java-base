public class Runable_Create implements Runnable {
    private String s;

    private Runable_Create(String s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }

    public static void main(String[] args) {
        Runable_Create t1 = new Runable_Create("大炮");
        Thread T = new Thread(t1);
        T.setName("123");
        T.start();
        System.out.println(new Thread(t1).isAlive());
        new Thread(new Runable_Create("二炮")).start();
    }
}
