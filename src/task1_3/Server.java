package task1_3;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(565); // назначим порт 565
                System.out.println("Server is running!"); // объявим, что сервер работает
                clientSocket = server.accept(); // accept() будет ждать подключения от студента

                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти к созданию потоков ввода/вывода.
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String archive = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(archive);
                    out.write("Server response: received a message from a student : " + archive + "\n");
                    out.flush(); // выталкиваем все из буфера

                } finally { // закрываем сокет и потоки
                    System.out.println(" ");
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is closed!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}