package com.google.training.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Results page code
public class CompanyInfo {
    public String message = " ";
    public Integer proCount = 0;
    public Integer adminCount = 0;
    public Integer leadCount = 0;
    public Integer operativesCount = 0;
    public Integer techCount = 0;
    public Integer craftCount = 0;
    public Integer salesCount = 0;
    public Integer serviceCount = 0;
    public Integer execCount = 0;
    public Integer laborCount = 0;
    HashMap<String, Integer> chartData = new HashMap<>();  
    public ArrayList<String> aList;

   public CompanyInfo (HashMap<String, List<String>> map) {
	   
	   HashMap<String, Integer> chartData = new HashMap<>();
	   ArrayList<Integer> countList = new ArrayList<Integer>();
    
        for( List<String> value : map.values()) { 
        	
        	//change to ints
        	int one = Integer.parseInt(value.get(0));
        int two = Integer.parseInt(value.get(1));
        //add them together
        int three = one + two;
        countList.add(three);
        }
        
        int index = 0;
        for (String key : map.keySet()) {
        	chartData.put(key, countList.get(index));
        	index++;
        }
        
        this.message = chartData.toString(); 
        
    }
}