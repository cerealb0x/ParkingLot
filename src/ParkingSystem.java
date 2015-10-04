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

	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<Exit> exits = new ArrayList<Exit>();
	private int firstCarEntryTime;
	private int enteringCarsDelay;
	private int firstCarExitTime;
	private int exitingCarsDelay;
	private Lot parkingLot;
	private int numOfSpaces;
	private int numOfEntries;
	private int numOfExits;
	
	
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
	
	public void startSystem() throws ExecutionException, InterruptedException{
	
		for(int i = 0; i < numOfEntries; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < numOfExits; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		
		ScheduledExecutorService scheduledExecutorService1 =
		        Executors.newScheduledThreadPool(numOfEntries); 
		ScheduledExecutorService scheduledExecutorService2 =
		        Executors.newScheduledThreadPool(numOfExits);

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
