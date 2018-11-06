
package summitsystem;
import java.util.Scanner;
import java.io.*;
import java.util.Formatter;
import java.util.ArrayList;

import java.util.Collections;


public  class  SummitSystem implements Serializable  {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    public static int checker()
     {
         Scanner t = new Scanner(System.in);
         int gt;
         try{
              
                   
                   gt= t.nextInt();
                  
                   return gt;
               
               
           }
           catch(Exception e) {
               gt=-1;
               
               return gt;
           }
     }
    private static void addUser(String name,String login,String password,String repeat,int type)
    {
       boolean f=true;
       for(User l:users)
       {
          
           if(l.getName().equals(login))
           {
               f=false;
           }
       }
      
           if(password.equals(repeat)&&f)
        {
        users.add(new User(name, login, password,type));
        }
           else
           {
               if(f)
               {
                   System.out.println("Введённые пароли не совпадают");
               }
               else
               {
                   System.out.println("Указанный логин занят");
               }
               
           }
           
       
    }
 
      private static  void save()
    {
         try
        {
            
            
            
          
            ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
            
            oos.writeObject(users);
            oos.writeObject(Leader.getRequests());
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 
    }
       private static void load()
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat")))
        {
         
             
           users=((ArrayList<User>)ois.readObject());
            Leader.getRequests().clear();
           Leader.getRequests().addAll(((ArrayList<Request>)ois.readObject()));
           
            
            
             
            
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        }
    }
       public static enum month{
           Январь,
           Февраль,
           Март,
           Апрель,
           Май,
           Июнь,
           Июль,
           Август,
           Сентябрь,
           Октябрь,
           Ноябрь,
           Декабрь
           
       }
        private static void processRequests()
    {
     
      boolean a,zz=true;
      String finish;
      int start=0,end=0,pu=0;
      
      
      ArrayList<Request> req = new ArrayList<>(Leader.getRequests());
       ArrayList<Integer> isk = new ArrayList<>();
       
       
      
      
          try{
              for(int i=0;i<req.size()-1;i++){
             
              a=req.get(i).getCountry().equals(req.get(i+1).getCountry());
              
              if(!a)
              {
                  finish="Возможности провести саммит нет.";
                  zz=false;
                  break;
                  
              }
              if(req.get(i).getMonth()!=req.get(i+1).getMonth())
              {
                  finish="Возможности провести саммит нет.";
                  zz=false;
                  break;
              }
              }
              if(zz)
              {
                  while(pu<2)
                  {
                      isk.clear();
                  for(int i=0;i<req.size();i++)
                  {
                      if(pu==0)
                      {
                      isk.add(req.get(i).getStartDay());
                      }
                      else
                      {
                          isk.add(req.get(i).getEndDay());
                      }
                  }
                  if(pu==0)
                  {
                      start=Collections.max(isk);
                  }
                  else{
                       end=Collections.min(isk);
                  }
                  pu++;
                  }
             
              
              if(start>end)
              {
                  System.out.println("Возможности провести саммит нет");
              }
              else
              {
                  finish=req.get(0).getCountry();
                       month[] allmonth=month.values();
              
              System.out.println("Страна:"+" "+finish+"\n"+"Месяц:"+" "+allmonth[req.get(0).getMonth()-1]+"\n"+"Дата начала:"+" "+start+"\n"+"Дата окончания:"+" "+end);
              Leader.getRequests().clear();
              }
              }
              else
              {
                  System.out.println("Возможности провести саммит нет");
              }
              
              
              
          }
          catch(Exception e)
          {
              System.out.println("Возможности провести саммит нет.");
          }
      
      
     
      
        
    }
       private static  User findUser(String login,String password)
        {
           for(User l : users)
           {
               
               if(l.enter(login, password))
               {
                    
                   return l;
                   
               }
                   
           }
           
       return null;
        }
        public static void goavt(){
              String lo,pa;
            
            Scanner t = new Scanner(System.in);
            System.out.println("Введите логин: ");
            lo=t.next();
            System.out.println("Введите пароль: ");
            pa=t.next();
            currentUser=findUser(lo,pa);
            
            if(currentUser==null)
            {
                System.out.println("Неверно введён логин или пароль");
            }
      
        }
         public static void gois()
      {
          Request r;
          ArrayList<Request> req = new ArrayList<>(Leader.getRequests());
          Scanner t = new Scanner(System.in);
          try{
              System.out.println("Введите вариант, который вам понравился: ");
              r=req.get(t.nextInt()-1);
              Leader.addRequest(r.getCountry(),r.getMonth(),r.getStartDay(),r.getEndDay());
              
          }
          catch(Exception e)
          {
              System.out.println("Ошибка, такого варианта не существует");
          }
      }
      
