package 多线程发送消息;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("192.168.137.1",8989);

            new SendThread(s).start();
            new ReceiveThread(s).start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
