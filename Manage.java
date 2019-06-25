package showtime;

import com.sun.javafx.scene.control.behavior.DateCellBehavior;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static showtime.Model.conn;

public class Manage {
    
    private HashMap<String,Admin> allAdminHashMap = new HashMap<String,Admin>();
    private HashMap<String,User> allUsersHashMap = new HashMap<String,User>();
    private HashMap<String,Theatre> allTheatresHashMap = new HashMap<String,Theatre>();
    private HashMap<String,HashMap<String,Timing>> showsHashMap = new HashMap<String,HashMap<String,Timing>>();
    private HashMap<String,Booking> allBookingsHashMap = new HashMap<String,Booking>();
    private HashMap<String,Movie> allMoviesHashMap = new HashMap<String,Movie>();
   
    //Create Admin accounts
    public void createAdminAccounts() throws Exception{
        ResultSet rs = Model.getAdminDetails();
        while(rs.next()){
            if(allAdminHashMap.get(rs.getString(1))==null){
                Admin admin = new Admin(rs.getString(1),rs.getString(2),rs.getString(3));
                allAdminHashMap.put(rs.getString(1),admin);
                System.out.println("Admin "+rs.getString(1)+" Created");
            }
        }
    }
    
    //Create User accounts
    public void createUserAccounts() throws Exception{
        ResultSet rs = Model.getUserDetails();
        while(rs.next()){
            if(allUsersHashMap.get(rs.getString(1))==null){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3));
                allUsersHashMap.put(rs.getString(1),user);
                System.out.println("User "+rs.getString(1)+" Created");
            }
        }
    }
    
    //Create Theatres
    public void createTheatres() throws Exception{
        ResultSet rs = Model.getTheatreDetails();
        while(rs.next()){
            if(allAdminHashMap.get(rs.getString(2))!=null){
                Theatre theatre = new Theatre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                Admin admin = allAdminHashMap.get(rs.getString(2));
                theatre.setAllAdminHashMap(rs.getString(2),admin);
                allTheatresHashMap.put(rs.getString(1),theatre);
                System.out.println("Theatre "+rs.getString(1)+" Created");
            }
        }
    }
    
    //Create Screens
    public void createScreens() throws Exception{
        ResultSet rs = Model.getScreenDetails();
        while(rs.next()){
            if(allTheatresHashMap.get(rs.getString(1))!=null){
                Theatre theatre = allTheatresHashMap.get(rs.getString(1));
                Screen screen = new Screen(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
                theatre.setAllScreensOfATheatreHashMap(rs.getString(2),screen);
                System.out.println("Screen "+rs.getString(2)+" Created for "+rs.getString(1));
            }
        }
    }
    
    //Create Timings
    public void createTimings() throws Exception{
        ResultSet rs = Model.getTimingDetails();
        while(rs.next()){
            if(allTheatresHashMap.get(rs.getString(1))!=null){
                Theatre theatre = allTheatresHashMap.get(rs.getString(1));
                Screen screen = theatre.getAllScreensOfATheatreHashMap().get(rs.getString(2));
                Timing timing = new Timing(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                screen.setAllTimingHashMap(rs.getString(3), timing);
            }
        }
    }
    
    //Create Shows
    public void createShows() throws Exception{
        ResultSet rs = Model.getShowDetails();
        while(rs.next()){
            String date = rs.getString(1);
            String theatreName = rs.getString(2);
            if(allTheatresHashMap.get(theatreName)!=null){
                Theatre theatre = allTheatresHashMap.get(theatreName);
                if(theatre.getAllScreensOfATheatreHashMap().get(rs.getString(3))!=null){
                    Screen screen = theatre.getAllScreensOfATheatreHashMap().get(rs.getString(3));
                    if(screen.getAllTimingHashMap().get(rs.getString(5))!=null){
                        Timing timing = screen.getAllTimingHashMap().get(rs.getString(5));
                        timing.setMovieName(rs.getString(4));
                        timing.setSeatAvailabilityHashMap(rs.getString(6),rs.getInt(7));
                        theatre.setDateHashMap(date, screen.getScreenName(), screen);
                        System.out.println("Show Added to "+theatreName);
                    } 
                }
            } 
        }
    }
    
    //Create Timings
    public void createCost() throws Exception{
        ResultSet rs = Model.getCostDetails();
        while(rs.next()){
            if(allTheatresHashMap.get(rs.getString(1))!=null){
                Theatre theatre = allTheatresHashMap.get(rs.getString(1));
                Cost cost = new Cost(rs.getString(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6));
                theatre.setCost(cost);
            }
        }
    }
    
    //Create Snacks
    public void createSnack() throws Exception{
        ResultSet rs = Model.getCostDetails();
        while(rs.next()){
            if(allTheatresHashMap.get(rs.getString(1))!=null){
                Theatre theatre = allTheatresHashMap.get(rs.getString(1));
                Snack snack = new Snack(rs.getString(1),rs.getString(2),rs.getDouble(3));
                theatre.setSnackHashMap(rs.getString(2),snack);
            }
        }
    }
    
    //Create Movies
    public void createMovies() throws Exception{
        ResultSet rs = Model.getMovieDetails();
        while(rs.next()){
            if(allMoviesHashMap.get(rs.getString(1))==null){
                Theatre theatre = allTheatresHashMap.get(rs.getString(1));
                Movie movie = new Movie(rs.getString(1),rs.getString(2));
                allMoviesHashMap.put(rs.getString(1),movie);
            }
        }
    }
    
    //Add new Admin Details
    public void addNewAdminDetails(String email) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Name");
        String name = br.readLine();
        System.out.println("Enter Contact Number");
        String contact = br.readLine();
        if(allAdminHashMap.get(email)==null){
            if(Model.addNewAdminDetails(email, name, contact)){
                Admin admin = new Admin(email, name, contact);
                allAdminHashMap.put(email,admin);
                System.out.println("Admin "+email+" Created");
            }
            else{
                System.out.println("Admin coundn't be created");
            }
                
        }
        else{
            System.out.println("Admin Already Exists");
        }
        
    }
    
    //Add new User Details
     public void addNewUserDetails(String email) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Name");
        String name = br.readLine();
        System.out.println("Enter Contact Number");
        String contact = br.readLine();
        if(allUsersHashMap.get(email)==null){
            if(Model.addNewUserDetails(email, name, contact)){
                User user = new User(email, name, contact);
                allUsersHashMap.put(email,user);
                System.out.println("User "+email+" Created");
            }
            else{
                System.out.println("User coundn't be created");
            }
        }
        else{
            System.out.println("User Already Exists");
        }
    }
    
    
    //Display All Admin Accounts
    public void displayAllAdminAccounts() throws Exception{
        
        for(Map.Entry<String,Admin> e : allAdminHashMap.entrySet()){
            System.out.println(e.getValue());
        }
        
    }
    
    //Display All User Accounts
    public void displayAllUserAccounts() throws Exception{
        
        for(Map.Entry<String,User> e : allUsersHashMap.entrySet()){
            System.out.println(e.getValue());
        }
        
    }
    
    //Display All Movies
    public void displayAllMovies() throws Exception{
        
        for(Map.Entry<String,Movie> e : allMoviesHashMap.entrySet()){
            System.out.println(e.getValue());
        }
        
    }
    
    //Display All Theatres
    public void displayAllTheatres() throws Exception{
        
        for(Map.Entry<String,Theatre> e : allTheatresHashMap.entrySet()){
            System.out.println(e.getValue());
        }
        
    }
    
    //Display Admin's particular theatres
    public void displayAllMyTheatres(String email){
         for(Map.Entry<String,Theatre> e : allTheatresHashMap.entrySet()){
             if(e.getValue().getAdminEmail().equals(email))
                System.out.println(e.getValue());
        }
    }
    
    //Check if the admin already exists
    public boolean adminExists(String email) throws Exception{
        
        ResultSet rs = Model.getEmailAndPasswordOfOneAdmin(email);
        
        while(rs.next()){
            if(rs.getString(1).equals(email))
                return true;
            
        }
        return false;
    } 
    
    //Create a new Admin
    public boolean CreateNewAdminAccount(String email,String password) throws Exception{
        return Model.createAdmin(email,password);
    }
    
    
    //Update Admin's Password
    public void UpdatePasswordForAdmin(String email) throws Exception{
        
        String password,newPassword;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Old Password:");
        password = br.readLine();
        ResultSet rs = Model.getEmailAndPasswordOfOneAdmin(email);
        while(rs.next()){
            
            if(rs.getString(2).equals(password)){
                System.out.println("Enter new Password:");
                newPassword = br.readLine();
                Model.UpdateAdminPassword(email, newPassword);
            }
            else{
                System.out.println("Unauthorized Access");
            }
            break;
            
        }
    }
    
    
    
    //check if the user exists
    public boolean userExists(String email) throws Exception{
        
        
        ResultSet rs = Model.getEmailAndPasswordOfOneUser(email);
        while(rs.next()){
            if(rs.getString(1).equals(email))
                return true;
            
        }
        return false;
    } 
    
    //Create a new User
    public boolean CreateNewUserAccount(String email,String password) throws Exception{
        
        return Model.createUser(email,password);
        
    }
    
    //Update password for user
    public void UpdatePasswordForUser(String email) throws Exception{
        
        String password,newPassword;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Old Password:");
        password = br.readLine();
        ResultSet rs = Model.getEmailAndPasswordOfOneAdmin(email);
        if(rs.getString(2).equals(password)){
            System.out.println("Enter new Password:");
            newPassword = br.readLine();
            Model.UpdateUserPassword(email, newPassword);
        }
        else{
            System.out.println("Unauthorized Access");
        }
        
    }
    
    //Admin Login
    public boolean adminLogin(String email,String password) throws Exception{
        
        ResultSet rs = Model.getEmailAndPasswordOfOneAdmin(email);
        while(rs.next()){
            if(rs.getString(2).equals(password)){
                return true;
            }
            break;
        }
        return false;
    }
    
    //User Login
    public boolean userLogin(String email,String password) throws Exception{
        
        ResultSet rs = Model.getEmailAndPasswordOfOneUser(email);
        while(rs.next()){
            if(rs.getString(2).equals(password)){
                return true;
            }
            break;
        }
        return false;
    }
    
    //Add new Movie
    public void addNewMovie()throws Exception{
        
        String movieName,director;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Movie Name");
        movieName = br.readLine();
        System.out.println("Enter Director Name");
        director = br.readLine();
        Model.addNewMovie(movieName,director);
    }
    
    //Add new Timing Directly
    public void addNewTiming()throws Exception{
        
        String theatreName,screenName;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        theatreName = br.readLine();
        System.out.println("Enter Screen Name");
        screenName = br.readLine();
        addNewTimingR(theatreName,screenName);
    }
    
    //Add new Timing Reusable
    public void addNewTimingR(String theatreName, String screenName) throws Exception{
     
        String timing,movieName;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Show Timing");
        timing = br.readLine();
        System.out.println("Enter Movie Name");
        movieName = br.readLine();
        Model.addNewTiming(theatreName, screenName, timing, movieName);
    }
    
    //Add new Screen
    public void addNewScreen() throws Exception{
        String theatreName;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        theatreName = br.readLine();
        addNewScreenR(theatreName);
    }
    
    //Add new Screen Reusable
    public void addNewScreenR(String theatreName) throws Exception{
         
        String screenName;
        int box,balcony,elite,gold,silver;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Screen Name");
        screenName = br.readLine();
        System.out.println("Enter Box Class Seats, 0 if not available");
        box = Integer.parseInt(br.readLine());
        System.out.println("Enter Balcony Class Seats, 0 if not available");
        balcony = Integer.parseInt(br.readLine());
        System.out.println("Enter Elite Class Seats, 0 if not available");
        elite = Integer.parseInt(br.readLine());
        System.out.println("Enter Gold Class Seats, 0 if not available");
        gold = Integer.parseInt(br.readLine());
        System.out.println("Enter Silver Class Seats, 0 if not available");
        silver = Integer.parseInt(br.readLine());

        //Insert into the Database
        if(Model.addNewScreen(theatreName, screenName, box, balcony, elite, gold, silver)){

            boolean tflag = true;
            do{

                System.out.println("Enter:\n1)Add new Timing For the Screen\nAnyother to Exit");
                if(Integer.parseInt(br.readLine())==1){
                    addNewTimingR(theatreName, screenName);
                }
                else
                    tflag = false;

            }while(tflag);
        }
    }
     
    //Add new Theatre
    public void addNewTheatre(String email)throws Exception{
        
        String theatreName,contact, address;        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        theatreName = br.readLine();
        System.out.println("Enter Contact No");
        contact = br.readLine();
        System.out.println("Enter Address");
        address = br.readLine();
        
        if(Model.addNewTheatre(theatreName, email, contact, address)){

            boolean sflag = true;
            do{

                System.out.println("Enter:\n1)Add new Screen For the Theatre\nAnyother to Exit");
                if(Integer.parseInt(br.readLine())==1){
                    addNewScreenR(theatreName);
                }
                else
                    sflag = false;

            }while(sflag);
        }
        
    }
    
    //adda new snack item
    public void addNewSnack() throws Exception{
        String theatreName, snackName;
        double cost;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        theatreName = br.readLine();
        System.out.println("Enter Snack Name");
        snackName = br.readLine();
        System.out.println("Enter Theatre Name");
        cost = Double.parseDouble(br.readLine());
        if(Model.addNewSnack(theatreName, snackName, cost)){
            
        }
    }
    
    //Display all shows of a theatre
    public void displayAllShowsOfATheatre()throws Exception{
        String theatreName;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        theatreName = br.readLine();
        if(allTheatresHashMap.get(theatreName)!=null){
            allTheatresHashMap.get(theatreName).displayAllShows();
        }
        else{
            System.out.println("Theatre Not Available");
        }
    }
    
    //*Add new Shows
    public void addNewShows(String adminEmail) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Theatre Name");
        String theatreName = br.readLine();
        System.out.println("Enter Movie Name");
        String movieName = br.readLine();
        if(allTheatresHashMap.get(theatreName)!=null){
            int ch;
            do{
                System.out.println("1)For one day\n2)For a Range of days\nAnyOther to Exit");
                ch = Integer.parseInt(br.readLine());
                if(ch==1){
                    int ch1;
                    System.out.println("Enter Start Date in dd-mm-yyyy format");
                    String sd = br.readLine();
                    do{
                        System.out.println("1)For one Screen\n2)For all screens\nAnyOther to Exit");
                        ch1 = Integer.parseInt(br.readLine());
                        if(ch1==1){
                            int ch2;
                            System.out.println("Enter Screen Name");
                            String screen = br.readLine();
                            Theatre th = allTheatresHashMap.get(theatreName);
                            Screen sc = th.getAllScreensOfATheatreHashMap().get(screen);
                            
                            do{
                                System.out.println("1)For one Timing\n2)For All Timings of that screen\nAnyOther to Exit");
                                ch2 = Integer.parseInt(br.readLine());
                                if(ch2==1){
                                    System.out.println("Enter Show Timing");
                                    String timin = br.readLine();
                                    Timing tmg = null;
                                    if(sc.getAllTimingHashMap().get(timin)!=null){
                                        tmg = sc.getAllTimingHashMap().get(timin);
                                    }
                                    
                                    ResultSet rs = Model.getScreenDetails();
                                    while(rs.next()){
                                        if(rs.getString(1).equals(th.getTheatreName()) && rs.getString(2).equals(sc.getScreenName())){
                                            //System.out.println("inside range of timings");
                                            tmg.setSeatAvailabilityHashMap("box",rs.getInt(3));
                                            Model.addNewShow(sd, theatreName, sc.getScreenName(), movieName,timin,"box",rs.getInt(3));
                                            tmg.setSeatAvailabilityHashMap("balcony",rs.getInt(4));
                                            Model.addNewShow(sd, theatreName, sc.getScreenName(),movieName ,timin,"balcony",rs.getInt(4));
                                            tmg.setSeatAvailabilityHashMap("elite",rs.getInt(5));
                                            Model.addNewShow(sd, theatreName, sc.getScreenName(),movieName ,timin,"elite",rs.getInt(5));
                                            tmg.setSeatAvailabilityHashMap("gold",rs.getInt(6));
                                            Model.addNewShow(sd, theatreName, sc.getScreenName(),movieName ,timin,"gold",rs.getInt(6));
                                            tmg.setSeatAvailabilityHashMap("silver",rs.getInt(7));
                                            Model.addNewShow(sd, theatreName, sc.getScreenName(),movieName,timin,"silver",rs.getInt(7));


                                            th.setDateHashMap(sd, movieName, sc);
                                        }
                                    }
                                    
                                    
                                }
                                else if(ch2==2){
                                    
                                    rangeOfTimings(sd, th, movieName, sc);
                                }
                                
                            }while(ch2 > 0 && ch2 < 3);
                        }
                        else if(ch1==2){
                            rangeOfScreens(sd, theatreName, movieName);
                        }
                        

                    }while(ch1>0 && ch1<3);
                }
                else if(ch==2){
                    rangeOfDates(theatreName,movieName);
                }
                
                
            }while(ch > 0 && ch < 3);
        }
        else{
            System.out.println("Invalid Theatre Name");
        }
        
    }
    
    //For a series of dates
    public void rangeOfDates(String theatreName, String movieName) throws Exception{
        
        List<Date> dates = new ArrayList<Date>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DateFormat formatter ; 
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        Date endDate = null;
        System.out.println("Enter Start Date as dd-MM-yyyy format");
        String str_date = br.readLine();
        System.out.println("Enter End Date as dd-MM-yyyy format");
        String end_date = br.readLine();
        
        
        
        try{
            startDate = (Date)formatter.parse(str_date);
            endDate = (Date)formatter.parse(end_date);
        }catch(Exception e) {} 
        
        long interval = 24*1000 * 60 * 60; // 1 day in millis
        long endTime = endDate.getTime() ; 
        long curTime = startDate.getTime();
        
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        
        for(int i=0;i<dates.size();i++){
            Date lDate =(Date)dates.get(i);
            String date = formatter.format(lDate);
            System.out.println(date);
            rangeOfScreens(date, theatreName, movieName);
        }

    }
    
    //For a range of screens
    public void rangeOfScreens(String date, String theatreName, String movieName) throws Exception{
        if(allTheatresHashMap.get(theatreName)!=null){
            Theatre theatre = allTheatresHashMap.get(theatreName);
            HashMap<String,Screen> hm = theatre.getAllScreensOfATheatreHashMap();
            for(Map.Entry<String,Screen> e : hm.entrySet()){
                rangeOfTimings(date, theatre, movieName, e.getValue());
            }
        }
    }
    
    
    
    //For a range of Timings
    public void rangeOfTimings(String date, Theatre theatre, String movieName, Screen screen) throws Exception{
        ResultSet rs = Model.getScreenDetails();
        for(Map.Entry<String,Timing> e : screen.getAllTimingHashMap().entrySet()){
            while(rs.next()){
                if(rs.getString(1).equals(theatre.getTheatreName()) && rs.getString(2).equals(screen.getScreenName())){
                    //System.out.println("inside range of timings");
                    e.getValue().setSeatAvailabilityHashMap("box",rs.getInt(3));
                    Model.addNewShow(date, theatre.getTheatreName(), screen.getScreenName(), movieName,e.getKey(),"box",rs.getInt(3));
                    e.getValue().setSeatAvailabilityHashMap("balcony",rs.getInt(4));
                    Model.addNewShow(date, theatre.getTheatreName(), screen.getScreenName(),movieName ,e.getKey(),"balcony",rs.getInt(4));
                    e.getValue().setSeatAvailabilityHashMap("elite",rs.getInt(5));
                    Model.addNewShow(date, theatre.getTheatreName(), screen.getScreenName(),movieName ,e.getKey(),"elite",rs.getInt(5));
                    e.getValue().setSeatAvailabilityHashMap("gold",rs.getInt(6));
                    Model.addNewShow(date, theatre.getTheatreName(), screen.getScreenName(),movieName ,e.getKey(),"gold",rs.getInt(6));
                    e.getValue().setSeatAvailabilityHashMap("silver",rs.getInt(7));
                    Model.addNewShow(date, theatre.getTheatreName(), screen.getScreenName(),movieName,e.getKey(),"silver",rs.getInt(7));
                    
                    
                    theatre.setDateHashMap(date, movieName, screen);
                }
            }
        }
    }
    
     
     
}
