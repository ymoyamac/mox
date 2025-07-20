package com.aey;

import com.aey.examples._01.CustomContext;
import com.aey.examples._01.User;

public class Main {
    public static void main(String[] args) {
        // user input from a front-end application
        User userOne = new User("Yael Moya", "yael@email.com", 25);

        // create your custom context
        CustomContext cc = new CustomContext();
        // declare a prop in your custom context
        cc.set("userDB", userOne);
        System.out.println(cc);

        // Some operations (like get sequence from db)
        int id = (int)(Math.random() * 100) + 1;
        cc.<User>get("userDB")
            .ifPresent(u -> {
                u.setUserId(id);
                cc.result(u);
            });

        if (cc.isSome()) {

            System.out.println(cc);
        }


    }
}
