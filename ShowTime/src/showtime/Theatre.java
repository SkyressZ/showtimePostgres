package showtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Theatre {
    
    private String theatreName, adminEmail, contact, address;
    private HashMap<String,Admin> allAdminHashMap = new HashMap<String,Admin>();
    private HashMap<String,HashMap<String,Screen>> dateHashMap = new HashMap<String,HashMap<String,Screen>>();
    private HashMap<String,Snack> snackHashMap = new HashMap<String,Snack>();
    private HashMap<String,Booking> allBookingHashMap = new HashMap<String,Booking>();
    private HashMap<String,Screen> allScreensOfATheatreHashMap = new HashMap<String,Screen>();
    private Cost cost;
    

    public Theatre(String theatreName, String adminEmail, String contact, String address) {
        this.theatreName = theatreName;
        this.adminEmail = adminEmail;
        this.contact = contact;
        this.address = address;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Snack> getSnackHashMap() {
        return snackHashMap;
    }

    public void setSnackHashMap(String snackName, Snack snack) {
        if(this.snackHashMap.get(snackName)==null){
            this.snackHashMap.put(snackName,snack);
        }
    }

    public HashMap<String, Admin> getAllAdminHashMap() {
        return allAdminHashMap;
    }

    public void setAllAdminHashMap(String adminEmail, Admin admin) {
        if(allAdminHashMap.get(adminEmail)==null){
            allAdminHashMap.put(adminEmail,admin);
        }
    }

    public HashMap<String, Screen> getAllScreensOfATheatreHashMap() {
        return allScreensOfATheatreHashMap;
    }

    public void setAllScreensOfATheatreHashMap(String screenName, Screen screen) {
        if(allScreensOfATheatreHashMap.get(screenName)==null){
            allScreensOfATheatreHashMap.put(screenName,screen);
        }
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }
    
    
    public void displayAllShows(){
        for(Map.Entry<String,HashMap<String,Screen>> hm : dateHashMap.entrySet()){
            System.out.println("Date : "+hm.getKey());
            for(Map.Entry<String,Screen> hm1 : hm.getValue().entrySet()){
                System.out.println("Screen : "+hm1.getKey());
                hm1.getValue().displayAllTimings();
            }
            
        }
    }

    public HashMap<String, HashMap<String, Screen>> getDateHashMap() {
        return dateHashMap;
    }

    public void setDateHashMap(String date, String screenName, Screen screen) {
        if(this.dateHashMap.get(date)!=null){
            if(this.dateHashMap.get(date).get(screenName)==null)
                this.dateHashMap.get(date).put(screenName,screen);
        }
        else{
            HashMap<String,Screen> hm = new HashMap<String,Screen>();
            hm.put(screenName,screen);
            dateHashMap.put(date,hm);
        }
    }

    public HashMap<String, Booking> getAllBookingHashMap() {
        return allBookingHashMap;
    }

    public void setAllBookingHashMap(HashMap<String, Booking> allBookingHashMap) {
        this.allBookingHashMap = allBookingHashMap;
    }
    
    
    

    @Override
    public String toString() {
        return String.format("Name : %s\tAdmin : %s\tContact : %s\tAddress : %s",getTheatreName(),getAdminEmail(),getContact(),getAddress());
    }
    
    
    
    
}
