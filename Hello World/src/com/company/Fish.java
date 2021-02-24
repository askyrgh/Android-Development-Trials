package com.company;

public class Fish extends Animal {

    public Fish(String name, String color) {
        super(name, color);
    }

    @Override public void Walk() {
        super.Walk();
        System.out.println("But, fish can't walk!");
    }
}
