import java.io.*;
import java.net.Socket;

public class ReadThread extends Thread {

    private BufferedReader dataIn;
    private final Socket socket;
    private final ChatClient client;

    public ReadThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.client = chatClient;

        try {
            InputStream input = this.socket.getInputStream();
            dataIn = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = dataIn.readLine();
                System.out.println("\r" + input);
                if (client.getUserName() != null) {
                    System.out.print(client.getUserName() + " >> ");
                }
            } catch (IOException e) {
                break;
            }
        }
    }
}
