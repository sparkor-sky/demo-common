package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        int port = 8088;
        String ip = "127.0.0.1";
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ip,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Send message:" + System.currentTimeMillis());
            String resp = in.readLine();
            System.out.println("Receive response:" + resp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
                out = null;
            }

            if(in != null){
                try {
                    in.close();
                } catch (IOException e) { }
                in = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) { }
                socket = null;
            }
        }
    }
}
