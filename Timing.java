package showtime;

import java.util.HashMap;
import java.util.Map;

public class Timing {
    
    private String theatreName, screenName, time, movieName;

    private HashMap<String,Integer> seatAvailabilityHashMap = new HashMap<String,Integer>();
    
    public Timing(String theatreName, String screenName, String time, String movieName) {
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.time = time;
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public void displaySeatAvailability(){
        for(Map.Entry<String,Integer> hm : seatAvailabilityHashMap.entrySet()){
            System.out.println("Class : "+hm.getKey()+"\tNo of Tickets : "+hm.getValue());
        }
        System.out.println("____________________________________________________________________________");
    }

    public HashMap<String, Integer> getSeatAvailabilityHashMap() {
        return seatAvailabilityHashMap;
    }

    public void setSeatAvailabilityHashMap(String seatClass, Integer seats) {
        if(this.seatAvailabilityHashMap.get(seatClass)==null){
            this.seatAvailabilityHashMap.put(seatClass,seats);
        }
    }
    
    
    
}
