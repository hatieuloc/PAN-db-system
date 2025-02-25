import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Ha_Loc_IP_Task5b {

    // Database credentials
    final static String HOSTNAME = "user.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "";
    final static String PASSWORD = "";

    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://user-sql-server.database.windows.net:1433;database=cs-dsa-4513-sql-db;user=user@user-sql-server;password={};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            HOSTNAME, DBNAME, USERNAME, PASSWORD);

 // Query templates
    final static String TEAMCARECLIENT = 
    		"\n(1) Enter a new team for the client; \n" +
			"(2) Enter a list of need of the client; \n" +
			"(3) Enter an insurance policy of the client; \n" +
    		"(4) Finish! \n";

    final static String TEAMVOLUNTEER = 
    		"\n(1) Enter a team that volunteer work in; \n" +
    		"(2) Finish! \n";
    
    final static String ANOTHERMONTH = 
    		"\n(1) Enter a new month; \n" +
    		"(2) Finish! \n";
    
    final static String REPORT = 
    		"\n(1) Enter a report to the employee; \n" +
    		"(2) Finish! \n";
    
    final static String DONATION = 
    		"\n(1) Enter a new donation of donor; \n" +
    		"(2) Finish! \n";
    
    final static String QUERYDISPLAYTEAM = "SELECT DISTINCT(team_name) FROM Team;";
        
    // User input prompt//
    final static String PROMPT = 
            "\nPlease select one of the queries below: \n" +
            "(1) Enter a new team into the database; \n" + 
            "(2) Enter a new client into the database and associate him or her with one or more teams; \n" + 
            "(3) Enter a new volunteer into the database and associate him or her with one or more teams; \n" +
            "(4) Enter the number of hours a volunteer worked this month for a particular team; \n" +
            "(5) Enter a new employee into the database and associate him or her with one or more teams; \n" +
            "(6) Enter an expense charged by an employee; \n" +
            "(7) Enter a new donor and associate him or her with several donations; \n" +
            "(8) Retrieve the name and phone number of the doctor of a particular client; \n" +
            "(9) Retrieve the total amount of expenses charged by each employee for a particular period of\n"
            + "time. The list should be sorted by the total amount of expenses ; \n" +
            "(10) Retrieve the list of volunteers that are members of teams that support a particular client; \n" +
            "(11) Retrieve the names of all teams that were founded after a particular date; \n" +
            "(12) Retrieve the names, social security numbers, contact information, and emergency contact\n"
            + "information of all people in the database; \n" +
            "(13) Retrieve the name and total amount donated by donors that are also employees. The list should be sorted\n"
            + "by the total amount of the donations, and indicate if each donor wishes to remain anonymous; \n" +
            "(14) Increase the salary by 10% of all employees to whom more than one team must report; \n" +
            "(15) Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5; \n" +
            "(16) Import: enter new teams from a data file until the file is empty \n"
            + "(the user must be asked to enter the input file name); \n" +
            "(17) Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file\n"
            + "instead of screen (the user must be asked to enter the output file output them to a data file name); \n" +
            "(18) Quit!";
            
            
            
    public static void main(String[] args) throws SQLException, ParseException, IOException, InputMismatchException 
    {
        System.out.println("WELCOME TO THE PATIENT ASSISTANT NETWORK DATABASE SYSTEM");

        Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        String option = ""; // Initialize user option selection as nothing        
        while (!option.equals("18")) 
        { // Loop until option 18 (exit) is selected
            System.out.println(PROMPT); // Print the available options
            System.out.println("Select query:");
            option = sc.next(); // Read in the user option selection

            switch (option) 
            { 
                case "1":                 
                	System.out.println("Connecting to the database...");
                	Connection cn1 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc1 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                  
                	// Execute a stored procedure query1
                       System.out.println("Enter team name:");
                       String tname1 = sc1.nextLine();

                       System.out.println("Enter team type:");
                       String ttype1 = sc1.nextLine();
                       
                       System.out.println("Enter the date team was formed (example:yyyy-mm-dd):");
                       String df1 = sc1.nextLine();

                       // Call the stored procedure using JDBC
                       String sql1 = "{CALL query1(?, ?, ?)}"; 
                       try (CallableStatement stmt = cn1.prepareCall(sql1)) 
                       {
                           // Set the parameters for the stored procedure
                       	stmt.setString(1, tname1);
                       	stmt.setString(2, ttype1);
           				stmt.setString(3, df1);

                           // Execute the stored procedure
           				stmt.execute();
                           System.out.println("Creating successfully a new team!");
                       }                     
                                        
                    catch (SQLException e1) 
                    {
                        System.out.println("Code: " + e1.getErrorCode() + "Error executing stored procedure: " + e1.getMessage());
                    }
 
                break;
                    
                case "2":
                	
                		System.out.println("Connecting to the database...");
                    	Connection cn2 = DriverManager.getConnection(URL);
                    	System.out.println("Dispatching the query...");
                    	Scanner sc2 = new Scanner(System.in);
                        System.out.println("Connected to the database.");
                                                   
                        //Execute a stored procedure query2
                        System.out.println("Enter ssn of the Client:");
                        int ssn2 = sc2.nextInt();
                        sc2.nextLine();  // consume newline

                        System.out.println("Enter name of the Client:");
                        String pname2 = sc2.nextLine();
                        
                        System.out.println("Enter gender of Client: (format: 'Male' or 'Female' or 'Other')");
                        String gend2 = sc2.nextLine();
                        
                        System.out.println("Enter profession of the Client:");
                        String pro2 = sc2.nextLine();
                        
                        System.out.println("Is the Client on mail list?(type: Y or N)");
                        String oml2 = sc2.nextLine();
                        
                        System.out.println("Enter email of the Client:");
                        String email2 = sc2.nextLine();
                        
                        System.out.println("Enter mailing addresss of the Client:");
                        String ma2 = sc2.nextLine();
                        
                        System.out.println("Enter phone number of the Client: (format:xxx-xxx-xxxx)");
                        String pn2 = sc2.nextLine();
                        
                        System.out.println("Enter emergency name of the Client:");
                        String emn2 = sc2.nextLine();
                        
                        System.out.println("Enter emergency phone number of the Client: (format:xxx-xxx-xxxx)");
                        String empn2 = sc2.nextLine();
                        
                        System.out.println("Enter emergency relationship of the Client:");
                        String emr2 = sc2.nextLine();
                        
                        System.out.println("Enter Doctor's name of the Client:");
                        String cdn2 = sc2.nextLine();
                        
                        System.out.println("Enter Doctor's phone number of the Client: (format: xxx-xxx-xxxx)");
                        String cdpn2 = sc2.nextLine();
                        
                        System.out.println("Enter date of the Client was first assigned to the Organization: (format: yyyy-mm-dd)");
                        String cda2 = sc2.nextLine();
                        
                        // Call the stored procedure using JDBC
                        String sql2 = "{CALL query2_1(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                        try (CallableStatement stmt = cn2.prepareCall(sql2)) 
                        {
                            // Set the parameters for the stored procedure
                        	stmt.setInt(1, ssn2);
                        	stmt.setString(2, pname2);
                        	stmt.setString(3, gend2);
                        	stmt.setString(4, pro2);
                        	stmt.setString(5, oml2);
                        	stmt.setString(6, email2);
                        	stmt.setString(7, ma2);
                            stmt.setString(8, pn2);
                            stmt.setString(9, emn2);                            
                            stmt.setString(10, empn2);
                            stmt.setString(11, emr2);                           
                            stmt.setString(12, cdn2);
                            stmt.setString(13, cdpn2);                            
            				stmt.setString(14, cda2);            				            				
                       
                            // Execute the stored procedure
                            stmt.execute();
                            System.out.println("Creating successfully a new Client!");
                        } 
                        catch (SQLException e2) 
                        {
                            System.out.println("Error executing stored procedure: " + e2.getMessage());
                        }
                        
                        // associate with another team
                        System.out.println("Choose options below\n");
                        Scanner sc2_0 = new Scanner(System.in);
                        String associate_more = "";
                        while (!associate_more.equals("4")) 
                        {                       	                       	
                        	System.out.println(TEAMCARECLIENT);
                        	System.out.println("Select option:");
                        	associate_more = sc2_0.next();
                        	switch (associate_more) 
                        	{
                        		case "1":                       		
                        			System.out.println("Connecting to the database...");
                                	Connection cn2_2 = DriverManager.getConnection(URL);
                                	System.out.println("Dispatching the query...");
                                	Scanner sc2_2 = new Scanner(System.in);
                                	
									System.out.println("Connected to the database.");
									System.out.println("SSN:" + ssn2);
									// display exist team name
									Statement stmtex2 = cn2_2.createStatement();
				                	ResultSet resultSet = stmtex2.executeQuery(QUERYDISPLAYTEAM);
				                	System.out.printf("|%-12s |%n", "Team_name");
				                	while (resultSet.next()) 
				                	{
				                		System.out.printf(String.format("|%-12s |%n", 
				                				resultSet.getString("team_name")));
				                	}
				                	// Manually closing resources
				                	resultSet.close();
				                	stmtex2.close();				                	
				                	
									System.out.println("Enter team name:");
									String tn2_2 = sc2_2.nextLine();
									System.out.println("Is Client active? (type: Y or N)");
									String tcliact2_2 = sc2_2.nextLine();
									// Call the stored procedure using JDBC
									String sql2_2 = "{call query2_2(?, ?, ?)}"; 
									try (CallableStatement stmt = cn2_2.prepareCall(sql2_2))
									{
									    // Set the parameters for the stored procedure
										stmt.setInt(1, ssn2);
										stmt.setString(2, tn2_2);
										stmt.setString(3, tcliact2_2);
									    // Execute the stored procedure
										stmt.execute();
									    System.out.println("Creating successfully a team care for the Client!");
									} 
									catch (SQLException e2_2) 
									{
									    System.out.println("Code: " + e2_2.getErrorCode() + "Error executing stored procedure: " + e2_2.getMessage());
									}	
									break;
									
                        		case "2":
                        			System.out.println("Connecting to the database...");
                                	Connection cn2_3 = DriverManager.getConnection(URL);
                                	System.out.println("Dispatching the query...");
                                	Scanner sc2_3 = new Scanner(System.in);                               	
									System.out.println("Connected to the database.");
									System.out.println("SSN:" + ssn2);
			                        
			                        System.out.println("Enter the Client's Need: ('visiting' or 'shopping' or 'housekeeping' or 'transportation' or "
			                        		+ "'yard_work' or 'food')");
			                        String cln2_3 = sc2_3.nextLine();
			                        			                        
			                        System.out.println("Enter how important of the Need:(from 1 to 10)");
			                        int clhoi2 = sc2_3.nextInt();
			                        sc2_3.nextLine();  // consume newline
									// Call the stored procedure using JDBC
									String sql2_3 = "{call query2_3(?, ?, ?)}"; 
									try (CallableStatement stmt = cn2_3.prepareCall(sql2_3))
									{
									    // Set the parameters for the stored procedure
										stmt.setInt(1, ssn2);
										stmt.setString(2, cln2_3);
										stmt.setInt(3, clhoi2);
									    // Execute the stored procedure
										stmt.execute();
									    System.out.println("Creating successfully a list of Need!");
									} 
									catch (SQLException e2_3) 
									{
									    System.out.println("Code: " + e2_3.getErrorCode() + "Error executing stored procedure: " + e2_3.getMessage());
									}	
									break;									     

                        		case "3":
                        			System.out.println("Connecting to the database...");
                                	Connection cn2_4 = DriverManager.getConnection(URL);
                                	System.out.println("Dispatching the query...");
                                	Scanner sc2_4 = new Scanner(System.in);                               	
									System.out.println("Connected to the database.");
									System.out.println("SSN:" + ssn2);
			                        
									System.out.println("Enter the Insurance Policy ID:");
			                        int ipid2 = sc2_4.nextInt();
			                        sc2_4.nextLine();  // consume newline
			                        
			                        System.out.println("Enter the provider name:");
			                        String pippn2 = sc2_4.nextLine();
			                        
			                        System.out.println("Enter the provider address:");
			                        String pippa2 = sc2_4.nextLine();
			                        
			                        System.out.println("Enter the type of insurance:('life' or 'health' or 'home' or 'auto')");
			                        String pippt2 = sc2_4.nextLine();

									// Call the stored procedure using JDBC
									String sql2_4 = "{call query2_4(?, ?, ?, ?, ?)}"; 
									try (CallableStatement stmt = cn2_4.prepareCall(sql2_4))
									{
									    // Set the parameters for the stored procedure
										stmt.setInt(1, ssn2);
										stmt.setInt(2, ipid2);
			            				stmt.setString(3, pippn2);                            
			            				stmt.setString(4, pippa2);
			            				stmt.setString(5, pippt2);
									    // Execute the stored procedure
										stmt.execute();
									    System.out.println("Creating successfully an insurance of Need!");
									} 
									catch (SQLException e2_4) 
									{
									    System.out.println("Code: " + e2_4.getErrorCode() + "Error executing stored procedure: " + e2_4.getMessage());
									}	
									break;
                      		
                        		case "4":                        		
	                    			System.out.println("Exiting option...");
	                                break;
                        		  
                        		default:
                                    System.out.println(String.format("Unrecognized option: %s. Please try again!", associate_more));
                                    break;
                        	}  
                        } 
                            
                   break;                                        
                
                case "3":
                	System.out.println("Connecting to the database...");
                	Connection cn3 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc3 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
             		
                    //Execute a stored procedure query2
                    System.out.println("Enter SSN of the Volunteer:");
                    int ssn3 = sc3.nextInt();
                    sc3.nextLine();  // consume newline

                    System.out.println("Enter name of the Volunteer:");
                    String pname3 = sc3.nextLine();
                    
                    System.out.println("Enter gender of the Volunteer: (format: 'Male' or 'Female' or 'Other')");
                    String gend3 = sc3.nextLine();
                    
                    System.out.println("Enter profession of the Volunteer:");
                    String pro3 = sc3.nextLine();
                    
                    System.out.println("Is Volunteer on mail list? (type: Y or N)");
                    String oml3 = sc3.nextLine();
                    
                    System.out.println("Enter email of the Volunteer:");
                    String email3 = sc3.nextLine();
                    
                    System.out.println("Enter mailing addresss of the Volunteer:");
                    String ma3 = sc3.nextLine();
                    
                    System.out.println("Enter phone number of the Volunteer (format:xxx-xxx-xxxx):");
                    String pn3 = sc3.nextLine();
                    
                    System.out.println("Enter emergency name of the Volunteer:");
                    String emn3 = sc3.nextLine();
                    
                    System.out.println("Enter emergency phone number of the Volunteer (format:xxx-xxx-xxxx):");
                    String empn3 = sc3.nextLine();
                    
                    System.out.println("Enter emergency relationship of the Volunteer:");
                    String emr3 = sc3.nextLine();
                    
                    System.out.println("Enter the date of the Volunteer first joined (format: yyyy-mm-dd):");
                    String dj3 = sc3.nextLine();
                    
                    System.out.println("Enter the Volunteer training location:(Ex: B130,...)");
                    String vtl3 = sc3.nextLine();
                    
                    System.out.println("Enter the Volunteer training date (format: yyyy-mm-dd):");
                    String vtd3 = sc3.nextLine();

                    // Call the stored procedure using JDBC
                    String sql3 = "{CALL query3_1(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // Replace with your stored procedure name
                    try (CallableStatement stmt = cn3.prepareCall(sql3)) 
                    {
                        // Set the parameters for the stored procedure
                    	stmt.setInt(1, ssn3);
                    	stmt.setString(2, pname3);
                    	stmt.setString(3, gend3);
                    	stmt.setString(4, pro3);
                    	stmt.setString(5, oml3);
                    	stmt.setString(6, email3);
                    	stmt.setString(7, ma3);
                        stmt.setString(8, pn3);
                        stmt.setString(9, emn3);                            
                        stmt.setString(10, empn3);
                        stmt.setString(11, emr3);  
                        stmt.setString(12, dj3);                            
                        stmt.setString(13, vtl3);                            
        				stmt.setString(14, vtd3);        				

                        // Execute the stored procedure
                        stmt.execute();
                        System.out.println("Creating successfully a new Volunteer!");
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Error executing stored procedure: " + e.getMessage());
                    }
                    
                    // associate with another team
                    System.out.println("Choose options below\n");
                    Scanner sc3_0 = new Scanner(System.in);
                    String associate_more3 = "";
                    while (!associate_more3.equals("2")) 
                    {                       	                       	
                    	System.out.println(TEAMVOLUNTEER);
                    	System.out.println("Select option:");
                    	associate_more3 = sc3_0.next();
                    	switch (associate_more3) 
                    	{
                    		case "1":                       		
                    			System.out.println("Connecting to the database...");
                            	Connection cn3_2 = DriverManager.getConnection(URL);
                            	System.out.println("Dispatching the query...");
                            	Scanner sc3_2 = new Scanner(System.in);
                            	
								System.out.println("Connected to the database.");
								sc3_0.hasNextInt();
								System.out.println("SSN:" + ssn3);
								// display exist team name
								Statement stmtex3_2 = cn3_2.createStatement();
			                	ResultSet resultSet3_2 = stmtex3_2.executeQuery(QUERYDISPLAYTEAM);
			                	System.out.printf("|%-12s |%n", "Team_name");
			                	while (resultSet3_2.next()) 
			                	{
			                		System.out.printf(String.format("|%-12s |%n", resultSet3_2.getString("team_name")));
			                	}
			                	// Manually closing resources
			                	resultSet3_2.close();
			                	stmtex3_2.close();	
			                	// read in user
								System.out.println("Enter team name:");
								String tn3_2 = sc3_2.nextLine();
								
								System.out.println("Is the Volunteer active? (type: Y or N)");
								String tcliact3_2 = sc3_2.nextLine();
								
								System.out.println("What is the Volunteer role? (type: 'Leader' or 'Member')");
		                        String tvlr3_2 = sc3_2.nextLine();
		                        
		                        System.out.println("Enter the month of the Volunteer work (format: 'Jan', 'Feb',...):");
		                        String wm3_2 = sc3_2.nextLine();
		                        
		                        System.out.println("Enter the year:");
		                        int wy3_2 = sc3_2.nextInt();
		                        sc3_2.nextLine();  // consume newline
		                        
		                        System.out.println("Enter the hours of the Volunteer work of its month and year:");
		                        int wh3_2 = sc3_2.nextInt();
		                        sc3_2.nextLine();  // consume newline
								// Call the stored procedure using JDBC
								String sql3_2 = "{CALL query3_2(?, ?, ?, ?, ?, ?, ?)}"; 
								try (CallableStatement stmt = cn3_2.prepareCall(sql3_2))
								{
								    // Set the parameters for the stored procedure
									stmt.setInt(1, ssn3);
									stmt.setString(2, tn3_2);
									stmt.setString(3, tcliact3_2);
									stmt.setString(4, tvlr3_2);
									stmt.setString(5, wm3_2);
									stmt.setInt(6, wy3_2);
									stmt.setInt(7, wh3_2);

								    // Execute the stored procedure
									stmt.execute();
								    System.out.println("Creating successfully a Team of the Volunteer work in!");
								} 
								catch (SQLException e) 
								{
								    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
								}	
								catch (InputMismatchException e2) 
			                    {
			                        System.out.println("Invalid input. Please try again");
			                    }
								break;                        			
								
                    		case "2":                        		
                    			System.out.println("Exiting option...");
                                break;
                                                       		  
                    		default:
                                System.out.println(String.format("Unrecognized option: %s. Please try again!", associate_more3));
                                break;
                        	}  
                        }                        
               	
                   break;
                        
                case "4":
                	System.out.println("Connecting to the database...");
                	Connection cn4 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc4 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try
                    {
                    	//Execute a stored procedure query4
                        System.out.println("Enter SSN of a Volunteer:");                    
                        int ssn4 = sc4.nextInt();
                        sc4.nextLine();  // consume newline
                        
                        System.out.println("Enter the Volunteer's team name:");
    					String tn4 = sc4.nextLine();
    					
    					System.out.println("Enter a specific month (format: 'Jan', 'Feb', 'Mar',...):");
    					String wm4 = sc4.nextLine();
    					
    					System.out.println("Enter year:");                    
                        int wy4 = sc4.nextInt();
                        sc4.nextLine();  // consume newline
                        
                        System.out.println("Enter worked hours:");                    
                        int wh4 = sc4.nextInt();
                        sc4.nextLine();  // consume newline                                        
                        
                     // Call the stored procedure using JDBC
                        String sql4 = "{call query4(?, ?, ?, ?, ?)}"; 
                        try (CallableStatement stmt = cn4.prepareCall(sql4)) 
                        {
                            // Set the parameters for the stored procedure
                        	stmt.setInt(1, ssn4);
                        	stmt.setString(2, tn4);
                        	stmt.setString(3, wm4);
                        	stmt.setInt(4, wy4);
                        	stmt.setInt(5, wh4);

                            // Execute the stored procedure
            				stmt.execute();
                            System.out.println("Adding successfully!");
                        } 
                        
                        catch (SQLException e) 
                        {
                            System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
                        } 
                    }
                    
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc4.nextLine(); // Clear the invalid input
                    }
                    
                	finally {}
                                           
                break;
              
                case "5":
                	System.out.println("Connecting to the database...");
                	Connection cn5 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc5 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                   
                	//Execute a stored procedure query5
                    System.out.println("Enter SSN of the Employee:");
                    int ssn5 = sc5.nextInt();
                    sc5.nextLine();  // consume newline

                    System.out.println("Enter name of the Employee:");
                    String pname5 = sc5.nextLine();
                    
                    System.out.println("Enter gender of the Employee (format: 'Male' or 'Female' or 'Other'):");
                    String gend5 = sc5.nextLine();
                    
                    System.out.println("Enter profession of the Employee:");
                    String pro5 = sc5.nextLine();
                    
                    System.out.println("Is the Employee on mail list? (type: Y or N)");
                    String oml5 = sc5.nextLine();
                    
                    System.out.println("Enter email of the Employee:");
                    String email5 = sc5.nextLine();
                    
                    System.out.println("Enter mailing addresss of the Employee:");
                    String ma5 = sc5.nextLine();
                    
                    System.out.println("Enter phone number of the Employee (format: xxx-xxx-xxxx):");
                    String pn5 = sc5.nextLine();
                    
                    System.out.println("Enter emergency name of the Employee:");
                    String emn5 = sc5.nextLine();
                    
                    System.out.println("Enter emergency phone number of the Employee (format: xxx-xxx-xxxx):");
                    String empn5 = sc5.nextLine();
                    
                    System.out.println("Enter emergency relationship of the Employee:");
                    String emr5 = sc5.nextLine();
                    
                    System.out.println("Enter the Employee salary:");
                    double es5 = sc5.nextDouble();
                    sc5.nextLine();  // consume newline
                    
                    System.out.println("Enter the Employee marital status(type one: 'married', 'single', 'divorced', 'separated', 'widowed'):");
                    String cdpn5 = sc5.nextLine();
                    
                    System.out.println("Enter the hired date of the Employee (format: yyyy-mm-dd):");
                    String hd5 = sc5.nextLine();

                    // Call the stored procedure using JDBC
                    String sql5 = "{CALL query5_1(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                    try (CallableStatement stmt = cn5.prepareCall(sql5)) 
                    {
                        // Set the parameters for the stored procedure
                    	stmt.setInt(1, ssn5);
                    	stmt.setString(2, pname5);
                    	stmt.setString(3, gend5);
                    	stmt.setString(4, pro5);
                    	stmt.setString(5, oml5);
                    	stmt.setString(6, email5);
                    	stmt.setString(7, ma5);
                        stmt.setString(8, pn5);
                        stmt.setString(9, emn5);                            
                        stmt.setString(10, empn5);
                        stmt.setString(11, emr5);                           
                        stmt.setDouble(12, es5);
                        stmt.setString(13, cdpn5);                            
        				stmt.setString(14, hd5);
                   
                        // Execute the stored procedure
                        stmt.execute();
                        System.out.println("Creating successfully, a new employee is inserted!");
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Error executing stored procedure: " + e.getMessage());
                    }
                    // associate with another team
                    System.out.println("Choose options below\n");
                    Scanner sc5_0 = new Scanner(System.in);
                    String associate_more5 = "";
                    while (!associate_more5.equals("2")) 
                    {                       	                       	
                    	System.out.println(REPORT);
                    	System.out.println("Select option:");
                    	associate_more5 = sc5_0.next();
                    	switch (associate_more5) 
                    	{
                    		case "1":                       		
                    			System.out.println("Connecting to the database...");
                            	Connection cn5_2 = DriverManager.getConnection(URL);
                            	System.out.println("Dispatching the query...");
                            	Scanner sc5_2 = new Scanner(System.in);
                            	
								System.out.println("Connected to the database.");
								System.out.println("SSN:" + ssn5);
								// display exist team name
								Statement stmtex5_2 = cn5_2.createStatement();
			                	ResultSet resultSet5_2 = stmtex5_2.executeQuery(QUERYDISPLAYTEAM);
			                	System.out.printf("|%-12s |%n", "Team_name");
			                	while (resultSet5_2.next()) 
			                	{
			                		System.out.printf(String.format("|%-12s |%n", 
			                				resultSet5_2.getString("team_name")));
			                	}
			                	// Manually closing resources
			                	resultSet5_2.close();
			                	stmtex5_2.close();				                	
			                	
								System.out.println("Enter team name:");
								String tn5_2 = sc5_2.nextLine();

								System.out.println("Enter the report description:");
								String rd5_2 = sc5_2.nextLine();
								
								System.out.println("Enter the reported date to the the Employee (format: yyyy-mm-dd):");
		                        String dr5_2 = sc5.nextLine();
								
								// Call the stored procedure using JDBC
								String sql5_2 = "{call query5_2(?, ?, ?, ?)}"; 
								try (CallableStatement stmt = cn5_2.prepareCall(sql5_2))
								{
								    // Set the parameters for the stored procedure
									stmt.setString(1, tn5_2);
									stmt.setInt(2, ssn5);
									stmt.setString(3, rd5_2);
									stmt.setString(4, dr5_2);
								    // Execute the stored procedure
									stmt.execute();
								    System.out.println("Creating successfully another team report to the employee!");
								} 
								catch (SQLException e) 
								{
								    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
								}	
								break;									                               	
                  		
                    		case "2":                        		
                    			System.out.println("Exiting option...");
                                break;
                    		  
                    		default:
                                System.out.println(String.format("Unrecognized option: %s. Please try again!", associate_more5));
                                break;
                    	}  
                    }
                     
                break;       
                
                case "6":
                	System.out.println("Connecting to the database...");
                	Connection cn6 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc6 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try
                    {
                    	//Execute a stored procedure query6
                        System.out.println("Enter ssn of the Employee:");
                        int ssn6 = sc6.nextInt();
                        sc6.nextLine();  // consume newline
                        
                        System.out.println("Enter an expense charged date (format: yyyy-mm-dd):");
                        String dc6 = sc6.nextLine();
                        
                        System.out.println("Enter an expense amount:");
                        double ea6 = sc6.nextDouble();
                        sc6.nextLine();  // consume newline
                        
                        System.out.println("Enter the expense description:");
    					String ed6 = sc6.nextLine();
                        
    					// Call the stored procedure using JDBC
    					String sql6 = "{call query6(?, ?, ?, ?)}"; 
    					try (CallableStatement stmt = cn6.prepareCall(sql6))
    					{
    					    // Set the parameters for the stored procedure
    						stmt.setInt(1, ssn6);
    						stmt.setString(2, dc6);
    						stmt.setDouble(3, ea6);
    						stmt.setString(4, ed6);
    					    // Execute the stored procedure
    						stmt.execute();
    					    System.out.println("Creating successfully an expense charged by an employee!");
    					} 
    					
    					catch (SQLException e) 
    					{
    					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
    					}	
                    }
                    
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc6.nextLine(); // Clear the invalid input
                    }
                    
                	finally {}
                    
                    
					break;
					
                case "7":
                	System.out.println("Connecting to the database...");
                	Connection cn7 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc7 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                	//Execute a stored procedure query7
                    System.out.println("Enter SSN of the Donor:");
                    int ssn7 = sc7.nextInt();
                    sc7.nextLine();  // consume newline

                    System.out.println("Enter name of the Donor:");
                    String pname7 = sc7.nextLine();
                    
                    System.out.println("Enter gender of the Donor:(format: 'Male' or 'Female' or 'Other')");
                    String gend7 = sc7.nextLine();
                    
                    System.out.println("Enter profession of the Donor:");
                    String pro7 = sc7.nextLine();
                    
                    System.out.println("Is the Donor on mail list? (type: Y or N)");
                    String oml7 = sc7.nextLine();
                    
                    System.out.println("Enter email of the Donor:");
                    String email7 = sc7.nextLine();
                    
                    System.out.println("Enter mailing addresss of the Donor:");
                    String ma7 = sc7.nextLine();
                    
                    System.out.println("Enter phone number of the Donor: (format: xxx-xxx-xxxx)");
                    String pn7 = sc7.nextLine();
                    
                    System.out.println("Enter emergency name of the Donor:");
                    String emn7 = sc7.nextLine();
                    
                    System.out.println("Enter emergency phone number of the Donor: (format: xxx-xxx-xxxx)");
                    String empn7 = sc7.nextLine();
                    
                    System.out.println("Enter emergency relationship of the Donor:");
                    String emr7 = sc7.nextLine();
                                        
                    System.out.println("Is the Donor anonymous?(type: 'Y' or 'N'):");
                    String ida7 = sc7.nextLine();

                    // Call the stored procedure using JDBC
                    String sql7 = "{CALL query7_1(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                    try (CallableStatement stmt = cn7.prepareCall(sql7)) 
                    {
                        // Set the parameters for the stored procedure
                    	stmt.setInt(1, ssn7);
                    	stmt.setString(2, pname7);
                    	stmt.setString(3, gend7);
                    	stmt.setString(4, pro7);
                    	stmt.setString(5, oml7);
                    	stmt.setString(6, email7);
                    	stmt.setString(7, ma7);
                        stmt.setString(8, pn7);
                        stmt.setString(9, emn7);                            
                        stmt.setString(10, empn7);
                        stmt.setString(11, emr7);                           
                        stmt.setString(12, ida7);
                   
                        // Execute the stored procedure
                        stmt.execute();
                        System.out.println("Creating successfully a new donor!");
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Error executing stored procedure: " + e.getMessage());
                    }
                    // associate donor with another donation
                    System.out.println("Choose options below\n");
                    Scanner sc7_0 = new Scanner(System.in);
                    String associate_more7 = "";
                    while (!associate_more7.equals("2")) 
                    {                       	                       	
                    	System.out.println(DONATION);
                    	System.out.println("Select option:");
                    	associate_more7 = sc7_0.next();
                    	switch (associate_more7) 
                    	{
                    		case "1":                       		
                    			System.out.println("Connecting to the database...");
                            	Connection cn7_2 = DriverManager.getConnection(URL);
                            	System.out.println("Dispatching the query...");
                            	Scanner sc7_2 = new Scanner(System.in);
                            	
								System.out.println("Connected to the database.");
								System.out.println("SSN:" + ssn7);
								
								System.out.println("Enter the donation date (format: yyyy-mm-dd):");
								String dd7_2 = sc7_2.nextLine();
								
								System.out.println("Enter amount of donation:");
								double da7_2 = sc7_2.nextDouble();
								sc7_2.nextLine();  // consume newline
				
								System.out.println("Enter the campaign name along with the donation(if applicable):");
		                        String dcn7_2 = sc7_2.nextLine();
		                        
		                        System.out.println("Enter the donation method: (type: 'Check' or 'Card')");
		                        String dt7_2 = sc7_2.nextLine();
		                        
		                        System.out.println("Enter check number:");
		                        String dncn7_2 = sc7_2.nextLine();
		                        
		                        System.out.println("Enter card type: (type: 'Visa' or 'Master' or 'Discover' or 'American Express')");
		                        String dct7_2 = sc7_2.nextLine();
		                        
		                        System.out.println("Enter card number:");
		                        String dnctn7_2 = sc7_2.nextLine();
		                        
		                        System.out.println("Enter the card expire date: (format: yyyy-mm-dd)");
								String dncd7_2 = sc7_2.nextLine();
								
								// Call the stored procedure using JDBC
								String sql7_2 = "{call query7_2(?, ?, ?, ?, ?, ?, ?, ?, ?)}"; 
								try (CallableStatement stmt = cn7_2.prepareCall(sql7_2))
								{
								    // Set the parameters for the stored procedure
									stmt.setInt(1, ssn7);
									stmt.setString(2, dd7_2);
									stmt.setDouble(3, da7_2);
									stmt.setString(4, dcn7_2);
									stmt.setString(5, dt7_2);
									stmt.setString(6, dncn7_2);
									stmt.setString(7, dct7_2);
									stmt.setString(8, dnctn7_2);
									stmt.setString(9, dncd7_2);
									
								    // Execute the stored procedure
									stmt.execute();
								    System.out.println("Creating successfully a Donation!");
								} 
								catch (SQLException e) 
								{
								    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
								}	
								break;									                               	
                  		
                    		case "2":                        		
                    			System.out.println("Exiting option...");
                                break;
                    		  
                    		default:
                                System.out.println(String.format("Unrecognized option: %s. Please try again!", associate_more7));
                                break;
                    	}  
                    }                    
                    
                break;
                
                case "8":
                	System.out.println("Connecting to the database...");
                	Connection cn8 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc8 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try
                    {
                    	//Execute a stored procedure query8
                        System.out.println("Enter ssn of the client:");
                        int ssn8 = sc8.nextInt();
                        sc8.nextLine();  // consume newline                    
                        
    					// Call the stored procedure using JDBC
    					String sql8 = "{CALL query8(?)}"; 
    					try (CallableStatement stmt = cn8.prepareCall(sql8))
    					{
    					    // Set the parameters for the stored procedure
    						stmt.setInt(1, ssn8);						

    					    // Execute the stored procedure
    						ResultSet resultSet = stmt.executeQuery();
    						System.out.printf("| %-12s | %-15s |%n", "Doctor name", "Doctor phone");
    						while (resultSet.next()) 
    	                	{
    	                	    System.out.printf(String.format("| %-12s | %-15s |%n",
    	                	    		resultSet.getString("dr_name"),
    	                	    		resultSet.getString("dr_phone")));
    	                	}
    						resultSet.close();
    						stmt.close();
    						cn8.close();
    					} 
    					catch (SQLException e) 
    					{
    					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
    					}
                    }
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc8.nextLine(); // Clear the invalid input
                    }
                    
                	finally {}                        
                    	
					break;
					
                case "9":
                	System.out.println("Connecting to the database...");
                	Connection cn9 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc9 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try 
                    {
                    	System.out.println("Enter an expense charged date from (format: yyyy-mm-dd):");
                        String dcf9 = sc9.nextLine();
                        
                        System.out.println("To (format: yyyy-mm-dd):");
                        String dct9 = sc9.nextLine();
                        
                     // Call the stored procedure using JDBC
    					String sql9 = "{CALL query9(?, ?)}"; 
    					try (CallableStatement stmt = cn9.prepareCall(sql9))
    					{
    					    // Set the parameters for the stored procedure
    						stmt.setString(1, dcf9);
    						stmt.setString(2, dct9);

    					    // Execute the stored procedure
    						ResultSet resultSet = stmt.executeQuery();
    						System.out.printf("| %-12s | %-15s |%n", "SSN", "Total amount of expense");
    						while (resultSet.next()) 
    	                	{
    	                	    System.out.printf(String.format("| %-12s | %-15s |%n",
    	                	    		resultSet.getString("ssn"),
    	                	    		resultSet.getString("total_amount")));
    	                	}
    						resultSet.close();
    						stmt.close();
    						cn9.close();
    					} 
    					catch (SQLException e) 
    					{
    					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
    					}
                    }
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc9.nextLine(); // Clear the invalid input
                    }
                    
                	finally {}  
                                        	
					break;
                	
                case "10":
                	System.out.println("Connecting to the database...");
                	Connection cn10 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc10 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try
                    {
                    	System.out.println("Enter SSN of the Client:");
                        int ssn10 = sc10.nextInt();
                        sc10.nextLine();                                     
                        
                     // Call the stored procedure using JDBC
    					String sql10 = "{CALL query10(?)}"; 
    					try (CallableStatement stmt = cn10.prepareCall(sql10))
    					{
    					    // Set the parameters for the stored procedure
    						stmt.setInt(1, ssn10);

    					    // Execute the stored procedure
    						ResultSet resultSet = stmt.executeQuery();
    						System.out.printf("| %-12s |%n", "Volunteer SSN");
    						while (resultSet.next()) 
    	                	{
    	                	    System.out.printf(String.format("| %-12s |%n",
    	                	    		resultSet.getString("ssn")));
    	                	}
    						resultSet.close();
    						stmt.close();
    						cn10.close();
    					} 
    					catch (SQLException e) 
    					{
    					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
    					}
                    }
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc10.nextLine(); // Clear the invalid input
                    }
                    
                	finally {} 
                   	
					break;
					
                case "11":
                	System.out.println("Connecting to the database...");
                	Connection cn11 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc11 = new Scanner(System.in);
                    System.out.println("Connected to the database.");
                    try
                    {
                    	System.out.println("Enter the date: (format: yyyy-mm-dd)");
                        String da11 = sc11.nextLine();
                        
                        // Call the stored procedure using JDBC
    					String sql11 = "{CALL query11(?)}"; 
    					try (CallableStatement stmt = cn11.prepareCall(sql11))
    					{
    					    // Set the parameters for the stored procedure
    						stmt.setString(1, da11);

    					    // Execute the stored procedure
    						ResultSet resultSet = stmt.executeQuery();
    						System.out.printf("| %-12s | %-15s |%n", "Team name", "Date formed");
    						while (resultSet.next()) 
    	                	{
    	                	    System.out.printf(String.format("| %-12s | %-15s |%n",
    	                	    		resultSet.getString("team_name"),
    	                	    		resultSet.getString("date_formed")));
    	                	}
    						resultSet.close();
    						stmt.close();
    						cn11.close();
    					} 
    					catch (SQLException e) 
    					{
    					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
    					}
                    }
                    catch (InputMismatchException e2) 
                    {
                        System.out.println("Invalid input. Please try again");
                        sc11.nextLine(); // Clear the invalid input
                    }
                    
                	finally {} 
                                       	
					break;
                	
                case "12":
                	System.out.println("Connecting to the database...");
                	Connection cn12 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	String sql12 = "{CALL query12}"; 
                	try (CallableStatement stmt = cn12.prepareCall(sql12))
					{					  
					    // Execute the stored procedure
						ResultSet resultSet = stmt.executeQuery();
						System.out.printf("| %-20s | %-12s | %-20s | %-35s | %-12s | %-15s | %-15s | %-24s |%n", 
								"Person Name", "SSN", "Email", "Mailing Address", "Phone Number", "Emergency Name", "Emergency Phone", "Relationship with Person");
						while (resultSet.next()) 
	                	{
	                	    System.out.printf(String.format("| %-20s | %-12s | %-20s | %-35s | %-12s | %-15s | %-15s | %-24s |%n",
	                	    		resultSet.getString("person_name"),
	                	    		resultSet.getInt("ssn"),
	                	    		resultSet.getString("email"),
	                	    		resultSet.getString("mailing_address"),
	                	    		resultSet.getString("phone_number"),
	                	    		resultSet.getString("emergency_name"),
	                	    		resultSet.getString("emergency_phone_number"),
	                	    		resultSet.getString("relationship")));
	                	}
						resultSet.close();
						stmt.close();
						cn12.close();
					} 
					catch (SQLException e) 
					{
					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
					}	
					break;
					
                case "13":
                	System.out.println("Connecting to the database...");
                	Connection cn13 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	String sql13 = "{CALL query13}"; 
                	try (CallableStatement stmt = cn13.prepareCall(sql13))
					{					  
					    // Execute the stored procedure
						ResultSet resultSet = stmt.executeQuery();
						System.out.printf("| %-20s | %-25s | %-20s |%n", 
								"Person Name", "Total Donation Amount", "Is Donor Anonymous?");
						while (resultSet.next()) 
	                	{
	                	    System.out.printf(String.format("| %-20s | %-21s | %-18s |%n",
	                	    		resultSet.getString("person_name"),
	                	    		resultSet.getDouble("total_donation_amount"),
	                	    		resultSet.getString("is_anonymous")));
	                	}
						resultSet.close();
						stmt.close();
						cn13.close();
					} 
					catch (SQLException e) 
					{
					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
					}	
					break;
					
                case "14":
                	System.out.println("Connecting to the database...");
                	Connection cn14 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	String sql14 = "{CALL query14}"; 
                	try (CallableStatement stmt = cn14.prepareCall(sql14))
					{					  
                		// Execute the stored procedure
        				stmt.execute();
                        System.out.println("Update successfully!");
					} 
					catch (SQLException e) 
					{
					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
					}	
					break;
					
                case "15":
                	System.out.println("Connecting to the database...");
                	Connection cn15 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	String sql15 = "{CALL query15}"; 
                	try (CallableStatement stmt = cn15.prepareCall(sql15))
					{					  
                		// execute the stored procedure
        				stmt.execute();
                        System.out.println("Delete successfully!");
					} 
					catch (SQLException e) 
					{
					    System.out.println("Code: " + e.getErrorCode() + "Error executing stored procedure: " + e.getMessage());
					}	
					break;
					
                case "16":               	
                	System.out.println("Connecting to the database...");
                	Connection cn16 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc16 = new Scanner(System.in);
                	// csv file need to be in the current directory
                    System.out.print("Enter the import file name:(must be a csv file .csv) ");
                    // scan for user input
                    String csviFileName = sc16.nextLine();
                    
                    // call procedure query1                    
                	String sql16 = "{CALL query1(?, ?, ?)}"; 
                	try (CallableStatement stmt = cn16.prepareCall(sql16))
					{					
                		try (BufferedReader br = new BufferedReader(new FileReader(csviFileName)))
                        {
                			String line;
                			// skip the header line if present
                            br.readLine(); 
                            while ((line = br.readLine()) != null) 
                            {
                            	// set the parameter for the stored procedure
                            	String[] values = line.split(",");
                            	stmt.setString(1, values[0]);
                            	stmt.setString(2, values[1]);
                            	stmt.setString(3, values[2]);
                            	// execute the current record
                				stmt.execute();
                				System.out.println("Stored procedure executed successfully for record: " + String.join(", ", values));
                            }                   	                         
                        }
					}
                	catch (SQLException e) 
            		{
                        e.printStackTrace();
                        System.out.println("Error executing stored procedure for record: " + String.join(", ", e.getMessage()));
            		}
                	catch (IOException e) 
                	{
                        e.printStackTrace();
                        System.out.println("Error reading the CSV file.");
                    }
  	
					break;

                case "17":    
                	System.out.println("Connecting to the database...");
                	Connection cn17 = DriverManager.getConnection(URL);
                	System.out.println("Dispatching the query...");
                	Scanner sc17 = new Scanner(System.in);
                	//export file 
                    System.out.print("Enter the export filen name:(format: filename.csv) ");
                    String outputFileName = sc17.nextLine();
                    // call procedure 17
                	String sql17 = "{CALL query17()}"; 
                	try (CallableStatement stmt = cn17.prepareCall(sql17))
					{					
                		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName)))
                        {
                			
                			ResultSet rs = stmt.executeQuery();
                			// write csv header to output file
                			bw.write("Name,Mailing Address");
                			bw.newLine();

                			while (rs.next()) 
                			{
                                String pn17 = rs.getString("person_name");
                                String ma17 = rs.getString("mailing_address");

                                // write the data as a comma-separated line
                                bw.write("\"" + pn17 + "\",\"" + ma17 + "\"");
                                bw.newLine();
                            }     
                			System.out.println("Data exported successfully to " + outputFileName);
                        }
					}            
                	catch (SQLException e) 
                	{
                        e.printStackTrace();
                        System.out.println("Database connection or procedure execution error");
                    }
                	catch (IOException e) 
                	{
                        e.printStackTrace();
                        System.out.println("Error writing to the output file");
                    }
  	
					break;
             
                case "18":
                    System.out.println("Exiting program...");
                    break;
                    
                default:
                    System.out.println(String.format("Unrecognized option: %s. Please try again!", option));
                    break;
            }
        }
        sc.close(); // Close the scanner before exiting the application
    }
}


