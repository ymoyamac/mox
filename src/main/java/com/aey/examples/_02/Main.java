package com.aey.examples._02;

import com.aey.mox.core.Actions;
import com.aey.mox.core.Context;
import com.aey.mox.core.Prop;

public class Main {
    public static void main(String[] args) {
        // user input from a front-end application
        User userOne = new User("Yael Moya", "yael@email.com", 25);
        Role role = new Role("ADMIN");

        Context<User, Exception> context = new Context<>(Actions.PUBLISH.getAction());

        UserContext userContext = new UserContext();
        RoleContext roleContext = new RoleContext();

        context.subscribe(Actions.PUBLISH.getAction(), userContext);
        context.subscribe(Actions.PUBLISH.getAction(), roleContext);

        int id = (int)(Math.random() * 100) + 1;

        // Set prop in context
        context.set(Prop.bind("userId", id));

        // Get userId prop from context and set to user
        context.<Integer>get("userId").ifPresent(uid -> userOne.setUserId(uid));

        System.out.println(context);

        context.emit(Actions.PUBLISH.getAction(), userContext, userOne);
        context.emit(Actions.PUBLISH.getAction(), roleContext, role);

        System.out.println(context);

        System.out.println("OK(" + context.ok(Actions.PUBLISH.getAction(), userContext).toString() + ")");
        System.out.println("OK(" + context.ok(Actions.PUBLISH.getAction(), roleContext).toString() + ")");


    }
}
