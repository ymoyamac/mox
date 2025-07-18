package com.aey.mox.examples;

import java.util.List;

import com.aey.mox.core.Context;
import com.aey.mox.examples.entities.MocaErr;
import com.aey.mox.examples.entities.Role;
import com.aey.mox.examples.entities.User;

public class UserContext extends Context<User, MocaErr> {
    
    
    private List<User> users;
    private Role adminRole;

    public UserContext() {
        super(MocaErr::new); // Fábrica para errores
    }
    
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    @Override
    public String toString() {
        return "UserContext ["+ "\n" +
                            "   props=" + super.getProps() + ",\n" +
                            "   users=" + users + ",\n" +
                            "   roles=" + adminRole + ",\n" +
                            "   ok=" + super.ok() + ",\n" +
                            "   errs=" + super.err() + ",\n" +
                "]";
    }
    public Role getAdminRole() {
        return adminRole;
    }
    public void setAdminRole(Role adminRole) {
        this.adminRole = adminRole;
    }

    

}
