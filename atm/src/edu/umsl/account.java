
package edu.umsl;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class account implements Serializable
{
    //ATM atm = new ATM();
    double balance;
    protected int firstdate;
    protected int seconddate;
    protected Calendar cal1 = new GregorianCalendar();
    protected Calendar cal2 = new GregorianCalendar();
    protected boolean dateflag = false;
    protected double rate;
    private String acctName;
    static Scanner sc = new Scanner(System.in);
    
    public account(){
        this.balance = 100;
    }
    public account(int balance){
        this.balance = balance;
        dateflag = false;
    }
    public void menu() throws IOException, ClassNotFoundException{
        String menuOpt = " ";
        int add;
        int subtract;
        Scanner sc = new Scanner(System.in);
        
        
        do
        {
            System.out.println("(C)heck your balance: ");
            System.out.println("(D)eposit: ");
            System.out.println("(W)ithdraw: ");
            System.out.println("(E)xit: ");
             //menuOpt = sc.nextLine();
             menuOpt = sc.next();
            
            if(menuOpt.equalsIgnoreCase("c")){
                if (dateflag == false){
                    getDate1();
                    System.out.println("Your balance is: $" + this.balance);
                    dateflag = true;
                    //menu();
                }else
                    getDate2();
                    Interest();
                    System.out.println("Your balance is: $" + this.balance);
                    //menu();
                }
            else if(menuOpt.equalsIgnoreCase("d")){
                if (dateflag == false)
                {
                    getDate1();
                System.out.println("How much money would you like to put into the account:");
                add = sc.nextInt();
                
                this.balance += add;

                System.out.println("Your new balance is: $" + this.balance);
                //menu();
                }
                else 
                {
                    getDate2();
                    Interest();
                    System.out.println("How much money would you like to put into the account:");
                    add = sc.nextInt();
                
                    this.balance += add;

                System.out.println("Your new balance is: $" + this.balance);
                //menu();
                }
            }

            else if(menuOpt.equalsIgnoreCase("w")){
                if (dateflag == false)
                {
                    System.out.println("How much would you like to withdraw: ");
                    subtract = sc.nextInt();
                    if(subtract <= this.balance)
                    {
                     getDate1();
                     this.balance -= subtract;
                     System.out.println("Your new balance is: " + this.balance);
                     //menu();
                    }
                    else
                    {
                     System.out.println("You don't have that much money");
                    }
                    
                }
                else
                {
                    System.out.println("How much would you like to withdraw: ");
                    subtract = sc.nextInt();
                    if(subtract < this.balance)
                    {
                     getDate2();
                     Interest();
                     this.balance -= subtract;
                     System.out.println("Your new balance is: " + this.balance);
                     //menu();
                    }
                    else
                    {
                     System.out.println("You don't have that much money");
                    }                    
                }
            }
//                if(dateflag == false){
//                System.out.println("How much would you like to withdraw: ");
//                subtract = sc.nextInt();
//                    if (subtract < this.balance){
//                    this.balance -= subtract;
//                    getDate1();
//                
//                    System.out.println("Your new balance is: $" + this.balance);
//                    }
//                    else{
//                        System.out.println("You don't have that much money");
//                    }
//                    else{
//                            
//                            }}
                    
                    
                

                
//                else{
//                    System.out.println("Hey you don't have that much money, you have " + this.balance);
                        
//            else{
//                System.out.println("You didn't choose an option...");
//            }

            
        }while(!menuOpt.equalsIgnoreCase("e"));
         
        //atm.choose();
    
    
        
    }
        
      
        
    
        
    
    private void getDate1()
    {
        if (dateflag==false){
        System.out.print("Enter first date(mm/dd/yyyy): ");

            Scanner sc = new Scanner(System.in);

             //= new BufferedReader(new InputStreamReader(System.in));

            String inputText = sc.nextLine();

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            ParsePosition pos = new ParsePosition(0);
            
            Date date = formatter.parse(inputText, pos);

            cal1.setTime(date);

            firstdate = cal1.get(Calendar.DAY_OF_YEAR);

            dateflag = true;
    }
    }
    private void getDate2()
    {
       if (dateflag == true)
       {
           System.out.print("Enter second date(mm/dd/yyyy): ");
           Scanner sc = new Scanner(System.in);
            String inputText = sc.nextLine();

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            ParsePosition pos = new ParsePosition(0);
            
            Date date = formatter.parse(inputText, pos);

            cal1.setTime(date);

            seconddate = cal1.get(Calendar.DAY_OF_YEAR);

            //dateflag = true;
            
            if (seconddate > firstdate)
            {
                Interest();
            }
            else
            {
                System.out.println("You can't back date!!!");
                getDate2();
            }
           
       }
    }
    private void Interest()
    {
            int datediff = seconddate - firstdate;

            rate = .05/365;

            double ratetime = Math.pow(1+rate,datediff);

            balance *= ratetime;
            balance = Double.parseDouble(new DecimalFormat("###############.##").format(balance));
            //System.out.println("$"+balance);
            firstdate = seconddate;
            
    }
    
    
}
