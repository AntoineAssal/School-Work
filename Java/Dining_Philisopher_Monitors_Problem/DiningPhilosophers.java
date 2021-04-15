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

		Scanner keyboard = new Scanner(System.in);
		int iPhilosophers = 0;
		String userInput = "";

		System.out.println("How many philisophers do we want on the table");

		try {
			userInput = keyboard.nextLine();
			iPhilosophers = Integer.parseInt(userInput);

			if (iPhilosophers < 1)
				throw new Exception();
		}

		catch (Exception e) {
			System.out.println(iPhilosophers + " is not a valid positive decimal integer, using default number.");
			iPhilosophers = DEFAULT_NUMBER_OF_PHILOSOPHERS;
		}

		try {
			soMonitor = new Monitor(iPhilosophers);
			Philosopher allPhilosophers[] = new Philosopher[iPhilosophers];
			// sit em down
			for (int i = 0; i < iPhilosophers; i++) {
				allPhilosophers[i] = new Philosopher();
				allPhilosophers[i].start();
			}

			System.out.println(iPhilosophers + " philosophers came in for dinner today");

			for (int i = 0; i < iPhilosophers; i++) {
				allPhilosophers[i].join();
				System.out.println("All philosophers have left. System terminates normally");
			}

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
