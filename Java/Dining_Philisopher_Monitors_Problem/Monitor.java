package Dining_Philisopher_Monitors_Problem;

/**
 * Class Monitor To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor {
	/*
	 * ------------ Data members ------------
	 */
	Chopstick[] chopsticks;
	int philos;
	boolean talking;

	private class Chopstick {
		public boolean holding;
		public int last;

		public Chopstick() {
			holding = false;
			last = 0;
		}

		public boolean lastCheck(final int piTID) {
			return last == piTID;
		}

		public boolean pickCheck(final int piTID) {
			return last != piTID && holding;
		}

		public void pickUP(final int piTID) {
			holding = true;
			last = piTID;
		}

		public void release() {
			holding = false;
		}
	}

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers) {
		philos = piNumberOfPhilosophers;
		chopsticks = new Chopstick[philos];
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Chopstick();
		}
	}

	/*
	 * ------------------------------- User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID) {
		int id = piTID - 1; // dont go out of bound

		while (true) {
			if (chopsticks[id].pickCheck(piTID) || chopsticks[(id + 1) % philos].pickCheck(piTID)) {
				// First or second is picked up, make sure its not same as current then pick it
				// up
				if (!chopsticks[id].holding && !chopsticks[id].lastCheck(piTID)) {
					chopsticks[id].pickUP(piTID);
				} else if (!chopsticks[(id + 1 % philos)].holding && !chopsticks[(id + 1) % philos].lastCheck(piTID)) {
					chopsticks[(id + 1) % philos].pickUP(piTID);
				}
			}

			else {
				chopsticks[id].pickUP(piTID);
				chopsticks[(id + 1) % philos].pickUP(piTID);
				break;
			}

			try {
				wait(); // until notifyAll()
			} catch (InterruptedException e) {
				System.err.println("Monitor.pickUP()");
				DiningPhilosophers.reportException(e);
				System.exit(1);
			}
		}

	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down and
	 * let others know they are available.
	 */
	public synchronized void putDown(final int piTID) {
		chopsticks[piTID - 1].release();
		chopsticks[(piTID) % philos].release();
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy (while she is not
	 * eating).
	 */
	public synchronized void requestTalk() {
		while (talking) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Monitor.requestTalk():");
				DiningPhilosophers.reportException(e);
				System.exit(1);
			}
		}
		talking = true;
	}

	/**
	 * When one philosopher is done talking stuff, others can feel free to start
	 * talking.
	 */
	public synchronized void endTalk() {
		talking = false;
		notifyAll();
	}
}

// EOF
