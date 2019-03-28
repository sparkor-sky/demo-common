package builder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        RoleBuilder builder = new RoleBuilder();

        //新建角色：悟空
        builder.setName("悟空");
        builder.setSkill(Arrays.asList(Role.JUMP,Role.HIT,Role.KICK));
        Role wukong = builder.build();

        //新建角色：悟饭
        builder.setName("悟饭");
        Role wufan = builder.build();

        //新建角色：比克大魔王
        builder.setName("比克");
        builder.setSkill(Arrays.asList(Role.KICK,Role.KICK,Role.KICK));
        Role bike = builder.build();

        wukong.skill();
        bike.skill();
        wufan.skill();
    }
}
