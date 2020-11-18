import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    private int port;
    private String userName;

    private ChatClient(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
	    if (args.length < 2) {
	        System.out.println("Please, enter port number next time.");
	        System.exit(0);
        }
	    ChatClient chatClient = new ChatClient(Integer.parseInt(args[0]));
	    chatClient.setUserName(args[1]);
	    chatClient.runClient();
    }

    private void runClient() {
        try {
            Socket socket = new Socket("localhost", this.port);

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
