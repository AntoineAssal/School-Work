package CPU_Scheduling_Simulator;

import java.util.Random;

public class PriorityQueueSimulatorTester {

    public static void main(String[] args) {
        int[] maxNumberOfJobs = {100, 1000, 10000};
        int[] maxNumberOfJobs2 = {10000, 100000, 1000000};

        for (int numberOfJobs : maxNumberOfJobs) {
            Job[][] jobsInputArray = createJobArray(numberOfJobs);

            Simulator unsorted = new Simulator("Unsorted List", jobsInputArray[0]);
            unsorted.execute();

            Simulator sorted = new Simulator("Sorted List", jobsInputArray[1]);
            sorted.execute();

            Simulator arrayHeap = new Simulator(
                    "ArrayList-based Heap",
                    jobsInputArray[2]
            );
            arrayHeap.execute();
      /*Simulator linkedHeap = new Simulator("LinkedList-based Heap", jobsInputArray[3]);
            linkedHeap.execute();*/
        }
    /*for (int numberOfJobs2 : maxNumberOfJobs2) {
            Job[][] jobsInputArray2 = createJobArray(numberOfJobs2);
            Simulator linkedHeap = new Simulator("LinkedList-based Heap", jobsInputArray2[0]);
            linkedHeap.execute();
        }*/

    }

    public static Job[][] createJobArray(int numberOfJobs) {
        Random randomGenerator = new Random();
        Job[][] jobsInputArray = new Job[3][numberOfJobs];
        for (int i = 0; i < numberOfJobs; i++) {
            int jobLength = randomGenerator.nextInt(70) + 1;
            int jobPriority = randomGenerator.nextInt(40) + 1;
            for (int j = 0; j < 3; j++) {
                jobsInputArray[j][i] =
                        new Job(
                                "Job_" + (i + 1),
                                jobLength,
                                jobLength,
                                jobPriority,
                                jobPriority,
                                0,
                                0,
                                0
                        );
            }
        }
        return jobsInputArray;
    }
}
