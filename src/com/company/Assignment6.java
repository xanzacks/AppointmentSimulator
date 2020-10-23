package com.company;

import java.util.Scanner;

public class Assignment6 {
    static int pos = 0;
    static int function(String target, String find){
        if(find.length() <= target.length() && target.substring(0, find.length()).equals(find)){
            return pos;
        }
        else if(find.length() > target.length()){
            return -1;
        }
        pos++;
        return function(target.substring(1), find);
    }
    public static void main(String[] args){
        Scanner Userin = new Scanner(System.in);
        boolean keeprunning = true;
        while(keeprunning){

        }
        System.out.println("Please input the word that you would like to search in");
        String target = Userin.nextLine();
        System.out.println("Please input the word that you would like to search for");
        String find = Userin.nextLine();
        int pos = function(target, find);
        if(pos == -1){
            System.out.println("Found nothing");
        }
        else{
            System.out.println("Found at pos " + pos);
        }
    }
}
