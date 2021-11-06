import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String... args) throws Exception {
        var address = InetAddress.getByName("10.1.71.51"); // запись адреса на который мы будем отправлять сообщения
        var fullAddress = new InetSocketAddress(address, 10000); // Потом добавляем протокол TCP

        try (SocketChannel channel = SocketChannel.open(fullAddress)) { // Запрос на подключение к адресу

            var charset = StandardCharsets.US_ASCII;
            var msg = charset.encode("99443322\n");
            channel.write(msg);

            Scanner scanner = new Scanner(channel, StandardCharsets.US_ASCII);
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());

            channel.write(charset.encode("553334\n"));

            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());

            BigInteger n = BigInteger.valueOf(45623564);

            n = n.pow(5);
            System.out.println("n: "+n);
            channel.write(charset.encode(n.toString()+"\n"));

            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());

            int sum = 45623564+57575675+27458467;
            n = BigInteger.valueOf(sum);
            channel.write(charset.encode(n.toString()+"\n"));
            System.out.println(scanner.nextLine());
        }
    }
}
