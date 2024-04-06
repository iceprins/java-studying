import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChattingClient {
    public static void main(String[] args) {
        String address = "118.33.161.160";
        int port = 8000;

        OutputStream outputStream = null;
        InputStream inputStream = null;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("연결 요청중...");
            Socket socket = new Socket(address, port);
            System.out.println("연결 성공");

            outputStream = socket.getOutputStream();
            System.out.print("클라이언트: ");
            String message = sc.nextLine();
            byte[] bytes = message.getBytes();
            outputStream.write(bytes);
            outputStream.flush();
            System.out.println("데이터 전송 완료");

            inputStream = socket.getInputStream();
            bytes = new byte[1024];
            int readByteNo = inputStream.read(bytes);
            message = new String(bytes, 0, readByteNo);
            System.out.printf("서버: %s\n", message);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
