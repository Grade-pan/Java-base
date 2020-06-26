package TEST;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class client1 extends Thread{
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            Socket s = new Socket("127.0.0.1", 8885);

            DataOutputStream dos = null;
            DataInputStream dis = null;
            String str = null;

            //上传用户名
            OutputStream os = s.getOutputStream();
            dos = new DataOutputStream(os);
            System.out.println(df.format(new Date()) + "  请输入用户名");
            System.out.print(df.format(new Date()) + "  ");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            dos.writeUTF(str);

            //得到服务端反馈信息
            InputStream is = s.getInputStream();
            dis = new DataInputStream(is);
            String msg = dis.readUTF();
            System.out.println(df.format(new Date()) + "  " + msg);

            dos.close();
            dis.close();
            os.close();
            is.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
