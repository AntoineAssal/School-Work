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
	private Chopstick[] chopsticks;
	private int philos;
	private boolean talking;

	private class Chopstick {
		public boolean free;
		public int last;

		public Chopstick() {
			free = true;
			last = -1;
		}

		public boolean isWith(final int piTID) {
			return !free && last == piTID;
		}

		public boolean has(final int piTID) {
			return !free && piTID != last;
		}

		public void pickUP(final int piTID) {
			free = false;
			last = piTID;
		}

		public void release() {
			free = true;
		}
	}

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers) {
		philos = piNumberOfPhilosophers;
		chopsticks = new Chopstick[philos];
		talking = false;
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
	public synchronized void pickUp(final int piTID) throws InterruptedException {
		Chopstick left = chopsticks[piTID-1];
		Chopstick right = chopsticks[piTID%philos];		
		while (true) {
			if (left.has(piTID) || right.has(piTID)){
				if (left.free && left.last!=piTID){
					left.pickUP(piTID);
				}
				if(right.free && right.last != piTID){
					right.pickUP(piTID);
				}
				wait();
			}
			else{
				left.pickUP(piTID);
				right.pickUP(piTID);
			}
		}

	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down and
	 * let others know they are available.
	 */
	public synchronized void putDown(final int piTID) {
		chopsticks[piTID - 1].release();
		chopsticks[piTID % philos].release();
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy (while she is not
	 * eating).
	 */
	public synchronized void requestTalk() throws InterruptedException {
		while (true) {
			if (talking) {
				wait();
			} else {
				talking = true;
				break;
			}
		}
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
