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
	
	public void writeFile ( String fileName , ArrayList<ArrayList<ArrayList<Long>>>calculatedTimes , int[] threadNumbers , int[] vectorSizes) {
		// The name of the file to open.

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Core Info.
			int threadIndex = 0 ;
			int vectorIndex = 0 ;
			for ( ArrayList<ArrayList<Long>> eachVectorArraylist : calculatedTimes){
				ArrayList<Float> allAverageTimes = new ArrayList<>() ;
				ArrayList<Long> allMinimums = new ArrayList<>() ;
				bufferedWriter.write("==============");
				bufferedWriter.write("Vector Size(" + (vectorSizes[vectorIndex++]) + ")");
				bufferedWriter.write("==============\n");
				for ( ArrayList<Long> AL : eachVectorArraylist){
					bufferedWriter.write("Threads(" + (threadNumbers[threadIndex++]) + ")\t");
					for ( Long time : AL){
						bufferedWriter.write( time + " us || ");
					}
					float avgTime = Main.calculateAverage(AL) ;
					allAverageTimes.add(avgTime) ;
					bufferedWriter.write("Max: " + Collections.max(AL) + " || Min : " + Collections.min(AL)+ " us || Avg : " + avgTime);
					allMinimums.add(Collections.min(AL)) ;
					bufferedWriter.write("\n");
				}
				bufferedWriter.write("Min Time (" + Collections.min(allMinimums)+") ");
				bufferedWriter.write(" || Threads(" + threadNumbers[ Main.findMinThreadIndex(allMinimums) ]+ ")\n");
				bufferedWriter.write("Min Avg (" + Collections.min(allAverageTimes)+")");
				bufferedWriter.write(" || Threads(" + threadNumbers[ Main.findMinAvgTimeIndex(allAverageTimes) ]+ ")\n");


				threadIndex = 0 ;
			}
			vectorIndex = 0 ;
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
