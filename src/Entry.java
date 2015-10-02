import java.util.ArrayList;


public class Entry{
	
	
	private String entryID;
	private Lot parkingLot;
	private boolean carWaiting;
	private boolean locked;
	private ArrayList<Car> carQueue;
	
	public Entry(String id, Lot parkingLot){
		this.parkingLot = parkingLot;
		this.entryID = id;
		this.carWaiting = true;
		this.locked = false;
		this.carQueue = new ArrayList<Car>();
		
	
		
		System.out.println(this.entryID + " has been created");
	}

	
	
	public String getEntryID() {
		return entryID;
	}



	public void setEntryID(String entryID) {
		this.entryID = entryID;
	}


	
	synchronized void checkLotCapacity(){
			
		parkingLot.checkForAvailableSpace();
				
	}
	
	public void notifyLotOfParkedCar(){
		parkingLot.parkCar();
	}

	public boolean hasCarWaiting() {
		return carWaiting;
	}

	public void setCarWaiting(boolean carWaiting) {
		this.carWaiting = carWaiting;
	}
	
	

	public ArrayList<Car> getCarQueue() {
		return carQueue;
	}

	public void setCarQueue(ArrayList<Car> carQueue) {
		this.carQueue = carQueue;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
	
		this.locked = locked;
	}
	
	
}
