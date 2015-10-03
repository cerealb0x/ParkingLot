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
	public boolean checkForAvailableSpace(){
		System.out.println("Lot: There are currently " + availableSpaces + " available parking in the lot \n");
		if(availableSpaces > 0){
			unlockEntries();
			parkCar();
			return true;
		}else{
			lockEntries();
			return false;
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
		System.out.println("Lot: The entries are unlocked \n");
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
		System.out.println("Lot: The entries are locked \n");

	}
	
	/**
	 * Decrements the amount of available spaces, done so
	 * whenever a car is parked
	 */
	public void parkCar(){
		
		this.availableSpaces--;
	//	checkForAvailableSpace();
				
	}
	
	/**
	 * Increments the amount of available spaces, done so 
	 * whenever a car exits
	 */
	public void freeUpSpace(){
		this.availableSpaces++;
		//unlock entries should be called here
		//checkForAvailableSpace();

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
