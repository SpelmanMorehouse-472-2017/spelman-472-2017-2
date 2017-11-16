package com.google.training.helloworld;

// Results page code

public class CompanyInfo {
    public String message = " ";

    public CompanyInfo (String race, String company) {
        this.message = "I am " + race + " and I want to work at " + company;
    }
   
    public String getMessage() {
        return message;
    }
}