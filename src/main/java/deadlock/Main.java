package deadlock;

public class Main {
    private static final Object lock1 = new Object();

    private static final Object lock2 = new Object();
    public static void main(String[] args) throws Exception{


        System.out.println("Thread 1 begin!!!");
        new Thread(() -> {
            try {
                synchronized (lock1){
                    Thread.sleep(5000);
                    synchronized (lock2){
                        Thread.sleep(5000);
                        System.out.println("I am fine!");
                    }
                }
            }catch (Exception e){}
        }).start();

        System.out.println("Thread 2 begin!!!");
        new Thread(() -> {
            try {
                synchronized (lock2){
                    Thread.sleep(3000);
                    synchronized (lock1){
                        System.out.println("thank you!");
                    }
                }
            }catch (Exception e){}
        }).start();
    }
}
