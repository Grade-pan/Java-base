package 作业2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TCP_Server implements Runnable {
    private Socket s;


    public TCP_Server(Socket s) {
        this.s = s;
    }

    public void run() {
        Main.Count++;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Flag = "?????????";
        Main.area.append(df.format(new Date()) + "  " + Thread.currentThread().getName() + "已连接" + "\r\n");
        Main.area.append(df.format(new Date()) + "  " + "连接客户端数量: " + Main.Count + "\r\n");
        while (!Flag.equals("quit")) {
            try {
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String msg = dis.readUTF();
                Main.area.append(df.format(new Date()) + "  " + "线程ID:  " + Thread.currentThread().getName() + "  端口号:  " + s.getPort() + "  " + msg + "\r\n");
                Flag = msg;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            s.close();
            Main.area.append(df.format(new Date()) + "  " + Thread.currentThread().getName() + "已断开连接" + "\r\n");
            Main.Count--;
            Main.area.append(df.format(new Date()) + "  " + "连接客户端数量: " + Main.Count + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


