import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Lot class represents the parking lot. It will contain a list of all entry gates and exit gates
 * associated with it, as well as a count of its current available spaces as well as its total capacity.
 * Entry gates and exit gates will notify the Lot with various pieces of information as cars arrive at them.
 * The Lot object is then in charge of manipulating its number of available spaces as well as locking/unlocking
 * gates
 */

public class Lot {

	private int capacity;
	private int availableSpaces;
	private ArrayList<Entry> entries;
	private ArrayList<Exit> exits;
	
	public Lot(int capacity) throws IllegalArgumentException{
		if(capacity < 0){
			throw new IllegalArgumentException("Negative number of parking spaces is not allowed");
		}
		this.capacity = capacity;
		this.availableSpaces = capacity;
	}

	/**
	 * Getter method for the max capacity of parking spaces in this lot
	 * @return the max number of parking spaces that this lot was initialized with
	 */
	public int getCapacity() {
		return capacity;
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
		System.out.println("Lot: There are currently " + availableSpaces + " available parking spaces in the lot \n");
		//consider returning the number of available space instead so that the Entry could report it
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
	 * The lot checks to see if there are any cars in the parking lot
	 * so that an exit request can be approved
	 * @return true if there is at least 1 car parked in the lot (availableSpaces is not equal to capacity)
	 * 			false if there are no cars parked in the lot (availableSpaces is equal to capacity)
	 */
	public boolean checkForCarsExiting(){
		
		if(availableSpaces != capacity){
			freeUpSpace();
			return true;
		}else{
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
	}
	
	/**
	 * Increments the amount of available spaces, done so 
	 * whenever a car exits
	 */
	public void freeUpSpace(){
		
		this.availableSpaces++;
		System.out.println("Lot: There are currently " + availableSpaces + " available parking spaces in the lot \n");

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