          public static void gonesam(){
            int f=1;
        int z;
        month[] allmonth=month.values();
        boolean b=true;
            ArrayList<Request> req = new ArrayList<>(Leader.getRequests());
            if(req.size()>0)
            {
            try{
                
            
            for(Request l:req)
       {
           System.out.println(f+"."+" "+"Страна:"+" "+l.getCountry()+" "+"Месяц:"+" "+allmonth[l.getMonth()-1]+" "+"Начало:"+" "+l.getStartDay()+" "+"Окончание:"+" "+l.getEndDay());
           f++;
        
          
           
       }
            System.out.println("Выберите цифру:\n1. Нет подходящих, добавить свой вариант вручную \n2. Выбрать из существующих\n3. Отменить действие добавления");
            while(b)
            {
             b=false;
            z=checker();
            switch(z)
            {
                case 1:goaddm();break;
                case 2:gois();break;
                case 3:break;
                default:b=true;System.out.println("Ошибка повторите ввод снова");break;
            }
            }
            
            }
            catch(Exception e)
            {
                System.out.println("Ошибка");
            }
            }
            else
            {
                System.out.println("Варианты отсутствуют");
            }
      
        }
          public static void goadd(){
           String na,lo,pa,repa;
           int type=0;
           int j;
           boolean b=true;
           System.out.println("Выберите одно из чисел:\n1. Регистрация аккаунта администратора\n2. Регистрация пользовательского аккаунта");
           while(b)
           {
               b=false;
           j=checker();
           switch(j){
               case 1:type=1;break;
               case 2:break;
               default:b=true;System.out.println("Ошибка повторите ввод снова");break;
               
           
           }
           }
            Scanner t = new Scanner(System.in);
            System.out.println("Введите ваше имя: ");
            na=t.next();
            System.out.println("Введите логин: ");
            lo=t.next();
            System.out.println("Введите пароль: ");
            pa=t.next();
            System.out.println("Введите пароль повторно: ");
            repa=t.next();
            addUser(na,lo,pa,repa,type);
           
        
        }
          public static int mdd(){
              int o;
              Scanner t = new Scanner(System.in);
              try{
                 o=t.nextInt();
                 return o;
            }
            catch(Exception e)
            {
                System.out.println("Ошибка, повторите ввод");
                return -1;
            }
              
          }
            public static void goaddm(){
                String a;
                int b,c,d;
                Scanner t = new Scanner(System.in);
                System.out.println("Введите страну: ");
            a=t.next();
            System.out.println("Введите месяц: ");
            b=mdd();
            while(!(b<=12&&b>0))
            {
                System.out.println("Ошибка, повторите ввод");
                  b=mdd();
            }
            
                    
          
            
           
            System.out.println("Введите желаемый день для начала проведения саммита: ");
            c=mdd();
            while(!(c<=31&&c>0))
            {
                System.out.println("Ошибка, повторите ввод");
                   c=mdd();
            }
           
            System.out.println("Введите день окончания саммита: ");
            d=mdd();
            while(!(d<=31&&d>0&&d>=c))
            {
                System.out.println("Ошибка, повторите ввод");
                   d=mdd();
            }
           
            Leader.addRequest(a,b,c,d);
                
            
            }
        
    public static  void main(String[] args) {
       
        int j,l;
        Scanner t = new Scanner(System.in);
      
   
       
        
        
        boolean bl;
        boolean bl_=true;
        boolean bl__;
        boolean rechek=true;
        while(rechek)
        {
            
            rechek=false;
        while(currentUser==null)
        {
            bl_=true;
            bl=true;
        System.out.println("Выберите одно из чисел:\n1. Войти в аккаунт\n2. Регистрация\n3. Вывести возможные места и время встречи\n4. Сохранить данные и завершить процесс \n5. Загрузить последнее сохранение ");
        while(bl)
        {
            bl=false;
        j=checker();
        
        switch(j){
            case 1:goavt();break;
            case 2:goadd();break;
            case 3:processRequests();break;
            case 4:save();System.exit(0);break;
            case 5:load();break;
            default:bl=true;System.out.println("Ошибка повторите ввод снова");break;
            
        }
        }
        }
        
        
        while(bl_)
        {
            System.out.println("Выберите цифрру:\n1. Ввести предложения места,времени вручную\n2. Выбрать место и время встречи из имеющихся \n3. Вывести возможные места и время встречи \n4. Сохранить данные и завершить процесс\n5. Выйти из аккаунта  \n6. Завершить процесс");
            bl_=false;
            bl=true;
            bl__=true;
            
        while(bl)
        {
            bl=false;
        j=checker();
        switch(j)
        {
            case 1:goaddm();break;
            case 2:gonesam();break;
            case 3:processRequests();break;
            case 4:save();System.exit(0);break;
            case 5:currentUser=null;bl__=false;rechek=true;break;
            case 6:System.exit(0);break;
                default:bl=true;System.out.println("Ошибка повторите ввод снова");break;
        }
        }
        System.out.println("Продолжить работу?\n1.Да \n2.Завершить процесс");
        while(bl__)
        {
            bl__=false;
            j=checker();
            switch(j)
            {
                case 1:bl_=true;break;
                case 2:System.exit(0);break;
                default:bl__=true;System.out.println("Ошибка повторите ввод снова");break;
            }
        }
        
        }
        
        
       
     
        }
    }
   
}
