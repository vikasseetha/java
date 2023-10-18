package com.library.management;

import java.util.Scanner;

public class LibraryManagementSystem {

    public static final int MAX_BOOKS = 100;
    public static final int MAX_PATRONS = 100;
    public String[] books;
    public String[] authors;
    public String[] patrons;
    public String[] borrowedBooks;

    public int numBooks;
    public int numPatrons;


    public LibraryManagementSystem() {
        books = new String[MAX_BOOKS];
        authors = new String[MAX_BOOKS];
        patrons = new String[MAX_PATRONS];
        borrowedBooks = new String[MAX_PATRONS];
    }
/*This is main method of library management ssystem  here we are selecting type of operation from console using scanner class
* here while condiotn for continuous operation in while we are using switch condition to perform  type of operation  in case class we are calling
* business logic of selected task
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        BusinessLogic businessLogic = new BusinessLogic();
        while (!exit) {
            System.out.println("Welcome to the Library Management System!");
            System.out.println("1. Add a book");
            System.out.println("2. Add a patron");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    businessLogic.addBook(scanner);
                    break;
                case 2:
                    businessLogic.addPatron(scanner);
                    break;
                case 3:
                    businessLogic.borrowBook(scanner);
                    break;
                case 4:
                    businessLogic.returnBook(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Thank you for using the Library Management System!");
    }

}