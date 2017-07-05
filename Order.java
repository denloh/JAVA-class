package flightSystem2;
import java.util.*;

import flightSystem2.Admin.AdminState;

import java.io.*;

public class Order {
	public static ArrayList<Order>orders=new ArrayList<Order>();
	public  String passengerID;
	public Flight flight;
	public Date createDate = new Date();
	
	
    public enum OrderStatus{UNPAID,PAID,CANCEL};
    private static OrderStatus thisOrder=OrderStatus.UNPAID;
    
	public Order(String passengerID,String FlightID, String startTime,String arrivalTime,
			String startCity,String arrivalCity,String departureDate,int price, Date createDate) {
		passengerID=this.passengerID;
		FlightID = flight.getFlightID();
		startTime = flight.getStartTime();
		arrivalTime = flight.getArrivalTime();
		startCity = flight.getStartCity();
		arrivalCity = flight.getArrivalCity();
		departureDate = flight.getDepartureDate();
		createDate = new Date();
		}
	
	   
public String getID() {
		return passengerID;
	}


	public void setID(String passengerID) {
		this.passengerID = passengerID;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public static OrderStatus getThisOrder() {
		return thisOrder;
	}


	public static void setThisOrder(OrderStatus thisOrder) {
		Order.thisOrder = thisOrder;
	}

public static void setOrder(String passengerID,Flight flight, Date createDate) throws FileNotFoundException{
	// open the text file for reading with a Scanner
	Scanner input = new Scanner( new File("orders.txt"));
	while (input.hasNext())
	{
		Order theOrder = new Order(passengerID,flight.getFlightID(),flight.getStartTime(),flight.getArrivalTime(),
				flight.getStartCity(),flight.getArrivalCity(),flight.getDepartureDate(),flight.getPrice(),createDate);
		orders.add(theOrder);			
	} // end while
	
	input.close();
	
	for (int i =0; i < orders.size(); i++ )
	  {
		System.out.printf("%-10d%10s\n",
				orders.get(i).getID(), /*     */orders.get(i).getCreateDate());
       }
		
	Formatter output = new Formatter("orders.txt"); // loop until end-of-file indicator
	Scanner input2 = new Scanner(System.in);
	
	while(input2.hasNext())
	{
	
			Order theOrder = new Order(passengerID,flight.getFlightID(),flight.getStartTime(),flight.getArrivalTime(),
					flight.getStartCity(),flight.getArrivalCity(),flight.getDepartureDate(),flight.getPrice(),createDate);
			orders.add(theOrder);
	}
		
	}
	

	public String toString(){
		return String.format("%-10s%-14s%-14s%-14s%-14s%-14s%-8d%-4d%10d\n",
					getID(),flight.getFlightID(), flight.getStartTime(),flight.getArrivalTime(),
					flight.getStartCity(),flight.getArrivalCity(),flight.getDepartureDate(),
					flight.getPrice(),getCreateDate());
	}
	
//displayOrder=queryOrder锛侊紒锛�
	
	
	
//write in the file
       writeFile(neworders);
	   
//close the scanner
       input2.close();
}//end the createFlight method
	
	
	//write an ArrayList into the file
public static void writeFile(ArrayList<Order> orders) throws FileNotFoundException
{
	Formatter output = new Formatter("orders.txt");
	for( int i = 0; i < orders.size(); i++ )
	{
		output.format("\n%-10s%-14s%-14s%-14s%-14s%-14s%-8d%4s\n",
				orders.get(i).getID(), orders.get(i).getFlight().getFlightID(),
				orders.get(i).getFlight().getStartTime(),orders.get(i).getFlight().getArrivalTime()
				,orders.get(i).getFlight().getStartCity(),orders.get(i).getFlight().getArrivalCity()
				,orders.get(i).getFlight().getPrice(),/**/orders.get(i).getCreateDate());
	}
	
	output.close();
}
	

//read from the file and add them into an ArrayList
//return the new ArrayList
	public static ArrayList<Order> readFile() throws FileNotFoundException
	{ 		
		ArrayList<Order>orders=new ArrayList<Order>();
		String passengerID;
		String FlightID;
		String startTime;
		String arrivalTime;
		String startCity;
		String arrivalCity;
		String departureDate;
		Date createDate;
		int price;
		Scanner input=new Scanner(new File("orders.txt"));
		
		while(input.hasNext()){
			passengerID = input.next();
			FlightID=input.next();
			startTime=input.next();
			arrivalTime=input.next();
			startCity=input.next();
			arrivalCity=input.next();
			departureDate=input.next();
			price=input.nextInt();
			createDate = new Date();
			Order neworder = new Order(passengerID,FlightID, startTime,arrivalTime,startCity,arrivalCity,departureDate,price,createDate);
			orders.add(neworder);	
			
		}
		input.close();
		return orders;
	}



public static void displayOrderFile()throws Exception
{
    ArrayList<Order>orders=new ArrayList<Order>();		
	Scanner input = new Scanner( new File("orders.txt"));
		String passengerID;
		String FlightID;
		String startTime;
		String arrivalTime;
		String startCity;
		String arrivalCity;
		String departureDate;
		Date createDate;
		int price;
		System.out.printf("%-20s%-10s%-14s%-14s%-14s%-14s%-14s%-8d%4s\n",
				"passengerID","FlightID", "startTime","arrivalTime","startcity","arrivalcity","departureDate","price","createDate");
			
			while (input.hasNext())
			{
				passengerID = input.next();
				FlightID=input.next();
				startTime=input.next();
				arrivalTime=input.next();
				startCity=input.next();
				arrivalCity=input.next();
				departureDate=input.next();
				price=input.nextInt();
				createDate = new Date();
				System.out.printf("%-20s%-10s%-14s%-14s%-14s%-14s%-14s%-8d%4s\n",
						passengerID,FlightID, startTime,arrivalTime,startCity,arrivalCity,departureDate,price,createDate);
				
				Order neworder = new Order(passengerID,FlightID, startTime,arrivalTime,startCity,arrivalCity,departureDate,price,createDate);
				orders.add(neworder);		
			} 
			
			input.close();
}
	 
		 
	public static void deleteOrder(String passengerID, String FlightID) throws FileNotFoundException
	{
		ArrayList<Order>orders=readFile();
			for(int i=0;i<orders.size();i++)
			{
			   if(orders.get(i).flight.getFlightID().equals(FlightID)&&orders.get(i).getID().equals(passengerID))
				  {
				     orders.remove(i);
				      break;//jump put from for loop
			       }
				  
			   }
			writeFile(orders);
	}
		 
		 
		 
	/*	 
	public String toString(int i){
		return String.format();//!!!
		
	}*/

}
