package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        // variables
        String name = "Sky";

        int rollNumber = 12;
        long contactNumber = 9717382577L;

        double height = 175.26;
        float pi = 3.141592f;

        float result = (float)(height*pi);
        int roundResult = Math.round(result);

        boolean isStudent = true;
        boolean isEligible = true;

        System.out.print("Name: " + name + "\n");
        System.out.print("Roll no: " + rollNumber + "\n");
        System.out.print("Contact no: " + contactNumber + "\n");

        // Conditional Statements
        if(name.equals("Sky")) {
            System.out.print("Valid username!\n");
        }
        else {
            System.out.print(("Invalid username!\n"));
        }

        // Logical operators
        if(isStudent && isEligible) {
            // Functions
            Greet(name);
        }
        else {
            Greet();
        }

        // Arrays
        int[] gradePoints = {10, 9, 8, 7, 6, 5, 4, 3};
        String[] grades = {"O", "A+", "A", "B+", "B", "C+", "C", "D"};

        int[] rollNumbers = new int[10];

        System.out.print("Size of gradePoints[] : " + gradePoints.length);

        System.out.print(gradePoints[0] + "\n");
        System.out.print(grades[0] + "\n");
        System.out.print(rollNumbers[0] + "\n");

        // Arraylists
        ArrayList<String> shoppingItems = new ArrayList<>();

        shoppingItems.add("Coffee");
        shoppingItems.add("Sugar");
        shoppingItems.add("Milk");

        System.out.print("Shopping list contains " + shoppingItems.size() + " items.\n");

        // print individual items of arrayList
        System.out.print(shoppingItems.get(0));

        // print the entire arrayList
        System.out.print(shoppingItems);

        // while loop
        int count = 0;

        while(count<10){
            System.out.println(count);
            count++;
        }

        // for loop
        for(int i=0; i<gradePoints.length; i++) {
            System.out.println(gradePoints[i] + " : " + grades[i]);
        }

        for(int i=0; i<shoppingItems.size(); i++) {
            System.out.println(shoppingItems.get(i));
        }

        for(String item : shoppingItems) {
            System.out.println(item + ": done");
        }

        // Classes and Objects
        Car myCar = new Car("Grey", "Mercedes", 4, true);

        System.out.println(myCar.color);

        myCar.LetsDrive();

        ArrayList<Car> cars = new ArrayList<Car>();

        Car myCar1 = new Car("Silver", "Mercedes", 4, true);
        Car myCar2 = new Car("Black", "Mercedes", 4, true);
        Car myCar3 = new Car("Grey", "BMW", 4, true);
        Car myCar4 = new Car("White", "Audi", 4, true);
        Car myCar5 = new Car("Blue", "Jaguar", 4, true);

        cars.add(myCar1);
        cars.add(myCar2);
        cars.add(myCar3);
        cars.add(myCar4);
        cars.add(myCar5);

        for (Car val : cars) {
            val.SellCar();
        }

        Car.PrintCarCount();

        // Access Modifiers, getters and setters
        BankAccount myAccount = new BankAccount();

        myAccount.setAmountAvailable(900);
        System.out.println(myAccount.getAmountAvailable());

        // Inheritance
        Animal animal1 = new Animal("Max", "Black");
        animal1.Walk();

        Dog dog1 = new Dog("Rex", "pale brown", true);
        dog1.Walk();

        Fish fish1 = new Fish("Goldie", "Orange");
        fish1.Walk();

        Bird bird1 = new Bird("Vector", "Black", 140);

        Animal myDog = new Dog("Delta", "Brown", true);

        ArrayList<Animal> petStore = new ArrayList<Animal>();
        petStore.add(dog1);
        petStore.add(fish1);
        petStore.add(bird1);
        petStore.add(myDog);
    }

    public static void Greet(String name) {
        System.out.print("Hello " + name + "!\n");
    }
    public static void Greet() {
        System.out.print("Sorry! The system doesn't recognize that username!");
    }

}
