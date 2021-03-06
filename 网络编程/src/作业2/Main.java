package 作业2;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Main {
    static int Count = 0;//连接客户端数量
    static JFrame f = new JFrame();
    static JPanel jp = new JPanel();
    static JLabel jl = new JLabel("内容如下");
    static JLabel jl1 = new JLabel("端口号");
    static JTextField jt = new JTextField(5);
    static JLabel jl2 = new JLabel("IP地址");
    static JTextField jt1 = new JTextField(5);
    static JTextArea area = new JTextArea(43, 40);
    static JButton jb = new JButton("退出");//退出服务端，关闭线程池

    public static void main(String[] args) throws IOException {
        //定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15),
//                new ThreadFactory() {
////                    @Override
////                    public Thread newThread(Runnable r) {
////                        return null;
////                    }
////                },
                (r, executor) -> area.append(r.toString() + "申请连接服务器被被拒绝" + "\r\n"));

        jp.add(jl1);
        jp.add(jt);
        jp.add(jl2);
        jp.add(jt1);
        jp.add(jl);
        jp.add(area);
        jp.add(jb);
        f.add(jp);
        f.setBounds(0, 0, 500, 800);
        f.setVisible(true);
        jb.addActionListener(e -> {
            pool.shutdown();
            System.exit(0);
        });
        ServerSocket ss = new ServerSocket(8080);
        jt.setText("127.0.0.1");
        jt1.setText("8080");
        while (true) {
            Socket s = ss.accept();
            TCP_Server tcp_server = new TCP_Server(s);
            Thread thread = new Thread(tcp_server);
            pool.execute(thread);
        }
    }
}
