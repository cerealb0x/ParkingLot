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
	private boolean permanentlyPark;
	
	
	public Car(String carID, ArrayList<Entry> entries, ArrayList<Exit> exits){
		this.carID = carID; 
		this.entries = entries;
		this.entry = entries.get(selectEntry(entries.size()));
		this.exits = exits;
		this.exit = exits.get(selectExit(exits.size()));
		this.permanentlyPark = selectPermanentParkStatus();
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
	
	
	public boolean selectPermanentParkStatus(){
		
		int index;
		boolean boolArr[] = {false, true};
		Random randomizer = new Random();
		index = randomizer.nextInt(2);
		
		
		return boolArr[index];
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
		try{
			
			System.out.print("Car: " + carID + " has arrived at "+ entry.getEntryID() + "\n");
            //let the entry know that this car has arrived
            boolean entryGranted = notifyEntry();           
        
            //if the entry is locked, the lot is full and the car drives away
            if(!entryGranted){
            	System.out.println("Car: " + carID + " turned around \n");
            } else{   
            //otherwise, the car gains entry and parks in the lot	
            	System.out.print("Car: " + carID + " will now park \n");
                //park for a while
    			Thread.sleep(1000);

    			//Check if this car is permanently parking in this lot
    			if(!this.permanentlyPark){
    				System.out.print("Car: " + carID + " will now exit the lot \n");
    				//unpark the car and exit the parking lot
    				unparkCar();
    			}
            }
            
			
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
