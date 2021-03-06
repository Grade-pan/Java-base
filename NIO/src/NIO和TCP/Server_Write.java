package NIO和TCP;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server_Write implements Runnable, GUI_way {

    private SocketChannel socketChannel;

    public Server_Write(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        while (true) {
            Server_UI.jb2.addActionListener(e -> {

                String msg = GUI_way.getText(Server_UI.jt);
                GUI_way.Clear_Text(Server_UI.jt);
                if (msg.equals("")) {
                    System.out.println();
                }
                try {
                    socketChannel.write(StandardCharsets.UTF_8.encode(msg));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });


        }
    }
}
