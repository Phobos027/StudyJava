package Network;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleChatServer {
    public void go(){
        try {
            ServerSocket chatServerSocket = new ServerSocket(4242);
            while (true){
                Socket socket = chatServerSocket.accept();


            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SimpleChatServer().go();
    }
}
