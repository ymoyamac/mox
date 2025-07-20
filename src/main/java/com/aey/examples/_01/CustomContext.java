package com.aey.examples._01;

import com.aey.mox.core.Context;

public class CustomContext extends Context<User, UserErr> {

    private User userToInsert;

    public User getUserToInsert() {
        return userToInsert;
    }

    public void setUserToInsert(User userToInsert) {
        this.userToInsert = userToInsert;
    }

    @Override
    public String toString() {
        return "CustomContext [" + "\n" +
                "\tprops: " + super.getProps() + "\n" +
                "\trestul: " + super.ok() + "\n" +
                "\tuserToInsert: " + userToInsert + "\n" +
            "]";
    }

    
}