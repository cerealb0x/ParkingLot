import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class ParkingSystem {

	public ArrayList<Entry> entries = new ArrayList<Entry>();
	public ArrayList<Exit> exits = new ArrayList<Exit>();
	public Lot parkingLot;
	public int numOfSpaces;
	public int numOfEntries;
	public int numOfExits;
	
	public void testStart(int numOfSpaces, int numOfEntries, int numOfExits){
		this.numOfSpaces = numOfSpaces;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
		parkingLot = new Lot(numOfSpaces, numOfEntries, numOfExits);
		
		for(int i = 0; i < 3; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 3; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for(int j = 0; j<8; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
	public static void main(String [] args){
	
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Lot parkingLot = new Lot(1,3,5);

		for(int i = 0; i < 3; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 5; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for(int j = 0; j<8; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
}
