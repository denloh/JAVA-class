package flightSystem2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import flightSystem2.Flight.FlightStatus;
import flightSystem2.Flight;
import flightSystem.Passenger;

public class Admin {

	private String userName;
	private String password;
	
	public static ArrayList<Admin>admins=new ArrayList<Admin>();

	// constructor
	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	//Admin State
    public enum AdminState{RIGHT,WRONG};
    private static AdminState thisAdmin=AdminState.WRONG;
    
  // 
  	 public static void initializeAdminData(){
  			Admin a1=new Admin("John","123");
  			Admin a2=new Admin("Mary","321");
  			Admin a3=new Admin("Cheer","345");
  			Admin.admins.add(a1);
  			Admin.admins.add(a2);
  			Admin.admins.add(a3);	
  		}
 	
 	
  	public void createFlight() throws Exception
	{
		String FlightID;
		String startTime;
		String arrivalTime;
		String startCity;
		String arrivalCity;
		String departureDate;
		int price;
		int seatCapacity;
		Scanner input2 = new Scanner(System.in);
		
	//create a new ArrayList named newflights
		ArrayList<Flight>newflights=readFile();
		
	//read from file and display
	    displayFlightFile();
	    
	//open the file
		Formatter output = new Formatter("flights.txt");
		 System.out.printf( "%s\n%s\n%s\n%s\n%s\n\n",
				 "You can create new Flight now.",
		         "To terminate input, type the end-of-file indicator ",
		         "when you are prompted to enter input.",
		         "On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
		         "On Windows type <ctrl> z then press Enter" );
		 
   //ask the admin to enter the new flight information
		System.out.printf("%s\n","Enter FlightID:");
		while(input2.hasNext())
		{	
				FlightID = input2.next(); 

			    System.out.println("Enter departuredate(month/day/year):");
				departureDate=input2.next();
			    System.out.println("Enter starttime in HH:mm:ss:");
			
   //Judging whether the starttime is 2 hours latter
   //If not,ask the admin to enter another time
			    Date now=new Date();
			    int twohours=120*60*1000;
			    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		        String startime=input2.next();
			    Date former=df.parse(departureDate+" "+startime);
			     while
				 (now.getTime()+twohours>former.getTime())
			     {
				    System.out.println("Ensure the starttime is at least 2 hours latter,"
						             + "please try again.");	
				    startime=input2.next();
				    former=df.parse(departureDate+" "+startime);
			     }
			   startTime=startime; 
	//Judging whether the entered arriavltime is later than 		   
			   System.out.println("Enter arrivaltime in HH:mm:ss:");
			   String arritime=input2.next();
			   Date later=df.parse(departureDate+" "+arritime);
			    while
				(former.getTime()>later.getTime())
			    {
				   System.out.println("Ensure the arrivaltime is latter than starttime"
						             +"Please try again");
				   arritime=input2.next();
				   later=df.parse(departureDate+" "+arritime);
			    }
			   arrivalTime=arritime;
			   System.out.println("Enter startcity:");
			   startCity=input2.next();
			   System.out.println("Enter arrivalcity:");
			   arrivalCity=input2.next();
			   System.out.println("Enter price");
			   price=input2.nextInt();
			   System.out.println("Enter seatcapacity");
			   seatCapacity=input2.nextInt();
				
			   Flight newflight = new Flight(FlightID, startTime,arrivalTime,startCity,arrivalCity,
						departureDate,price,seatCapacity);
				newflights.add(newflight);
				System.out.println(" FlightID\tstartTime\tarrivalTime\tstartCity\tarrivalCity\tdepartureDate\tprice\tseatCapacity");
				System.out.println("The new Flight:"+newflight.getFlightID()+"\t"+newflight.getStartTime()
				+"\t"+newflight.getArrivalTime()+"\t"+newflight.getStartCity()+"\t"+newflight.getArrivalCity()
				+"\t"+newflight.getDepartureDate()+"\t"+newflight.getPrice()+"\t"+newflight.getSeatCapacity());
		
				System.out.println("Enter FlightID:");	
		}
		
	//write in the file
	       writeFile(newflights);
		   
	//close the scanner
	       input2.close();
	}//end the createFlight method
	
 	
 	
