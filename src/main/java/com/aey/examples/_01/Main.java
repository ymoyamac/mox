package com.aey.examples._01;

import com.aey.mox.core.Prop;
import com.aey.mox.multy.Context;

public class Main {
    public static void main(String[] args) {
        // user input from a front-end application
        User userOne = new User("Yael Moya", "yael@email.com", 25);

        // Create context and custom context
        Context<User, Exception> context = new Context<>("publish");
        CustomContext cc = new CustomContext();

        // subscribe custom context
        context.subscribe("publish", cc);
        // publish the user
        context.notify("publish", cc, userOne);

        System.out.println("OK(" + context.ok("publish", cc).toString() + ")");

        // Some random actions like get sequece from db
        int id = (int)(Math.random() * 100) + 1;

        // Set prop in context
        context.set(Prop.bind("userId", id));

        // Get userId prop from context and set to user
        context.<Integer>get("userId").ifPresent(uid -> userOne.setUserId(uid));

        // publish the user again with the userId
        context.notify("publish", cc, userOne);

        System.out.println(context.isSome("publish", cc));

        // Finally, when you want the most up-to-date value, check the OK field.
        System.out.println("OK(" + context.ok("publish", cc).toString() + ")");

        // unsubscribe
        context.unsubscribe("publish", cc);

        System.out.println(context.isSome("publish", cc));

    }
}
