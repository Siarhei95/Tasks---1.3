//создайте клиент-серверное приложение “Архив”.
//Общие требования к заданию:
//        •  В архиве хранятся Дела (например, студентов). Архив находится на сервере.
//        •  Клиент, в зависимости от прав, может запросить дело на просмотр, внести в
//        него изменения, или создать новое дело.
//        Требования к коду лабораторной работы:
//        •  Для реализации сетевого соединения используйте сокеты.
//        •  Формат хранения данных на сервере – xml-файлы.


//разбирал через подробные примеры из интернета, чтобы понять структуру и принцип действия сокетов. Понял, что примерно это будет выглядет так:

package task1_3;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // введем ридер, для запроса со стороны студента
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 565, такой же как у сервера
                clientSocket = new Socket("localhost", 565); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать серверу
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Hello student! What would you like? Enter this: "); // предложим клиенту что то ввести

                String archive = reader.readLine(); // ждём пока клиент что-нибудь напишет

                out.write(archive + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverArchive = in.readLine(); // ждём ответа сервера
                System.out.println(serverArchive); // получив - выводим на экран
            }
            finally { // закрываем сокет и потоки
                System.out.println("The client was closed");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
