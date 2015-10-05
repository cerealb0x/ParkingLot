package parking;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * 
 * The ParkingSystem class acts as a way to simulate the whole process of this 
 * parking lot management system. It creates car threads that enter and exit
 * at given delays.
 *
 */

public class ParkingSystem {

	private static ArrayList<Entry> entries = new ArrayList<Entry>();
	private static ArrayList<Exit> exits = new ArrayList<Exit>();
	private static int firstCarEntryTime;
	private static int enteringCarsDelay;
	private static int firstCarExitTime;
	private static int exitingCarsDelay;
	private static Lot parkingLot;
	private static int numOfSpaces;
	private static int numOfEntries;
	private static int numOfExits;
	
	/**
	 * ParkingSystem constructor
	 * @param num of parking spaces, num of entry gates, num of exit gates, entry time for first car
	 * @param delay between entries, exit time of first car, delay between exits
	 */
	public ParkingSystem(int numOfSpaces, int numOfEntries, int numOfExits, int firstCarEntryTime, int enteringCarsDelay, int firstCarExitTime, int exitingCarsDelay){
		this.numOfSpaces = numOfSpaces;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
		this.firstCarEntryTime = firstCarEntryTime;
		this.enteringCarsDelay = enteringCarsDelay;
		this.firstCarExitTime = firstCarExitTime;
		this.exitingCarsDelay = exitingCarsDelay;
		parkingLot = new Lot(numOfSpaces);
	}
	
	public static void startSystem() throws ExecutionException, InterruptedException{
	
		//Create the entry gates and link them to the parking lot
		for(int i = 0; i < numOfEntries; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		//Create the exit gates and link them to the parking lot
		for(int k = 0; k < numOfExits; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		//Create a thread pool based on the number of entries and exits for this lot
		ScheduledExecutorService scheduledExecutorService1 =
		        Executors.newScheduledThreadPool(numOfEntries); 
		ScheduledExecutorService scheduledExecutorService2 =
		        Executors.newScheduledThreadPool(numOfExits);

		//Schedule the threads, which will act as Car objects entering and exiting the parking lot
		ScheduledFuture<?> scheduledFuture1 =
		    scheduledExecutorService1.scheduleWithFixedDelay(new Car(entries, exits, 0), firstCarEntryTime, enteringCarsDelay, TimeUnit.SECONDS);
		
		ScheduledFuture<?> scheduledFuture2 =
			    scheduledExecutorService2.scheduleWithFixedDelay(new Car(entries, exits, 1), firstCarExitTime, exitingCarsDelay, TimeUnit.SECONDS);

					
		scheduledFuture1.get();
		scheduledFuture2.get();
		scheduledExecutorService1.shutdown();
		scheduledExecutorService2.shutdown();
	
	}
		
}
