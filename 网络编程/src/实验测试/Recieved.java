package 实验测试;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Recieved implements Runnable {
    private Socket s;
   public static String Flag = "暂时先为空";

    public Recieved(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            while (true) {
                String msg = dis.readUTF();
                Flag = msg;
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
