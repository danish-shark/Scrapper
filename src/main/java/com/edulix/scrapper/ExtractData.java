package com.edulix.scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.edulix.beans.AcadDetails;
import com.edulix.beans.AppliedUni;
import com.edulix.beans.Student;

public class ExtractData implements Runnable {
	
	private String url;
	
	public static ArrayList<Student> allData = new ArrayList<Student>();
	public static int count;
	public static int max;
	public static int prev;
	public static int timeOut;
	
	static Logger logger = Logger.getLogger("TimeOutLog");  
	static FileHandler fh;
	
	static{
		try{  
			LogManager.getLogManager().reset();
	        fh = new FileHandler("/home/danish-shark/workspace/Scrapper/logs/timeOutLog.log");  
	        logger.addHandler(fh);
	        fh.setFormatter(new SimpleFormatter());  
	    }
		catch (IOException e) {  
	        e.printStackTrace();  
	    } 
	}
	
	public ExtractData(String url){
		this.url = url;
	}

	@Override
	public void run() {
		getData(url);
	}
	
	public void getData(String url){
		try{
			Document doc = Jsoup.connect(url).get();
			ArrayList<AppliedUni> uniList = new ArrayList<AppliedUni>();
			Elements table = doc.select("table:contains(Universities Applied)");
			Elements data = table.select("tr");
			for(int i=1;i<data.size();){
				String tempName = data.get(i).child(0).text();
				String tempDecision = data.get(i).child(1).text();
				String tempComments = "No comments";
				if((i+1)<data.size() && data.get(i+1).childNodeSize()==1){
					tempComments = data.get(i+1).child(0).text();
					i=i+2;
				}
				else
					i++;
				AppliedUni appliedUni = new AppliedUni(tempName, tempComments, tempDecision);
				uniList.add(appliedUni);
			}
			table = doc.select("table:contains(Details of)");
			data = table.select("td");
			String tempAwa=new String("Not Available"),tempToefl=new String("Not Available"),tempGreQuant=new String("Not Available"),tempGreVerbal=new String("Not Available"),tempUnderGradUni=new String("Not Available"),
				 tempUnderGradDepartment=new String("Not Available"),tempUnderGradGrade=new String("Not Available"),tempUnderGradTopperGrade=new String("Not Available"),
				 tempUnderGradGradeScale=new String("Not Available");
			if(data.select("td:containsOwn(AWA)").size()!=0)
				tempAwa = data.select("td:containsOwn(AWA)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(TOEFL)").size()!=0)
				tempToefl = data.select("td:containsOwn(TOEFL)").first().nextElementSibling().nextElementSibling().text();
			if(data.select("td:containsOwn(Quantitative)").size()!=0)
				tempGreQuant = data.select("td:containsOwn(Quantitative)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Verbal)").size()!=0)
				tempGreVerbal = data.select("td:containsOwn(Verbal)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(University/College)").size()!=0)
				tempUnderGradUni = data.select("td:containsOwn(University/College)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Department)").size()!=0)
				tempUnderGradDepartment = data.select("td:containsOwn(Department)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Grade)").size()!=0)
				tempUnderGradGrade = data.select("td:containsOwn(Grade)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Topper's Grade)").size()!=0)
				tempUnderGradTopperGrade = data.select("td:containsOwn(Topper's Grade)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Grade Scale)").size()!=0)
				tempUnderGradGradeScale = data.select("td:containsOwn(Grade Scale)").first().nextElementSibling().text();
			AcadDetails acadDetails = new AcadDetails(tempAwa, tempToefl, tempGreQuant, tempGreVerbal, tempUnderGradUni, tempUnderGradDepartment, tempUnderGradGrade, tempUnderGradTopperGrade, tempUnderGradGradeScale);
			
			String tempAttendingUni=new String("Not Available"),tempTerm=new String("Not Available"),tempProgram=new String("Not Available"),
					tempWorkEx=new String("Not Available"),tempMiscellaneousDetails=new String("Not Available");
			if(data.select("td:containsOwn(University (will be) Attending)").size()!=0)
				tempAttendingUni = data.select("td:containsOwn(University (will be) Attending)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Industrial Experience)").size()!=0)
				tempWorkEx = data.select("td:containsOwn(Industrial Experience)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Term and Year)").size()!=0)
				tempTerm = data.select("td:containsOwn(Term and Year)").first().nextElementSibling().text();
			if(data.select("td:containsOwn(Other Miscellaneous Details)").size()!=0)
				tempMiscellaneousDetails = data.get(data.indexOf(data.select("td:containsOwn(Other Miscellaneous Details)").first())+1).text();
			if(data.select("td:containsOwn(Program)").size()!=0){
				 tempProgram = data.select("td:containsOwn(Program)").first().nextElementSibling().text();
				 if(data.select("td:containsOwn(Major)").size()!=0)
					 tempProgram = tempProgram + data.select("td:containsOwn(Major)").first().nextElementSibling().text();
			}
			Student student = new Student(tempAttendingUni, tempTerm, tempProgram, acadDetails, tempWorkEx, tempMiscellaneousDetails, uniList);
			allData.add(student);
			data.clear();
			table.clear();
			count++;
			if((count-prev) > (max/10)){
				System.out.print(".");
				prev = count;
			}
		}
		catch(Exception exception){
			timeOut++;
			logger.warning("timeout for " + url);
		}
	}
}