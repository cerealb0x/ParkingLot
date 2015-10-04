import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

import java.util.Scanner;

/**
 * 
 * jUnit tests for the parking system and its classes
 *
 */

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
	
	/**
	 * 
	 * Main method to test various values for the parking lot (entry count, exit count,etc.)
	 * as well as the general behaviour of the system
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	
	public static void main(String [] args) throws ExecutionException, InterruptedException{
		String scenario1 = "(1) : 1 parking space, 1 entry, 1 exit, 2s delay between entries, exits after 3s w/ 2s delays";
		String scenario2 = "(2) : 10 parking spaces, 5 entries, 3 entries, 2s delay b/w entries, exits after 20s w/ 5s delays";
		String scenario3 = "(3) : 50 parking spaces, 20 entries, 10 entries, entries after 5s w/ 10s delays, exits after 50s w/ 2s delays";
		String scenario4 = "(4) : Entries locked for a long time";
		
		ParkingSystem ps = new ParkingSystem();
		int selectedScenario = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a Parking Lot System Scenario: ");
		System.out.println(scenario1);
		System.out.println(scenario2);
		System.out.println(scenario3);
		System.out.println(scenario4);
		
        if (sc.hasNextInt()) {
          selectedScenario = sc.nextInt();
        }

        
        switch(selectedScenario){
        	//parameters: num of parking spaces, num of entries, num of exits, time delay before the first car comes,
        	//			  time delay between entering cars, time delay before the first car exits, time delay between exits
        	case 1: ps.startSystem(1, 1, 1, 0, 2, 3, 2);
        			break;
        	case 2: ps.startSystem(10, 5, 3, 0, 2, 20, 5);
        			break;
        	case 3: ps.startSystem(50, 20, 10, 5, 10, 50, 2);
        			break;
        			
        	case 4:  ps.startSystem(10, 10, 10, 0, 2, 1000, 1);
        			break;
        	default:
        			System.out.println("You entered an invalid input");
        
        }
        
	}
	
}
