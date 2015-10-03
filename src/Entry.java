import java.util.ArrayList;


public class Entry{
	
	
	private String entryID;
	private Lot parkingLot;
	private boolean locked;
	
	public Entry(String id, Lot parkingLot){
		this.parkingLot = parkingLot;
		this.entryID = id;
		this.locked = false;
		
	}

	/**
	 * Getter method for the entry ID variable
	 * @return the ID of the entry
	 */
	public String getEntryID() {
		return entryID;
	}

	/**
	 * Setter method for the entry ID
	 * @param entryID
	 */
	public void setEntryID(String entryID) {
		this.entryID = entryID;
	}

	/**
	 * Signals the parking lot this entry is associated with
	 * to check for available space
	 */
	public boolean checkLotCapacity(){

		synchronized(parkingLot){
	
			return parkingLot.checkForAvailableSpace();
		}
	}
	
	/**
	 * Signals the parking lot of a car that has been
	 * allowed in and is about to park
	 */
	public void notifyLotOfParkedCar(){
		
		synchronized(parkingLot){
			parkingLot.parkCar();
		}
	}


	/**
	 * Getter method for the locked variable
	 * @return a boolean value that determines whether the entry is locked or not
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Setter method for the locked variable
	 * @param locked
	 */
	public void setLocked(boolean locked) {
	
		this.locked = locked;
	}

	public Lot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(Lot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	
	
}
