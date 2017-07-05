package flightSystem2;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import flightSystem2.Admin;
import flightSystem.Passenger;

public class Flight {
	public static enum FlightStatus{TERMINATE,FULL,AVAILABLE,UNPUBLISHED}
	private static FlightStatus status=FlightStatus.UNPUBLISHED;//Initial status
	private String FlightID;
	private String startTime;
	private String arrivalTime;
	private String startCity;
	private String arrivalCity;
	private String departureDate;
	private int price;
	private int seatCapacity;
	private ArrayList<Passenger> passengersID;
	
	public static ArrayList<Flight>flights=new ArrayList<Flight>();
	
	public  Flight(String flightid,String starttime,String arrivaltime,String startcity,String arrivalcity,String departuredate,int price,int seatcapacity){
		FlightID=flightid;
		startTime=starttime;
		arrivalTime=arrivaltime;
		startCity=startcity;
		arrivalCity=arrivalcity;
		departureDate=departuredate;
		this.price=price;
		seatCapacity=seatcapacity;
		status=FlightStatus.UNPUBLISHED;
			
	}
	public static void initializeFlightData() throws FileNotFoundException{
		ArrayList<Flight>flights=new ArrayList<Flight>();
		Flight f1=new Flight("CZ1000","11:10:00","15:45:00","ShenZhen","Beijing","5/24/2017",1999,188);
		Flight f2=new Flight("CZ1001","10:30:00","12:20:00","Beijing","ShenZhen","5/24/2017",880,200);
		Flight f3=new Flight("CZ1002","15:20:00","17:40:00","ShenZhen","ShangHai","5/24/2017",799,132);	
		Flight f4=new Flight("CZ1003","15:20:00","17:40:00","ShangHai","ShenZhen","5/24/2017",799,132);	
		Flight f5=new Flight("CZ1004","15:20:00","17:40:00","ShangHai","Beijing","5/24/2017",799,132);	
		Flight f6=new Flight("CZ1005","15:20:00","17:40:00","Beijing","ShangHai","5/24/2017",799,132);	
		Flight f7=new Flight("CZ1006","15:20:00","17:40:00","ShenZhen","ShangHai","5/25/2017",799,132);	
		Flight f8=new Flight("CZ1007","15:20:00","17:40:00","ShangHai","ShenZhen","5/25/2017",799,132);	
		Flight f9=new Flight("CZ1008","15:20:00","17:40:00","ShenZhen","Beijing","5/25/2017",799,132);	
		Flight f10=new Flight("CZ1009","15:20:00","17:40:00","Beijing","ShenZhen","5/25/2017",799,132);	
		Flight f11=new Flight("CZ1010","15:20:00","17:40:00","ShangHai","Beijing","5/25/2017",799,132);	
		Flight f12=new Flight("CZ1011","15:20:00","17:40:00","Beijing","ShangHai","5/25/2017",799,132);	
		Flight f13=new Flight("CZ1012","15:20:00","17:40:00","ShenZhen","ShangHai","5/26/2017",799,132);	
		Flight f14=new Flight("CZ1013","15:20:00","17:40:00","ShangHai","ShenZhen","5/26/2017",799,132);	
		Flight f15=new Flight("CZ1014","15:20:00","17:40:00","ShenZhen","Beijing","5/26/2017",799,132);	
		Flight f16=new Flight("CZ1015","15:20:00","17:40:00","Beijing","ShenZhen","5/26/2017",799,132);	
		Flight f17=new Flight("CZ1016","15:20:00","17:40:00","ShangHai","Beijing","5/26/2017",799,132);	
		Flight f18=new Flight("CZ1017","15:20:00","17:40:00","Beijing","ShangHai","5/26/2017",799,132);	
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);
		flights.add(f4);
		flights.add(f5);
		flights.add(f6);
		flights.add(f7);
		flights.add(f8);
		flights.add(f9);
		flights.add(f10);
		flights.add(f11);
		flights.add(f12);
		flights.add(f13);
		flights.add(f14);
		flights.add(f15);
		flights.add(f16);
		flights.add(f17);
		flights.add(f18);
		Admin.writeFile(flights);
	}		
	
	public static FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	public String getFlightID() {
		return FlightID;
	}

	public void setFlightID(String flightID) {
		FlightID = flightID;
	}

	public String getStartTime() {
		
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getSeatCapacity() {
		return seatCapacity;
	}


	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
	public void statusUpdate(){
	//transform 2 hours into milliseconds
		int time;
		time=120*60*1000;
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date now=new Date();
		Date afterDate=new Date(now.getTime()+time);	
		Date dt1 = null;
			try {
					dt1 = df.parse(departureDate+" "+startTime);
				} 
			catch (ParseException e) 
			    {
			 		e.printStackTrace();
				}
	//If the plane will take off in less than 2 hours,change the status to terminate
			if(afterDate.getTime()>dt1.getTime())
			   setStatus(FlightStatus.TERMINATE);		
	
	}//end statusUpdate method
	
	public String toString(){
		return String.format("%-10s%-14s%-14s%-14s%-14s%-14s%-8d%4d\n",
					getFlightID(), getStartTime(),getArrivalTime(),
					getStartCity(),getArrivalCity(),getDepartureDate(),
					getPrice(),getSeatCapacity());
	}
	
	public String topartString(){
		return String.format("%-10s%-14s%-14s%-14s%-14s%-14s%-8d\n",
				getFlightID(), getStartTime(),getArrivalTime(),
				getStartCity(),getArrivalCity(),getDepartureDate(),
				getPrice());
	}
	
	
	//模糊查找
		public static void search(String flightID2, ArrayList<Flight> flights2) {
			 ArrayList results = new ArrayList();
			 Pattern pattern = Pattern.compile(flightID2);
			   for(int i=0; i < flights2.size(); i++){
			      Matcher matcher = pattern.matcher(((Flight)flights2.get(i)).getFlightID());
			      if(matcher.find()){
			         results.add(flights2.get(i));
			         System.out.printf("FlightID: %s  Price: %s  Status: %s\n",Flight.flights.get(i).getFlightID(),Flight.flights.get(i).getPrice(),Flight.flights.get(i).status);			      }
			   }
			
		}
	
	
	

}
