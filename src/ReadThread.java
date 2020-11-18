import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadThread extends Thread {

    private DataInputStream dataIn;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.client = chatClient;

        try {
            this.dataIn = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = dataIn.readUTF();
                System.out.println("\n" + input);
                System.out.print(client.getUserName() + " >> ");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
