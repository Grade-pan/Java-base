import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable_Create implements Callable<String> {

    @Override
    public  String call() throws Exception {
        int sum = 0;
        int i = 0;
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "循环变量i的值" + i);
            sum += i;
        }
        return String.valueOf(sum);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable_Create t1 = new Callable_Create();
        Callable_Create t2=new Callable_Create();

        FutureTask<String> task = new FutureTask<String>(t1);
        FutureTask<String> task1 = new FutureTask<String>(t2);

        new Thread(task).start();
        new Thread(task1).start();

        System.out.println(task.get());
        System.out.println(task1.get());
    }
}
