package Chap2;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientThread extends Thread {
    private Socket toClientSocket = null;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea jTextArea;
    private int clientCounts = 0;

    public ClientThread(Socket toClientSocket, int clientCounts, JTextArea jTextArea) {
        this.toClientSocket = toClientSocket;
        this.clientCounts = clientCounts;
        this.jTextArea = jTextArea;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(), "UTF-8"), true);
            String revStr;
            while ((revStr = in.readLine()) != null) {
                Date date = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date);
                jTextArea.append(toClientSocket.getRemoteSocketAddress() + "客户机编号-----" + clientCounts + "消息：" + revStr + "---------- " + time + "\n");
                out.println(toClientSocket.getLocalSocketAddress() + "客户机编号-----" + clientCounts + "客户端消息：" + revStr + "----------" + time);
            }
            Server_UI.clientCounts--;
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (toClientSocket != null)
                toClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
