package Default;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

public class Project {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String fileName = Const.INPUT_DATA_FNAME;
			
		Debug.Log("Starting program.");
		Graph g = new Graph();
		
		//The modified file names
		String map = fileName+".map";
		String modified = fileName+".processed";
		
		File mapFile = new File(map);
		File modifiedFile = new File(modified);
		
		//First condition is if we want to test the 
		if (Const.IGNORE_EXISTING_MAPPING_FILES || (!mapFile.exists() && !modifiedFile.exists())) {
			Debug.Log("Creating mapping files.");
			createFiles(fileName);
		}
		else {
			Debug.Log("Mapping files exist. Skip creating them.");
		}
		
		Debug.Log("*** Creating graph. ***");
		
		//Create the graph with the nodes with the modified file.
		Scanner sc = new Scanner(new File(modified));
		int pairCounter = 0;

		while(sc.hasNext()) {
			String[] pairs = sc.next().split(",");
			pairCounter++;

			if(pairCounter%1000000==0){
				Debug.Log("Processing pair " + pairCounter / 1000000 + " million.");
			}	
			
			if(pairs.length==2) {
				//As we have already sorted out the irrelevant pairs we can easily just create the edges
				
				g.CreateEdge(Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1]));
			}
		}
		
		sc.close();
		Debug.Log("*** Calculating graph node distribution. ***");
		
		//Class that handles the entire calculations
		GraphCalculator<Integer> calc = new GraphCalculator<Integer>(g);
		
		//Our degreeDistribution hashmap that is going to become a histogram
		calc.createFrequencyFile();

		Debug.Log("*** Calculating graph node distribution. *** DONE");

		ProcessBuilder processBuilder = new ProcessBuilder("python3", "createHistogramGraphDegree.py");

            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("Python script 1 executed successfully.");
            } else {
                System.out.println("Python script 1 execution failed.");
            }
		System.out.println("Histogram created under file name 'degreeDistribution.png'");
		
		int componentsSize = calc.getNumberOfComponentsLargerThan(3);
		System.out.println("There are " + componentsSize +" graph components larger than or equal to 3.");
		
		calc.WriteDensityFile();

		ProcessBuilder processBuilder2 = new ProcessBuilder("python3", "createHistogramDensityDistribution.py");

		// Start the process
		Process process2 = processBuilder2.start();

		// Wait for the process to complete
		int exitCode2 = process2.waitFor();
		
		if (exitCode2 == 0) {
			System.out.println("Python script 2 executed successfully.");
		} else {
			System.out.println("Python script 2 execution failed.");
		}

		System.out.println(LocalDateTime.now()+" done");
	}
	
	/*
	 * Input: A string with the filename.
	 * Side effects: creates two files with the name fname.processed and fname.map.
	 * Output: a CodeTable with the relevant pairs.
	 */
	private static CodeTable createFiles(String fname) {
	    
		CodeTable table = new CodeTable();
	    int lineCounter = 0;
	    try (Scanner sc = new Scanner(new File(fname));
	    	
	    	// Starting the file writing process
			// Using .tmp files to avoid processing inclompleted files later.  
	        BufferedWriter forMap = new BufferedWriter(new FileWriter(fname + ".map.tmp"));
	        BufferedWriter forModified = new BufferedWriter(new FileWriter(fname + ".processed.tmp"))) {
	    	
	    	
	        while (sc.hasNextLine()) {
	        	lineCounter++;

				if(lineCounter%1000000==0){
					Debug.Log("Processing line " + lineCounter/1000000 + " million");
				}
	            String[] values = sc.nextLine().split("\t");
	            
	            //Is the line correct? 
	            if (values.length <= 11) {
	            	continue;
	            }
	            
	            //Overlap lenghts that have to be 1000 or larger
	            int firstOverlap = Integer.parseInt(values[6]) - Integer.parseInt(values[5]);
	            int secondOverlap = Integer.parseInt(values[10]) - Integer.parseInt(values[9]);
 
	            if (firstOverlap < 1000 || secondOverlap < 1000) {
	            	continue;
	            }
	            
	            //Getting keys and adding keys with correct mapping onto them
	            Integer key1 = table.getKey(values[0]);
	            Integer key2 = table.getKey(values[1]);

	            if (key1==null) {
	            	key1=table.add(values[0]);
	                forMap.write(values[0] + "," + key1);
	                forMap.newLine();
	            }

	            if (key2==null) {
	            	key2=table.add(values[1]);
	                forMap.write(values[1] + "," + key2);
	                forMap.newLine();
	            }
	            
	            //Writing to the .processed file
	            forModified.write(key1.toString());
	            forModified.write(",");
	            forModified.write(key2.toString());
	            forModified.newLine();
	        }
	    
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ArrayIndexOutOfBoundsException e1) {
			Debug.Log("Something went wrong writing or reading: " + e1.getMessage());
	    }
		   
		File oldfile =new File(fname + ".map.tmp");
        File newfile =new File(fname + ".map");

		if(oldfile.renameTo(newfile)){
			Debug.Log(fname + ".map.tmp renamed to " + fname + ".map");
        }else{
			Debug.Log(fname + ".map.tmp could not be renamed.");
			System.exit(1);
        }

		oldfile =new File(fname + ".processed.tmp");
    	newfile =new File(fname + ".processed");

		if(oldfile.renameTo(newfile)){
			Debug.Log(fname + ".processed.tmp renamed to " + fname + ".processed");
        }else{
			Debug.Log(fname + ".processed.tmp could not be renamed.");
			System.exit(1);
        }
		
	    return table;
	}
}
