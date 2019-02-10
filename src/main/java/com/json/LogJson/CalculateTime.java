package com.json.LogJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CalculateTime  {
	public void getData() {
		
		JSONParser parser = new JSONParser();
		try {
			 FileReader reader = new FileReader("che_log.json");
			 Object obj = parser.parse(reader);
			 JSONArray jsarr = (JSONArray) obj;
	         
	         HashMap<String , UserUpTime> user = new HashMap<String , UserUpTime>();
	          
	        for(Object o : jsarr) {
	        	 JSONObject cloudData = (JSONObject) o;
	        	 int x = jsarr.indexOf(o);
	        	 String logs = (String) cloudData.get("log");
	        	 
	        //---------------------------------------------------------------------
	          if(logs.contains("Starting workspace")) {
	        		 
	        		 String name="";
	        		 for(int i=logs.indexOf('/')+1;  logs.charAt(i)!='\''; i++){
	        			 name = name + logs.charAt(i);
	        		 }
	        		 
	        	 if(logs.contains("by user \'"+name+"\'\n"))/* || logs.contains("\'admin\'")*/{
	        		 if(!user.containsKey(name)) {
	        			 
	        			 String time = (String)cloudData.get("time");
		        		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        		 SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd___HH:mm:ss");
		        		 Date d = sdf.parse(time);
		        		 String formattedTime = output.format(d);
		        		 UserUpTime u = new UserUpTime(name);
		        		 ArrayList<String> str = new ArrayList<String>();
		        		 str.add(formattedTime);
		        		 
		        		 u.setStartTime(str);
		        		 user.put(name, u);
		        		 
	        	    }else {
	        			 String time = (String)cloudData.get("time");
	        			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        		 SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd___HH:mm:ss");
		        		 Date d = sdf.parse(time);
		        		 String formattedTime = output.format(d);
		        		 
		        		 UserUpTime u2 = new UserUpTime(name);
		        		 u2 = user.get(name);
		        		 
		        		 ArrayList<String> list1 = new ArrayList<String>();
		        		 if(u2.getStartTime()==null) {
		        			 list1.add(formattedTime);
		        		 }else {
		        			 list1 = u2.getStartTime();
		        			 list1.add(formattedTime);
		        		 }
		        		
		        		 u2.setStartTime(list1);
	        			 user.put(name, u2);
	        			 	
	        		}
	            }
	        }else if(logs.contains("\' stopped by user")) {
	        		 String name2="";
	        		 for(int i=logs.indexOf('/')+1;  logs.charAt(i)!='\''; i++) {
	        			 name2 = name2 + logs.charAt(i);
	        		 }
	        		 
	        	if(logs.contains("\'activity-checker\'\n")) {
	        		 if(!user.containsKey(name2)) {
	        			 
        			     String time2 = (String)cloudData.get("time");
		        		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        		 SimpleDateFormat output2 = new SimpleDateFormat("yyyy-MM-dd___HH:mm:ss");
		        		 Date d2 = sdf2.parse(time2);
		        		 String formattedTime2 = output2.format(d2);
		        		 
		        		 UserUpTime u = new UserUpTime(name2);
		        		 ArrayList<String> str2 = new ArrayList<String>();
		        		 str2.add(formattedTime2);
		        		 
		        		 u.setEndTime(str2);
		        		 user.put(name2,u);
		        		 
	        	   }else {
	        			 String time2 = (String)cloudData.get("time");
		        		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        		 SimpleDateFormat output2 = new SimpleDateFormat("yyyy-MM-dd___HH:mm:ss");
		        		 Date d2 = sdf2.parse(time2);
		        		 String formattedTime2 = output2.format(d2);
		        		 
		        		 UserUpTime u2 = new UserUpTime(name2);
		        		 u2 = user.get(name2);
		        		 ArrayList<String> list2 = new ArrayList<String>(); 
		        		 if(u2.getEndTime()==null) {
		        			 list2.add(formattedTime2);
		        		 }else {
		        			 list2 = u2.getEndTime();
		        			 list2.add(formattedTime2);
		        		 }
		        		
		        		 u2.setEndTime(list2);
		        		 user.put(name2, u2);
	        		  }
	        	   }
		         }
	       
		       }
	        //-----------------------------------------------------------------------------------------
	        
	        ArrayList<String> strt = new ArrayList<String>();
        	ArrayList<String> end = new ArrayList<String>();
        	
        	for(String val : user.keySet()) {
	        	String key = val;
	        	if( user.get(val).getStartTime() != null) {
	        		strt = user.get(val).getStartTime();
	        	}
	        	if(user.get(val).getEndTime()!= null) {
	        		end  = user.get(val).getEndTime();
	        	}
	        	
	        	System.out.println("User : "+ key);
	        	System.out.println("Start Timings : "+ strt);
	        	System.out.println("End Timings : "+end);
	        	System.out.println("---------------------------------\n");
	        	String eachStrtDate ;
	        	long date3 = 0;
	        	
	        	for(int i=0; i< strt.size();i++) {
	        		eachStrtDate="";
	        		for(int x=0; x<10; x++) {
	        			eachStrtDate += strt.get(i).charAt(x);
	        		}
	        		for(int j=0; j< end.size(); j++) {
	        			if(end.get(j).contains(eachStrtDate)) {
	        				String eachStrtTime="";
	        				String eachEndTime="";
	        				
	        				for(int x=13; x<=20; x++) {
	        					eachStrtTime += strt.get(i).charAt(x);
	        					eachEndTime += end.get(j).charAt(x);
	        				}
	        				try {
	        					DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	        					Date date1 = sdf.parse(eachStrtTime);
	        					Date date2 = sdf.parse(eachEndTime);
	        					
	        					 if(date1.getTime()<date2.getTime()){
	        						if((date2.getTime()-date1.getTime())/1000 <28800) {
	        							date3 += (date2.getTime()-date1.getTime())/1000;
	        							
	        						}else {
	        							date3 += 28800;
	        						}
	        						strt.remove(i);
         	        				end.remove(j);
         	        				break;
	        					}
	        				}catch(Exception e) {
	        					e.printStackTrace();
	        				}
	        				
	        			}
	        		}
	        	}
	        	
	       //-------------------------------------------------------------------------------------
	        	if(strt.size()!=0) {
	        		for(int i=0; i< strt.size();i++) {
		        		 eachStrtDate ="";
		        		 for(int x=0; x<10; x++) {
			        			eachStrtDate += strt.get(i).charAt(x);
			        	 }
		        		 String eachtime ="";
		        		 for(int x=13; x<=20; x++) {
	     					eachtime += strt.get(i).charAt(x);
	     				 }
		        		 try {
		        			 DateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		        			 Date df = sdf2.parse(eachtime);
		        			 Date date24 = sdf2.parse("24:00:00");
		        			 
		        			 if((date24.getTime()-df.getTime())/1000 <28800) {
		        				date3 +=  (date24.getTime()-df.getTime())/1000;
		        			 }else {
		        				 date3 += 28800;
		        			 }
	     					
		        			 strt.remove(i);
		        			 for(int w=i+1; w<strt.size(); w++) {
		        				 if(strt.get(w).contains(eachStrtDate)) {
		        					 strt.remove(w);
		        				 }
		        			 }
		        		 }catch(Exception e) {
		        			 e.printStackTrace();
		        		 }
		        	}
	        	}
	            while(strt.size() ==0 && end.size()!=0) {
	            	String eachEndDate;
	        		for(int j=0; j< end.size(); j++) {
	        			
	        			 eachEndDate ="";
		        		 for(int x=0; x<10; x++) {
			        			eachEndDate += end.get(j).charAt(x);
			        	 }
		        		 String eachEndtime ="";
		        		 for(int x=13; x<=20; x++) {
	     					eachEndtime += end.get(j).charAt(x);
	     				 }
		        		 
		        		 try {
		        			 DateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
		        			 Date dff = sdf3.parse(eachEndtime);
		        			 Date date12 = sdf3.parse("00:00:00");
	        					
		        			 if((dff.getTime()-date12.getTime())/1000 <28800) {
			        				date3 +=  (dff.getTime()-date12.getTime())/1000;
			        			 }else {
			        				 date3 += 28800;
			        		 }
		        			 end.remove(j);
		        			 for(int w=0; w<end.size(); w++) {
		        				 if(end.get(w).contains(eachEndDate)) {
		        					 end.remove(w);
		        				 }
		        			 }
		        		 }catch(Exception m) {
		        			 m.printStackTrace();
		        		 }
	        		}
	        	}
	        	long hours = date3 / 3600;
				long minutes = (date3 % 3600) / 60;
				long seconds = date3 % 60;
				System.out.println("Up Time : " + hours + " hours "+ minutes+" minutes "+ seconds + " seconds");
				
				long downTime = 259200 -date3;
				hours = downTime / 3600;
				minutes = (downTime % 3600) / 60;
			    seconds = downTime % 60;
			    System.out.println("DOWN Time in three days : " + hours + " hours "+ minutes+" minutes "+ seconds + " seconds");
				System.out.println("______________________________________________________________________________\n");
	        	
	        }
	        
	     } catch (FileNotFoundException e) {
	            e.printStackTrace();
	     }catch(Exception e) {
	        	e.printStackTrace();
         }
	}

}
