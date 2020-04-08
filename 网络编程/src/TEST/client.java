package TEST;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class client {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            //读取图片
            OutputStream os = s.getOutputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("E:\\QQ图片20191216153559.jpg"));
            byte[] b = new byte[1024];
            int len = 0;
            System.out.println(df.format(new Date()) + "  开始读取图片");
            while ((len = bufferedInputStream.read(b)) != -1) {
                os.write(b, 0, len);
            }
            s.shutdownOutput();

            //服务端反馈信息
            InputStream in = s.getInputStream();
            byte[] b2 = new byte[1024];
            int len2 = in.read(b2);
            System.out.println(df.format(new Date()) + "  " + new String(b2, 0, len2));


            os.close();
            bufferedInputStream.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
