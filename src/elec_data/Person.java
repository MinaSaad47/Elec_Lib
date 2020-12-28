
package elec_data;

import out_err.IO;

public class Person {
    int id;
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

    public boolean isIsBlocked() {
        return isBlocked;
    }
    
    public void displayInfo() {
        IO.row("0310102525150710",
               String.valueOf(id), firstName, lastName, email, address, cellPhone, String.valueOf(isBlocked), type);
    }
    
    
    
    
}
