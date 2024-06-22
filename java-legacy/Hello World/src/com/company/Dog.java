package com.company;

public class Dog extends Animal {
    boolean isBarking;

    public Dog(String name, String color, boolean barking) {
        super(name, color);
        this.isBarking = barking;
    }
}
