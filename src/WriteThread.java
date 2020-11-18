import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WriteThread extends Thread {

    private Socket socket;
    private ChatClient client;
    private DataOutputStream dataOut;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            this.dataOut = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Console console = System.console();

        sendMessage(client.getUserName());

        //MAIN
        String output;
        do {
            output = console.readLine(client.getUserName() + " >> ");
            sendMessage(output);
        } while (!output.equalsIgnoreCase("disconnect"));

        //close socket
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            dataOut.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
