package barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(8, () -> {
        System.out.println("运动员跑完一圈，集体休息！！！");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public static void main(String[] args){
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(new Athlete(cyclicBarrier,"博尔特"));
        athleteList.add(new Athlete(cyclicBarrier,"鲍威尔"));
        athleteList.add(new Athlete(cyclicBarrier,"盖伊"));
        athleteList.add(new Athlete(cyclicBarrier,"布雷克"));
        athleteList.add(new Athlete(cyclicBarrier,"加特林"));
        athleteList.add(new Athlete(cyclicBarrier,"苏炳添"));
        athleteList.add(new Athlete(cyclicBarrier,"路人甲"));
        athleteList.add(new Athlete(cyclicBarrier,"路人乙"));
        Executor executor = Executors.newFixedThreadPool(8);

        for (Athlete athlete : athleteList) {
            executor.execute(athlete);
        }

        //停止线程池，要不然程序不会结束……
        ((ExecutorService) executor).shutdown();
    }
}

class Athlete implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String name;

    public Athlete(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "就位");
        try {
            for (int i = 0; i < 2; i++) {
                Random random =new Random();
                double time = random.nextDouble() + 9;
                System.out.println(name + "到达了终点！用时：" + time);
                cyclicBarrier.await();
            }
        } catch (Exception e) {
        }
    }
}
