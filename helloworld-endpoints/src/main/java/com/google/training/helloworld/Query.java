// Imports the Google Cloud client library
package com.google.training.helloworld;

import java.util.UUID;
import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;



public class Query {
	private final static Logger log =  Logger.getLogger(Query.class.getName());
	
	public static CompanyInfo lookUp(String raceVar, String companyVar) {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		
		 QueryJobConfiguration queryConfig =
		    	    QueryJobConfiguration.newBuilder(/*
		    	            "SELECT "
		    	                + "APPROX_TOP_COUNT(corpus, 10) as title, "
		    	                + "COUNT(*) as unique_words "
		    	                + "FROM `bigquery-public-data.samples.shakespeare`;")*/
		    	    		"SELECT job, job_skill"
		    	    			+ "FROM `vallydata.newvallydaya_2017`"
		    	    			+ "WHERE race = raceVar, company = companyVar;")
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
					// TODO Auto-generated catch block
					e.printStackTrace();
					//add something else 
				}

		    	// Check for errors
		    	if (queryJob == null) {
		    	  throw new RuntimeException("Job no longer exists");
		    	} else if (queryJob.getStatus().getError() != null) {
		    	  // You can also look at queryJob.getStatus().getExecutionErrors() for all
		    	  // errors, not just the latest one.
		    	  throw new RuntimeException(queryJob.getStatus().getError().toString());
		    	}

		    	// Get the results.
		    	QueryResponse response = bigquery.getQueryResults(jobId);
		    	log.warning(response.toString());
		    	log.warning(queryJob.toString());
		    	log.warning("hello");
		    	
		    	// why do we need new?
		   return new CompanyInfo(raceVar, companyVar);
		//return processResponse(response);
	}
	
	//type process response func
 
}