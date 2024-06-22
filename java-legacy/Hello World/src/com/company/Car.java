package com.company;

public class Car {
    static int totalCount;

    String color;
    String brand;

    int wheelCount;
    boolean isFast;

    Car(String colorName, String brandName, int carWheelCount, boolean fast) {
        color = colorName;
        brand = brandName;
        wheelCount = carWheelCount;
        isFast = fast;

        totalCount++;
    }

    public void LetsDrive() {
        System.out.println("Now driving " + brand + "!");
    }

    public void SellCar() {
        System.out.println(brand + " just sold!");
    }

    public static void PrintCarCount() {
        System.out.println(totalCount);
    }
}
