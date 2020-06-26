package 售票;

import java.util.concurrent.*;

public class SaleThread implements Callable<String> {
    private static int tickets = 1000;
    private int i;

    private SaleThread(int i) {
        this.i = i;
    }

    @Override
    public String call() {
        while (true) {
            synchronized ("加个锁，防止脏数据") {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了" + i + "张票");
                    tickets--;
                    i++;
                } else {
                    System.out.println("票已售完");
                    break;
                }
            }
        }
        return "售票员" + Thread.currentThread().getName() + "售出了" + String.valueOf(i - 1) + "张票";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);//线程池大小，CPU核心数加一。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()+1, 6, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
        int i = 1;

        SaleThread saleThread = new SaleThread(i);
        SaleThread saleThread1 = new SaleThread(i);
        SaleThread saleThread2 = new SaleThread(i);
        SaleThread saleThread3 = new SaleThread(i);
        SaleThread saleThread4 = new SaleThread(i);

        FutureTask<String> t1 = new FutureTask<String>(saleThread);
        FutureTask<String> t2 = new FutureTask<String>(saleThread1);
        FutureTask<String> t3 = new FutureTask<String>(saleThread2);
        FutureTask<String> t4 = new FutureTask<String>(saleThread3);
        FutureTask<String> t5 = new FutureTask<String>(saleThread4);

        pool.submit(t1);
        pool.submit(t2);
        pool.submit(t3);
        pool.submit(t4);
        pool.submit(t5);

        System.out.println(t1.get());
        System.out.println(t2.get());
        System.out.println(t3.get());
        System.out.println(t4.get());
        System.out.println(t5.get());

        pool.shutdown();
    }
}
