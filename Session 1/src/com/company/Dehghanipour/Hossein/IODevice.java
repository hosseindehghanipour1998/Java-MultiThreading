package com.company.Dehghanipour.Hossein;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class IODevice {
	
	
	
	public ArrayList<String> readFile (  ArrayList<String> file , String fileName ) {
		String line = null ;
		try {
			
			FileReader fileReader = new FileReader(fileName) ; 


	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	           file.add(line);
	           
	        }   

	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(Exception e) {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
		
		return file ;
	}
	
	
	
	public void writeFile ( String fileName , ArrayList<Integer> output) {
		// The name of the file to open.
		
		int temp[] = new int[output.size()];
		for ( int  i = 0 ; i < output.size() ; i++) {
			temp[i] = output.get(i) ;
		}
		
		Arrays.sort(temp);

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            for ( int i = 0 ; i < output.size() ; i++) {
            	bufferedWriter.write(""+temp[i] + " ");
            	
            }


            // Always close files.
            bufferedWriter.close();
        }
        catch(Exception ex) {
            System.out.println(
                "\n++++++ERROR++++++\n");

        }
    }	
}
