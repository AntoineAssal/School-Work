package CPU_Scheduling_Simulator;
public class SortedListPriorityQueue {
    // My work is modifying the Implementation of a priority queue with a sorted list (page 369) to match assignment instructions.
    private MyArrayList<Job> arrayList = new MyArrayList<>();
    private int terminations = 0;
    private int priorityChanges = 0;


    public Job get(int kek) {
        return arrayList.get(kek);
    }

    public int size() {
        return arrayList.size();
    }
    public int getPriorityChanges(){
        return priorityChanges;
    }
    // Adding a job the beginning of the sublist to simulate FIFO behaviour
    public void add(Job job) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (job.getFinalPriority() < arrayList.get(i).getFinalPriority()) {
                arrayList.add(i, job);
                return;
            }
        }
        arrayList.add(job);
    }
    public int getLastIndexOfPriority(int priority){
        for(int i = 0; i < arrayList.size(); i++){
            if(priority < arrayList.get(i).getJobPriority()){
                return i;
            }
        }
        return arrayList.size();
    }
    // Simulation of executing a process. Decrement the jobLength at index and place it at the end of the list. Avoids starvation.
    public int execute(int kek, int currentTime) {
        Job temp = arrayList.get(kek);
        arrayList.remove(kek);
        int priority = temp.getFinalPriority();
        int position = getLastIndexOfPriority(priority);
        temp.decrementCurrentJobLength();
        // Handling the starvation procedure
        if (temp.getCurrentJobLength()==0){
            terminations++;
            currentTime++;
            // Periodically check (every 30 terminations) for the oldest job that has been waiting and bump it to 1st priority.
            if (terminations!=0 && terminations%30==0){
                int oldestIndex = 0;
                for (int i = 0; i<arrayList.size(); i++){
                    if (arrayList.get(i).getEntryTime()<arrayList.get(oldestIndex).getEntryTime())
                        oldestIndex=i;
                }
                Job oldest = arrayList.get(oldestIndex);
                System.out.println("Oldest Job Entered is : " + oldest.getJobName() + ", entryTime : " + (int) oldest.getEntryTime());
                // Put it as top priority, increment counter and remove it then put it to be processed until termination.
                oldest.setFinalPriority(1);
                priorityChanges++;
                arrayList.remove(oldestIndex);
                arrayList.add(0,oldest);
            }
        }
        else arrayList.add(position, temp);
        return currentTime;
    }


}
