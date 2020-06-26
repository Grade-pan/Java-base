package 作业;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TCP_Server {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("输入exit退出聊天");
            Socket s = ss.accept();
            DataOutputStream dos = null;
            DataInputStream dis = null;
            OutputStream os = null;
            InputStream is1 = null;
            //设置退出标志，不要设置为null
            String str = "123";

            while (!str.equals("exit")) {
                //发送数据
                os = s.getOutputStream();
                dos = new DataOutputStream(os);
                Scanner sc = new Scanner(System.in);
                str = sc.nextLine();
                System.out.println(df.format(new Date()) + "    服务端---王浩哲---传送的数据是：" + str);
                dos.writeUTF(str);
//            //数字
//            InputStream is = s.getInputStream();
//            int msg = is.read();
//            System.out.println("传输的数字是：" + msg);


                //字符串
                //接受数据
                is1 = s.getInputStream();
                dis = new DataInputStream(is1);
                String msg1 = dis.readUTF();
                System.out.println(df.format(new Date()) + "    客户端---孙大炮---传送的数据是：" + msg1);
                // is.close();
            }

            System.out.println("已经退出聊天");
            is1.close();
            os.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
