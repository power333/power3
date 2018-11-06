/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summitsystem;
import java.io.Serializable;


public class Request implements Serializable {
    private String country;
    private int month;
    private int startDay;
    private int endDay;
    Request(String country,int month,int from,int to)
    {
        this.country=country;
        this.month=month;
        this.startDay=from;
        this.endDay=to;
    }
    public String getCountry(){
        return this.country;
    }
     public int getMonth(){
         return this.month;
        
    }
      public int getStartDay(){
          return this.startDay;
        
    }
       public int getEndDay(){
        return this.endDay;
    }
}
