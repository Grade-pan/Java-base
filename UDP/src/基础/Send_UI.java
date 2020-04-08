package 基础;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Send_UI {
    private static String Flag = "??????";
    private static final String IP = "255.255.255.255";//广播IP


    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,
                2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3));

        for (int i = 0; i < 1; i++) {
            Send send = new Send(Flag, IP);
            Thread thread = new Thread(send);
            threadPool.execute(thread);
        }
    }
}
