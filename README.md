# Stock-Portfolio-Management-System
1.    Setup:
IntelliJ IDEA x.x(it’s OK to use eclipse, but intelliJ best);
PostgreSQL x.x(should be installed with PgAdmin4 or newer version);
Create a database called 9900stockportfolio, then edit the corresponding users information(database username and password) at row 28-31 in web/GroupCode/evan/classes/DBTool.java
Tomcat (version >= 8);
JAVA (jdk version >= 1.8);
All this should be configured in IDE.
 
2.    Required Packages(included in war file):
web/jquery/jquery-3.3.1.min.js
web/jquery/Chart.js
web/jquery/Chart.min.js
web/resource/layer-v3.1.1/layer/layer.js
web/WEB-INF/lib/java-string-similarity-1.1.0.jar

 
3.    Building Steps:
Pass(how to run)
Step 1: Run Pgadmin4, right click on the newly created database 9900stockportfolio, choose ‘Restore'.
Then select the stock.backup file we submit and confirm to rebuild our database, it takes about 10-20 mins to finish it.

Step 2: After the database is successfully rebuilt, if the IDE was configured correctly, run the project and you are supposed to access to the index.jsp page with several stock information on it.Make sure that 'users' and 'symbols' these two tables exist.

Step 3: login using our pre-created test account (username:z5103300, password:z5103300) to use the portfolio feature on user page, search for a stock(ie. Apple)using search tool on very top of the page, then click favourite button right and go back to personal finance page to check if it is displayed, any stock can be unfollowed here;

4.    Validate the System:
Step 1: if the main page shows several stock info(first one is MSFT), it means the data pre-process and store process have finished;

Step2: if the user can sign up and login, it means the interaction between project and database is successful;

Step3: After following a stock, if user can see the detail line charts and news after click corresponding button, it means most functions have been rebuilt successfully.
 
5.    Debug:
Common problems and solutions

6. It is a group project of 9900.
