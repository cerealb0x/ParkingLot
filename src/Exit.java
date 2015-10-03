
/**
 * The Exit class represents the exit gates of the parking lot. These gates will notify the
 * parking lot of any cars that may be exiting.
 *
 */

public class Exit {

	private String exitID;
	private Lot parkingLot;
	
	
	
	public Exit(String id, Lot parkingLot){
		this.exitID = id;
		this.parkingLot = parkingLot;
		
	}
	
	/**
	 * Signals the parking lot of a car exiting the lot
	 */
	public boolean notifyLotOfExitingCar(){
		synchronized(parkingLot){
			return parkingLot.checkForCarsExiting();
		}
	}
	
}
