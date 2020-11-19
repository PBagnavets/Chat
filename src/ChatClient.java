import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    private final int port; //default value of port is 8080
    public static final String STOP_WORD = "bye";
    private String userName;

    private ChatClient(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
	    ChatClient chatClient = new ChatClient(args.length > 0 ? Integer.parseInt(args[0]) : 8080);
	    chatClient.startClient();
    }

    private void startClient() {
        try {
            Socket socket = new Socket("localhost", port);

            System.out.println("Connected to Chat Server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
}
