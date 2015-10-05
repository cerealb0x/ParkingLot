package parking;

/**
 * The Exit class represents the exit gates of the parking lot. These gates will notify the
 * parking lot of any cars that may be exiting.
 *
 */

public class Exit {

	private String exitID;
	private Lot parkingLot;
	
	/**
	 * Exit object constructor
	 * @param id, parkingLot
	 */
	public Exit(String id, Lot parkingLot){
		this.exitID = id;
		this.parkingLot = parkingLot;
		
	}
	
	/**
	 * Signals the parking lot of a car exiting the lot
	 */
	public void notifyLotOfExitingCar(){
		synchronized(parkingLot){
			parkingLot.checkForCarsExiting();
		}
	}
	
}
