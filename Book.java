import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Book {
        
    String title;
    String author;
    int ISBN;
    List<BookCopy> copies;

    Student borrower;
    boolean available;
    Date dueDate;
    Date currentDate;
    int totalCopies;
    int availableCopies;

    public Book(String title, String author,int ISBN,int totalCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.copies=new ArrayList<>();
        for(int i=0;i<totalCopies;i++){
            this.copies.add(new BookCopy(this,i+1));    // Adding the copies
        }
        this.totalCopies=totalCopies;
        this.availableCopies=totalCopies;
        this.available = totalCopies>0;
        this.borrower=null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public int getTotalCopies() {
        return copies.size();
    }

    public BookCopy getAvailableCopy() {
        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                return copy;
            }
        }
        return null;
    }

//     public Student getBorrower() {
//         return borrower;
//     }

//     public boolean isAvailable() {
//         return totalCopies > 0;
//     }

//     public void setBorrowedDate(Date currentDate){
//         this.currentDate=currentDate;
//     }

//     public Date getCurrentDate(){
//         return currentDate;
//     }

//    public void setDueDate(Date dueDate){
//         this.dueDate=dueDate;
//     }

//     public Date getDueDate() {
//         return dueDate;
//     }
//     public int getTotalCopies(){
//         return totalCopies;
//     }
//     public int getAvailableCopies(){
//         return availableCopies;
//     }
//     public void borrowBook(){
                  
//             availableCopies--;
//     }
//     public void returnBook(){
//         if(availableCopies<totalCopies){
//             this.borrower=null;
//             availableCopies++;
//         }
//     }

}

class BookCopy {
    Book book;
    int copyNumber;
    boolean available;
    Student borrower;
    Date borrowedDate;
    Date dueDate;
    Date returnDate;
    public BookCopy(Book book, int copyNumber) {
        this.book = book;
        this.copyNumber = copyNumber;
        this.available = true;
        this.borrower = null;
        this.borrowedDate = null;
        this.dueDate = null;
        this.returnDate=null;
    }

    public boolean isAvailable() {
        return available;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void borrowCopy(Student borrower, Date borrowedDate, Date dueDate) {
        this.available = false;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnDate=null;        
    }

    public void returnCopy() {
        this.available = true;
        this.returnDate = new Date();
    }
}