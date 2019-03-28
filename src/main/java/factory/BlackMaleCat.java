package factory;

public class BlackMaleCat implements MaleCat {
    private String name;

    public BlackMaleCat(String name){this.name = name;}

    @Override
    public String getSex() {
        return "雄性";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return "黑色";
    }
}
