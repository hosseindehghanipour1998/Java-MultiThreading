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

	public static  void writePerLine(String text , String fileName ){

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
	
	public static void writeFileMatrix(String fileName , ArrayList<Long> calculatedTimes , int[] threadNumbers , int matrixSize) {
		// The name of the file to open.

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Core Info.START
			bufferedWriter.write("File Name : " + fileName);
			bufferedWriter.write("\n" );
            bufferedWriter.write( "Matrix Size : " + matrixSize + "*" + matrixSize );
            bufferedWriter.write( "\n");
			for (int i = 0 ; i < calculatedTimes.size() ; i++){
				bufferedWriter.write("Thread(" + threadNumbers[i] + ") :\t -> \t" + calculatedTimes.get(i) );
				bufferedWriter.write( "\n");
			}
			bufferedWriter.write( "\n");
			bufferedWriter.write("===================== Result =====================");
			bufferedWriter.write( "\n");
			int minIndex = ThreadTools.findMinThreadIndex(calculatedTimes);
			bufferedWriter.write("Min : " + calculatedTimes.get(minIndex) + " ns\t ->\t Thread(" + threadNumbers[minIndex]+")");

			//Core Info.END
            // Always close files.
            bufferedWriter.close();
        }
        catch(Exception ex) {
        	ex.printStackTrace();
            System.out.println(
                "\n++++++ERROR++++++\n");

        }
    }	
}
