package com.example.atm;

import java.util.Scanner;
public class LoginAccount {
    Account[] accounts = new Account[100];
    int accountCount = 0;
    int choice;
    ATM atm = new ATM();
    public void start() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Welcome to the Simple ATM");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount(accounts, accountCount);
                    accountCount++;
                    break;
                case 2:
                    login(accounts, accountCount);
                    break;
                case 3:
                    System.out.println("Visit Again!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        } while (choice != 3);
    }


    private void createAccount(Account[] accounts, int accountCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account No: ");
        int accno = scanner.nextInt();
        System.out.print("Enter Pin: ");
        int pin = scanner.nextInt();
        System.out.print("Enter Balance: ");
        double balance = scanner.nextDouble();
        Account account = new Account(accno, pin, balance);
        accounts[accountCount] = account;
        System.out.println("Account successfully created");

    }

    private void login(Account[] accounts, int accountCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account No: ");
        int accno = scanner.nextInt();
        System.out.print("Enter Pin: ");
        int pin = scanner.nextInt();
        boolean flag=false;
        boolean flag1=true;

            for (int i = 0; i < accountCount; i++) {
                Account account = accounts[i];
                if (account != null && account.getAccno() == accno && account.getPin() == pin) {
                    Account accounttt = account;
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("Welcome to the Simple ATM");
                        System.out.println("1. Check Balance");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdraw");
                        System.out.println("4. Exit");
                        System.out.print("Choose an option: ");
                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Current balance: " + accounttt.getBalance());
                                break;
                            case 2:
                                System.out.print("Enter deposit amount: ");
                                double depositAmount = scanner.nextDouble();
                                atm.deposit(accounttt, depositAmount);
                                break;
                            case 3:
                                System.out.print("Enter withdrawal amount: $");
                                double withdrawalAmount = scanner.nextDouble();
                                atm.withdraw(accounttt, withdrawalAmount);
                                break;
                            case 4:
                                System.out.println("Thank you for using our service");
                                flag1=false;
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid option. Please choose correct again.");
                        }
                        System.out.println();
                    }
                }
                flag = true;
            }
            if(flag == true&&flag1==true){
                System.out.println("Please Provide Valid Details");
            }
    }
}
