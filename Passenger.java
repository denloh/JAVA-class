package flightSystem;
import java.io.*;
import java.util.*;

import flightSystem.Admin.AdminState;
import flightSystem.Flight.FlightStatus;
import flightSystem.Order.OrderStatus;


public class Passenger {
  	static ArrayList<Passenger> passengers=new ArrayList<Passenger>();
  	String userName;
  	String password;
  	String id;
	static boolean a;
	  
	//constructor
	public Passenger(String userName, String password, String id) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = id;
	}

    public enum PassengerState{RIGHT,WRONG};
    private static PassengerState thisPass=PassengerState.WRONG;
 	
    //log in for passengers
    public static void passLogIn() throws FileNotFoundException{
   	 
   	 Scanner input1 = new Scanner(new File("passengers.txt"));

   		while(input1.hasNext()){
   			String userName = input1.next();
   			String password = input1.next();
   			String id = input1.next();
   		
   			Passenger mypass = new Passenger(userName,password,id);
   			passengers.add(mypass);
   		}
   		
   	     input1.close();
   	 
   	Scanner input=new Scanner(System.in);
   	
   	System.out.println("Please enter your user Name: ");
   	String userNameLI=input.nextLine();
   	System.out.println("Please enter your password ");
   	String userpasswordLI=input.nextLine();
   	
   	int i;
   	boolean flag=false;
   	//if they are the same
   	for(i=0;i<passengers.size();i++){
     	   if(passengers.get(i).getUserName().equals(userNameLI)&&passengers.get(i).getPassword().equals(userpasswordLI)){
     			   System.out.println("Succeed!");
     			   thisPass=PassengerState.RIGHT;
     			   flag=true;
     	       }
   	}
   	if (flag==false)
   		System.out.println("Don't exist!");
   	
   	Formatter output=new Formatter("admins.txt");
   	for (i=0;i<passengers.size();i++){
	  			output.format("%s\t%s\n", passengers.get(i).getUserName(),passengers.get(i).getPassword());
          }
          output.close();
    }

    
	    public static void checkPassword(String Password1, String Password2) {
		        Scanner input = new Scanner(System.in);
		        if (Password1.equals(Password2)){
		           System.out.println("The passwords are the same.");
		           a=true;
		        } 
		        else{
		           a=false;	
		        } 
		        input.close();
		        }
	    
	  public void userManagement() throws FileNotFoundException{
	  		
	  		System.out.println("If you want to add new passenger, please enter 1"
	  				+"If you want to refresh your information, please enter 2");
	  		
	  		Scanner input=new Scanner(System.in);
	  		int n=input.nextInt();
	  		
	  		switch(n){
	  		case 1:
	  		
	  		System.out.println("Please enter the user's name");
	  		String userName1=input.next();
	  		System.out.println("Please enter the user's password");
	  		//enter for two times
	  		String password1=input.next();
	  		
	  	    System.out.println("Please enter the password again.");
	  	    String verifyPassword=input.next();
	  	    
	  	    Admin.checkPassword(password1, verifyPassword);
	  	    
	  	    if (a==true){
	 
	    //add a new passenger

	Scanner input1 = new Scanner(new File("passengers.txt"));
  	while(input1.hasNext()){
  		userName = input1.next();
  		password = input1.next();
  		id = input1.next();
  		Passenger mypassenger = new Passenger(userName,password,id);
  		passengers.add(mypassenger);
  	}

 input1.close(); 
System.out.println("You have successfully added a new passenger");


 Formatter output=new Formatter("passengers.txt");
 
		String userName2=userName1;
		String password2=password1;
		String id2=id1;
		
		Passenger newpassengers= new Passenger(userName2,password2,id2);
		passengers.add(newpassengers);
		
	    
  for (int i=0;i<passengers.size();i++){
		output.format("%s\t%s\t%s\n", 
				passengers.get(i).getUserName(),passengers.get(i).getPassword(),passengers.get(i).getPassengerID());
  }

  output.close();
  

  }
  

  
  if (a==false){
  System.out.println("not same again");
  }
  
  break;

	case 2:
	updatePassenger();
	break;
	
  default: break;
	}
}


