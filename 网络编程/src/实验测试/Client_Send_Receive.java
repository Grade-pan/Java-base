package 实验测试;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client_Send_Receive extends Client_UI implements Runnable, WAY_UI {
    private Socket s;

    public Client_Send_Receive(Socket s) {
        this.s = s;
    }

    public void run() {
        String[] args = {""};
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            jb.addActionListener(e -> {
                    args[0]= WAY_UI.getText(jt);
                    if(!args[0].equals("")){
                        try {
                            dos.writeUTF(df.format(new Date())+"  "+Thread.currentThread().getName()+args[0]);
                            jte.append(df.format(new Date())+"  "+Thread.currentThread().getName()+args[0]);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
