package com.aey;

import com.aey.mox.core.Prop;
import com.aey.mox.examples.UserContext;
import com.aey.mox.examples.entities.ErrorCodes;
import com.aey.mox.examples.entities.Role;
import com.aey.mox.examples.entities.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        User user = new User("Yael", 25, "yael@gmail.com");
        User userTwo = new User("Alejandro", 27, "ale_taboa@gmail.com");
        Role adminRole = new Role(1, "ADMIN");
        List<User> users = List.of(user, userTwo);

        UserContext userContext = new UserContext();

        userContext.set(Prop.bind("adminRole", adminRole));
        userContext.set(Prop.bind("action", "create"));
        //userContext.set("user", user);
        userContext.setUsers(users);

        if (userContext.get("user").isPresent()) {
            userContext.result("user");
        } else {
            userContext.setErr(ErrorCodes.NOT_FOUND);
        }

        System.out.println(userContext);
        System.out.println(userContext.get("adminRole"));

    }
}