package demo.model;

/**
 * Created by DanielY on 6/23/2015.
 */
//Used by login.html's form
public class user {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setUsername(String un){
        this.username = un;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public void setFirstName(String fn){
        this.firstName = fn;
    }
    public void setLastName(String ln){
        this.lastName = ln;
    }
}
