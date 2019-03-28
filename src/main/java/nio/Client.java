package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();
        boolean stop = false;

        String ip = "127.0.0.1";
        int port = 8088;
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);

        if(sc.connect(new InetSocketAddress(ip,port))){
            sc.register(selector, SelectionKey.OP_READ);
            doWrite(sc,"You are 66666!");
        }

        while (!stop){
            selector.select(1000);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            SelectionKey key = null;
            while (it.hasNext()){
                key = it.next();
                it.remove();

            }
        }
    }

    private static void doWrite(SocketChannel sc, String data) throws IOException {
        byte[] req =data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
        byteBuffer.put(req);
        byteBuffer.flip();
        sc.write(byteBuffer);
        if(!byteBuffer.hasRemaining()){
            System.out.println("Send 2 client successed! ");
        }
    }
}
