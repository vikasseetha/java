package com.library.management;

import java.util.Scanner;

public class BusinessLogic {
    LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();

    /*Here we are using array with maximum capacity of 100 delcared with MAX_BOOKS constant if we add data after 100 it is shown already full
    * for addbook we are adding book title and author from console */
    public void addBook(Scanner scanner) {
        if (libraryManagementSystem.numBooks >= libraryManagementSystem.MAX_BOOKS) {
            System.out.println("The library is already full. Cannot add more books.");
            return;
        }

        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();

        libraryManagementSystem.books[libraryManagementSystem.numBooks] = title;
        libraryManagementSystem.authors[libraryManagementSystem.numBooks] = author;
        libraryManagementSystem.numBooks++;

        System.out.println("Book added successfully!");
    }

    /*Here we are using array with maximum capacity of 100 if we add data after 100 it is shown already full
     * for addPatron we are adding patronname from console */
    public void addPatron(Scanner scanner) {
        if (libraryManagementSystem.numPatrons >= libraryManagementSystem.MAX_PATRONS) {
            System.out.println("The library has reached the maximum number of patrons.");
            return;
        }

        System.out.print("Enter the name of the patron: ");
        String name = scanner.nextLine();

        libraryManagementSystem.patrons[libraryManagementSystem.numPatrons] = name;
        libraryManagementSystem.numPatrons++;

        System.out.println("Patron added successfully!");
    }

    /* number of books in library declared with numbooks variable if numBooks =0 means  there are no books to boorow from library,
    and number of patrons declared with numPatrons if patrons 0 means no person is ther e to borrow book
    isBorrowed Book method is used to check if the person if already borrowed the book or not so we are borrowing books based on number
    in array we are storing books and patrons from 0
    but here we are assigning numbers to books and patrons from 1
    * */
    public void borrowBook(Scanner scanner) {
        if (libraryManagementSystem.numBooks == 0) {
            System.out.println("There are no books in the library.");
            return;
        }

        if (libraryManagementSystem.numPatrons == 0) {
            System.out.println("There are no patrons in the library.");
            return;
        }

        System.out.println("Available books:");
        for (int i = 0; i < libraryManagementSystem.numBooks; i++) {
            if (!isBookBorrowed(i)) {
                System.out.println((i + 1) + ". " + libraryManagementSystem.books[i] + " by " + libraryManagementSystem.authors[i]);
            }
        }

        System.out.print("Enter the number of the book to borrow: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (bookNumber < 1 || bookNumber > libraryManagementSystem.numBooks) {
            System.out.println("Invalid book number. Please try again.");
            return;
        }

        int bookIndex = bookNumber - 1;
        if (isBookBorrowed(bookIndex)) {
            System.out.println("The selected book is already borrowed.");
        } else {
            System.out.println("Available patrons:");
            for (int i = 0; i < libraryManagementSystem.numPatrons; i++) {
                System.out.println((i + 1) + ". " + libraryManagementSystem.patrons[i]);
            }

            System.out.print("Enter the number of the patron: ");
            int patronNumber = scanner.nextInt();


            if (patronNumber < 1 || patronNumber > libraryManagementSystem.numPatrons) {
                System.out.println("Invalid patron number. Please try again.");
                return;
            }

            int patronIndex = patronNumber - 1;
            if(libraryManagementSystem.borrowedBooks[patronIndex]==null) {
                libraryManagementSystem.borrowedBooks[patronIndex] = libraryManagementSystem.books[bookIndex];
                System.out.println("Book borrowed successfully!");
            }
            else{
                System.out.println("The Person already exceeded his limit");
            }
        }
    }
/* here borrowed books are store in borrowedBooks array here borrowed book is stored like if we select book number 2 it is stored in borrowedBook 1 index postion book number
* 4 means store in 3rd  position*/
    public void returnBook(Scanner scanner) {
        if (libraryManagementSystem.numBooks == 0) {
            System.out.println("There are no books in the library.");
            return;
        }

        if (libraryManagementSystem.numPatrons == 0) {
            System.out.println("There are no patrons in the library.");
            return;
        }

        System.out.println("Borrowed books:");
        for (int i = 0; i < libraryManagementSystem.numPatrons; i++) {
            if (libraryManagementSystem.borrowedBooks[i] != null) {
                System.out.println((i + 1) + ". " + libraryManagementSystem.borrowedBooks[i]);
            }
        }

        System.out.print("Enter the number of the book to return: ");
        int bookNumber = scanner.nextInt();


        if (bookNumber < 1 || bookNumber > libraryManagementSystem.numPatrons) {
            System.out.println("Invalid book number. Please try again.");
            return;
        }

        int bookIndex = bookNumber - 1;
        if (libraryManagementSystem.borrowedBooks[bookIndex] == null) {
            System.out.println("The selected book is not currently borrowed.");
        } else {
            libraryManagementSystem.borrowedBooks[bookIndex] = null;
            System.out.println("Book returned successfully!");
        }
    }

    private boolean isBookBorrowed(int bookIndex) {
        for (String borrowedBook : libraryManagementSystem.borrowedBooks) {
            if (borrowedBook != null && borrowedBook.equals(libraryManagementSystem.books[bookIndex])) {
                return true;
            }
        }
        return false;
    }

}
