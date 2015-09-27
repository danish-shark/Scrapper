package com.edulix.scrapper;

import java.io.File;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetAdmitList {

	public static ArrayList<String> getLinks(ArrayList<String> fileLoc,String... lookFor){
		ArrayList<String> links = new ArrayList<String>();
		for(int j=0;j<fileLoc.size();j++){
			File in = new File(fileLoc.get(j));
			try{
				Document doc = Jsoup.parse(in,null);
				Elements table = doc.select("table:contains(Search Result)");
				Elements data = table.select("tr");
				for (Element element : data) {
					Elements rows = element.select("td");
					for (int i=0;i<rows.size();i++){
						Element temp = rows.get(i);
						for(int k=0;k<lookFor.length;k++){
							if(temp.text().contains(lookFor[k]) && i==2){
								links.add(rows.get(i-2).child(0).attr("href"));
								break;
							}
						}
					}
				}
			}
			catch(Exception exception){
				exception.printStackTrace();
			}
		}
		return links;
	}
}