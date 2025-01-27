
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class LibraryManagementSystem {

    
    private ArrayList<Librarian> librarians;

    
     public LibraryManagementSystem(){
        
        librarians= new ArrayList<>();
        LoadLibrarianFromFile();
        LoadStudentsFromFile();  
        LoadBooksFromFile();
             
     }

     public void registerLibrarian(String name, String passward) {
        int id = librarians.size() + 1; // Assign a unique ID

        File PasswardFile= new File("Register.txt");
        boolean isCorrectPassward= verifyPassward(passward);
        
        if (isCorrectPassward) {
            Librarian librarian = new Librarian(id, name, passward);
            WritePasswardToFile(id,name,passward);
            librarians.add(librarian);
            System.out.println("\n\n\t\t\t\t\t\t\tLibrarian registered successfully.");    
        }else{
            System.out.println("\n\n\t\t\t\t\t\t\tThis User Name ID already exists. ");
            return;
        }
    
        
    }

    // Function to read Librarians from file
    public void LoadLibrarianFromFile(){

        File passwordFile = new File("Register.txt");

        try {
            FileReader fileReader = new FileReader(passwordFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String passWard = null;
            String ID = null;
            String name = null;
            while ((passWard=reader.readLine())!=null) {
                ID = reader.readLine();
                name = reader.readLine();
                RegisterLibrarianInFile(Integer.valueOf(ID), name, passWard);
                               
            }
        fileReader.close();               
        reader.close();    

        } catch (Exception e) {
            System.out.println("\n\n\t\t\t\t\t\t\tError in reading the file. "+e.getMessage());
        }
    }

    public void WritePasswardToFile(int id,String name,String passward){
        
        File passwardFile = new File("Register.txt");
        try {
            FileWriter writer =new FileWriter(passwardFile,true);
            writer.write(passward+"\n");
            writer.write(Integer.toString(id)+"\n");
            writer.write(name+"\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("An error "+e.getMessage());
        }

    }
    public void RegisterLibrarianInFile(int id,String name, String password){

        Librarian librarian = new Librarian(id, name, password);
        librarians.add(librarian);
    }

    public boolean verifyPassward(String passward){
        File borrowFile = new File("Register.txt");
        try {
            
            FileReader reader  = new FileReader(borrowFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String passWard = null;
            String ID = null;
            String name = null;
            while ((passWard=bufferedReader.readLine())!=null) {
                ID = bufferedReader.readLine();
                name = bufferedReader.readLine();
                if (passWard.equals(passward)) {
                    
                    return false;
                }
                               
            }
            reader.close();
            bufferedReader.close();

            
        } catch (Exception e) {

            System.out.println("Error in reading the file. "+e.getMessage());
        }
        return true;     

    }

    // Method to login a librarian
    public Librarian loginLibrarian(String password) {
       

       for (Librarian librarian : librarians) {
        if (librarian.getMail().equals(password)) {
            System.out.println("\n\t\t\t\t\t\t\tLibrarian logged in successfully.");
           return librarian;
           }        

        }
        System.out.println("\n\n\t\t\t\t\t\t\tLibrarian not found. Please register first.");       
        return null;
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<Book> booksListHead;
    private Node<Student> studentsListHead;
        

    // Library class methods

    // Method to add a book to the library
    public void addBook(String title, String author, int ISBN,int totalCopies) {        

        Book newBook = new Book(title, author, ISBN,totalCopies);    
        
        Node<Book> newNode = new Node<>(newBook);
        if (booksListHead == null) {

            booksListHead = newNode;

        } else {

            newNode.next = booksListHead;
            booksListHead = newNode;

        }
        writeBooksToFile(title,author,ISBN,totalCopies);
        
        System.out.println("\n\t\t\t\t\t\t\t\tBook added successfully.");

    
    }

    public void addBooksFromFile(String title,String author,int ISBN,int totalCopies){
        Book newBook = new Book(title, author, ISBN,totalCopies);
        Node<Book> newNode = new Node<>(newBook);

        if(booksListHead==null){

            booksListHead=newNode;

        }
        else{
            newNode.next=booksListHead;
            booksListHead=newNode;
        }
    }
    
    // Write Books to file
    public void writeBooksToFile(String title,String author,int ISBN,int totalCopies){
        try {
            File myFile = new File("Books.txt");
            
            FileWriter writer = new FileWriter(myFile, true); // Use false to overwrite the file
             
            writer.write(Integer.toString(ISBN) + "\n");
            writer.write(title + "\n");
            writer.write(author + "\n");
            writer.write(Integer.toString(totalCopies)+"\n");
            writer.close();
            //System.out.println("Books data written successfully to file.");
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\t\t\tError in writing file: " + e.getMessage());
        }
    }
    // // Load the Books.

    public void LoadBooksFromFile() {
        booksListHead = null; // Clear the current list before loading new data
        try {

            File myFile = new File("Books.txt");
            FileReader fileReader = new  FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ISBN = null;
            String title = null;
            String author = null;
            String tCopies= null;
            while ((ISBN = bufferedReader.readLine())!= null) {

                title = bufferedReader.readLine();
                author = bufferedReader.readLine();
                tCopies=bufferedReader.readLine();
               // System.out.println("ISBN: " + ISBN + "\nTitle: " + title + "\nAuthor" + author);
                addBooksFromFile(title, author, Integer.valueOf(ISBN),Integer.valueOf(tCopies));
        
            }
            
            bufferedReader.close();
            loadBorrowedBooksStatus();
            loadReturnedBooksStatus();
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t\t\t\t\t\t\tFile not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\t\t\tError reading file: " + e.getMessage());
        }
    }

     // Method to display all books in the library
     public void displayBooks() {        
        
        Node<Book> current = booksListHead;   
        if (current == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo books available in the library.");
            return;
        }
        
        System.out.println("List of all Books:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-10s %-10s\n", "Title", "Author", "ISBN", "Copies");
        System.out.println("---------------------------------------------------------------");

        while (current != null) {
            Book book = current.data;
            System.out.printf("%-20s %-20s %-10d %-10d\n", book.getTitle(), book.getAuthor(), book.getISBN(),book.getTotalCopies());
            current = current.next;
        }
        System.out.println("---------------------------------------------------------------");
    }
    
    // Method to remove a book from the library
    public void removeBook(int ISBN) {
        
        if (booksListHead == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo books available in the library.");
            return;
        }
    
        if (booksListHead.data.getISBN() == ISBN) {
            booksListHead = booksListHead.next;
            System.out.println("\n\t\t\t\t\t\t\t\tBook removed successfully.");
            updateBooksFile();
            return;
        }
    
        Node<Book> current = booksListHead;
        Node<Book> previous = null;
    
        while (current != null && current.data.getISBN() != ISBN) {
            previous = current;
            current = current.next;
        }
    
        if (current == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tBook with ISBN " + ISBN + " not found in the library.");
            return;
        }
    
        previous.next = current.next;
        updateBooksFile();
        System.out.println("\n\t\t\t\t\t\t\t\tBook removed successfully.");
    }
    
    private void updateBooksFile() {
        File tempFile = new File("Temp.txt");
        File booksFile = new File("Books.txt");
    
        try (FileWriter fileWriter = new FileWriter(tempFile, true)) {
            tempFile.createNewFile();
    
            Node<Book> currentBook = booksListHead;
            while (currentBook != null) {
                fileWriter.write(currentBook.data.getISBN() + "\n");
                fileWriter.write(currentBook.data.getTitle() + "\n");
                fileWriter.write(currentBook.data.getAuthor() + "\n");
                fileWriter.write(currentBook.data.getTotalCopies() + "\n");  // Include total copies information
                currentBook = currentBook.next;
            }
    
            fileWriter.close();
            booksFile.delete();
            tempFile.renameTo(booksFile);
    
        } catch (IOException e) {
            System.out.println("\n\t\t\t\t\t\t\t\tAn error occurred while updating the books file: " + e.getMessage());
        }
    }
    // Method to search for a book in the library
    public void searchBook(int ISBN) {
        
        Node<Book> current = booksListHead;
    
        while (current != null) {
            if (current.data.getISBN() == ISBN) {
                System.out.println("\n\t\t\t\t\t\t\t\tBook found: \n");
                System.out.println("\n\t\t\t\t\t\t\t\tTitle: " + current.data.getTitle());
                System.out.println("\n\t\t\t\t\t\t\t\tAuthor: " + current.data.getAuthor());
                System.out.println("\n\t\t\t\t\t\t\t\tISBN: " + current.data.getISBN());
                
                // Check if any copy of the book is available
                boolean anyCopyAvailable = false;
                for (BookCopy copy : current.data.getCopies()) {
                    if (copy.isAvailable()) {
                        anyCopyAvailable = true;
                        break;
                    }
                }
                
                System.out.println("\n\t\t\t\t\t\t\t\tAvailable: " + (anyCopyAvailable ? "Yes" : "No"));
                return;
            }
            current = current.next;
        }
        System.out.println("\n\t\t\t\t\t\t\t\tBook with ISBN " + ISBN + " not found in the library.");
    }
    
        // Implementation remains the same as before
        public void borrowBook(int ISBN, int studentID, int noOfDaysToBorrow) {
            Node<Book> current = booksListHead;
    
            while (current != null) {
                if (current.data.getISBN() == ISBN) {
                    BookCopy availableCopy = current.data.getAvailableCopy();
                    if (availableCopy != null) {
                        Node<Student> studentNode = findStudent(studentID);
                        if (studentNode != null) {
                            Student student = studentNode.data;
    
                            Date currentDate = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DAY_OF_YEAR, noOfDaysToBorrow);
                            Date dueDate = calendar.getTime();
    
                            availableCopy.borrowCopy(student, currentDate, dueDate);
    
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            System.out.println("\n\t\t\t\t\t\tBook with name " + current.data.getTitle() + " borrowed successfully by Student " + studentNode.data.getName() + ".");
                            System.out.println("\n\t\t\t\t\t\t\t\tBorrowed Date: " + dateFormat.format(currentDate));
                            System.out.println("\n\t\t\t\t\t\t\t\tDue Date: " + dateFormat.format(dueDate));
                           // Date returnedDate = new Date();
                            addBorrowedBooksToFile(ISBN, studentID, noOfDaysToBorrow, currentDate, dueDate, availableCopy.copyNumber);
    
                            return;
                        } else {
                            System.out.println("\n\t\t\t\t\t\t\t\tStudent with ID " + studentID + " not found in the system.");
                            return;
                        }
                    } else {
                        System.out.println("\n\t\t\t\t\t\t\t\tBook is currently unavailable.");
                        return;
                    }
                }
                current = current.next;
            }
            System.out.println("\n\t\t\t\t\t\t\t\tBook with ISBN " + ISBN + " is not found in the library.");
        }
    
    // To add the books that are borrowed in the file

    private void addBorrowedBooksToFile(int ISBN, int studentID, int noOfDaysToBorrow, Date currDate, Date dDate, int copyNumber) {
   
    File borrowedFile = new File("BorrowedBooks.txt");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = dateFormat.format(currDate);
    String dueDate = dateFormat.format(dDate);
    try {
        FileWriter writer = new FileWriter(borrowedFile, true);
        writer.write(ISBN + "\n");
        writer.write(studentID + "\n");
        writer.write(noOfDaysToBorrow + "\n");
        writer.write(currentDate + "\n");
        writer.write(dueDate + "\n");
        writer.write(copyNumber + "\n");
        writer.write("null" + "\n"); // Write null for the returnDate initially
        writer.close();
    } catch (Exception e) {
        System.out.println("\n\t\t\t\t\t\t\t\tAn error in creating the file. " + e.getMessage());
    }
}

    // Function to correct the status of books that are borrowed
    private void loadBorrowedBooksStatus() {

    try {
        File borrowedFile = new File("BorrowedBooks.txt");
        FileReader reader = new FileReader(borrowedFile);
        BufferedReader bufferReader = new BufferedReader(reader);
        String isbn = null;

        while ((isbn = bufferReader.readLine()) != null) {
            String sID = bufferReader.readLine();
            String noOfDays = bufferReader.readLine();
            String currentDate = bufferReader.readLine();
            String dueDate = bufferReader.readLine();
            String copyNumber = bufferReader.readLine();
            String returnDate = bufferReader.readLine(); // Read the return date

            int ISBN = Integer.parseInt(isbn);
            int studentID = Integer.parseInt(sID);
            int copyNum = Integer.parseInt(copyNumber);
            Node<Book> current = booksListHead;
            while (current != null) {
                if (current.data.getISBN() == ISBN) {
                    for (BookCopy copy : current.data.getCopies()) {
                        if (copy.copyNumber == copyNum) {
                            copy.available = false;
                            Node<Student> studentNode = findStudent(studentID);
                            if (studentNode != null) {
                                copy.borrower = studentNode.data;
                                copy.borrower.borrowedBooks();
                            }
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            copy.borrowedDate = dateFormat.parse(currentDate);
                            copy.dueDate = dateFormat.parse(dueDate);
                            if (!returnDate.equals("null")) {
                                copy.returnDate = dateFormat.parse(returnDate);
                            }
                            break;
                        }
                    }
                    break;
                }
                current = current.next;
            }
        }
        bufferReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("\n\t\t\t\t\t\t\t\tBorrowedBooks file not found in the system. ");
    } catch (Exception e) {
        System.out.println("\n\t\t\t\t\t\t\t\tAn error in reading the file. " + e.getMessage());
    }
}

    
        
    // Method to return a book
    public void returnBook(int ISBN, int copyNumber) {

        if (booksListHead == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNothing to return.");
            return;
        }
        Node<Book> current = booksListHead;

        while (current != null) {
            
            if (current.data.getISBN() == ISBN) {
                
                for (BookCopy copy : current.data.getCopies()) {
                    
                    if (copy.copyNumber == copyNumber && !copy.isAvailable()) {
                                              
                        Date returnDate = new Date();
                       
                        FineCalculation fineCalculation = new FineCalculation(returnDate,copy.dueDate);
                       
                        double fine = fineCalculation.calculateFine();
                        System.out.println("Now After fine function");
                        if (fine > 0) {
                            System.out.println("\n\t\t\t\t\t\t\t\tBook returned late. Fine amount: $" + fine);
                          
                        } else {
                            System.out.println("\n\t\t\t\t\t\t\t\tBook returned on time. No fine.");
                           
                        }

                        copy.returnCopy();
                        manageReturnedBooksFromFile(ISBN, copyNumber);
                        System.out.println("\n\t\t\t\t\t\t" + current.data.getTitle() + " book copy " + copyNumber + " successfully returned.");

                        return;
                    }
                }
                System.out.println("\n\t\t\t\t\t\t\t\tBook copy " + copyNumber + " is already available or not found.");
                return;
            }
            current = current.next;
        }

        System.out.println("\n\t\t\t\t\t\t\t\tBook with ISBN " + ISBN + " not found in the library.");
    }

    // Function to create the Returned Boooks file and manage the returned books
private void manageReturnedBooksFromFile(int ISBN, int copyNumber) {

    File borrowedFile = new File("BorrowedBooks.txt");
    File returnedFile = new File("ReturnedBooks.txt");
    File tempFile = new File("TempFile.txt");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    try {
        FileWriter writer = new FileWriter(returnedFile, true);
        FileWriter fileWriter = new FileWriter(tempFile, true);
        FileReader fileReader = new FileReader(borrowedFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String isbn = null;
        String SID = null;
        String noOfDays = null;
        String cDate = null;
        String dDate = null;
        String cNumber = null;
        String returnDate = null;

        while ((isbn = bufferedReader.readLine()) != null) {

            SID = bufferedReader.readLine();
            noOfDays = bufferedReader.readLine();
            cDate = bufferedReader.readLine();
            dDate = bufferedReader.readLine();
            cNumber = bufferedReader.readLine();
            returnDate = bufferedReader.readLine();

            if (Integer.parseInt(isbn) == ISBN && Integer.parseInt(cNumber) == copyNumber) {
                writer.write(isbn + "\n");
                writer.write(SID + "\n");
                writer.write(noOfDays + "\n");
                writer.write(cDate + "\n");
                writer.write(dDate + "\n");
                writer.write(cNumber + "\n");
                writer.write(dateFormat.format(new Date()) + "\n"); // Write the return date
            } else {
                fileWriter.write(isbn + "\n");
                fileWriter.write(SID + "\n");
                fileWriter.write(noOfDays + "\n");
                fileWriter.write(cDate + "\n");
                fileWriter.write(dDate + "\n");
                fileWriter.write(cNumber + "\n");
                fileWriter.write(returnDate + "\n"); // Preserve existing return date
            }
        }

        bufferedReader.close();
        writer.close();
        fileWriter.close();

        borrowedFile.delete();
        tempFile.renameTo(borrowedFile);
    } catch (Exception e) {
        System.out.println("\n\t\t\t\t\t\t\t\tAn error in writing or reading the files. " + e.getMessage());
    }
}

    
    // Function to correct the status of books that are returned and correct the users as well
    
private void loadReturnedBooksStatus() {
    try {
        File returnedFile = new File("ReturnedBooks.txt");
        FileReader reader = new FileReader(returnedFile);
        BufferedReader bufferReader = new BufferedReader(reader);
        String isbn = null;

        while ((isbn = bufferReader.readLine()) != null) {
            String sID = bufferReader.readLine();
            String noOfDays = bufferReader.readLine();
            String currentDate = bufferReader.readLine();
            String dueDate = bufferReader.readLine();
            String copyNumber = bufferReader.readLine();
            String returnDate = bufferReader.readLine(); // Read the return date

            int ISBN = Integer.parseInt(isbn);
            int studentID = Integer.parseInt(sID);
            int copyNum = Integer.parseInt(copyNumber);
            Node<Book> current = booksListHead;
            while (current != null) {
                if (current.data.getISBN() == ISBN) {
                    for (BookCopy copy : current.data.getCopies()) {
                        if (copy.copyNumber == copyNum) {
                            copy.available = true;
                            Node<Student> studentNode = findStudent(studentID);
                            if (studentNode != null) {
                                copy.borrower = studentNode.data;
                                copy.borrower.returnedBooks();
                            }
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            copy.borrowedDate = dateFormat.parse(currentDate);
                            copy.dueDate = dateFormat.parse(dueDate);
                            copy.returnDate = dateFormat.parse(returnDate);
                            break;
                        }
                    }
                    break;
                }
                current = current.next;
            }
        }
        bufferReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("\n\t\t\t\t\t\t\t\tReturnedBooks file not found in the system. ");
    } catch (Exception e) {
        System.out.println("\n\t\t\t\t\t\t\t\tAn error in reading the file. " + e.getMessage());
    }
}

    
    // Function to view issued books
    public void viewIssuedBooks() {
        Node<Book> current = booksListHead;
        Boolean borrowBook = false;
    
        if (current == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Book Available in Library now. Please Add Book first");
            return;
        }
    
        System.out.println("\n\t\t\t\t\t\t\t\tBooks that are issued are: ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (current != null) {
            for (BookCopy copy : current.data.getCopies()) {
                if (!copy.isAvailable()) {
                    System.out.println("\n\t\t\t\t\t\t\t\tBook Title: " + current.data.getTitle());
                    System.out.println("\n\t\t\t\t\t\t\t\tISBN: " + current.data.getISBN());
                    System.out.println("\n\t\t\t\t\t\t\t\tCopy Number: " + copy.copyNumber);
                    System.out.println("\n\t\t\t\t\t\t\t\tAuthor: " + current.data.getAuthor());
                    System.out.println("\n\t\t\t\t\t\t\t\tStudent ID: " + copy.borrower.getID());
                    if (copy.borrower != null) {
                        System.out.println("\n\t\t\t\t\t\t\t\tBorrower Name: " + copy.borrower.getName());
                        System.out.println("\n\t\t\t\t\t\t\t\tIssued Date: " + dateFormat.format(copy.borrowedDate));
                        System.out.println("\n\t\t\t\t\t\t\t\tDue Date: " + dateFormat.format(copy.dueDate));
                    } else {
                        System.out.println("\n\t\t\t\t\t\t\t\tBorrower Information is missing");
                    }
    
                    System.out.println("\n\t\t\t\t\t\t\t\t------------------------------");
                    borrowBook = true;
                }
            }
            current = current.next;
        }
        if (!borrowBook) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Book is borrowed in system.");
            System.out.println("\n\t\t\t\t\t\t\t\tAll Books are available now.");
        }
    }
    // Function to view Returned Books

    public void viewReturnedBooks() {

        Node<Book> current = booksListHead;
        Boolean returnedBooksExist = false;
        if (current == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Book available in library now");
            System.out.println("\n\t\t\t\t\t\t\t\tPlease add Book first");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (current != null) {
            for (BookCopy copy : current.data.getCopies()) {
                if (copy.returnDate != null) { // Check if the book has been returned
                    System.out.println("\n\t\t\t\t\t\t\t\tTitle: " + current.data.getTitle());
                    System.out.println("\n\t\t\t\t\t\t\t\tISBN: " + current.data.getISBN());
                    System.out.println("\n\t\t\t\t\t\t\t\tCopy Number: " + copy.copyNumber);
                    System.out.println("\n\t\t\t\t\t\t\t\tAuthor: " + current.data.getAuthor());
                    System.out.println("\n\t\t\t\t\t\t\t\tReturned by: " + copy.borrower.getName());
                    System.out.println("\n\t\t\t\t\t\t\t\tReturned Date: " + dateFormat.format(copy.returnDate));
                    System.out.println("\n\t\t\t\t\t\t\t\t----------------------------");
                    returnedBooksExist = true;
                }
            }
            current = current.next;
        }
        if (!returnedBooksExist) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo books have been returned yet.");
        }
    }
    
    // Function to verify ISBN of Book.
    public boolean Verify_ISBN(int isbn){
        Node<Book> currentPtr = booksListHead;
        if(currentPtr==null){
            return true;
        }
        while (currentPtr!=null) {
            if(currentPtr.data.ISBN==isbn){
                return false;
            }
            currentPtr=currentPtr.next;
        }
        return true;
    }    

    // Method to add a student
    public void addStudent(int studentID, String name,String mail) {
        // Implementation remains the same as before
        Student newStudent = new Student(studentID, name,mail);
        Node<Student> newNode = new Node<>(newStudent);

        if (studentsListHead == null) {
            studentsListHead = newNode;
        } else {
            newNode.next = studentsListHead;
            studentsListHead = newNode;
        }
        writeStudentOnFile(studentID, name, mail);
        System.out.println("\n\t\t\t\t\t\t\t\tStudent added successfully.");
    }

    // To Store the record of Students after reading from file.
    private void addStudentsFromFile(int studentID,String name,String mail){

        Student newStudent = new Student(studentID, name, mail);
        Node<Student> newNode = new Node<Student>(newStudent);
        if(studentsListHead==null){
            studentsListHead=newNode;
        }
        else{
            newNode.next=studentsListHead;
            studentsListHead=newNode;
        }
    }

    // To Store the Students record on file.
    private void writeStudentOnFile(int studentID,String name,String mail){

        try {
            File studentFile = new File("Students.txt");
            FileWriter writer = new FileWriter(studentFile,true);
            studentFile.createNewFile();

            writer.write(Integer.toString(studentID)+"\n");
            writer.write(name+"\n");
            writer.write(mail+"\n");
            //writer.write(Integer.toString(numberOfBooks)+"\n");
            writer.close();
        }
         catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\t\t\tAn error in  creating the Student file. "+e.getMessage());
        }
    }

    // To Read Student Information from Student file
    private void LoadStudentsFromFile(){

        studentsListHead=null;

        try {

            File studentFile = new File("Students.txt");
            FileReader reader = new FileReader(studentFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
    
            String studentID = null;
            String sName = null;
            String sMail = null;


            while ((studentID=bufferedReader.readLine())!=null) {
                sName = bufferedReader.readLine();
                sMail = bufferedReader.readLine();
    
                addStudentsFromFile(Integer.valueOf(studentID),sName,sMail);            
            }
            bufferedReader.close();
        }
         catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\t\t\tAn error in reading the file. "+e.getMessage());
        }
       
    }


    // Function to verify Student ID.
    public boolean verify_ID(int ID){
        Node<Student> currentPtr = studentsListHead;
        while (currentPtr!=null) {
            if(currentPtr.data.ID==ID){
                System.out.println("Current ID "+currentPtr.data.ID+"Student ID "+ID);
                return false;
            }
            currentPtr=currentPtr.next;
        }
        return true;
    }

    // Method to remove a student
    public void removeStudent(int studentID) {
        
        Node<Student> currentStudent = studentsListHead;
        Node<Student> prev = null;
    
        if (currentStudent == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Student is available in the library now.");
            return;
        }

        while (currentStudent != null && currentStudent.data.getID() != studentID) {
            
            prev = currentStudent;
            currentStudent = currentStudent.next;
            
        }
    
    
        if (currentStudent == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Student with this ID " + studentID + " exists. Please add this student first.");
            return;
        }

        
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t\t\t\tIs this Student you want to delete:");
        System.out.println("\n\t\t\t\t\t\t\t\tName: " + currentStudent.data.getName());
        System.out.println("\n\t\t\t\t\t\t\t\tStudent ID: " + currentStudent.data.getID());
        System.out.println("\n\t\t\t\t\t\t\t\tTo delete, press Y ");
    
        char ch = scanner.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            if (prev == null) {
                studentsListHead = currentStudent.next;
                removeStudentFromFile();
            } else {
                prev.next = currentStudent.next;
                removeStudentFromFile();
            }
            System.out.println("\n\t\t\t\t\t\t\t\tDeleted Record successfully.");
        } else {
            System.out.println("\n\t\t\t\t\t\t\t\tDeletion cancelled.");
        }
    }

    // To Update file after removing the  Student
    private void removeStudentFromFile(){

        File tempFile = new File("Temp.txt");
        File studentFile = new File("Students.txt");
        try {
            FileWriter writer = new FileWriter(tempFile);
            Node<Student> currentStudent = studentsListHead;
            while (currentStudent!=null) {
                writer.write(Integer.toString(currentStudent.data.ID)+"\n");
                writer.write(currentStudent.data.name+"\n");
                writer.write(currentStudent.data.Email+"\n");
                //writer.write(Integer.toString(currentStudent.data.getMaxBooks()+"\n");
                currentStudent=currentStudent.next;
                
            }
            writer.close();
            studentFile.delete();
            tempFile.renameTo(studentFile);
            
            
        } catch (Exception e) {

            System.out.println("\n\t\t\t\t\t\t\t\tAn errorin creating the file. "+e.getMessage());
        }
    }

    // Method to display all students
    public void displayStudents() {
        if (studentsListHead == null) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo Student is registered now. Please add Student first.");
            return;
        }
    
        Node<Student> currentStudent = studentsListHead;
        System.out.println("\n\t\t\t\t\t\t\t\tList of all Students \n");
        System.out.println("\t\t\t\t\t---------------------------------------------------------------");
        System.out.printf("\t\t\t\t\t%-20s%-20s%-20s\n", "Name", "ID", "Email");
        System.out.println("\t\t\t\t\t---------------------------------------------------------------");
    
        while (currentStudent != null) {
            System.out.printf("\t\t\t\t\t%-20s%-20s%-20s\n", currentStudent.data.getName(), currentStudent.data.getID(), currentStudent.data.getMail());
            currentStudent = currentStudent.next;
        }
        System.out.println("\t\t\t\t\t---------------------------------------------------------------");
    }
    

    public Node<Student> findStudent(int studentID){
        Node<Student> current = studentsListHead;
        while (current!=null) {
            if(current.data.getID()==studentID){

                return current;              
            }
            current=current.next;
        }

        return null;
    }

    // Function to Display the borrowed books of specific student
    public void viewIssuedBooksByStudent(int id){

        Node<Book> currentBook = booksListHead;
        Node<Student> studentNode = findStudent(id);
        if(studentNode!=null){
            boolean issuedBooksExists=false;
            while (currentBook!=null) {
                if(!currentBook.data.available && currentBook.data.borrower!=null){
                    {
                        if(currentBook.data.borrower.getID()==id){
                            System.out.println("\n\t\t\t\t\t\t\t\tBooks that are issued by student "+currentBook.data.borrower.getName()+" is ");
                            System.out.println("\n\t\t\t\t\t\t\t\tBook Title: "+currentBook.data.title);
                            System.out.println("\n\t\t\t\t\t\t\t\tBook ISBN: "+currentBook.data.ISBN);
                            System.out.println("\n\t\t\t\t\t\t\t\tBook Auther: "+currentBook.data.author);
                            System.out.println("\n\t\t\t\t\t\t\t\t================================");
                            issuedBooksExists=true;
                        } 
                    }                 
        
                }
                currentBook=currentBook.next;    
            }
            if(!issuedBooksExists){
                System.out.println("\n\t\t\t\t\t\t\t\tThis Student has no issued book now.");
            }            
        }
    else{
        System.out.println("\n\t\t\t\t\t\t\t\tThis Student id did not found in the system. ");
        return;
    }
}
     // Function to view Returned Books by Student
     public void viewReturnedBooksByStudent(int iD){

        Node<Book> currentBook = booksListHead;
        Node<Student> studentNode = findStudent(iD);
        if(studentNode!=null){
            boolean isReturnedBooksExists = false;
            while (currentBook!=null) {
                if(currentBook.data.available && currentBook.data.borrower!=null){
                    if(currentBook.data.borrower.getID()==iD){

                        System.out.println("\n\t\t\t\t\t\t\t\tBooks that are Returned by student "+currentBook.data.borrower.name+" is ");
                        System.out.println("\n\t\t\t\t\t\t\t\tBook Title: "+currentBook.data.title);
                        System.out.println("\n\t\t\t\t\t\t\t\tBook ISBN: "+currentBook.data.ISBN);
                        System.out.println("\n\t\t\t\t\t\t\t\tBook Auther: "+currentBook.data.author);
                        System.out.println("\n\t\t\t\t\t\t\t\t================================");
                        isReturnedBooksExists=true;
                        
                    }
                }
                currentBook=currentBook.next;
            }
            if(!isReturnedBooksExists){
                System.out.println("\n\t\t\t\t\t\t\t\tThis Student has no Returned Books now. ");
            }

        }
        else{
            System.out.println("\n\t\t\t\t\t\t\t\tThis Student did not exists in the system. ");
            return;
        }

    }
    public static void welcome(){
       
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n\n\t\t\t\t\t\tDSA Semester Project");
    System.out.println("\n\t\t\t\tGroup Members\t\t\t1.M Faizan\n\t\t\t\t\t\t\t\t2.Junaid Zafar");
    System.out.println("\t\t\t\tProject Title\t\t\tLibrary Management System");
    System.out.println("\t\t\t\tInstitute\t\t\tGCUF Chiniot Campus");
    System.out.println("\t\t\t\tFaculty\t\t\t\tPhysical Sciences");
    System.out.println("\t\t\t\tDepartment\t\t\tComputer Science");
    System.out.println("\t\t\t\tInstructor\t\t\tDr.Waqar Hussain");
    System.out.println("\t\t\t\tDeveloped by\t\t\tM Faizan & Junaid Zafar");
    System.out.print("\n\t\t\t\t\t Press Enter to Continue......");
    scanner.nextLine();

    }

    //--------------------------------------------------------------------------------//

    // Main method
    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        //System.out.print("\033[H\033[2J");
        welcome();

        
        

        while (true) {
            try {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t╔══════════════════════════╗");
                System.out.println("\t\t\t\t\t\t\t║     Library Management   ║");
                System.out.println("\t\t\t\t\t\t\t║           System         ║");
                System.out.println("\t\t\t\t\t\t\t╚══════════════════════════╝");

                System.out.println("\n\t\t\t\t\t\t\t1. Librarian Functionalities");
                System.out.println("\n\t\t\t\t\t\t\t2. Student Functionalities");
                System.out.println("\n\t\t\t\t\t\t\t3. Exit");
                System.out.print("\n\t\t\t\t\t\t\tEnter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        while (true) {
                            Console console = System.console();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\n\t\t\t\t\t========================================================================");
                            System.out.println("\n\t\t\t\t\t\t                    Register and Login                                    ");
                            System.out.print("\n\t\t\t\t\t========================================================================");
                            System.out.println("\n\n\t\t\t\t\t\t\t\t1. Login");
                            System.out.println("\n\t\t\t\t\t\t\t\t2. Register");
                            System.out.print("\n\t\t\t\t\t\t\t\tEnter your choice: ");
                            int loginRegisterChoice = scanner.nextInt();
                            if (loginRegisterChoice == 1) {
                            //Console console = System.console();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\n\t\t\t\t\t========================================================================");
                            System.out.println("\n\t\t\t\t\t\t                    Login Librarian                                   ");
                            System.out.print("\n\t\t\t\t\t========================================================================");
                
                                
                            System.out.print("\n\n\t\t\t\t\t\t\tEnter Librarian passward to login: ");
                                char [] arrayPassward =  console.readPassword();
                                String passwardString  = new String(arrayPassward); 
                                Librarian loggedLibrarian = librarySystem.loginLibrarian(passwardString);
                                if (loggedLibrarian != null) {
                                    loggedLibrarian.librarianFunctionalities(librarySystem);

                                } else {
                                    System.out.println("\n\t\t\t\t\t\t\tInvalid passward. Please try again.");
                                }
                                break;
                            } else if (loginRegisterChoice == 2) {

                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\n\t\t\t\t\t========================================================================");
                            System.out.println("\n\t\t\t\t\t\t                    Register Librarian                                   ");
                            System.out.print("\n\t\t\t\t\t========================================================================");    
                            
                               
                                if(console==null){
                                    System.out.println("No console available. ");
                                    break;
                                }

                                System.out.print("\n\n\t\t\t\t\t\t\tEnter details to register a new librarian:");
                                String userName = console.readLine();
                                scanner.nextLine();
                                 
                                System.out.print("\n\n\t\t\t\t\t\t\tEnter Passward ");
                                char [] passwardArray  = console.readPassword();

                                String passward = new String(passwardArray);
                                
                                librarySystem.registerLibrarian(userName, passward);
                                // System.out.println("Press Enter to continue.");
                                // scanner.nextLine();
                                break;
                            } else {
                                System.out.println("\n\t\t\t\t\t\t\tInvalid choice. Please try again.");
                                scanner.nextLine();
                                scanner.nextLine();
                            }
                        }
                        break;

                case 2:

                    Student.studentFunctionalities(librarySystem);
                    break;

                case 3:

                    System.out.println("\n\t\t\t\t\t\t\tExiting...");
                    System.out.println("\n\t\t\t\t\t\t\tGoodbye! Thank you for using the Library Management System.");
                    System.exit(0);

                default:

                    System.out.println("\n\t\t\t\t\t\t\t\t\tInvalid Choice. Please try again.");
            }

        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\t\t\t\tInvalid input. Please try again.");
            scanner.nextLine(); // Clear the input buffer
        }

        System.out.println("\n\t\t\t\t\t\t\t\t\tPress Enter Key to continue.");
        scanner.nextLine(); // Wait for Enter key
        scanner.nextLine(); // Wait for Enter key again
    }
}

    
}
    
    


