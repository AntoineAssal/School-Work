import common.BaseThread;

public class Philosopher extends BaseThread
{
    /**
     * Max time an action can take (in milliseconds)
     */
    public static final long TIME_TO_WASTE = 1000;

    /**
     * The act of eating.
     * - Print the fact that a given phil (their TID) has started eating.
     * - yield
     * - Then sleep() for a random interval.
     * - yield
     * - The print that they are done eating.
     */
    public void eat()
    {
        try
        {
            System.out.println("*** "+iTID + ": started eating");
            Philosopher.yield();
            sleep((long)(Math.random() * TIME_TO_WASTE));
            Philosopher.yield();
            System.out.println("*** "+iTID + ": finished eating");
        }
        catch(InterruptedException e)
        {
            System.err.println("Philosopher.eat():");
            DiningPhilosophers.reportException(e);
            System.exit(1);
        }
    }

    /**
     * The act of thinking.
     * - Print the fact that a given phil (their TID) has started thinking.
     * - yield
     * - Then sleep() for a random interval.
     * - yield
     * - The print that they are done thinking.
     */
    public void think()
    {
        try
        {
            System.out.println("*** "+ iTID + ": light bulb went on! ***");
            Philosopher.yield();
            sleep((long)(Math.random() * TIME_TO_WASTE));
            Philosopher.yield();
            System.out.println("*** "+iTID + ": light bulb is no more.");
        }
        catch(InterruptedException e)
        {
            System.err.println("Philosopher.thinking():");
            DiningPhilosophers.reportException(e);
            System.exit(1);
        }
    }

    /**
     * The act of talking.
     * - Print the fact that a given phil (their TID) has started talking.
     * - yield
     * - Say something brilliant at random
     * - yield
     * - The print that they are done talking.
     */
    public void talk()
    {
        try
        {
            System.out.println("*** "+iTID + ": ready to talk");
            Philosopher.yield();
            saySomething();
            sleep((long)(Math.random() * TIME_TO_WASTE));
            Philosopher.yield();
            System.out.println("*** "+iTID + ": done talking.");
        }
        catch(InterruptedException e)
        {
            System.err.println("Philosopher.talking():");
            DiningPhilosophers.reportException(e);
            System.exit(1);
        }
    }

    public void run()
    {
        for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
        {
            DiningPhilosophers.soMonitor.pickUp(getTID());
            eat();
            DiningPhilosophers.soMonitor.putDown(getTID());
            think();
            if((int)(Math.random() * 4) == 1)
            {
                DiningPhilosophers.soMonitor.requestTalk();

                talk();

                DiningPhilosophers.soMonitor.endTalk();

            }

            Philosopher.yield();
        }
    }

    public void saySomething()
    {
        String[] astrPhrases =
                {
                        "If my calculator had a history, it would be more embarrassing than my browser history.",
                        "Instead of colorizing photos, in 50 years we will be removing snapchat filters.",
                        "Nothing is on fire, fire is on things.",
                        "Your stomach thinks that all potatoes are mashed.",
                        "In English, execute and kill have the same meaning, but on a computer, they're opposites."
                };

        System.out.println
                (
                        "Philosopher " + getTID() + " says: " +
                                astrPhrases[(int)(Math.random() * astrPhrases.length)]
                );
    }
}

// EOF
