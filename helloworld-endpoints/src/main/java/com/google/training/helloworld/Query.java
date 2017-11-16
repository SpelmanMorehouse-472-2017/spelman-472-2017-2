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

import java.util.List;

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

//package com.google.cloud.helix.samples;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.QueryRequest;
//import com.google.api.services.bigquery.model.QueryResponse;
import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Query {
	
	private final static Logger log =  Logger.getLogger(Query.class.getName());
	 //private static final String PROJECT_ID = "spelman-472-2017-2";
	
	public static CompanyInfo lookUp(String raceVar, String companyVar) {
		
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    QueryJobConfiguration queryConfig =
	        QueryJobConfiguration.newBuilder(
	                /*"SELECT "
	                    + "APPROX_TOP_COUNT(corpus, 10) as title, "
	                    + "COUNT(*) as unique_words "
	                    + "FROM `bigquery-public-data.samples.shakespeare`;")*/
	        		"SELECT job, IFNULL(job_skill,\"\") FROM `vallydata.newvallydata_2017`" 
    	    			+ "WHERE race = \"" + raceVar + "\"AND company = \"" + companyVar + "\" LIMIT\n" + 
    	    			"  3;")
	            // Use standard SQL syntax for queries.
	            // See: https://cloud.google.com/bigquery/sql-reference/
	            .setUseLegacySql(false)
	            .build();

	    // Create a job ID so that we can safely retry.
	    JobId jobId = JobId.of(UUID.randomUUID().toString());
	    Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

	    new CompanyInfo(raceVar, companyVar);
	    
	    // Wait for the query to complete.
	    try {
			queryJob = queryJob.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.warning("In try catch loop");
		    return new CompanyInfo(raceVar, companyVar);
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
	    QueryResult result = response.getResult();

	    // Print all pages of the results.
	    while (result != null) {
	      for (List<FieldValue> row : result.iterateAll()) {
	        String job = row.get(0).getStringValue();
	        String job_skill = row.get(1).getStringValue();
	        log.warning(job);
	        log.warning(job_skill);
	      }
	    }
	    return new CompanyInfo(raceVar, companyVar);
	  }	
	}