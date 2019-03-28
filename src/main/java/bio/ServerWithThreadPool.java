package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWithThreadPool {
    public static void main(String[] args){
        int port = 8088;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server is starting in port: " + port);
            Socket socket = null;

            //-------只改了下面一点点代码-------
            ExecutorService es = Executors.newFixedThreadPool(100);
            while (true){
                socket = server.accept();
                es.execute(new ClientHandler(socket));
            }
            //-------只改了上面一点点代码-------

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
