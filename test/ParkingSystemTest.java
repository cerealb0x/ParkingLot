import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;


public class ParkingSystemTest {

	
	@Test
	public void checkForNoSpaceTest(){
		Lot parkingLot = new Lot(0,2,2);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
		
		parkingLot.checkForAvailableSpace();
		assertTrue(entry.isLocked());
	}
	
	@Test
	public void checkForAvailableSpaceTest(){
		
		Lot parkingLot = new Lot(2,2,2);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
			
		parkingLot.checkForAvailableSpace();
		assertFalse(entry.isLocked());
		
	}
	
	@Test
	public void parkingCausesSpaceDecrementTest(){
		Lot parkingLot = new Lot(100,1,1);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
		
		parkingLot.parkCar();
		assertTrue(parkingLot.getAvailableSpaces() == 99);
	}
	
	@Test
	public void exitingCausesSpaceIncrementTest(){
		Lot parkingLot = new Lot(1000,1,1);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
		
		parkingLot.freeUpSpace();
		assertTrue(parkingLot.getAvailableSpaces() == 1001);
	}
	
	@Test
	public void oneSpaceAndTwoCarsTest(){
		ParkingSystem ps = new ParkingSystem();
		ps.testStart(1,2,2,2);
		ps.main(null);
	}
	
	/*
	@Test
	public void equalExitsAndEntriesTest(){
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Lot parkingLot = new Lot(8,3,3);

		for(int i = 0; i < 3; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 3; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for(int j = 0; j<10; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
	@Test
	public void singleAvailableSpaceTest(){
		
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
		for(int j = 0; j<10; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
		
	}*/

}
