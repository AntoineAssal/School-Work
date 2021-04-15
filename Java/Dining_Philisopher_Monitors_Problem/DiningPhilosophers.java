package Dining_Philisopher_Monitors_Problem;

import java.util.Scanner;

public class DiningPhilosophers {
	/**
	 * This default may be overridden from the command line
	 */
	public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;

	/**
	 * Dining "iterations" per philosopher thread while they are socializing there
	 */
	public static final int DINING_STEPS = 10;

	/**
	 * Our shared monitor for the philosphers to consult
	 */
	public static Monitor soMonitor = null;

	public static void main(String[] argv) {
		try {
			int iPhilosophers = argv.length > 0 ? Integer.parseInt(argv[0]) : DEFAULT_NUMBER_OF_PHILOSOPHERS;

			soMonitor = new Monitor(iPhilosophers);
			Philosopher allPhilos[] = new Philosopher[iPhilosophers];

			for (int i = 0; i < iPhilosophers; i++) {
				allPhilos[i] = new Philosopher();
				allPhilos[i].start();
			}

			System.out.println(iPhilosophers + " philosopher(s) came in for a dinner.");

			for (int i = 0; i < iPhilosophers; i++) {
				allPhilos[i].join();
			}
			
			System.out.println("All philosophers have left. System terminates normally.");

		} catch (NumberFormatException e) {
			System.out.println("\"" + argv[0] + "\" is not a valid positive integer.");
			System.out.println("Usage: java DiningPhilosophers [NUMBER_OF_PHILOSOPHER]");
			System.exit(1);
			return;
		} catch (InterruptedException e) {
			System.err.println("main():");
			reportException(e);
			System.exit(1);
		}
	} // main()

	/**
	 * Outputs exception information to STDERR
	 * 
	 * @param poException Exception object to dump to STDERR
	 */
	public static void reportException(Exception poException) {
		System.err.println("Caught exception : " + poException.getClass().getName());
		System.err.println("Message          : " + poException.getMessage());
		System.err.println("Stack Trace      : ");
		poException.printStackTrace(System.err);
	}
}

// EOF
