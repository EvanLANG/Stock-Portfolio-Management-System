package Chen.Class;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username; //this will be primary key
    private String id;
    //private String gender;//'M' or 'F'
    //private String dateofbirth;//'xxxx-xx-xx'
    private String email;
    private String follow;
    public User(){
        ;
    }
    public void setUsername(String u){
        username = u;
    }
    public String getUsername(){
        return username;
    }
    public void setId(String n){
        id = n;
    }
    public String getId(){
        return id;
    }
    public void setEmail(String e){
        email = e;
    }
    public String getEmail(){
        return email;
    }
    public void setFollow(String n){
        follow = n;
    }
    public String[] getFollow(){
        if(follow.length() == 0){
            return null;
        }else{
            String[] list = follow.split("#");
            return list;
        }
    }
    public void addFollow(String sym){
        follow += "#"+sym;
    }
    public String getFollowString(){
        return follow;
    }
}
