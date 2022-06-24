package com.epam.spring.homework2.beans;

import java.util.Random;

public abstract class AbstractBean {
    private String name;
    private int value;

    public void validate() {
        if (this.name == null) {
            name = "not null now";
        }
        if (this.value < 0) {
            value = new Random().nextInt(Integer.MAX_VALUE);
        }
    }
}
