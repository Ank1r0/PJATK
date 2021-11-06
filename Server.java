import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {
    public static void main(String... args) throws Exception {
        var address = InetAddress.getByName("0.0.0.0");// От кого мы будем получать запросы, если 0.0.0.0 - получать запросы от всех
        var fullAddress = new InetSocketAddress(address, 10000);//port means 10000 TCP\UDP
        var cs = StandardCharsets.US_ASCII; // набор символов, набор расшифровки сообщений

        try (var ch = ServerSocketChannel.open()) { // Open server socket channel открыть сервер и потом биндить сервер к адресу
            ch.bind(fullAddress);//Команда для бинда запросов на привязанные IP address
            while (true)
                try (SocketChannel client = ch.accept()) { // Accept connection
                    try {
                        var flag = ByteBuffer.allocate(1024);
                        client.read(flag); // Считывает с сервера запрос
                        flag.flip(); // ставит limit в текущую позицию и текущую позицию в 0
                        String clientInput = cs.decode(flag).toString();
                        Scanner scanner = new Scanner(client, "ASCII");
                        if (clientInput.equals("99443322\n")) {
                            client.write(cs.encode("Remove all '2' from string\n"));
                            client.write(cs.encode("552323342\n"));

                            flag.clear();
                            client.read(flag);
                            flag.flip();
                            clientInput = cs.decode(flag).toString();

                            if (clientInput.equals("553334\n")) {

                                client.write(cs.encode("Raise number to the power of 5\n"));
                                client.write(cs.encode("45623564\n"));

                                flag.clear();
                                client.read(flag);
                                flag.flip();
                                clientInput = cs.decode(flag).toString();
                                                    //  197672402815134037867838373985884621824
                                if (clientInput.equals("197672402815134037867838373985884621824\n")) {

                                    client.write(cs.encode("Receive 3 natural numbers in 3 lines. Sum up the numbers and return to the result to the server.\n"));
                                    client.write(cs.encode("45623564\n"));
                                    client.write(cs.encode("57575675\n"));
                                    client.write(cs.encode("27458467\n"));


                                    flag.clear();
                                    client.read(flag);
                                    flag.flip();
                                    clientInput = cs.decode(flag).toString();

                                    if(clientInput.equals("130657706\n"))
                                    {
                                        client.write(cs.encode("Correct!\n"));
                                    }
                                    else {
                                        client.write(cs.encode("Wrong flag!\n"));
                                    }

                                } else {
                                    client.write(cs.encode("Wrong flag!\n"));
                                }

                            } else {
                                client.write(cs.encode("Wrong flag!\n"));
                            }

                        } else {
                            client.write(cs.encode("Wrong flag!\n"));
                        }
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        client.close();
                    }
                }
        }
    }
}
