// Imports the Google Cloud client library
package com.google.training.helloworld;

import java.util.UUID;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Query {
	
	private final static Logger log =  Logger.getLogger(Query.class.getName());
	//public static CompanyInfo lookUp(String raceVar, String companyVar) {
	
	public static CompanyInfo lookUp(String companyVar) {
		
		ArrayList<String> aList = new ArrayList<>();
	
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    QueryJobConfiguration queryConfig =
	        QueryJobConfiguration.newBuilder(
	                /*"SELECT "
	                    + "APPROX_TOP_COUNT(corpus, 10) as title, "
	                    + "COUNT(*) as unique_words "
	                    + "FROM `bigquery-public-data.samples.shakespeare`;")*/
	        		/*"SELECT job, IFNULL(job_skill,\"\") FROM `vallydata.newvallydata_2017`" 
    	    			+ "WHERE race = \"" + raceVar + "\"AND company = \"" + companyVar + "\";")*/
	        		"SELECT job FROM `vallydata.vallydata_aa` WHERE company = \"" + companyVar + "\";")
	        
	        /*"SELECT job FROM `vallydata.newvallydata_2017` WHERE company = \"" + companyVar + "\";") */
	            // Use standard SQL syntax for queries.
	            // See: https://cloud.google.com/bigquery/sql-reference/
	            .setUseLegacySql(false)
	            .build();
	    
	    // Create a job ID so that we can safely retry.
	    JobId jobId = JobId.of(UUID.randomUUID().toString());
	    Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

	    
	    // Wait for the query to complete.
	    try {
			queryJob = queryJob.waitFor();
		} catch (InterruptedException e) {
		    //return new CompanyInfo(raceVar, companyVar);
			 return new CompanyInfo(companyVar);
		}
	    

	    // Check for errors
	    if (queryJob == null) {
	      throw new RuntimeException("Job no longer exists");
	    } else if (queryJob.getStatus().getError() != null) {
	      // You can also look at queryJob.getStatus().getExecutionErrors() for all errors, not just the latest one.
	      throw new RuntimeException(queryJob.getStatus().getError().toString());
	    }

	   
	    // Get the results.
	    QueryResponse response = bigquery.getQueryResults(jobId);
	    QueryResult result = response.getResult();
	    
	    if (result == null) {
	    		log.warning("no results");
	    }
	    
	    // Print rows from query
	    while (result != null) {
	      for (List<FieldValue> row : result.iterateAll()) {
	        String job = row.get(0).getStringValue();
	        //String job_skill = row.get(1).getStringValue();
	        log.warning(job);
	       // log.warning(job_skill);
	        aList.add(job);
	      }
	    }
	    
	    //log.warning(aList.toString());
	    
	    //return new CompanyInfo(raceVar, companyVar);
	    return new CompanyInfo(companyVar);
	    
	    
	  }
	
	
	}

