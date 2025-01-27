import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Librarian extends Person {


    public Librarian(int ID, String name,String Email) {
        super(ID, name,Email);
    }
    
    

    public void librarianFunctionalities(LibraryManagementSystem librarySystem){

            Scanner scanner = new Scanner(System.in);

            System.out.print("\n\t\t\t\t\t\t\tPress Enter to continue. ");
            scanner.nextLine();
        
            while (true) {
                try {
                System.out.print("\033[H\033[2J");
                System.out.flush();                        
                System.out.println("\n\t\t\t\t========================================================================");
                System.out.println("\n\t\t\t\t\t\t          Librarian Functionalities                                 ");
                System.out.print("\n\t\t\t\t========================================================================"); 
    
    
               System.out.println("\n\n\t\t\t\t\t\t\t 1. Manage Students");
               System.out.println("\n\t\t\t\t\t\t\t 2. Manage Books");
               System.out.println("\n\t\t\t\t\t\t\t 3. Return to Main Menu");
               System.out.print("\n\t\t\t\t\t\t\t Enter your choice: ");
               int mainChoice = scanner.nextInt();
               scanner.nextLine(); // Consume newline character
   
               switch (mainChoice) {
                   case 1:
                       manageStudents(librarySystem, scanner);
                       break;
   
                   case 2:
                       manageBooks(librarySystem, scanner);
                       break;
   
                   case 3:
                       return;
   
                   default:
                       System.out.println("\n\t\t\t\t\t\t\t Invalid Choice.....");
               }                    
                } catch (Exception e) {
                    System.out.println("\n\t\t\t\t\t\t\t Invalid Choice. ");
                }
                System.out.println("\n\t\t\t\t\t\t\t Press Enter key to Continue. ");
                scanner.nextLine();
                scanner.nextLine();

            }
        }  
           private void manageStudents(LibraryManagementSystem librarySystem, Scanner scanner){

            while (true) {
                try {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\n\t\t\t\t\t========================================================================");
                    System.out.println("\n\t\t\t\t\t\t\t           Manage Students                                     ");
                    System.out.println("\n\t\t\t\t\t========================================================================");
                    System.out.println("\n\t\t\t\t\t\t\t\t1. Add Students");
                    System.out.println("\n\t\t\t\t\t\t\t\t2. Remove Student");
                    System.out.println("\n\t\t\t\t\t\t\t\t3. Display Student");
                    System.out.println("\n\t\t\t\t\t\t\t\t4. Return to Previous Menu");
                    System.out.print("\n\t\t\t\t\t\t\t\tEnter Your Choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                       
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t                Add Student                                     ");
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Student name: ");
                        String studentName  = scanner.nextLine();
                                               
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Student ID: ");
                        int StudentID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Email of Student: ");
                        String mail = scanner.nextLine();
                        // System.out.print("\n\t\t\t\t\t\t\t\tHow many Books this Can Allocate: ");
                        // int numberOfBooks= scanner.nextInt();

                        boolean isVaildID = librarySystem.verify_ID(StudentID);
                        if(isVaildID){
    
                                addStudent(librarySystem, StudentID, studentName, mail);    
                        }else{
    
                            System.out.println("\n\t\t\t\t\t\t\t\tThis ID already exists.");
                            System.out.println("\n\t\t\t\t\t\t\t\tPlease Enter Another ID");
                        }      
                        
                        break;

                        case 2:
                        // Implement remove student functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Remove Student                                     ");
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        
                        System.out.print("\n\t\t\t\t\t\t\t\t Enter Student ID to remove: ");
                        int removeID = scanner.nextInt();
                        removeStudent(librarySystem, removeID);
                        break;

                        case 3:
                        // Implement display students functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t              Students List                                     ");
                        System.out.println("\n\t\t\t\t\t========================================================================");
                        displayStudents(librarySystem);
                    
                        break;

                        case 4:
                            return;                       
                    
                        default:
                            System.out.println("\n\t\t\t\t\t\t\t\tInvalid Choice. ");
                            //scanner.nextLine();
                    }


                } catch (Exception e) {
                    System.out.println("\n\t\t\t\t\t\t\t\tInvalid Choice. ");
                }
                System.out.println("\n\t\t\t\t\t\t\t\tPress Enter Key to Continue. ");
                scanner.nextLine();
                scanner.nextLine();
            }

        }

        private void manageBooks(LibraryManagementSystem librarySystem, Scanner scanner){
            while (true) {
                try {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\n\t\t\t\t\t=====================================================================");
                    System.out.println("\n\t\t\t\t\t\t\t               Manage Books                                    ");
                    System.out.println("\n\t\t\t\t\t=====================================================================");
                    

                    System.out.println("\n\t\t\t\t\t\t\t\t\t1. Add Book");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t2. Remove Book");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t3. Search Books");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t4. Borrow Book");                   
                    System.out.println("\n\t\t\t\t\t\t\t\t\t5. Return Book");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t6. Display Books");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t7. Display Issued Books");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t8. View Returned Books");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t9. Return to Previous Menu");
                    System.out.print("\n\t\t\t\t\t\t\t\t\tEnter Your Choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Add Books                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        
        
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter book author: ");
                        String author = scanner.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter book ISBN: ");
                        int ISBN = scanner.nextInt();
                        System.out.print("Enter number of copies: ");
                        int totalCopies = scanner.nextInt();
                        boolean checkISBN= librarySystem.Verify_ISBN(ISBN);
    
                        if(checkISBN){
                            addBook(librarySystem, title, author, ISBN,totalCopies);
                        }else{
    
                            System.out.println("\n\t\t\t\t\t\t\t\tThis ISBN of Book alread exists. ");
                            System.out.println("\n\t\t\t\t\t\t\t\tPlease Enter another ISBN.");
    
                        }                                                    
                        break;

                        case 2:
                        // Implement remove book functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Remove Book                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter ISBN of the book to remove: ");
                        int removeISBN = scanner.nextInt();
                            removeBook(librarySystem, removeISBN);
                        break;
        
                    case 3:
                        // Implement search book functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Search Book                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter ISBN of the book to search: ");
                        int searchISBN = scanner.nextInt();
                            searchBook(librarySystem, searchISBN);
                        break;
        
                    case 4:
                         // Borrow book option
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Borrow Book                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");                        

                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Student ID.");
                         int borrowID= scanner.nextInt();
                         System.out.print("\n\t\t\t\t\t\t\t\tEnter Book ISBN.");
                         int BorrowISBN=scanner.nextInt();
                         System.out.print("\n\t\t\t\t\t\t\t\tHow many days you want to borrow book.");
                        int noOfDaysToBorrow = scanner.nextInt(); 
                            borrowBook(librarySystem, BorrowISBN, borrowID,noOfDaysToBorrow);
                        break;
        
                        case 5:
                        // Implement return book functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Return Book                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                    
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Book ISBN: ");
                        int returnISBN = scanner.nextInt();
                        System.out.print("\n\t\t\t\t\t\t\t\tEnter Copy Number: ");
                        int returnCopyNumber = scanner.nextInt();
                        returnBook(librarySystem, returnISBN, returnCopyNumber);
                        break;
        
                    case 6:
                        // Implement display books functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush(); // Clear the buffer and displays the output immediatelly
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Books Lsit                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");                        

                        displayBooks(librarySystem);
                        break;

                    case 7:
                        // Implement display issued books functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Issued Books                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        
                        displayIsuuedBooks(librarySystem);                         
                        break;  

                    case 8:
                        // Implement view returned books functionality
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        System.out.println("\n\t\t\t\t\t\t\t               Returned Books                                    ");
                        System.out.println("\n\t\t\t\t\t=====================================================================");
                        

                        displayReturnedBooks(librarySystem);
                        break;
        
                    case 9:
                        return;
                    
                    default:
                        System.out.println("\n\t\t\t\t\t\t\t\tInvalid Choice. Press Enter to Continue.");
                        scanner.nextLine();
                    }

                } catch (Exception e) {
                    System.out.println("\n\t\t\t\t\t\t\t\tInvalid Choice. ");
                }
                System.out.println("\n\t\t\t\t\t\t\t\tPress Enter Key to Continue. ");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
           ////////////////////////////////////////////////////////////////


    public void addBook(LibraryManagementSystem LMS, String title, String author, int ISBN,int total_Copies) {
        LMS.addBook(title, author, ISBN,total_Copies);
    
    }

    public void removeBook(LibraryManagementSystem LMS, int ISBN) {
        LMS.removeBook(ISBN);
    }

    public void searchBook(LibraryManagementSystem LMS, int ISBN) {
        LMS.searchBook(ISBN);
    }

    public void borrowBook(LibraryManagementSystem LMS,int ISBN, int studentID,int noOfDays) {
        LMS.borrowBook(ISBN, studentID,noOfDays);
    }

    public void returnBook(LibraryManagementSystem LMS,int ISBN,int copyNum) {
        LMS.returnBook(ISBN,copyNum);
    }

    public void displayBooks(LibraryManagementSystem LMS) {
        LMS.displayBooks();
    }

    public void displayIsuuedBooks(LibraryManagementSystem LMS){
        LMS.viewIssuedBooks();
    }

    public void displayReturnedBooks(LibraryManagementSystem LMS){
        LMS.viewReturnedBooks();
    }

    public void addStudent(LibraryManagementSystem LMS, int studentID, String name,String Mail) {
        LMS.addStudent(studentID, name,Mail);
    }

    public void removeStudent(LibraryManagementSystem LMS,int studentID) {
        LMS.removeStudent(studentID);
    }

    public void displayStudents(LibraryManagementSystem LMS) {
        LMS.displayStudents();
    }
}


