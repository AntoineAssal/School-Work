package CPU_Scheduling_Simulator;
public class Job {
    private String jobName;
    private int jobLength;
    private int currentJobLength;
    private int jobPriority;
    private int finalPriority;

    private long entryTime = 0;
    private long endTime;
    private long waitTime;

    public Job(int entryTime) {
        jobName = "JOB__" + (entryTime + 1);
        jobLength = (int) (Math.random() * 70) + 1;
        currentJobLength = jobLength;
        jobPriority = (int) (Math.random() * 40) + 1;
        finalPriority = jobPriority;
        this.entryTime = entryTime;
        endTime = 0;
        waitTime = 0;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public int getCurrentJobLength() {
        return currentJobLength;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public int getFinalPriority() {
        return finalPriority;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setFinalPriority(int priority) {
        finalPriority = priority;
    }

    public void setCurrentJobLength(int length) {
        currentJobLength = length;
    }

    public void setWaitTime(long time) {
        waitTime = time;
    }

    public String toString() {
        return "Now Executing " + jobName +
                " Job length: " + jobLength +
                " cycles; Current remaining length: " + currentJobLength +
                " cycles; Initial priority: " + jobPriority +
                "; Current priority: " + finalPriority;
    }

    public void decrementCurrentJobLength() {
        currentJobLength--;
    }

    public void incrementWaitTime() {
        waitTime++;
    }

}