  	//write an ArrayList into the file
	public static void writeFile(ArrayList<Flight> flights) throws FileNotFoundException
	{
		Formatter output = new Formatter("flights.txt");
		for( int i = 0; i < flights.size(); i++ )
		{
			output.format("\n%-10s%-14s%-14s%-14s%-14s%-14s%-8d%4d\n",
					flights.get(i).getFlightID(), flights.get(i).getStartTime(),flights.get(i).getArrivalTime(),
					flights.get(i).getStartCity(),flights.get(i).getArrivalCity(),flights.get(i).getDepartureDate(),
					flights.get(i).getPrice(),flights.get(i).getSeatCapacity());
		}
		
		output.close();
	}
 	
	
	//read from the file and add them into an ArrayList
	//return the new ArrayList
 	public static ArrayList<Flight> readFile() throws FileNotFoundException
 	{ 		
 		ArrayList<Flight>flights=new ArrayList<Flight>();
 		String FlightID;
 		String startTime;
 		String arrivalTime;
 		String startCity;
 		String arrivalCity;
 		String departureDate;
 		int price;
 		int seatCapacity;
 		Scanner input=new Scanner(new File("flights.txt"));
 		
 		while(input.hasNext()){
 			FlightID=input.next();
 			startTime=input.next();
 			arrivalTime=input.next();
 			startCity=input.next();
 			arrivalCity=input.next();
 			departureDate=input.next();
 			price=input.nextInt();
 			seatCapacity=input.nextInt();
 			Flight newflight = new Flight(FlightID, startTime,arrivalTime,startCity,arrivalCity,departureDate,price,seatCapacity);
 			flights.add(newflight);	
 			
 		}
 		input.close();
 		return flights;
 	}
 


	public static void displayFlightFile()throws Exception
	{
        ArrayList<Flight>flights=new ArrayList<Flight>();		
		Scanner input = new Scanner( new File("flights.txt"));
			
				String FlightID;
				String startTime;
				String arrivalTime;
				String startCity;
				String arrivalCity;
				String departureDate;
				int price;
				int seatCapacity;
				System.out.printf("%-10s%-14s%-14s%-14s%-14s%-17s%-10s%-8s\n",
						"FlightID", "startTime","arrivalTime","startcity","arrivalcity","departureDate","price","seatCapacity");
				
				while (input.hasNext())
				{
					FlightID = input.next(); 
					startTime= input.next(); 
					arrivalTime=input.next();
					startCity=input.next();
					arrivalCity=input.next();
					departureDate=input.next();
					price=input.nextInt();
					seatCapacity=input.nextInt();
					System.out.printf("%-10s%-14s%-14s%-14s%-14s%-14s%8d%10d\n",
							FlightID, startTime,arrivalTime,startCity,arrivalCity,departureDate,price,seatCapacity);
					
					Flight newflight = new Flight(FlightID, startTime,arrivalTime,startCity,arrivalCity,
							departureDate,price,seatCapacity);
					flights.add(newflight);			
				} 
				
				input.close();
	}
  	 
	// 2) publish flight
	public void publish(String FlightID) throws FileNotFoundException
	{
		ArrayList<Flight>flights=readFile();
		for(int i=0;i<flights.size();i++)
		{		
			if
			(flights.get(i).getFlightID().equals(FlightID))
		    flights.get(i).setStatus(FlightStatus.AVAILABLE);
		}
		writeFile(flights);
	}
	
