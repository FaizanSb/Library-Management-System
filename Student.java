import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Student extends Person {

    private int borrowedBooks;
    private int totalBorrowedBooks;
   // private int maxBooks;

    public Student(int ID, String name,String Email) {
        super(ID, name,Email);
        borrowedBooks = 0;
    }

    public void borrowedBooks() {
        borrowedBooks++;
    }

    public void returnedBooks() {
        borrowedBooks--;
    }

    public int getBorrowedBooksCount() {
        return borrowedBooks;
    }

    public int getTotalBorrowed(){
        return totalBorrowedBooks;
    }

    // public int getMaxBooks(){
    //     return maxBooks;
    // }

    public static void studentFunctionalities(LibraryManagementSystem librarySystem) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                System.out.print("\033[H\033[2J");
                System.out.flush();                        
                System.out.println("\n\t\t\t\t========================================================================");
                System.out.println("\n\t\t\t\t\t\t         Student Functionalities                                 ");
                System.out.print("\n\t\t\t\t========================================================================");

                System.out.println("\n\n\t\t\t\t\t\t\t1. Check Availability Status");
                System.out.println("\n\t\t\t\t\t\t\t2. View Issued Books");
                System.out.println("\n\t\t\t\t\t\t\t3. View Returned Books Status");
                System.out.println("\n\t\t\t\t\t\t\t4. Return to Main Menu ");
                System.out.print("\n\t\t\t\t\t\t\tEnter your choice: ");
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                        // Implement check availability status functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();                        
                        System.out.println("\n\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t           Searching the Book                                  ");
                        System.out.print("\n\t\t\t\t========================================================================");

                        System.out.print("\n\n\t\t\t\t\tEnter ISBN of book to check the availability of book.");
                        int isbn = scanner.nextInt();
                        librarySystem.searchBook(isbn);
                        break;
    
                    case 2:
                        // Implement view issued books functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();                        
                        System.out.println("\n\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t               Issued Books                                  ");
                        System.out.print("\n\t\t\t\t========================================================================");
                        
                        System.out.print("\n\n\t\t\t\t\t\t\t\tEnter Student ID: ");
                        int id = scanner.nextInt();
                        librarySystem.viewIssuedBooksByStudent(id);
                        break;
    
                    case 3:
                        // Implement view returned books status functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();                        
                        System.out.println("\n\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t            Returned Books                                          ");
                        System.out.print("\n\t\t\t\t========================================================================");

                        System.out.print("\n\n\t\t\t\t\t\t\tEnter Student ID: ");
                        int studentID = scanner.nextInt();
                        librarySystem.viewReturnedBooksByStudent(studentID);
                        break;
    
                    case 4:
                        return;
    
                    default:
                        System.out.println("\n\t\t\t\t\t\t\tInvalid Choice.....");
                }
                
            } catch (Exception e) {
                System.out.println("\n\t\t\t\t\t\t\tInvalid Input. ");
                
            }
            System.out.print("\n\t\t\t\t\t\t\tPress Enter to Continue. ");
            scanner.nextLine();
            scanner.nextLine();
        }
    }    
    
}
