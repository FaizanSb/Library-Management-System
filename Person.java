import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
class Person {

    protected int ID;
    protected String name;
    protected String Email;

    public Person(int ID, String name,String mail) {

        this.ID = ID;
        this.name = name;
        this.Email = mail;

    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    public String getMail(){

        return Email;
    }

}
