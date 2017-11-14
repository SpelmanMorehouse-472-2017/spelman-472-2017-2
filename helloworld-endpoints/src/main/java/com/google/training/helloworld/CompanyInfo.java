package com.google.training.helloworld;

//Hello Class
public class CompanyInfo {
   // public String message = "Hello World";
    public String message = " ";

   /* public HelloClass () {
    }*/

    public CompanyInfo (String race, String company) {
        this.message = "I am " + race + "  and I want to work at" + company;
    }
    
    
    /*public HelloClass (String userEmail,String userIngredient) {
        this.testString = "The email you entered: " + userEmail +" " + " Ingredient:" + userIngredient;
    }*/
    
    
   /* public HelloClass (String stringVar, String random, String random2) {
    		this.testString = stringVar;
    }*/
  
   
    public String getMessage() {
        return message;
    }
    
   
    
}
