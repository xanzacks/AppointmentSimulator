package com.company;
//This is P9.4
import com.sun.source.tree.AssignmentTree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    abstract static class Appointment{//the parent class
        LocalDate Date;
        String Description;

        Appointment(){
            Date = null;
            Description = null;
        }

        Appointment(String Inputdate, String description){//convert strings into date and description
            try{
                Date = LocalDate.parse(Inputdate);
            }
            catch(Exception e){
                System.out.println("unable to initialize the time, Using the current time as default");
                Date = LocalDate.now();//if error take the current time
            }
            Description = description;
        }

        void setLocalDate(String Inputdate){
            try{
                Date = LocalDate.parse(Inputdate);
            }
            catch(Exception e){
                System.out.println("unable to initialize the time, Using the current time as default");
                Date = LocalDate.now();
            }
        }

        void setDescription(String description){//set description
            Description = description;
        }

        int getDay(){//return days
            return Date.getDayOfMonth();
        }

        int getYear(){//return years
            return Date.getYear();
        }

        int getMounth(){//return mounths
            return Date.getMonthValue();
        }

        void ShowDescription(){//return the description
            System.out.println(Description);
        }

        public abstract boolean occursOn(String input);//the abstract class that would be implemented later
    }

    static class Onetime extends Appointment{
        Onetime(String Inputdate, String description){//constructor
            super(Inputdate, description);
        }

        Onetime(){//default constructor
            super();
        }


        public boolean occursOn(String input){
            LocalDate Date = LocalDate.parse(input);
            if(Date.equals(super.Date)) {//if the date equal
                return true;
            }
            else{
                return false;
            }
        }
    }

    static class Daily extends Appointment{
        Daily(String Inputdate, String description){
            super(Inputdate, description);
        }

        Daily(){//default constructor
            super();
        }

        public boolean occursOn(String input){
            LocalDate Date = LocalDate.parse(input);
            if(Date.compareTo(super.Date) >= 0){// if the date is larger than the compared date
                return true;
            }
            else{
                return false;
            }
        }
    }

    static class Monthly extends Appointment{
        Monthly(String Inputdate, String description){
            super(Inputdate, description);
        }

        Monthly(){//default constructor
            super();
        }

        public boolean occursOn(String input){
            LocalDate Date = LocalDate.parse(input);
            if(Date.compareTo(super.Date) >= 0 && Date.getDayOfMonth() == super.getDay()){//if date is larger and days are the same
                return true;
            }
            else{
                return false;
            }
        }
    }

    public static Appointment ChooseTypes(char input){//function return the according object and ask the user to redo if error
        int i = 0;
        while (i == 0){
            if (input == 'O'){
                Onetime obj = new Onetime();
                return obj;
            }
            else if(input == 'D'){
                Daily obj = new Daily();
                return obj;
            }
            else if(input == 'M'){
                Monthly obj = new Monthly();
                return obj;
            }
            else{
                System.out.println("Not an option, please try again");//ask the user to do it again
                System.out.print("Enter the type (O-Onetime, D-Daily, or M-Monthly): ");
                Scanner Userin = new Scanner(System.in);
                String inputstr = Userin.nextLine();
                input = inputstr.charAt(0);
            }
        }
        return null;//never reach
    }

    public static void main(String[] args) {
        ArrayList<Appointment> MyAppointment = new ArrayList<Appointment>();//arraylist
        boolean keeprunning = true;
        do{
            System.out.print("Select an option: A for add an appointment, C for checking, Q to quit: ");
            Scanner Userin = new Scanner(System.in);
            String input = Userin.nextLine();
            if(input.charAt(0) == 'A'){// if A set up everything
                System.out.print("Enter the type (O-Onetime, D-Daily, or M-Monthly): ");
                input = Userin.nextLine();
                Appointment obj = ChooseTypes(input.charAt(0));
                System.out.print("Enter the date (yyyy-mm-dd): ");
                input = Userin.nextLine();
                obj.setLocalDate(input);
                System.out.print("Enter the description: ");
                input = Userin.nextLine();
                obj.setDescription(input);
                MyAppointment.add(obj);// add to the array
            }
            else if(input.charAt(0) == 'C'){//If C, check
                if(MyAppointment.size() == 0){//When there is nothing
                    System.out.println("There is no appointment at all");
                }
                else{//else input everything
                    System.out.print("Enter the year: ");
                    input = Userin.nextLine();
                    System.out.print("Enter the month: ");
                    input = input + "-" + Userin.nextLine();
                    System.out.print("Enter the day: ");
                    String temp = Userin.nextLine();
                    if(temp.charAt(0) == '0'){
                        input = input + "-" + temp;
                    }
                    else{//when number is less then 10
                        input = input + "-0" + temp;
                    }
                    Boolean NothingToday = false;
                    for(Appointment i : MyAppointment){//go through the whole array and compare everything
                        if(i.occursOn(input)){
                            i.ShowDescription();
                            NothingToday = true;
                        }
                    }
                    if(!NothingToday){//if it is false until the complete iteration, give the user nothing happened information
                        System.out.println("nothing special today ");
                    }
                }
                }
            else if(input.charAt(0) == 'Q'){//quit
                System.exit(7);
            }
            else{
                System.out.println("Not an option, please try again");
            }
        }while(keeprunning);
    }
}