	//find the flight according to the FlightID
	//change the flight information 
	//if it is UNPUBLISHED, all the information can be changed
	//if it is AVAILABLE&FULL, only the price and seatcapacity can be changed
	public void updateFlight(String FlightID) throws FileNotFoundException
	{
		ArrayList<Flight>flights=readFile();
		Scanner input = new Scanner(System.in);
		for(int i=0;i<flights.size();i++)
		{
			if(flights.get(i).getFlightID().equals(FlightID))
			  {	
				System.out.printf("%-10s%-14s%-14s%-14s%-14s%-17s%-10s%-8s\n","FlightID", "startTime","arrivalTime","startcity","arrivalcity",
						"departureDate","price","seatCapacity");
				
				System.out.println(flights.get(i));
				
	//call the method to update the flight status
				flights.get(i).statusUpdate(); 
				   if(flights.get(i).getStatus().equals(FlightStatus.UNPUBLISHED))
				    {
			           System.out.println("Please enter the new information of the flight"+flights.get(i).getFlightID());
			           System.out.println("If you want to update the starttime , please enter 1\n"
			           		+ "If you want to update the arrivaltime, please enter 2\n"
			           		+ "If you want to update the startcity, please enter 3\n"
			           		+ "If you want to update the arrivaltime, please enter 4\n"
			           		+ "If you want to update the departuredate, please enter 5\n"
			           		+ "If you want to update the price, please enter 6\n"
			           		+ "If you want to update the seatcapacity, please enter 7\n");
	    	     
	    	           switch(input.nextInt())
	    	           {
	    	           case 1:
	    		             System.out.println("Please enter the new starttime:");
	    	                 flights.get(i).setStartTime(input.next());
	    	                 break;
	    	           case 2:
	    	               	 System.out.println("Please enter the new arrivaltime:");
	    		             flights.get(i).setArrivalTime(input.next());
	    		             break;
	    	           case 3:
	    		             System.out.println("Please enter the new startcity:");
	    		             flights.get(i).setStartCity(input.next());
	    		             break;
	    	           case 4:
	    		             System.out.println("Please enter the new arrivalcity");
	    		             flights.get(i).setArrivalCity(input.next());
	    		             break;
	    	           case 5:
	    	            	 System.out.println("Please enter the new departuredate:");
	    		             flights.get(i).setArrivalCity(input.next());
	    		             break;
	    	           case 6:
	    		             System.out.println("Please enter the new price:");
	    		             flights.get(i).setPrice(input.nextInt());
	    		             break;
	    	                 case 7:
	    		             System.out.println("Please enter the new seatcapacity:");
	    		             flights.get(i).setSeatCapacity(input.nextInt());
	    		             break;
	    		       default:
	    			         break;
	    	           }
		           }
		           else if(flights.get(i).getStatus().equals(FlightStatus.TERMINATE))
		           {
			           System.out.println("The Plane will take off in less than 2 hours, "
			           		+ "you cannot update the flight information any more");
		            }
		           else 
		           {
			           System.out.println("The Flight has already been punbished, "
			           					+ "enter 1 if you want to change the price,"
					                    + "enter 2 if you want to change the seatcapacity");
			            switch(input.nextInt())
			            {
			              case 1:
	    		                System.out.println("Please enter the new price:");
	    		                flights.get(i).setPrice(input.nextInt());
	    		                break;
	    	              case 2:
	    		                System.out.println("Please enter the new seatcapacity:");
	    		                flights.get(i).setSeatCapacity(input.nextInt());
	    		                break;
			            }
		            }
	//jump out of the for loop
			        break;
	         }//end if
	  }//end  for
	   writeFile(flights);
	
	}//end updateFlight method 
  	 
  	 
	public void deleteFlight(String FlightID) throws FileNotFoundException
	{
		ArrayList<Flight>flights=readFile();
			for(int i=0;i<flights.size();i++)
			{
			   if(flights.get(i).getFlightID().equals(FlightID))
			   {
				  if(flights.get(i).getStatus().equals(FlightStatus.TERMINATE)||
						  flights.get(i).getStatus().equals(FlightStatus.UNPUBLISHED))
				  {
				     flights.remove(i);
				      break;//jump put from for loop
			       }
				  else
				  {
					  System.out.println("The flight is not UNPUBLISHED or TERMINATE, you cannot delete it now.");
				  }
			   }
			}
			writeFile(flights);
	}
  	 
  	 
  	 
  	 
  	public String toString(int i){
		return String.format("%s\t%s", Flight.flights.get(i).getStartCity(),Flight.flights.get(i).getArrivalCity());
  		
  	}
  	 
  	 
  	 
  	 
  	 
