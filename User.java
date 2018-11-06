

package summitsystem;
import java.util.Scanner;
import java.io.*;
import java.util.Formatter;
import java.util.ArrayList;


public  class User implements Serializable {
    private String name;
    private String login;
    private String password;
    private int type;
    
      User(String name,String login,String password,int type)
    {
        this.name=name;
        this.login=login;
        this.password=password;
        this.type=type;
    }
  
    boolean enter(String login,String password)
    {
       
        if(this.login.equals(login)&&this.password.equals(password))
        {
            System.out.println("Вы успешно авторизировались!");
            return true;
        }
        else
        {
            return false;
        }
    }
    String getName()
    { 
        return this.login;
    }
    
}

