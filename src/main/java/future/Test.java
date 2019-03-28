package future;


import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        /** 第一种方式
            //得到线程池
            ExecutorService executor = Executors.newCachedThreadPool();

            //创建并执行线程
            Task task = new Task();
            Future result = executor.submit(task);

            //线程池停止接受新任务
            executor.shutdown();
        */

        /** 第二种方式
         *  注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
         */

        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        try{
            //主线程睡眠
            Thread.sleep(1000);
        } catch(InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try{
            //获取执行结果
            System.out.println("task运行结果:"+futureTask.get());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
class Task implements Callable {
    @Override public Integer call() throws Exception {
        System.out.println("子线程在进行计算");

        Thread.sleep(3000);

        int sum = 0;
        for(int i=0; i<100; i++){
            sum += i;
        }

        System.out.println("子线程计算完毕！");
        return sum;
    }
}
