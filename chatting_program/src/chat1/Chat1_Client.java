package chat1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Chat1_Client {

    public static void main(String[] args) {
        String serverIp = "127.0.0.1";
        Socket socket = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        Scanner sc = new Scanner(System.in);

        try {
            socket = new Socket(serverIp, 7777);
            System.out.println("서버에 연결되었습니다.");
        } catch (IOException e) {
            System.exit(0);
        }

        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:MM:SS");

            while(in != null) {
                System.out.println("\nServer - "+in.readUTF());
                System.out.print("메시지를 입력하세요 : ");
                String msg = sc.nextLine() + " ["+f.format(LocalDateTime.now())+"]";
                out.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
