package builder;

import java.util.Arrays;

public class Director {
    private RoleBuilder roleBuilder = new RoleBuilder();

    public Role getARole(String name){
        roleBuilder.setName(name);
        roleBuilder.setSkill(Arrays.asList(Role.KICK,Role.KICK,Role.KICK));
        return roleBuilder.build();
    }

    public Role getBRole(String name){
        roleBuilder.setName(name);
        roleBuilder.setSkill(Arrays.asList(Role.HIT,Role.HIT,Role.HIT));
        return roleBuilder.build();
    }

    public Role getCRole(String name){
        roleBuilder.setName(name);
        roleBuilder.setSkill(Arrays.asList(Role.JUMP,Role.HIT,Role.KICK));
        return roleBuilder.build();
    }
}
