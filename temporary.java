package showtime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import static showtime.Model.conn;

public class temporary {
    
    //Get All Admin Email
    public static ResultSet getAllAdminAccounts() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email from public.adminaccounts"); 
        return stmt.executeQuery();
    }
    
    //Get All User Email
    public static ResultSet getAllUserAccounts() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email from useraccounts"); 
        return stmt.executeQuery();
    }
    
    //insert into shows("date","theatrename","screenname","moviename","timing","seatclass","availabletickets") values('24-06-2019','spi cinemas','screen 1','Avengers','09:00AM','silver','30')
    
    //DELETE FROM adminaccounts WHERE email = 's@g.com'
    //insert into useraccounts("email","password") values('user2@g.com','123')
    //insert into adminaccounts("email","password") values('admin@g.com','123')
    //UPDATE adminaccounts SET password = '456' WHERE email = 'b@g.com'
    
    //insert into theatre("name","adminemail","contact","address") values('spi cinemas','admin@g.com','9898989890','Coimbatore')

    //insert into screen("theatrename","screenname","box","balcony","elite","gold","silver")values('baba cinemas','screen 1','0','30','20','50','30')

    //insert into cost("theatrename","box","balcony","elite","gold","silver") values('spi cinemas','250','200','0','180','150')
    
    
//    public void setSeatAvailability(String timing,String seatClass,Integer seats) {
//        if(this.seatAvailability.get(timing)!=null){
//            HashMap<String,Integer> hm = this.seatAvailability.get(timing);
//            if(hm.get(seatClass)==null){
//                 hm.put(seatClass,seats);
//                this.seatAvailability.put(timing,hm);
//            }
//            else{
//                hm.put(seatClass,seats);
//            }
//        }
//        else{
//            HashMap<String,Integer> hm = new HashMap<String,Integer>();
//            hm.put(seatClass,seats);
//            this.seatAvailability.put(timing,hm);
//        }
//    }
//    
//    public void updateSeatAvailability(String Date,String theatre,String timing,String seatClass,Integer seats) throws Exception{
//        if(this.seatAvailability.get(timing)!=null){
//            HashMap<String,Integer> hm = this.seatAvailability.get(timing);
//            if(hm.get(seatClass)!=null){
//                 hm.put(seatClass,hm.get(seatClass)+seats);
//                 //updateTicketsInFile(Date,theatreName,screenName,timing,seatClass,hm.get(seatClass));  update database function
//            }
//        }
//    }
//    
//    public HashMap<String, HashMap<String, Integer>> getSeatAvailability() {
//        return seatAvailability;
//    }
//
//    
//    
//    public void displayAllSeats(){
//        System.out.println("Screen :"+getScreenName());
//        for(Map.Entry<String,HashMap<String,Integer>> hm : seatAvailability.entrySet()){
//            System.out.println("Timing : "+hm.getKey());
//            
//            for(Map.Entry<String,Integer> hm1 : hm.getValue().entrySet()){
//                System.out.println("Class : "+hm1.getKey()+"\nNo of Available Seats : "+hm1.getValue());                
//            }
//            System.out.println();
//            System.out.println("____________________________________");
//            System.out.println();
//        }
//        
//    }
    
}
