public class Monitor
{
    Chopstick[] chops;
    int nb_philos;
    boolean cur_talking;

    private class Chopstick
    {
        public boolean holding_chop;
        public int last_holder;

        public Chopstick()
        {
            holding_chop = false;
            last_holder = 0;
        }

        //verifiies if this philo picked up the chopstick last return true if yes
        public boolean Last_Pick_Check(final int piTID)
        {
            return last_holder == piTID;
        }

        //true if chopstick is picked up by another philosopher
        public boolean Pick_Check(final int piTID)
        {
            return last_holder != piTID && holding_chop;
        }

        //pick up chopstick
        public void pickUp(final int piTID)
        {
            holding_chop = true;
            last_holder = piTID;
        }

        //releases the chopstick
        public void putDown()
        {
            holding_chop = false;
        }
    }

    //monitor constructor creates chopsticks and sets num of philos
    public Monitor(int piNumberOfPhilosophers)
    {
        nb_philos = piNumberOfPhilosophers;
        chops = new Chopstick[nb_philos];

        for(int i = 0; i < chops.length; i++)
        {
            chops[i] = new Chopstick();
        }

    }

    //synchronized so cant pick up chopstick at same time
    //
    public synchronized void pickUp(final int piTID)
    {
        //avoid out of bound
        int id = piTID-1;

        while(true)
        {
            if(chops[id].Pick_Check(piTID) || chops[(id + 1) % nb_philos].Pick_Check(piTID))
            {
                // either 1st or 2nd chopstick is already picked up
                // checks if last chopstick holder is not the same one as now
                // if not then pick up chopstick to avoid same philosopher always eating
                if(!chops[id].holding_chop && !chops[id].Last_Pick_Check(piTID))
                {
                    //picks up first chopstick
                    chops[id].pickUp(piTID);
                }
                //if second chopstick is free and wasnt help by .this thread(philo)
                else if(!chops[(id + 1) % nb_philos].holding_chop && !chops[(id + 1) % nb_philos].Last_Pick_Check(piTID))
                {
                    // picks up second chopstick
                    chops[(id + 1) % nb_philos].pickUp(piTID);
                }
            }
            else
            {
                //pick up both chopstick and break loop since both available
                chops[id].pickUp(piTID);
                chops[(id + 1) % nb_philos].pickUp(piTID);
                break;
            }

            try {
                //waits until notifyAll()
                wait();
            } catch (InterruptedException e) {
                System.err.println("Monitor.pickUp():");
                DiningPhilosophers.reportException(e);
                System.exit(1);
            }
        }
    }

    public synchronized void putDown(final int piTID)
    {
        chops[piTID - 1].putDown();
        chops[(piTID) % nb_philos].putDown();
        //resumes wait()
        notifyAll();
    }

    // since philosophers cant talk at the same time the requestTalk() checks if a philo
    // is currently talking... If one is then puts Thread(philosopher) in a wait() state until freed by endTalk() aka notifyAll()
    public synchronized void requestTalk()
    {
        //puts thread in a waiting state while a philo is currently talking
        while(cur_talking)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Monitor.requestTalk():");
                DiningPhilosophers.reportException(e);
                System.exit(1);
            }
        }

        cur_talking = true;
    }

    public synchronized void endTalk()
    {
        cur_talking = false;
        notifyAll();
    }
}

// EOF
