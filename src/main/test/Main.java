import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static ThreadLocal<SimpleDateFormat> localFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("YYYY-MM-dd"));

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    public static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(new DateThread());
            t.start();
        }
    }
    public static class DateThread implements Runnable{

        @Override
        public void run() {
            System.out.println(df.format(df.parse("2018-10-17")));
        }
    }


}

class DateFormatUtils {
    private static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String time){
        return LocalDate.parse(time, sdf);
    }

    public static String format(LocalDate date){
        return sdf.format(date);
    }
}

class Demo{
    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new DateThread());
            t.start();
        }
    }

    public static class DateThread implements Runnable{

        @Override
        public void run() {
            System.out.println(DateFormatUtils.format(DateFormatUtils.parse("2019-03-19")));
        }
    }
}
