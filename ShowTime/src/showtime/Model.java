package showtime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {
    
    public static Connection conn;
    
    //Connection variable
    static{
        
        try{
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/showtime", "postgres", "admin");
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    //Get Admin Details
    public static ResultSet getAdminDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from admindetails"); 
        return stmt.executeQuery();
    }
    
    //Get Admin Details
    public static ResultSet getUserDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from userdetails"); 
        return stmt.executeQuery();
    }
    
    //Get Theatre Details
    public static ResultSet getTheatreDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from theatre"); 
        return stmt.executeQuery();
    }
    
    //Get Screen Details
    public static ResultSet getScreenDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from screen");
        return stmt.executeQuery();
    }
    
    //Get Timing Details
    public static ResultSet getTimingDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from timing");
        return stmt.executeQuery();
    }
    
    //Get Cost Details
    public static ResultSet getCostDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from cost");
        return stmt.executeQuery();
    }
    
    //Get Cost Details
    public static ResultSet getMovieDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from movies");
        return stmt.executeQuery();
    }
    
    //Get Show Details
    public static ResultSet getShowDetails() throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select * from shows");
        return stmt.executeQuery();
    }
    
    
    //Add A Movie
    public static boolean addNewMovie(String movieName, String directorName) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into movies(\"moviename\",\"directorname\") values(?,?)");
        try{
            stmt.setString(1,movieName);
            stmt.setString(2,directorName);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Movie "+movieName+" Added Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Movie "+movieName+" Already Exists");
        }
        return false;
    }
    
    //Add A Theatre
    public static boolean addNewTheatre(String theatreName, String adminEmail, String contact, String address) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into theatre(\"name\",\"adminemail\",\"contact\",\"address\") values(?,?,?,?)");
        try{
            stmt.setString(1,theatreName);
            stmt.setString(2,adminEmail);
            stmt.setString(3,contact);
            stmt.setString(4,address);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Theatre "+theatreName+" Added Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Theatre "+theatreName+" Already Exists");
        }
        return false;
    }
    
    //Add a new Screen
    public static boolean addNewScreen(String theatreName, String screenName,int box, int balcony, int elite, int gold, int silver) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into screen(\"theatrename\",\"screenname\",\"box\",\"balcony\",\"elite\",\"gold\",\"silver\")values(?,?,?,?,?,?,?)");
        try{
            stmt.setString(1,theatreName);
            stmt.setString(2,screenName);
            stmt.setInt(3,box);
            stmt.setInt(4,balcony);
            stmt.setInt(5,elite);
            stmt.setInt(6,gold);
            stmt.setInt(7,silver);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Screen "+screenName+" Added Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Screen "+screenName+" Already Exists");
        }
        return false;
    }
    
    //Add new Timing
    public static boolean addNewTiming(String theatreName, String screenName,String timing, String movieName) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into timing(\"theatrename\",\"screenname\",\"time\",\"moviename\") values(?,?,?,?)");
        try{
            stmt.setString(1,theatreName);
            stmt.setString(2,screenName);
            stmt.setString(3,timing);
            stmt.setString(4,movieName);
           
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Timing Added Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Timing Already Exists");
        }
        return false;
    }
    
    
    
    //Add new Snack
    public static boolean addNewSnack(String theatreName, String snackName, double cost) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into snacks(\"theatrename\",\"snackname\",\"cost\") values(?,?,?)");
        try{
            stmt.setString(1,theatreName);
            stmt.setString(2,snackName);
            stmt.setDouble(3,cost);
           
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Snack Added Successfully to "+theatreName);
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Snack Already Exists");
        }
        return false;
    }
    
    //Add new Show
     public static boolean addNewShow(String date, String theatreName, String screenName, String movieName, String timing, String seatClass, Integer seats) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into shows(\"date\",\"theatrename\",\"screenname\",\"moviename\",\"timing\",\"seatclass\",\"availabletickets\") values(?,?,?,?,?,?,?)");
        try{
            stmt.setString(1,date);
            stmt.setString(2,theatreName);
            stmt.setString(3,screenName);
            stmt.setString(4,movieName);
            stmt.setString(5,timing);
            stmt.setString(6,seatClass);
            stmt.setInt(7,seats);
            
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Show Added Successfully @"+timing+" in "+screenName+" of "+theatreName+" on "+date);
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Show was not added");
        }
        return false;
    }
    
    
    
    //Get Email of a Single Admin
    public static ResultSet getEmailOfOneAdmin(String email) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email from adminaccounts where email = ?");
        stmt.setString(1,email);
        return stmt.executeQuery();
    }
    
    //Get Email and Password of One Admin
    public static ResultSet getEmailAndPasswordOfOneAdmin(String email) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email,password from adminaccounts where email = ?");
        stmt.setString(1,email);
        return stmt.executeQuery();
    }
    
    //Create a new Admin
    public static boolean createAdmin(String email, String password) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into public.adminaccounts(\"email\",\"password\") values(?,?)");
        try{
            stmt.setString(1,email);
            stmt.setString(2,password);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Account Created Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Email Already Exists");
        }
        return false;
    }
    
    //Update Admin's Password
    public static void UpdateAdminPassword(String email, String newPassword) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("UPDATE adminaccounts SET password = ? WHERE email = ?");
        
        try{
            stmt.setString(1,newPassword);
            stmt.setString(2,email);
            int aff = stmt.executeUpdate();
            if(aff>0)
                System.out.println("Password Updated Successfully");
        }
        catch(Exception e){
            System.out.println("Password Update Failed");
        }
            
    }
    
    //Get Email of a Single User
    public static ResultSet getEmailOfOneUser(String email) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email from useraccounts where email = ?");
        stmt.setString(1,email);
        return stmt.executeQuery();
    }
    
    //Get Email and Password of One User
    public static ResultSet getEmailAndPasswordOfOneUser(String email) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("select email,password from useraccounts where email = ?");
        stmt.setString(1,email);
        return stmt.executeQuery();
    }
    
    //Create a new user
    public static boolean createUser(String email, String password) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into useraccounts(\"email\",\"password\") values(?,?)");
        try{
            stmt.setString(1,email);
            stmt.setString(2,password);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Account Created Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Email Already Exists");
        }
        return false;
    }
    
    //Update User's Password
    public static void UpdateUserPassword(String email, String newPassword) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("UPDATE useraccounts SET password = ? WHERE email = ?");
        try{
            stmt.setString(1,newPassword);
            stmt.setString(2,email);
            int aff = stmt.executeUpdate();
            if(aff>0)
                System.out.println("Password Updated Successfully");
        }
        catch(Exception e){
            System.out.println("Password Update Failed");
        }
    }
    
    //Add new Admin details
    public static boolean addNewAdminDetails(String email,String name, String contact) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("insert into admindetails(\"email\",\"name\",\"contact\") values(?,?,?)");
        try{
            stmt.setString(1,email);
            stmt.setString(2,name);
            stmt.setString(3,contact);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("Admin Details Added Successfully Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Admin Details Already Exists");
        }
        return false;
    }
    
    
    //Add new User Details
    public static boolean addNewUserDetails(String email,String name, String contact) throws Exception{
        
        PreparedStatement stmt = conn.prepareStatement("insert into userdetails(\"email\",\"name\",\"contact\") values(?,?,?)");
        try{
            stmt.setString(1,email);
            stmt.setString(2,name);
            stmt.setString(3,contact);
            int aff = stmt.executeUpdate();
            if(aff>0){
                System.out.println("User Details Added Successfully Successfully");
                return true;
            }
        }
        catch(Exception e){
            System.out.println("User Details Already Exists");
        }
        return false;
    }
    
}
