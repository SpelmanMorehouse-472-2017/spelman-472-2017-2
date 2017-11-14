package com.google.training.helloworld;

import com.google.api.server.spi.config.Api;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

//comment

/**
 * Defines endpoint functions APIs..
 */
@Api(name = "helloworldendpoints", version = "v1",
scopes = {Constants.EMAIL_SCOPE },
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
        description = "API for hello world endpoints.")

public class HelloWorldEndpoints {

   // Declare this method as a method available externally through Endpoints
    /*@ApiMethod(name = "sayHello", path = "sayHello",
            httpMethod = HttpMethod.GET)

    public HelloClass sayHello() {
        return new HelloClass();
    }*/

    // Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName",
            httpMethod = HttpMethod.GET)

    public CompanyInfo sayHelloByName (@Named("race") String raceVar, @Named("company") String companyVar) {
    		return new CompanyInfo(raceVar, companyVar);
        //return Query.lookup(name);
    }
    
   /* @ApiMethod(name = "testMethod",path = "testMethod", 
    		httpMethod = HttpMethod.GET)
    
    public HelloClass testMethod (@Named("stringVar") String myVar,@Named("random1") String r1, @Named("random2") String r2) {
    		return new HelloClass(myVar,r1,r2);
    }
    
    
    @ApiMethod(name = "getEmailAndIng", path = "getEmailAndIng",
    		     httpMethod = HttpMethod.GET)
    
   
    public HelloClass getEmailAndIng (@Named("userEmail") String uEmail, @Named("userIngredient") String ing) {
        
    		return new HelloClass(uEmail,ing);
    }*/

    
    
  
 
      
}


