import java.util.ArrayList;


public class Exit {

	private String exitID;
	private Lot parkingLot;
	private boolean carWaiting;
	private ArrayList<Car> carQueue;
	
	
	
	public Exit(String id, Lot parkingLot){
		this.exitID = id;
		this.parkingLot = parkingLot;
		this.carWaiting = true;
		this.carQueue = new ArrayList<Car>();

		
		System.out.println(this.exitID + " has been created");
	}
	
	/**
	 * Signals the parking lot of a car exiting the lot
	 */
	public void notifyLotOfExitingCar(){
		parkingLot.freeUpSpace();
	}
	
}
