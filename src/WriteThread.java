import java.io.*;
import java.net.Socket;

public class WriteThread extends Thread {

    private final Socket socket;
    private final ChatClient client;
    private PrintWriter dataOut;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            dataOut = new PrintWriter(output, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Console console = System.console();

        String userName = console.readLine("\nEnter your name: ");
        dataOut.println(userName);
        client.setUserName(userName);
        //MAIN
        String output;
        if (userName != null) {
            do {
                output = console.readLine(userName + " >> ");
                dataOut.println(output);
            } while (!output.equalsIgnoreCase(ChatClient.STOP_WORD));
        }
        //close socket
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Disconnected.");
        }
    }
}
