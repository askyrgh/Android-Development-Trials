package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String name = "Sky";

        int rollNumber = 12;
        long contactNumber = 9717382577L;

        double height = 175.26;
        float pi = 3.141592f;

        float result = (float)(height*pi);
        int roundResult = Math.round(result);

        boolean isStudent = true;

        System.out.print("Name: " + name + "\n");
        System.out.print("Roll no: " + rollNumber + "\n");
        System.out.print("Contact no: " + contactNumber + "\n");
    }
}
