package builder;

import java.util.ArrayList;
import java.util.List;

public class Role implements Cloneable{
    public static final String JUMP = "jump";
    public static final String HIT = "hit";
    public static final String KICK = "kick";

    private List<String> skill = new ArrayList<>();

    private String name;

    public Role(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSkill(List<String> skill){
        this.skill = skill;
    }

    public void jump(){
        System.out.println(getName()+ "高高的跳起！");
    }

    public void hit(){
        System.out.println(getName()+ "使出左勾拳！");
    }

    public void kick(){
        System.out.println(getName()+ "用左脚向前猛踢！");
    }

    public void skill(){
        System.out.println("**********准备释放大招！**********");

        for(String s:this.skill){
            switch (s){
                case Role.JUMP:
                    jump();
                    break;
                case Role.HIT:
                    hit();
                    break;
                case Role.KICK:
                    kick();
                    break;
                default:break;
            }
        }

        System.out.println("**********大招释放完毕！**********");
        System.out.println(System.getProperty("line.separator"));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