    // admin's function, query flight information about passengers and orders
  	public void superQuery(){
  		
  		System.out.println("If you want to check all order messages of a fight, please enter 1"
  				+"If you want to check order, please enter 2"
  				+"If you want to check fight, please enter 3");
  	// let the administrator make a choice	
  		Scanner input=new Scanner(System.in);
  		int n=input.nextInt();
  		
  	switch(n){
          //query by flightID and display all the reserve messages about this flight
  	    //such as passenger's name, identityID...
  	    case 1:		
  		
  			
  	    	
  	    	break;
  			
  	    //query information about orders
  		case 2:
  			
  			Passenger.queryOrder(PassengerID);
  			break;
  			
          //query information about flights
  		case 3:
  			
  			queryFlight();
  			break;
  		default: break;
  	}
  }
	
	//query information about flights
    //both administrators and passengers can use this method to query
	public static void queryFlight(){
		
		System.out.println("1 Query by start city, arrival city and departure date, 2 Query by Flight ID");
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		
		switch(n){
		case 1: 
		// query by entering start city, arrival city and departure date
		query();
		break;
		
		
		case 2: 
		
		System.out.println("Please enter the flight ID");
		String flightID=input.next();
		// fuzzy search
		Flight.search(flightID, Flight.flights);
        break;
        
        default: break;
		}
	}
	
		//query by entering start city, arrival city and departure date
		public static void query(){
			Scanner input=new Scanner(System.in);

			System.out.println("Please enter the start city");
			String startCity=input.nextLine();
			System.out.println("Please enter the arrival city");
			String arrivalCity=input.nextLine();
			System.out.println("Please enter the departure date");
			String departureDate=input.nextLine();
			
		   int i;
		   //determine if there exists such a flight
		   boolean flag=false;
	       //search the flights arrayList
	       for(i=0;i<Flight.flights.size();i++){
	    	   if(Flight.flights.get(i).getStartCity().equals(startCity)&&Flight.flights.get(i).getArrivalCity().equals(arrivalCity)&&Flight.flights.get(i).getDepartureDate().equals(departureDate)){
	    		   System.out.printf("FlightID: %s  Price: %s  Status: %s\n",Flight.flights.get(i).getFlightID(),Flight.flights.get(i).getPrice(),Flight.flights.get(i).getStatus());	
	    		   flag=true;    
	    	   }
	       }//end for
	       
	   	   if (flag==false)
			System.out.println("Don't exist!");
	   	   }
		
		 
		
		    static boolean a;  
		    //to judge whether the passwords are the same
		    public static void checkPassword(String Password1, String Password2) {
			        Scanner input = new Scanner(System.in);
			        if (Password1.equals(Password2)){
			           System.out.println("The passwords are the same.");
			           a=true;
			        } 
			        else{
			           a=false;	
			        } 
			        }
	    
