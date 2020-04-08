package 作业;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TCP_Client {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            DataInputStream dis = null;
            DataOutputStream dos = null;
            System.out.println("输入exit退出聊天,需要客户端和服务端都输入");
            //设置退出标志，不要设置为null
            String str = "123";
//            //数字
//            OutputStream os = s.getOutputStream();
//            os.write(110);

            while (!str.equals("exit")) {
                //接收数据
                InputStream is = s.getInputStream();
                dis = new DataInputStream(is);
                String msg = dis.readUTF();
                msg = Cryptography.getHash(msg, "sha-256");
                System.out.println(df.format(new Date()) + "    服务端---王浩哲---传送的数据是：" + msg);


                //发送数据
                OutputStream os1 = s.getOutputStream();
                dos = new DataOutputStream(os1);
                Scanner sc = new Scanner(System.in);
                str = sc.nextLine();
                System.out.println(df.format(new Date()) + "    客户端---孙大炮---传送的数据是：" + str);
                dos.writeUTF(str);
            }

            System.out.println("已经退出聊天");
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
