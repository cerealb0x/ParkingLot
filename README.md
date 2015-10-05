# ParkingLot
OOP Parking Lot Management System

The Design:
The core of the design is the Lot object, which represents the parking lot. The Lot objects keeps track of the number of available parking spaces, as well as contains a list of all entries and exits. The entries and exits are separate objects that notify the parking lot of any incoming or outgoing cars, respectively. The cars are objects that are run as threads in a thread pool, with the pool count being the number of entries and exits. They arrive in certain intervals, and the parking spaces are decremented and incremented accordingly. When the amount of available space reaches 0, the entries are locked. When a car exits, incrementing the space count, the entries are unlocked.


Classes:
Entry - represents an entry gate object
Exit - represents an exit gate object
Lot - represents the parking lot. The lot will contain a list of entries and exits
Car - represents the cars coming in and out of the parking lot.
ParkingSystem - a class that simulates the process of the whole system


Instructions/Explanations:
- Source code for main system is contained in the src folder.
- Unit tests can be ran in the ParkingLotTest.java file in the test folder. A main method is also contained in the test file, that runs the parking system under different scenarios. A manual test can be done by observing the output and the behaviour of the parking lot system.
