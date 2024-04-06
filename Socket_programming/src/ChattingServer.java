import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChattingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("클라이언트 접속 대기중...");
            Socket socket = serverSocket.accept();
            System.out.println("클라이언트 접속 완료");

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int readByteNo = inputStream.read(bytes);
            String message = new String(bytes, 0, readByteNo);
            System.out.printf("클라이언트: %s\n", message);

            outputStream = socket.getOutputStream();
            System.out.print("서버: ");
            message = sc.nextLine();
            bytes = message.getBytes();
            outputStream.write(bytes);
            outputStream.flush();
            System.out.println("데이터 전송 성공");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
