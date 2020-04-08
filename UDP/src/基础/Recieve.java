package 基础;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recieve {
    private static String Flag = "?/???";

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888); //创建套接字
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);//创建数据接收包
        System.out.println("等待接收数据中......");
        while (!Flag.equals("quit")) {
            datagramSocket.receive(datagramPacket);//接收数据
            byte[] arr = datagramPacket.getData();
            int len = datagramPacket.getLength();
            Flag = new String(arr, 0, len);
            System.out.println(datagramPacket.getAddress().getHostAddress() + ":" + datagramPacket.getPort() + "  " + Flag);
        }
        System.out.println("连接终止......");
        datagramSocket.close();
    }
}
