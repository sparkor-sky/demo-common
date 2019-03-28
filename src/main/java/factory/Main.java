package factory;

public class Main {
    public static void main(String[] args){
        CatFactory catFactory = new MaleCatFactory();

        MaleCat cat = (MaleCat)catFactory.createBrownCat("mimi");

        System.out.println("我的名字是:" + cat.getName());
        System.out.println("我的一只骄傲的:" + cat.getSex() + "猫咪");
        System.out.println("我的颜色是:" + cat.getSex());

        //……以此类推  使用雄性工厂和雌性工厂，创建各种三种颜色的猫咪！
    }
}
