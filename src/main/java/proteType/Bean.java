package proteType;

public class Bean implements Cloneable{
    private String name;

    public Bean(String name){
        this.name = name;
        System.out.println(name + "创建完毕！");
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
