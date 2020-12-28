
package elec_lib;
import out_err.IO;
import db_util.DB;
import elec_data.Person;
import java.util.Scanner;


public class Elec_Lib {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        start();
        
    }
    
    private static void start()  {
        IO.clear();
        IO.menu("Electronic Library");
        IO.list("log in", "exit");
        IO.prompt("your answer");
        String ans = in.next();
        
        if (ans.equals("1")) {
            IO.clear();
            log_in();
        } else if (ans.equals("2")) {
            IO.clear();
            System.exit(0);
        } else {
            IO.error("Invalid Answer");
            IO.sleep(2000);
            IO.clear();
            start();
        }
        
    }
    
    private static void log_in() {
        IO.menu("Log In");
        IO.prompt("Your Email");
        String email = in.next();
        IO.prompt("Your Password");
        String pass = in.next();
        Person person = login_valid(email, pass);
        IO.loading("Signing In");
        if (person != null) {
            IO.menu("Signed In");
            dash_board(person);
        } else {
            IO.error("Invalid Crediential");
        }
    }
    
    
    private static Person login_valid(String email, String pass) {
       DB.connect();
       DB.createStat("SELECT * FROM person");
       while(DB.shift()) {
           if (DB.getString("email").equals(email) && DB.getString("password").equals(pass)) {
               Person person = new Person(Integer.parseInt(DB.getString("id")),
               DB.getString("first_name"), DB.getString("last_name"),
               DB.getString("email"), DB.getString("password"), DB.getString("address"),
               DB.getString("cell_phone"), DB.getBoolean("is_blocked"), DB.getString("type"));
               
               DB.disconnect();
               return person;
           }
       }
        DB.disconnect();
        return null;
    }
    
    private static void dash_board(Person person) {
        IO.clear();
        String type = person.getType();
        IO.menu("DashBoard [" + type + "]");
        IO.rowContent("0310102525150710", "ID", "Frist_Name", "Last_Name",
                "Email", "Address", "Cell_Phone", "1"
                        + "", "Type");
        person.displayInfo();
        
        if (person.getType().equals("Librarian")) {
            lib_board(person);
        } else {
            
        }
                
    }
    
    private static void lib_board(Person person) {
        IO.list("Search", "Add", "Remove", "Exit");
        IO.prompt("Your Answer");
        String ans = in.next(); 
        if (ans.equals("1")) {
            search();
        } else if (ans.equals("2")) {
            //TODO: add();
        } else if (ans.equals("3")) {
            //TODO: remove();
        } else if (ans.equals("4")) {
            System.exit(0);
        } else {
            IO.error("Invalid Answer");
        }
        IO.clear();
        dash_board(person);
    }
    
    private static void search() {
        IO.clear();
        IO.menu("Search");
        IO.list("Book", "User");
        IO.prompt("Your Answer");
        String ans = in.next();
        if (ans.equals("1")) {
            IO.prompt("A Book Name");
            String name = in.next();
            search_book(name);
        } else if (ans.equals("2")) {
            
            
        } else {
            IO.error("Invalid Answer");
            search();
        }
    }
    
    private static void search_book(String name) {
        DB.connect();
        DB.createStat("SELECT * FROM book");
        IO.rowContent("03502005", "ID", "Name", "Author", "Date");
        boolean dataFound = false;
        while(DB.shift()) {
            if (DB.getString("name").contains(name)) {
                dataFound = true;
                IO.row("03502005", 
                    DB.getString("id"), DB.getString("name"),
                    DB.getString("author"), DB.getString("release_date"));
            }
        }
        if (!dataFound) {
            IO.error("No Data Found");
            IO.sleep(2000);
            IO.clear();
            return;
        } 
        
        IO.prompt("An ID To Select");
        String ans = in.next();
        //TODO: operations
    }
    
    
}
