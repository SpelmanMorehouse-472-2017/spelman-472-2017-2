package com.google.training.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

// Results page code
public class CompanyInfo {
    public String message = " ";
    public ArrayList<String> aList;
    public HashMap<Integer, ArrayList<String>> map;

    /*public CompanyInfo (String race, String company) {
        this.message = "I am " + race + " and I want to work at " + company;
    }*/
    
   /*public CompanyInfo (String company) {
        this.message = "I want to work at " + company;
    }*/
   
    /*public String getMessage() {
        return message;
    }*/
    
   public CompanyInfo (ArrayList<String> aList) {
        this.message = aList.toString();
    }
}