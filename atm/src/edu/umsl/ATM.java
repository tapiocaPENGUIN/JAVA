package edu.umsl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ATM 
{
    boolean loadaccount = false;
    int option = 0;
    account[] acc = new account[3];
    

    public static void main(String args[]) throws IOException, ClassNotFoundException{

        ATM atm = new ATM();
        
        //atm.populateAccount();
        //atm.choose();
        atm.mainMenu();

        

        
        
    }
    public  void loadAccount() throws IOException, ClassNotFoundException 
    {
         FileInputStream inStream = new FileInputStream("C:/Temp/test.txt");
         ObjectInputStream in = new ObjectInputStream(inStream);
         acc = (account[])in.readObject();
         
         in.close();
         loadaccount = true;
    }
    
 public void saveAccount() throws FileNotFoundException, IOException
{
    FileOutputStream outStream = new FileOutputStream("C:/Temp/test.txt");
    ObjectOutputStream os = new ObjectOutputStream(outStream);
    os.writeObject(acc);
    os.flush();
    os.close();
}   
 public  void populateAccount(){
    if (loadaccount == false)
    {
        Scanner sc = new Scanner(System.in);
            for(int i = 0; i < acc.length; i++)
        {
//            int balance;
//              System.out.println("What is the balance of the account? ");
//              balance = sc.nextInt();

              acc[i] = new account();
    }
}
    else
    {
        System.out.println("I am not going to let you populate accounts now that you have loaded accounts");
    }
}
    
    public  void choose() throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        int accNum;
        do{
        System.out.println("Which account would you like 0,1,2 [3 to main menu]: ");
        accNum = sc.nextInt();
        
        switch(accNum){
            case 0:
            {
                acc[accNum].menu();
                break;
            }
            case 1:
            {
                acc[accNum].menu();
                break;
            }
            case 2:
            {
                acc[accNum].menu();
                break;
            }
            case 3:
            {
                mainMenu();
                
            }
            default:
                System.out.println("You didn't choose one of the options... ");
                break;
        }
        }while (accNum!=4);
    }
    
    public void mainMenu() throws IOException, ClassNotFoundException
    {
                do
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("What would you like to do 1)Load file 2)Populate 3)Save 4)Exit");
            option = sc.nextInt();
            switch(option)
            {
                case 1:
                {
                    loadAccount();
                    choose();
                    break;
                }
                case 2:
                {
                    populateAccount();
                    choose();
                    break;
                }
                case 3:
                {
                    saveAccount();
                    mainMenu();
                    break;
                }
                case 4:
                {
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("You didn't choose the correct option!");
                 
            }
        }while (option!=4);
    }
    
  
}
