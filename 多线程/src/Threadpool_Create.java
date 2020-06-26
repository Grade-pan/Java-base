import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threadpool_Create implements Runnable {
    private String s;

    private Threadpool_Create(String s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "：  " + s);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);//newFixedThreadPool方法是类Executors的静态方法.不能用new

        Future f1 = pool.submit(new Threadpool_Create("happy"));
        Future f2 = pool.submit(new Threadpool_Create("Sad"));

        try {
            if (f1.get() == null && f2.get() == null) {
                System.out.println("线程运行完毕");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
