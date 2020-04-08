package TEST;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server1 extends Thread{
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ServerSocket ss = new ServerSocket(8885);
            Socket s = ss.accept();
            DataInputStream dis = null;
            DataOutputStream dos = null;
            //设置用户名
            String str = "孙大炮和王浩哲";
            //读取数据
            InputStream is = s.getInputStream();
            dis = new DataInputStream(is);
            String msg = dis.readUTF();

            System.out.println(df.format(new Date()) + "  开始验证用户名");
            if (!str.equals(msg)) {
                //返回验证失败
                OutputStream os = s.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF(msg + "用户名验证失败");
                System.out.println(df.format(new Date()) + "  用户名验证失败");

                dos.close();
                os.close();
            }
            if (str.equals(msg)) {
                //返回验证成功
                OutputStream os = s.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF(msg + "用户名验证成功");
                System.out.println(df.format(new Date()) + "  用户名验证成功");

                dos.close();
                os.close();
            }

            dis.close();
            s.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
