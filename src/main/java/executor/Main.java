package executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Random random = new Random();
                    int res = random.nextInt(1000000);
                    if(res == 999){
                        System.out.println("I am alive!");
                    }
                }
            }
        });
        es.shutdownNow();
        System.out.println("SHUTDOWN!");
    }
}
