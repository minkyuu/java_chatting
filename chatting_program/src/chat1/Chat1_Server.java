package chat1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Chat1_Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        Scanner sc = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("서버를 작동시켰습니다.");
        } catch (IOException e) {
            System.exit(0);
        }

        try {
            serverSocket.setSoTimeout(5 * 1000);
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress()+ "에서 서버에 연결을 하였습니다.\n");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            out.writeUTF("서버와 연결이 되셨습니다 :)");
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:MM:SS");

            while (in != null) {
                System.out.println("\nClient - "+ in.readUTF());
                System.out.print("메시지를 입력하세요 : ");
                String msg = sc.nextLine() + "  ["+f.format(LocalDateTime.now())+"]";
                out.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
