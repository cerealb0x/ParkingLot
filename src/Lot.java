import java.util.ArrayList;
import java.util.Iterator;


public class Lot {

	private int capacity;
	private int numOfEntries;
	private int numOfExits;
	private int availableSpaces;
	private int occupiedSpaces;
	private ArrayList<Entry> entries;
	private ArrayList<Exit> exits;
	
	public Lot(int capacity, int numOfEntries, int numOfExits){
		this.capacity = capacity;
		this.availableSpaces = capacity;
		this.occupiedSpaces = 0;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
	}
	
	
	public int getAvailableSpaces() {
		return availableSpaces;
	}
	public void setAvailableSpaces(int availableSpaces) {
		this.availableSpaces = availableSpaces;
	}
	
	public void checkForAvailableSpace(){
		if(availableSpaces > 0){
			System.out.println("There is space");
			unlockEntries();
		}else{
			System.out.println("There are "+ availableSpaces + " parking spaces left");
			lockEntries();
		}
	}
	
	public void unlockEntries(){
		Iterator<Entry> it = entries.iterator();
		while(it.hasNext()){
			it.next().setLocked(false);
		}
		System.out.println("entries have been unlocked");
	}
	
	public void lockEntries(){
		Iterator<Entry> it = entries.iterator();
		while(it.hasNext()){
			it.next().setLocked(true);
		}
		System.out.println("entries have been locked");

	}
	
	synchronized void parkCar(){
		
		this.availableSpaces--;
		checkForAvailableSpace();
		System.out.println("There are "+ availableSpaces + " parking spaces left");

	}
	
	synchronized void freeUpSpace(){
		this.availableSpaces++;
		//unlock entries should be called here
		checkForAvailableSpace();
		System.out.println("There are "+ availableSpaces + " parking spaces left");

	}
	
	public int getOccupiedSpaces() {
		return occupiedSpaces;
	}
	public void setOccupiedSpaces(int occupiedSpaces) {
		this.occupiedSpaces = occupiedSpaces;
	}


	public ArrayList<Entry> getEntries() {
		return entries;
	}


	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
	
	
	
	public ArrayList<Exit> getExits() {
		return exits;
	}


	public void setExits(ArrayList<Exit> exits) {
		this.exits = exits;
	}


	public void start(){
		
		
		Iterator<Entry> it = entries.iterator();
		
	
		
		//check each entry
		//if an entry has a car waiting...
		//check if there is space for parking
		//if not, lock that entry
		//if there is space, allow for entry
		
		//then, check exits
		//if a car leaves, free up a space
		
		
	}
	
	
}