	    //create a new administrator
		  public static void newAdmin() throws FileNotFoundException{
		  		
		  		Scanner input=new Scanner(System.in);
		  		System.out.println("Please enter the user's name");
		  		String userName1=input.next();
		  		System.out.println("Please enter the user's password");
		  		String password1=input.next();
		  		//let the administrator enter the password twice
		  	    System.out.println("Please enter the password again.");
		  	    String verifyPassword=input.next();
		  	    
		  	    //to judge whether the passwords are the same
		  	    checkPassword(password1, verifyPassword);
		  	    
		  	    //same, now create a new administrator
		  	    if (a==true){

		  	    	ArrayList<Admin> admins=new ArrayList<Admin>();
		  	    	String userName;
		  	    	String password;
		  	    	
		  	    	//read the file
			  		Scanner input1 = new Scanner(new File("admins.txt"));

		  	    	while(input1.hasNext()){
		  	    		userName = input1.next();
		  	    		password = input1.next();
		  	    		
		  	    		Admin myadmin = new Admin(userName,password);
		  	    		admins.add(myadmin);
		  	    	}

		  	        input1.close(); //end read
		  	        
		 	   System.out.println("You have successfully added a new admin");

		 	   //write the file
		  	   Formatter output=new Formatter("admins.txt");
		  	   
		  			String userName2=userName1;
		  			String password2=password1;
		  			
		  			Admin newadmin= new Admin(userName2,password2);
		  			admins.add(newadmin);
		  			
			  	    
	              for (int i=0;i<admins.size();i++){
	  	  			output.format("%s\t%s\n", admins.get(i).getUserName(),admins.get(i).getPassword());
	              }

	              output.close();//end write
	              

		  	    }
		  	    
		  	 
		  	    //not same, re-enter the new adminstrator's name and password
		  	    if (a==false){
		  	    System.out.println("not same");
		  	    newAdmin();
		  	    }

		  	}
	  
	
	//refresh the administrator's information: name or password
	    public static void updateAdmin() throws FileNotFoundException{
	  	
	  	Scanner input=new Scanner(System.in);
	  	
	    //let the administrator choose to refresh name or password  
	  	System.out.println("If you want to update your username, please enter 1");
	  	System.out.println("If you want to update your password, please enter 2");
	  	
	  	int n=input.nextInt();
	  	
	  	// read the file, create the new arrayList
	  	 ArrayList<Admin> admins=new ArrayList<Admin>();

		    	String userName;
		    	String password;
		    	
		  		Scanner input1 = new Scanner(new File("admins.txt"));

		    	while(input1.hasNext()){
		    		userName = input1.next();
		    		password = input1.next();
		    	
		    		Admin myadmin = new Admin(userName,password);
		    		admins.add(myadmin);
		    	}
		    	
		   input1.close();//end read
		   
		   //let the administrator input his name to get his position in the arrayList
		      System.out.println("Please enter your name");
		      String userName1=input.next();
			      int i;
		    	 for(i=0;i<admins.size();i++){
		      	 if(admins.get(i).getUserName().equals(userName1)){
		      		 break;
		      	  }   
		    	 }
	   	       
		   Formatter output=new Formatter("admins.txt");//open the file
		 
		   //determine to refresh name or password
	  		switch(n){
	  		//refresh name
	      	case 1:
	      		System.out.println("Please enter your new username");
	      		String newName=input.next();
	      		Admin a=new Admin(newName, admins.get(i).getPassword());
	  	        //replace with the new information in the arrayList
	      		admins.set(i, a);
	          	System.out.println("Now your new name is "+admins.get(i).getUserName());
	          	 for (i=0;i<admins.size();i++){
	   	  			output.format("%s\t%s\n", admins.get(i).getUserName(),admins.get(i).getPassword());
	               }
	               output.close();
	      		
	          	break;
	      	//refresh password
	      	case 2:        		
	      		System.out.println("Please enter your new password");
	      	    String newPassword=input.next();
	      	    Admin b=new Admin(admins.get(i).getUserName(),newPassword);
	      	        //replace with the new information in the arrayList
	     		    admins.set(i, b);
	             	System.out.println("Now your new password is "+admins.get(i).getPassword());
	             	for (i=0;i<admins.size();i++){
	   	  			output.format("%s\t%s\n", admins.get(i).getUserName(),admins.get(i).getPassword());
	               }
	               output.close();
	         		
	             	break;
	         
	        default:
	        	break;
	  		}
	     
	  }

	
  	 public void setUserName(String userName){
		 this.userName=userName;
	 }
	 
	 public void setPassword(String password){
		 this.password=password;
	 }
	 
	 //admin get method1
	  public String getUserName(){
	    	return userName;
	    }
		
	 //admin get method2
	  public String getPassword(){
		   return password;
	  }
	  
	  public static void main(String[] args) throws FileNotFoundException
	    {
		  initializeAdminData();
		  Flight.initializeFlightData();
		  
		  admins.get(0).superQuery();

		  Scanner input = new Scanner(System.in);
		  //admins.get(0).createFlight();
	    }

}
