package com.company;

public class BankAccount {
    private int amountAvailable = 0;

    public void setAmountAvailable(int amt) {
        
        if(amt>1000){
            System.out.println("You can't add that much money!");
        }
        else {
            amountAvailable = amt;
        }
    }
    public int getAmountAvailable() {
        
        return amountAvailable;
    }
}
