package builder;

import java.util.List;

interface Builder{
    Role build();
}

public class RoleBuilder implements Builder{
    private Role role = new Role();

    @Override
    public Role build(){
        StringBuilder sb = new StringBuilder();
        Role newRole = null;
        try {
            newRole = (Role)role.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newRole;
    }

    public void setName(String name){
        role.setName(name);
    }

    public void setSkill(List<String> skill){
        role.setSkill(skill);
    }
}
