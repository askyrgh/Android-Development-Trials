package com.askyr.animalsdemo;

// This class is used to represent each animal with their name, images and other parameters
public class Animal {

    String name;
    int image;

    public Animal(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
