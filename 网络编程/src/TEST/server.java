package TEST;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server {
    public static void main(String args[]) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            ServerSocket ss = new ServerSocket(8888);
            Socket s = ss.accept();


            File dir = new File("E:\\aaaaaa");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "12345.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);


            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            byte[] b = new byte[1024];
            int len;
            System.out.println(df.format(new Date()) + "  开始接收图片");
            while ((len = bis.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }

            //向客户端回馈信息
            OutputStream out=s.getOutputStream();
            out.write("图片上传成功".getBytes());


            fileOutputStream.close();
            bis.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(df.format(new Date()) + "  图片接收成功");
    }
}
