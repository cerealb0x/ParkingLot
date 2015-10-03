import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class ParkingSystem {

	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<Exit> exits = new ArrayList<Exit>();
	private Lot parkingLot;
	private int numOfSpaces;
	private int numOfEntries;
	private int numOfExits;
	private int numOfCars;
	
	
	public void testStart(int numOfSpaces, int numOfEntries, int numOfExits, int numOfCars) throws ExecutionException, InterruptedException{
		this.numOfSpaces = numOfSpaces;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
		this.numOfCars = numOfCars;
		parkingLot = new Lot(numOfSpaces);
		
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
		    scheduledExecutorService1.scheduleAtFixedRate(new Car(entries, exits, 0), 0, 2, TimeUnit.SECONDS);
		
		ScheduledFuture<?> scheduledFuture2 =
			    scheduledExecutorService2.scheduleAtFixedRate(new Car(entries, exits, 1), 4, 4, TimeUnit.SECONDS);


		scheduledFuture1.get();
		scheduledFuture2.get();
		scheduledExecutorService1.shutdown();
		scheduledExecutorService2.shutdown();
	
	}
	
	public static void main(String [] args) throws InterruptedException, ExecutionException{
	
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Lot parkingLot = new Lot(2);

		for(int i = 0; i < 100; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 25; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);


		ScheduledExecutorService scheduledExecutorService1 =
		        Executors.newScheduledThreadPool(100); 
		ScheduledExecutorService scheduledExecutorService2 =
		        Executors.newScheduledThreadPool(25);

		ScheduledFuture<?> scheduledFuture1 =
		    scheduledExecutorService1.scheduleWithFixedDelay(new Car(entries, exits, 0), 0, 5, TimeUnit.SECONDS);
		
		ScheduledFuture<?> scheduledFuture2 =
			    scheduledExecutorService2.scheduleWithFixedDelay(new Car(entries, exits, 1), 0, 1, TimeUnit.SECONDS);

					
		scheduledFuture1.get();
		scheduledFuture2.get();
		scheduledExecutorService1.shutdown();
		scheduledExecutorService2.shutdown();
	}
	
}
