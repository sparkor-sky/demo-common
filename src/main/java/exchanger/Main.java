package exchanger;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Consumer(exchanger).start();
        new Producer(exchanger).start();
    }

}
class Producer extends Thread {
    List<Integer> list = new ArrayList<>();
    Exchanger<List<Integer>> exchanger = null;
    public Producer(Exchanger<List<Integer>> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        Random rand = new Random();
        for(int i=0; i<5; i++) {
            list.clear();
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            try {
                //触发交换
                list = exchanger.exchange(list);
                //等待交换
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    List<Integer> list = new ArrayList<>();
    Exchanger<List<Integer>> exchanger = null;
    public Consumer(Exchanger<List<Integer>> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            try {
                //触发交换
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(list.get(0)+", ");
            System.out.print(list.get(1)+", ");
            System.out.print(list.get(2)+", ");
            System.out.print(list.get(3)+", ");
            System.out.println(list.get(4)+", ");
            System.out.println();
        }
    }
}
