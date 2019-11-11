package com.company.Dehghanipour.Hossein;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

	public static void deletePredefinedFile(String fileName){

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

	public static  void writePerLine(String fileName ,String text ){

		try {
			FileWriter fileWriter = new FileWriter(fileName , true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("\n" + text + "\n");
			bufferedWriter.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
