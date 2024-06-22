package com.company;

public class Bird extends Animal{
    int flyingSpeed;

    public Bird(String name, String color, int speed) {
        super(name, color);
        this.flyingSpeed = speed;
    }
}
