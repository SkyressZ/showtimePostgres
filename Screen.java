package showtime;

import java.util.HashMap;
import java.util.Map;

public class Screen {
    
    private String theatreName, screenName;
    private int box,balcony,elite,gold,silver;
    private HashMap<String,Timing> allTimingHashMap = new HashMap<String,Timing>();

    public Screen(String theatreName, String screenName, int box, int balcony, int elite, int gold, int silver) {
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.box = box;
        this.balcony = balcony;
        this.elite = elite;
        this.gold = gold;
        this.silver = silver;
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

    public HashMap<String, Timing> getAllTimingHashMap() {
        return allTimingHashMap;
    }

    public void setAllTimingHashMap(String time, Timing timing) {
        if(this.allTimingHashMap.get(time)==null){
            this.allTimingHashMap.put(time,timing);
        }
    }
    
    public void displayAllTimings(){
        for(Map.Entry<String,Timing> hm : allTimingHashMap.entrySet()){
            System.out.println("Show Timing :"+hm.getKey());
            hm.getValue().displaySeatAvailability();
        }
    }

    
    
}
