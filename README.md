# DownTime-Calculator
 

 ## Overview

 This is a maven application to calculate downtime of multiple cloud workspaces using a log file containing logs of various 
 start-time and endtime of various workspaces as JSON objects.

 ## Maven Dependencies 

 Below mentioned are certain maven dependencies used in the application :

````maven
   
   <dependency>
	    <groupId>com.googlecode.json-simple</groupId>
	    <artifactId>json-simple</artifactId>
	    <version>1.1.1</version>
	</dependency>

````
 
 ## Flow

 ..* The JSON file is considered as the array of JSON Objects.
 ..* Each JSON object- logs are parsed to check if it is a valid start or end log or not.
 ..* Valid start log sample : 

````json
 
 {
 	"log":"2019-01-23 17:15:28,042[p-nio-88-exec-2]  [INFO ] [o.e.c.a.w.s.WorkspaceRuntimes 321]   - Starting workspace 'poorna/deepak' with id 'workspacen5qas1f5m0crizs1' by user 'deepak'\n",
 	"stream":"stdout",
 	"time":"2019-01-23T17:15:28.043226124Z"
 }

````
 ..* Valid stop log sample :

````json
 
 {
 	"log":"2019-01-23 16:32:33,816[ceSharedPool-52]  [INFO ] [o.e.c.a.w.s.WorkspaceRuntimes 476]   - Workspace 'poorna/nilu' with id 'workspaceok2mizvju50cbc0c' stopped by user 'activity-checker'\n",
 	"stream":"stdout",
 	"time":"2019-01-23T16:32:33.816812869Z"
 }

````
..* The logs which are typically of the above types are considered.
..* It is also assumed that no workspace can remain 'ON' for more than 8 hours a day.
..* Every user has his/her own workspace. 
