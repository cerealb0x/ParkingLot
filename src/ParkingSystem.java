import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class ParkingSystem {

	

	public static void main(String [] args){
	
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Lot parkingLot = new Lot(1,3,3);

		for(int i = 0; i < 3; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 3; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for(int j = 0; j<5; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
}
