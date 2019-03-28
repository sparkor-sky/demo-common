package factory;

public class BrownMaleCat implements MaleCat {
    private String name;

    public BrownMaleCat(String name){this.name = name;}

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
        return "棕色";
    }
}
