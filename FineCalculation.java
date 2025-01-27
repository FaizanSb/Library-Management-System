import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class FineCalculation {

    private Date retrunDate;
    private Date dueDate;
    private long diffInMillies;
    private long diffIndays;
    private double finePerDay = 100.0;
 
    public FineCalculation(Date rDate,Date duDate){
         this.retrunDate=rDate;
         this.dueDate=duDate;
     }
 
     public double calculateFine(){
         
         diffInMillies = retrunDate.getTime()-dueDate.getTime();
         diffIndays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
         if(diffIndays>0){
             return diffIndays*finePerDay;
         } else {
             return 0;
         }
 
     }
    
 }