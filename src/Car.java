import java.util.ArrayList;
import java.util.Random;

/*This is the car class, which represents a car object that will be
 * parking in the parking lot - each car will be run as an individual
 * thread, and so we implement the Runnable interface*/
public class Car implements Runnable{

	
	private Thread t;
	private ArrayList<Entry> entries;	//a list of all the entries in the parking lot
	private Entry entry;				//the entry that this car will use
	private ArrayList<Exit> exits;		//a list of all the exits in the parking lot
	private Exit exit;					//the exit that this car will use
	private String carID;				//a String ID used to distinguish this car object
	
	
	
	public Car(String carID, ArrayList<Entry> entries, ArrayList<Exit> exits){
		this.carID = carID; 
		this.entries = entries;
		this.entry = entries.get(selectEntry(entries.size()));
		this.exits = exits;
		this.exit = exits.get(selectExit(exits.size()));
	}
	
	/**
	 * Getter method for the Car's entry 
	 * @return the entry that this car has chosen
	 */
	public Entry getEntry() {
		return entry;
	}

	/**
	 * Setter method for the car's entry
	 * @param entry
	 */
	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	/**
	 * Randomly selects one of the entries in the lot
	 * @param numOfEntries
	 * @return an integer index to the entry list
	 */
	public int selectEntry(int numOfEntries){
		
		int index;
		Random randomizer = new Random();
		index = randomizer.nextInt(numOfEntries);
		
		
		return index;
	}
	
	/**
	 * Getter method for the car's chosen exit
	 * @return the car's chosen exit
	 */
	public Exit getExit() {
		return exit;
	}

	/**
	 * Setter method for the car's exit
	 * @param exit
	 */
	public void setExit(Exit exit) {
		this.exit = exit;
	}
	
	/**
	 * Randomly selects an exit for the car
	 * @param numOfExits
	 * @return an integer index to the exit list
	 */
	public int selectExit(int numOfExits){
		int index;
		Random randomizer = new Random();
		index = randomizer.nextInt(numOfExits);
		
		
		return index;
		
	}

	/**
	 * Signals the entry that this car has arrived
	 */
	public void notifyEntry(){
		entry.checkLotCapacity();
	}
	
	/**
	 * Signals the parking lot of this car's intention to park
	 */
	public void parkCar(){
		entry.notifyLotOfParkedCar();
	}
	
	/**
	 * Signals the parking lot of this car
	 * exiting its space and the parking lot
	 */
	public void unparkCar(){
		exit.notifyLotOfExitingCar();
	}
	
	
	/**
	 * The car thread's run function. Simulates the process
	 * of a car arriving at an entry, notifying it of its
	 * arrival, getting permission to enter, parking, unparking,
	 * and finally exiting
	 */
	public void run(){
		try{
            System.out.println(carID + " is entering through " + entry.getEntryID());
			
            //let the entry know that this car has arrived
            notifyEntry();           
        
            //if the entry is locked, wait
            while(entry.isLocked()){
            	Thread.sleep(100);
            }
            
            //once given entry, park the car
            parkCar();
            
            System.out.println(carID + " has parked");
            
            //park for a while
			Thread.sleep(100);

			System.out.println(carID + " is exiting");
			//unpark the car and exit the parking lot
			unparkCar();
			
		}catch(InterruptedException ie){
	        System.out.println(carID +" interrupted.");
		}
		

	}
	
	public void start(){
	     if (t == null)
	      {
	         t = new Thread(this, "car");
	         t.start();
	      }
	}
	
		
	
	
}
