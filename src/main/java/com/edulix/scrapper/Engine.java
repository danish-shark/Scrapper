package com.edulix.scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.edulix.beans.Student;

public class Engine {
	
	private ArrayList<String> allLinks = new ArrayList<String>();
	
	public void init() throws IOException{
		ArrayList<String>  fileLoc = new ArrayList<String>();
		Collections.addAll(fileLoc,"/home/danish-shark/workspace/Scrapper/input/2015.html"/*,
								"/home/danish-shark/workspace/Scrapper/input/2014.html",
								"/home/danish-shark/workspace/Scrapper/input/2013.html"*/);
		allLinks = GetAdmitList.getLinks(fileLoc, "Computer","Computer"); 
	}
	
	public static void main(String[] args) throws IOException{
		Engine engine = new Engine();
		ExecutorService executorService = Executors.newFixedThreadPool(20);
	    SaveData saveData = new SaveData("/home/danish-shark/workspace/Scrapper/output/MS_CS.txt");
		engine.init();
		System.out.println("Total profiles : " + engine.allLinks.size());
		ExtractData.max = engine.allLinks.size();
		System.out.print("Processing ");
		for (int i=0;i <engine.allLinks.size(); i++)
            	executorService.submit(new ExtractData(engine.allLinks.get(i)));
	    executorService.shutdown();
	    try {
	    	executorService.awaitTermination(1, TimeUnit.DAYS);
	    } 
	    catch (InterruptedException e) {
	    	System.out.println("Something went wrong in thread pool");
	    }
	    System.out.println("");
	    System.out.println("Total profiles saved : " + (ExtractData.max-ExtractData.timeOut));
	    System.out.println(ExtractData.timeOut + " profile(s) timed out...check log file for details");
	    ArrayList<Student> temp = ExtractData.allData;
	    for (Student student : temp) {
			saveData.writeToFile(student);
			saveData.writeToDatabase(student);
		}
	    System.out.println("Finished :)");
	}
}