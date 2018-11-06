
package summitsystem;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Leader extends User implements Serializable  {
   
    private static ArrayList<Request> requests = new ArrayList<>();
    Leader()
    {
        super("","","",1);
    }
  
       public static int checker()
     {
         Scanner t = new Scanner(System.in);
         int gt=0;
         try{
              
                   
                   gt= t.nextInt();
                  
                   return gt;
               
               
           }
           catch(Exception e) {
               gt=-1;
               
               return gt;
           }
     }

    public static  void addRequest(String country,int month,int from,int to)
    {
        
        requests.add(new Request(country,month,from,to));
    }
    public static ArrayList getRequests()
    {
     return requests;
    }
}
