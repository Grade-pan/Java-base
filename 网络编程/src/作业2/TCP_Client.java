package 作业2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Client {
    public static void main(String[] args) throws IOException {
        String Flag = "????????????";
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            while (!Flag.equals("quit")) {
                Scanner sc = new Scanner(System.in);
                String msg = sc.next();
                dos.writeUTF(msg);
                Flag = msg;
            }
            dos.close();
            if (os != null) {
                os.close();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
