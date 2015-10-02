import java.util.ArrayList;
import java.util.Iterator;


public class Lot {

	private int capacity;
	private int carsLetThrough;
	private int numOfEntries;
	private int numOfExits;
	private int availableSpaces;
	private ArrayList<Entry> entries;
	private ArrayList<Exit> exits;
	
	public Lot(int capacity, int numOfEntries, int numOfExits){
		this.capacity = capacity;
		this.carsLetThrough = 0;
		this.availableSpaces = capacity;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
	}
	
	/**
	 * Getter method for the available spaces variable
	 * @return number of available spaces in this lot
	 */
	public int getAvailableSpaces() {
		return availableSpaces;
	}
	
	/**
	 * Setter method for the available spaces variable
	 * @param availableSpaces
	 */
	public void setAvailableSpaces(int availableSpaces) {
		this.availableSpaces = availableSpaces;
	}
	
	/**
	 * Checks to see if there is currently any available space
	 * in the parking lot
	 */
	public void checkForAvailableSpace(){
		
		if(availableSpaces > 0){
			
			unlockEntries();
		}else{
			
			lockEntries();
		}
	}
	
	/**
	 * Unlocks all of the entries associated with this lot
	 * (sets their locked values to false)
	 */
	public void unlockEntries(){
		Iterator<Entry> it = entries.iterator();
		while(it.hasNext()){
			it.next().setLocked(false);
		}
		//System.out.println("entries have been unlocked");
	}
	
	/**
	 * Locks all of the entries associated with this lot
	 * (sets their locked values to true)
	 */
	public void lockEntries(){
		Iterator<Entry> it = entries.iterator();
		while(it.hasNext()){
			it.next().setLocked(true);
		}
		//System.out.println("entries have been locked");

	}
	
	/**
	 * Decrements the amount of available spaces, done so
	 * whenever a car is parked
	 */
	public void parkCar(){
		
		this.availableSpaces--;
		checkForAvailableSpace();
		System.out.println("There are "+ availableSpaces + " parking spaces left");
		
		
	}
	
	/**
	 * Increments the amount of available spaces, done so 
	 * whenever a car exits
	 */
	public void freeUpSpace(){
		this.availableSpaces++;
		//unlock entries should be called here
		checkForAvailableSpace();
		System.out.println("There are "+ availableSpaces + " parking spaces left");

	}
	
	/**
	 * Getter method for the list of entries associated with this lot
	 * @return the list of entries associated w/ this lot
	 */
	public ArrayList<Entry> getEntries() {
		return entries;
	}


	/**
	 * Setter method for the list of entries for this lot
	 * @param entries
	 */
	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
	
	
	/**
	 * Getter method for the list of exits associated with this lot
	 * @return the list of exits associated w/ this lot
	 */
	public ArrayList<Exit> getExits() {
		return exits;
	}


	/**
	 * Setter method for the lists of exits for this lot
	 * @param exits
	 */
	public void setExits(ArrayList<Exit> exits) {
		this.exits = exits;
	}
	
}
