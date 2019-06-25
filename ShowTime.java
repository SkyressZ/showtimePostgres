package showtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShowTime {

    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Manage manage = new Manage();
        
        manage.createAdminAccounts();
        manage.createUserAccounts();
        manage.createMovies();
        manage.createTheatres();
        manage.createScreens();
        manage.createTimings();
        manage.createSnack();
        manage.createCost();
        manage.createShows();
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int AdminOrUser;
        do{
            System.out.println("1)Admin\n2)User\nAnyOther to Exit");
            AdminOrUser = Integer.parseInt(br.readLine());
            
            if(AdminOrUser==1){
                System.out.println("Administrator:");
                int LoginOrSignUp;
                do{
                System.out.println("1)Login\n2)SignUp\nAnyOther <--");
                LoginOrSignUp = Integer.parseInt(br.readLine());
                
                if(LoginOrSignUp==1){
                    
                    String adminEmail,adminPassword;
                    System.out.println("Enter Email:");
                    adminEmail = br.readLine();
                    System.out.println("Enter Password:");
                    adminPassword = br.readLine();
                    
                    if(manage.adminLogin(adminEmail,adminPassword)){
                        System.out.println("Login Successful");
                        int choice;
                        do{
                            System.out.println("Enter:\n1)Update Password\n2)Add Movie\n3)Add Theatre\n4)Add Screen\n5)Add Timing\n6)Add Snack\n7)Display My Theatres\n8)Add new shows\n);
                            choice = Integer.parseInt(br.readLine());
                            
                            if(choice==1){
                                manage.UpdatePasswordForAdmin(adminEmail);
                            }
                            else if(choice==2){
                                manage.addNewMovie();
                            }
                            else if(choice==3){
                                manage.addNewTheatre(adminEmail);
                            }
                            else if(choice==4){
                                manage.addNewScreen();
                            }
                            else if(choice==5){
                                manage.addNewTiming();
                            }
                            else if(choice==6){
                                manage.addNewSnack();
                            }
                            else if(choice==7){
                                manage.displayAllMyTheatres(adminEmail);
                            }
                            else if(choice==8){
                                manage.displayAllMyTheatres(adminEmail);
                                System.out.println("Choose a Theatre");
                                manage.addNewShows(adminEmail);
                            }
                            
                        }while(choice > 0 && choice < 7);
                    }
                    else{
                        System.out.println("Invalid Credentials");
                    }
                    
                    
                }
                
                else if(LoginOrSignUp==2){
                    
                    String adminEmail,adminPassword,confirmAdminPassword;
                    System.out.println("Enter Email:");
                    adminEmail = br.readLine();
                    System.out.println("Enter Password:");
                    adminPassword = br.readLine();
                    System.out.println("Confirm Password:");
                    confirmAdminPassword = br.readLine();
                    
                    if(adminPassword.equals(confirmAdminPassword) && !(manage.adminExists(adminEmail))){
                        
                        if(manage.CreateNewAdminAccount(adminEmail,adminPassword)){
                            manage.addNewAdminDetails(adminEmail);
                        }
                         
                    }
                    else{
                        System.out.println("Invalid Credentials");
                    }
                
                }
            }while(LoginOrSignUp>0 && LoginOrSignUp<3);
                
                
                
            }
            else if(AdminOrUser==2){
                
                System.out.println("User:");
                System.out.println("1)Login\n2)SignUp\nAnyOther <--");
                int LoginOrSignUp = Integer.parseInt(br.readLine());
                do{
                
                if(LoginOrSignUp==1){
                    
                    String userEmail,userPassword;
                    System.out.println("Enter Email:");
                    userEmail = br.readLine();
                    System.out.println("Enter Password:");
                    userPassword = br.readLine();
                    
                    if(manage.userLogin(userEmail,userPassword)){
                        System.out.println("Login Successful");
                        int choice;
                        do{
                            System.out.println("Enter:\n1)Update Password\n2)Display All Theatres\n3)Display All Movies\n4)Display All Shows of a Theatre");
                            choice = Integer.parseInt(br.readLine());
                            
                            if(choice==1){
                                manage.UpdatePasswordForUser(userEmail);
                            }
                            else if(choice==2){
                                manage.displayAllTheatres();
                            }
                            else if(choice==3){
                                manage.displayAllMovies();
                            }
                            else if(choice==4){
                                manage.displayAllShowsOfATheatre();
                            }
                            
                        }while(choice > 0 && choice < 5);
                    }
                    else{
                        System.out.println("Invalid Credentials");
                    }
                    
                    
                }
                
                else if(LoginOrSignUp==2){
                    
                    String userEmail,userPassword,confirmUserPassword;
                    System.out.println("Enter Email:");
                    userEmail = br.readLine();
                    System.out.println("Enter Password:");
                    userPassword = br.readLine();
                    System.out.println("Confirm Password:");
                    confirmUserPassword = br.readLine();
                    
                    if(userPassword.equals(confirmUserPassword) && !(manage.adminExists(userEmail))){
                        if(manage.CreateNewUserAccount(userEmail,userPassword)){
                            manage.addNewUserDetails(userEmail);
                        }
                         
                    }
                    else{
                        System.out.println("Invalid Credentials");
                    }
                
                }
            }while(LoginOrSignUp>0 && LoginOrSignUp<3);
                
                
            }
            
        }while(AdminOrUser>0 && AdminOrUser<3);
  
    }
    
}
