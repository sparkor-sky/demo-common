package proteType;


public class Box implements Cloneable{
    private Bean bean;

    private final int code = 1;

    public Box(Bean bean){
        this.bean = bean;
    }

    public void setBean(Bean bean){
        this.bean = bean;
    }

    public void showBean(){
        System.out.println(this.bean.getName());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Box box = (Box)super.clone();
        Bean newBean = null;
        try {
            newBean = (Bean)this.bean.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        box.setBean(newBean);
        return box;
    }
}
