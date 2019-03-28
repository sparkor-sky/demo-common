package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        int port = 8088;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server is starting in port: " + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
            }

        }catch (Exception e){

        }finally {
            if(server != null){
                System.out.println("Server stop at "+ System.currentTimeMillis());
                try {
                    server.close();
                } catch (IOException e) { }
                server = null;
            }
        }
    }
}
