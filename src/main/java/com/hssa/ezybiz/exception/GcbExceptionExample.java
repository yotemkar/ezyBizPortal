package com.hssa.ezybiz.exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;



public class GcbExceptionExample {
	private static final Logger logger = LogManager.getLogger(GcbExceptionExample.class);

    public void createException() {
    	
    	String filename = "input.txt";
    	 BufferedWriter wr = null;
		try {
			logger.info("INSIDE GCB getUserData Controller Method SSO:{} ");
			 wr = new BufferedWriter(new FileWriter(new File(filename), true));
             
	            // Write a sample string to the end of the file.
	            wr.write("A sample string to be written at the end of the file!\n");
	            Class loadedClass = Class.forName("");
	            System.out.println("Class " + loadedClass + " found successfully!");
	    }
		catch(IOException | ClassNotFoundException ex) {
			new GcbException("Exception", ex);
			logger.error("Exception while deleting the file", ex);
		}
		
    }
    
 public void createException1() throws GcbException {
    	
    	String filename = "input.txt";
    	 BufferedWriter wr = null;
		try {
			logger.info("INSIDE GCB getUserData Controller Method SSO:{} ");
			 wr = new BufferedWriter(new FileWriter(new File(filename), true));
			 wr.write("A sample string to be written at the end of the file!\n");
	            
			 Class loadedClass = Class.forName("");
	         System.out.println("Class " + loadedClass + " found successfully!");
	         
	    }
		catch(FileNotFoundException ex) {
			throw new GcbException("FileNotFoundException Exception", ex);
		}
		catch(IOException ex) {
			logger.error("IOException Exception", ex);
		}
		catch(ClassNotFoundException ex) {
			logger.error("ClassNotFoundException Exception", ex);
		}	
		catch(Exception ex) {
			logger.error("Exception", ex);
		}	
		
    }
}
