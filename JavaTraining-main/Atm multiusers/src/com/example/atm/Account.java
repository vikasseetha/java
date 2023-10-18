package com.example.atm;

import java.util.Scanner;

public class Account {
    private int accno;
    private int pin;
    private double balance;



    public Account(int accno, int pin, double balance) {
        this.accno = accno;
        this.pin = pin;
        this.balance = balance;
    }


    public int getAccno() {
        return accno;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
