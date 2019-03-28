package proteType;

public class Main {
    public static void main(String[] args){
        Bean dd = new Bean("豆豆");

        Box box = new Box(dd);

        box.showBean();

        Box box2 = null;
        try {
            box2 = (Box)box.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        box2.showBean();

        dd.setName("花花");

        box.showBean();
        box2.showBean();
    }
}
