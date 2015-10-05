package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

import parking.Entry;
import parking.Exit;
import parking.Lot;
import parking.ParkingSystem;

import java.util.Scanner;

/**
 * 
 * jUnit tests for the parking system and its classes
 *
 */

public class ParkingSystemTest {

	
	/**
	 * Tests if entries are locked when there are no available spaces
	 * Expected Output: entry.isLocked() == true
	 */
	
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
	
	
	/**
	 * Tests if ALL entries are locked when there are no available spaces
	 * Expected Output: entry1.isLocked() == true, entry2.isLocked() == true
	 */
	
	@Test
	public void multipleEntriesLockTest(){
		Lot parkingLot = new Lot(0);
		Entry entry1 = new Entry("test1", parkingLot);
		Entry entry2 = new Entry("test2", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry1);
		entries.add(entry2);
		parkingLot.setEntries(entries);
		
		entry1.checkLotCapacity();
		entry2.checkLotCapacity();
		assertTrue(entry1.isLocked());
		assertTrue(entry2.isLocked());
	}
	
	/**
	 * Tests to see if ALL entries unlock
	 * Expected Output: entry1.isLocked() == false, entry2.isLocked() == false
	 */
	
	@Test
	public void multipleEntriesUnlockTest(){
		Lot parkingLot = new Lot(2);
		Entry entry1 = new Entry("test1", parkingLot);
		Entry entry2 = new Entry("test2", parkingLot);
		Exit exit1 = new Exit("Test1", parkingLot);
		Exit exit2 = new Exit("Test2", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		entries.add(entry1);
		entries.add(entry2);
		parkingLot.setEntries(entries);
		exits.add(exit1);
		exits.add(exit2);
		parkingLot.setExits(exits);
		
		entry1.checkLotCapacity();
		entry2.checkLotCapacity();
		
		exit1.notifyLotOfExitingCar();
		exit2.notifyLotOfExitingCar();
		
		assertFalse(entry1.isLocked());
		assertFalse(entry2.isLocked());

	}
	
	
	/**
	 * Tests if the entries are unlocked initially
	 * Expected Output: entry.isLocked() == false
	 */
	
	@Test
	public void defaultUnlockStateTest(){
		System.out.println("unlocking when available space test");
		Lot parkingLot = new Lot(2);
		Entry entry = new Entry("test", parkingLot);
		ArrayList<Entry> entries = new ArrayList<Entry>();
		entries.add(entry);
		parkingLot.setEntries(entries);
			
		entry.checkLotCapacity();
		assertFalse(entry.isLocked());
		
	}
	
	/**
	 * Tests if the entry is unlocked after being locked
	 * when space is free again
	 * Expected Output: entry.isLocked() == false;
	 */
	
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
	
	/**
	 * Tests if the number of available parking spaces
	 * is decremented when the entry sends in a car
	 * Expected Output: parkingLot.getAvailableSpaces() == 99;
	 */
	
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
	
	/**
	 * Tests if the number of available spaces is incremented
	 * if an entry signals that a car has exited
	 * ExpectedOutput: parkingLot.getAvailableSpaces() == 999
	 */
	
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
	
	/**
	 * Tests to see if exiting is prevented
	 * when there aren't any cars parked in the lot
	 * Expected Output: parkingLot.getAvailableSpaces() == 1234
	 */
	
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
	
	/**
	 * Tests to see if an IllegalArgumentException is thrown
	 * when a parking lot is initialized with a negative
	 * number of parking spaces
	 */
	
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
		String scenario1 = "(1) : 1 parking space and entry and exits happen at an equal rate";
		String scenario2 = "(2) : 10 parking spaces, 5 entries, 3 entries, 2s delay b/w entries, exits after 20s w/ 5s delays";
		String scenario3 = "(3) : 50 parking spaces, 20 entries, 10 entries, entries after 5s w/ 10s delays, exits after 50s w/ 2s delays";
		String scenario4 = "(4) : Entries locked for a long time";
		String scenario5 = "(5) : No Parking Spaces in Lot";
		ParkingSystem ps;
		
		int selectedScenario = 0;
		
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a Parking Lot System Scenario (Enter the number between the parentheses): ");
		System.out.println(scenario1);
		System.out.println(scenario2);
		System.out.println(scenario3);
		System.out.println(scenario4);
		System.out.println(scenario5);
		
        if (sc.hasNextInt()) {
          selectedScenario = sc.nextInt();
        }

        
        switch(selectedScenario){
        	//parameters: num of parking spaces, num of entries, num of exits, time delay before the first car comes,
        	//			  time delay between entering cars, time delay before the first car exits, time delay between exits
        	case 1: ps = new ParkingSystem(1, 1, 1, 0, 2, 3, 2);
            		ps.startSystem();
        			break;
        	case 2: ps = new ParkingSystem(10, 5, 3, 0, 2, 20, 5);
        			ps.startSystem();
        			break;
        	case 3: ps = new ParkingSystem(50, 20, 10, 5, 10, 50, 2);
        			ps.startSystem();
        			break;
        			
        	case 4: ps = new ParkingSystem(10, 10, 10, 0, 2, 1000, 1); 
        			ps.startSystem();
        			break;
        	case 5: ps = new ParkingSystem(0, 100, 100, 0, 1, 0, 2);
        			ps.startSystem();
        			break;
        	default:
        			System.out.println("You entered an invalid input");
        			
        	
        }
        
	}
	
}
