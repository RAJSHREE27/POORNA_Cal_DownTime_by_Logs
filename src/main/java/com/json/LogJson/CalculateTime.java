package com.json.LogJson;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CalculateTime {
	public void getData() {
		
		JSONParser parser = new JSONParser();
		try {
				 FileReader reader = new FileReader("che_log.json");
				 System.out.println(reader);
				 System.out.println(parser);
				 Object obj = parser.parse(reader);
				 System.out.println("++++++++++++++++++++++++++++++++++++\n\n\n");
				 System.out.println("\nsimple object  @@@ : "+obj);
				 
				 JSONArray jsarr = (JSONArray) obj;
		         System.out.println("######## "+jsarr);
		         HashMap<String , UserUpTime> user = new HashMap<String , UserUpTime>();
		          
		        for(Object o : jsarr) {
		        	 JSONObject cloudData = (JSONObject) o;
		        	 int x = jsarr.indexOf(o);
		        	 String logs = (String) cloudData.get("log");
		        	 
		        //---------------------------------------------------------------------
		        	 if(logs.contains("Starting workspace")) {
		        		 
		        		 String name="";
		        		 for(int i=logs.indexOf('/')+1;  logs.charAt(i)!='\''; i++) {
		        			 name = name + logs.charAt(i);
		        		 }
		        		 
		        	if(logs.contains("\'"+name+"\'\n")) {
		        		 
		        		 if(!user.containsKey(name)) {
		        			 System.out.println(name +" ---- "+ x + " Started ======== does not exist ");
			        		 String time = (String)cloudData.get("time");
			        		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			        		 SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd , HH:mm:ss");
			        		 Date d = sdf.parse(time);
			        		 String formattedTime = output.format(d);
			        		 UserUpTime u = new UserUpTime(name);
			        		 ArrayList<String> str = new ArrayList<String>();
			        		 str.add(formattedTime);
			        		 
			        		 u.setStartTime(str);
			        		 System.out.println(formattedTime +"  **************");
			        	
		        		 }else {
		        			 System.out.println(name +" ---- "+ x + " Started ++++++++  exist ");
			        		 
		        			 String time = (String)cloudData.get("time");
		        			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			        		 SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd , HH:mm:ss");
			        		 Date d = sdf.parse(time);
			        		 String formattedTime = output.format(d);
			        		 
			        		 UserUpTime u2 = user.get(name);
			        		 u2.setUserName(name);
			        		 ArrayList<String> list1 = u2.getStartTime();
			        		 list1.add(formattedTime);
			        		 u2.setStartTime(list1);
		        			 user.put(name, u2);
		        			 System.out.println(formattedTime +"  **************");
					        	
		        		}
		            }
		        }else if(logs.contains("\' stopped by user")) {
		        		 String name2="";
		        		 for(int i=logs.indexOf('/')+1;  logs.charAt(i)!='\''; i++) {
		        			 name2 = name2 + logs.charAt(i);
		        		 }
		        	if(logs.contains("\'activity-checker\'\n")) {
		        		 if(!user.containsKey(name2)) {
		        			 
		        			 System.out.println(name2 +" ---- "+ x + " stopped ####### does not exist ");
			        		 String time2 = (String)cloudData.get("time");
			        		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			        		 SimpleDateFormat output2 = new SimpleDateFormat("yyyy-MM-dd , HH:mm:ss");
			        		 Date d2 = sdf2.parse(time2);
			        		 String formattedTime2 = output2.format(d2);
			        		 UserUpTime u = new UserUpTime(name2);
			        		 ArrayList<String> str2 = new ArrayList<String>();
			        		 str2.add(formattedTime2);
			        		 
			        		 u.setEndTime(str2);
			        		 System.out.println(formattedTime2 +"  ****************");
			        	 
		        		 }else {
		        			 System.out.println(name2 +" ---- "+ x + " stopped ^^^^^^^^^^ exist ");
			        		 
		        			 String time2 = (String)cloudData.get("time");
			        		 System.out.println(time2);
			        		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			        		 SimpleDateFormat output2 = new SimpleDateFormat("yyyy-MM-dd , HH:mm:ss");
			        		 Date d2 = sdf2.parse(time2);
			        		 String formattedTime2 = output2.format(d2);
			        		 
			        		 UserUpTime u2 = user.get(name2);
			        		 u2.setUserName(name2);
			        		 ArrayList<String> list2 = u2.getEndTime();
			        		 list2.add(formattedTime2);
			        		 u2.setEndTime(list2);
			        		 System.out.println(formattedTime2 +"  ****************");
				        	 
			        		 user.put(name2, u2);
		        			 
		        		 }
		        	 }
		         
		        }
		       }
			} catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
		}

}
