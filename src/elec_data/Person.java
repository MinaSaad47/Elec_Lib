
package elec_data;

import db_util.DB;
import out_err.IO;

public class Person {
    int id;;
    private String firstName, lastName, email, password, address, cellPhone, type;
    boolean isBlocked;

    public Person(int id, String firstName, String lastName, String email, String password, String address, String cellPhone, boolean isBlocked, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cellPhone = cellPhone;
        this.type = type;
        this.isBlocked = isBlocked;
    }
    public Person() {};

    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getType() {
        return type;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }
    
    public void displayInfo() {
        IO.row("0310102525150710",
               String.valueOf(id), firstName, lastName, email, address, cellPhone, String.valueOf(isBlocked), type);
    }
    
    public static void search(String name) {
        DB.connect();
        DB.createStat("SELECT * FROM person");
        IO.rowContent("0310102525150710", "ID", "Frist_Name", "Last_Name",
                "Email", "Address", "Cell_Phone", "1"
                        + "", "Type");
        
        DB.createStat("SELECT * FROM person");
        boolean dataFound = false;
        Person person = new Person();
        while(DB.shift()) {
            if (DB.getString("first_name").contains(name) || DB.getString("last_name").contains(name)) {
                dataFound = true;
                person = new Person(Integer.parseInt(DB.getString("id")),
                DB.getString("first_name"), DB.getString("last_name"),
                DB.getString("email"), DB.getString("password"), DB.getString("address"),
                DB.getString("cell_phone"), DB.getBoolean("is_blocked"), DB.getString("type"));
                person.displayInfo();
            }
        }
        DB.disconnect();
        if (!dataFound) {
            IO.error("No Data Found");
            IO.sleep(2000);
            IO.clear();
            return;
        }
    }
    
    public static void add(Person person) {
        person.setId(0);
        person.setIsBlocked(false);
        DB.connect();
        String query = "INSERT INTO person VALUES ( '" + String.valueOf(person.getId()) + "', '" +
                person.getFirstName()+ "', '" + person.getLastName()+ "', '" + person.getEmail()+ 
                "', '" + person.getPassword() + "', '" + person.getAddress() +"', '" + person.getCellPhone() +
                "', '" + person.getIsBlocked() + "', '" + person.getType() + "' )";
        DB.createStat(query);
        DB.disconnect();
    }
    
    
    
}
