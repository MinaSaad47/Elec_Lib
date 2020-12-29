
package elec_lib;
import out_err.IO;
import db_util.DB;
import elec_data.Book;
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
        String ans = IO.prompt("Your Answer");
        
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
        String email = IO.prompt("Your Email");
        String pass = IO.prompt("Your Password");
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
        String ans = IO.prompt("Your Answer");
        if (ans.equals("1")) {
            search();
        } else if (ans.equals("2")) {
            add();
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
        String ans = IO.prompt("Your Answer");
        if (ans.equals("1")) {
            String name = IO.prompt("A Book Name");
            Book.search(name);
        } else if (ans.equals("2")) {
            String name = IO.prompt("An User name");
            Person.search(name);            
        } else {
            IO.error("Invalid Answer");
            search();
        }
    }
    
    
    
    private static void add() {
        IO.clear();
        IO.menu("Add Menu");
        IO.list("Book", "User");
        String ans = IO.prompt("Your Answer");
        if (ans.equals("1")) {
            Book book = new Book();
            book.setName(IO.prompt("Name"));
            book.setAuthor(IO.prompt("Author"));
            book.setReleaseDate(IO.prompt("Release Date"));
            Book.add(book);
            IO.loading("Adding Book");
            return;
        } else if (ans.equals("2")) {
            Person person = new Person();
            person.setFirstName(IO.prompt("First Name"));
            person.setLastName(IO.prompt("Last Name"));
            person.setEmail(IO.prompt("Email"));
            person.setPassword(IO.prompt("Password"));
            person.setAddress(IO.prompt("Address"));
            person.setCellPhone(IO.prompt("Cell Phone"));
            person.setType(IO.prompt("Type"));
            Person.add(person);
            IO.loading("Adding Person");
            return;
        } else {
            IO.error("Invalid Answer");
            IO.sleep(2000);
            add();
        }
    }
    
    
}
