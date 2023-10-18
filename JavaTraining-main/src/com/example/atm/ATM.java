package com.example.atm;

public class ATM {
    public void deposit(Account accounts, double amount) {
        if (amount > 0) {
            accounts.setBalance(accounts.getBalance() + amount);
            System.out.println("Deposit successful");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(Account accounts, double amount) {
        if (amount > accounts.getBalance()) {
            System.out.println("Insufficient balance");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
        } else {
            accounts.setBalance(accounts.getBalance() - amount);
            System.out.println("Withdrawal successful");
        }
    }

}
