package factory;

public class WhiteMaleCat implements MaleCat {
    private String name;

    public WhiteMaleCat(String name){this.name = name;}

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
        return "白色";
    }
}

class WhiteFemaleCat implements MaleCat {
    private String name;

    public WhiteFemaleCat(String name){this.name = name;}

    @Override
    public String getSex() {
        return "雌性";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return "白色";
    }
}
