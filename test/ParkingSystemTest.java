import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;


public class ParkingSystemTest {

	
	@Test
	public void lockingWhenNoAvailableSpaceTest(){
		System.out.println("locking when no available space test");
		Lot parkingLot = new Lot(0);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
		
		parkingLot.checkForAvailableSpace();
		assertTrue(entry.isLocked());
	}
	
	@Test
	public void defaultUnlockStateTest(){
		System.out.println("unlocking when available space test");
		Lot parkingLot = new Lot(2);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
			
		parkingLot.checkForAvailableSpace();
		assertFalse(entry.isLocked());
		
	}
	
	@Test
	public void unlockAfterLockingTest(){
		System.out.println("unlock after locking test");
		Lot parkingLot = new Lot(1);
		Entry entry = new Entry("Test", parkingLot);
		Exit exit = new Exit("Test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		
		entries.add(entry);
		exits.add(exit);
		parkingLot.setEntries(entries);
		parkingLot.setExits(exits);
		
		entry.checkLotCapacity();
		entry.checkLotCapacity();
		assertTrue(entry.isLocked());
		exit.notifyLotOfExitingCar();
		entry.checkLotCapacity();
		assertFalse(entry.isLocked());
	}
	
	@Test
	public void parkingCausesSpaceDecrementTest(){
		System.out.println("parking causing decrement test");
		Lot parkingLot = new Lot(100);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
		
		entry.checkLotCapacity();
		assertTrue(parkingLot.getAvailableSpaces() == 99);
	}
	
	@Test
	public void exitingCausesSpaceIncrementTest(){
		System.out.println("exiting causing increment test");
		Lot parkingLot = new Lot(1000);
		Entry entry = new Entry("test", parkingLot);
		Exit exit = new Exit("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		ArrayList<Exit> exits = new ArrayList<Exit>();
		exits.add(exit);
		parkingLot.setEntries(entries);
		parkingLot.setExits(exits);
		
		entry.checkLotCapacity();
		entry.checkLotCapacity();
		exit.notifyLotOfExitingCar();
		assertTrue(parkingLot.getAvailableSpaces() == 999);
	}
	
	@Test
	public void emptyParkLotPreventsExitsTest(){
		Lot parkingLot = new Lot(1234);
		Entry entry = new Entry("test", parkingLot);
		Exit exit = new Exit("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		ArrayList<Exit> exits = new ArrayList<Exit>();
		exits.add(exit);
		parkingLot.setEntries(entries);
		parkingLot.setExits(exits);
		
		exit.notifyLotOfExitingCar();
		assertTrue(parkingLot.getAvailableSpaces() == 1234);
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void negativeAvailableSpacesTest(){
		Lot parkingLot = new Lot(-1);
	}
	
	/*
	@Test
	public void oneSpaceAndTwoCarsTest() throws ExecutionException, InterruptedException{
		System.out.println("one space two cars test");
		ParkingSystem ps = new ParkingSystem();
		ps.testStart(1,2,2,2);
		
	}
	
	@Test
	public void manyCarsTest() throws ExecutionException, InterruptedException{
		System.out.println("many cars test");
		ParkingSystem ps = new ParkingSystem();
		ps.testStart(50, 5, 3, 100);
	}
	*/
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
