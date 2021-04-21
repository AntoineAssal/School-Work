import java.util.Scanner;


public class DiningPhilosophers {
    /*
     * ------------ Data members ------------
     */

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

    /*
     * ------- Methods -------
     */

    /**
     * Main system starts up right here
     */
    public static void main(String[] argv) {

        Scanner userInput = new Scanner(System.in);
        int iPhilosophers = 0;
        String input = "";

        System.out.print("Please Input number of philosopher on the table : ");

        // take user input for custom philosophers
        try {
            input = userInput.nextLine();
            iPhilosophers = Integer.parseInt(input);
            if (iPhilosophers < 1)
                throw new Exception();
        } catch (Exception e) {
            System.out.println(
                    "\n% java DiningPhilosophers " + input + "\n" + input + " is not a positive decimal integer\n"
                            + "Usage: java DiningPhilosophers [" + DEFAULT_NUMBER_OF_PHILOSOPHERS+ "] \n" + "%\n");
            iPhilosophers = DEFAULT_NUMBER_OF_PHILOSOPHERS;
        }

        try {

            // Make the monitor aware of how many philosophers there are
            soMonitor = new Monitor(iPhilosophers);

            // Space for all the philosophers
            Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

            // Let 'em sit down
            for (int j = 0; j < iPhilosophers; j++) {
                aoPhilosophers[j] = new Philosopher();
                aoPhilosophers[j].start();
            }

            System.out.println(iPhilosophers + " philosopher(s) came in for a dinner.");

            // Main waits for all its children to die...
            // I mean, philosophers to finish their dinner.
            for (int j = 0; j < iPhilosophers; j++)
                aoPhilosophers[j].join();

            System.out.println("All philosophers have left. System terminates normally.");
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
