package showtime;

import java.util.HashMap;

public class Admin {
    
    private String email, name, contact;
    private HashMap<String,Theatre> theatresOfAdminHashMap = new HashMap<String,Theatre>();

    public Admin(String email, String name, String contact) {
        this.email = email;
        this.name = name;
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String toString(){
        return String.format("Name : %s\tEmail : %s\tContact : %s",this.name,this.email,this.contact);
    }
    
}
