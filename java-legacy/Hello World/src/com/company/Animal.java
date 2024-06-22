package com.company;

public class Animal {
    String animalName;
    String animalColor;

    public Animal(String name, String color) {
        this.animalName = name;
        this.animalColor = color;
    }

    public void Walk() {
        System.out.println(this.animalName + " is now walking!");
    }
}
