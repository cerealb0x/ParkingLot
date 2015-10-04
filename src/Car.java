import java.util.ArrayList;
import java.util.Random;

/**
 * This is the car class, which represents a car object that will be
 * parking in the parking lot - each car will be run as an individual
 * thread, and so we implement the Runnable interface 
*/
public class Car implements Runnable{
	
	private static int carCount;
	private Thread t;
	private ArrayList<Entry> entries;	//a list of all the entries in the parking lot
	private Entry entry;				//the entry that this car will use
	private ArrayList<Exit> exits;		//a list of all the exits in the parking lot
	private Exit exit;					//the exit that this car will use
	private String carID;				//a String ID used to distinguish this car object
	private int status;
	
	/**
	 * Car object constructor
	 * @param entries list for this lot, exits list for this lot, 
	 * 		  status of car (entering or exiting)
	 */
	public Car(ArrayList<Entry> entries, ArrayList<Exit> exits, int status){
		this.entries = entries;
		this.exits = exits;
		this.status = status;
	}

	public static int getCarCount() {
		return carCount;
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
	 * Signals the entry gate that this car has arrived at it
	 * @return the entry gate will return a boolean value
	 * determining whether or not this car got permission to enter
	 */
	public boolean notifyEntry(){
		return entry.checkLotCapacity();
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
	
		if(this.status == 0){
			//select an entrace
			this.setEntry(entries.get(selectEntry(entries.size())));
			
			System.out.print("Car: A car has arrived at "+ entry.getEntryID() + "\n");
            //let the entry know that this car has arrived
            boolean entryGranted = notifyEntry();           
        
            
            //if the entry is locked, the lot is full and the car drives away
            if(!entryGranted){
            	System.out.println("Car: A car has turned around \n");
            } else{   
            //otherwise, the car gains entry and parks in the lot	
            	System.out.print("Car: A car will now park \n");
                //park for a while
    		
            }    	      	
		}else{
			
			this.setExit(exits.get(selectExit(exits.size())));
			unparkCar();

			
			
		}
		
	}
	
	
}