//update information (username/password)
public void updatePassenger() throws FileNotFoundException{

	Scanner input=new Scanner(System.in);

	//先遍历查找
	
	System.out.println("If you want to update your username, please enter 1");
	System.out.println("If you want to update your password, please enter 2");
	
	int n=input.nextInt();
	
	// read the file, create the new arraylist
	ArrayList<Passenger> passengers=new ArrayList<Passenger>();
	
	String userName;
	String password;

	Scanner input1 = new Scanner(new File("passengers.txt"));

	while(input1.hasNext()){
		userName = input1.next();
		password = input1.next();
		id = input.next();
		Passenger mypassenger = new Passenger(userName,password,id);
		passengers.add(mypassenger);
	}
	
	input1.close();
	
	//let the passenger input his username and password to check if he is himself
	
	System.out.println("Please enter your name");
	String userName1=input.next();
	int i;
	 for(i=0;i<passengers.size();i++){
		 if(passengers.get(i).getUserName().equals(userName1)){
		  	
		  
			 break;
		  }   
	 }
	
	 System.out.println("Please enter your password");
		   String password1=input.next(); 
			 
		  if (passengers.get(i).getPassword().equals(password1)){
			 thisPass=PassengerState.RIGHT;
			}
	
	else{
	  int chance;
	  for(chance=3;chance>0;chance--){
	      System.out.printf("Your password is not right, please try again, you only have %s chances left.",chance);
	   password1=input.next();
	      if(passengers.get(i).getPassword().equals(password1)){
	      thisPass=PassengerState.RIGHT;
	      break;
	      }
	   }
	}
	
	if (thisPass==PassengerState.RIGHT){
	
	Formatter output=new Formatter("passengers.txt");
	
	switch(n){
	//改名字
	case 1:
		System.out.println("Please enter your new username");
		String newName=input.next();
		Passenger a=new Passenger(newName, passengers.get(i).getPassword(),passengers.get(i).getPassengerID());
		passengers.set(i, a);
		System.out.println("Now your new name is "+passengers.get(i).getUserName());
		 for (i=0;i<passengers.size();i++){
				output.format("%s\t%s\t%d\n", 
						passengers.get(i).getUserName(),passengers.get(i).getPassword(),passengers.get(i).id);
	   }
	   output.close();
		
		break;
	//改密码
	case 2:        		
		System.out.println("Please enter your new password");
	  String newPassword=input.next();
	  Passenger b=new Passenger(passengers.get(i).getUserName(),newPassword,passengers.get(i).getPassengerID());
	  passengers.set(i, b);
	  System.out.println("Now your new password is "+passengers.get(i).getPassword());
	  for (i=0;i<passengers.size();i++){
				output.format("%s\t%s\t%d\n", 
						passengers.get(i).getUserName(),passengers.get(i).getPassword(),passengers.get(i).getPassengerID());
	   }
	   output.close();
			
	 	break;
	}
	}
	
	
	if (thisPass==PassengerState.WRONG)   		
		System.out.println("Sorry, your identity cannot be verified!");	
	}
	

	public void setUserName(String userName){
		this.userName=userName;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public void setPassengerID(String passengerID){
		this.id=passengerID;
	}
	
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getPassengerID(){
		return id;
	}

	
	
	//预定航班
	public static void reserveFlight(String flightID){
		Scanner input = new Scanner(System.in);
		//判断航班状态
		if (Flight.getStatus().equals("AVAILABLE")) {
			//支付
			System.out.println("Do you want to pay for it? Y or N");
			String ifPay = input.next();
			if (ifPay.equals("Y")) {
				System.out.println("You have paid successfully!");
				Order.setThisOrder(OrderStatus.PAID);
				if (Flight.flights.get(8).getSeatCapacity()>0){
					//判断人数是否FULL
					Flight.flights.get(8).setSeatCapacity(Flight.flights.get(8).getSeatCapacity()-1);
					//seatCapacity-1
					Order.setOrder(Order.getID(),Flight.getFlightID(), Flight.getStartTime(),Flight.getArrivalTime(),
							Flight.getStartCity(),Flight.getArrivalCity(),Flight.getDepartureDate(),
							Flight.getPrice(),Order.getCreateDate());			}
			else if (ifPay.equals("N"))
				System.out.println("You have not paid!");
			
				
		}else if (Flight.getStatus().equals("FULL")){//capacity==0
			System.out.println("This flight is FULL, please try another!");
			System.out.println("Please enter the new flight ID: ");
			String id=input.next();
			reserveFlight(id);
		}
	}
	}
	
	public static void unsubscribeFlight(String flightID,String passengerID) throws FileNotFoundException{
		System.out.println("Do you want to unsubscribe it? Y or N");
		Scanner input = new Scanner(System.in);
		String ifBack = input.next();
		//give the money back
		if (ifBack.equals("Y")) {
			System.out.println("You have unsubscribed it successfully!");
			Order.setThisOrder(OrderStatus.CANCEL);
			Flight.flights.get(8).setSeatCapacity(Flight.flights.get(8).getSeatCapacity()+1);
			Order.deleteOrder(flightID, passengerID);
		}
	}
	
		
		
	   public void queryOrder(String PassengerID){
		   if (thisPass==PassengerState.RIGHT){
	  		 System.out.printf("Here are the orders of %s:\n",PassengerID);
	  		 int i;
	  		 for (i=0;i<Order.orders.size();i++){
	  			 if(Order.orders.get(i).getID().equals(PassengerID)){
	  				System.out.printf("FlightID: %s  Price: %s  Status: %s\n",
	  						Flight.flights.get(i).getFlightID(),Flight.flights.get(i).getPrice(),Flight.flights.get(i).getStatus());	
	  				 //print all！！！
	  			 }
	  		 }
	  	 }
	}
	   public static void userLogIn() throws FileNotFoundException{
	    	 
	    	 Scanner input1 = new Scanner(new File("passengers.txt"));

	    		while(input1.hasNext()){
	    			String userName = input1.next();
	    			String password = input1.next();
	    			String id = input1.next();
	    			Passenger mypass = new Passenger(userName,password,id);
	    			passengers.add(mypass);
	    		}
	    	     input1.close();
	    	 
	    	Scanner input=new Scanner(System.in);
	    	
	    	System.out.println("Please enter your user Name: ");
	    	String userNameLI=input.nextLine();
	    	System.out.println("Please enter your password ");
	    	String userpasswordLI=input.nextLine();
	    
	    	int i;
	    	boolean flag=false;
	    	for(i=0;i<passengers.size();i++){
	      	   if(passengers.get(i).getUserName().equals(userNameLI)&&passengers.get(i).getPassword().equals(userpasswordLI)){
	      			   System.out.println("Succeed!");
	      			   thisPass=PassengerState.RIGHT;
	      			   flag=true;
	      	       }
	    	}
	    	if (flag==false)
	    		System.out.println("Don't exist!");
	    	
	    	Formatter output=new Formatter("passengers.txt");
	    	for (i=0;i<passengers.size();i++){
 	  			output.format("%s\t%s\t%s\n", passengers.get(i).getUserName(),passengers.get(i).getPassword(),passengers.get(i).getPassengerID());
             }
             output.close();
	     }
	   
}