public class Simulator {

  private String PQueueType;

  private long time;
  private Job[] jobsInputArray;
  private long totalWaitingTime;
  private int priorityChanges;
  private double systemTime;

  public Simulator(String PQueueType, Job[] jobsInputArray) {
    this.PQueueType = PQueueType;
    this.jobsInputArray = jobsInputArray;
  }

  private PriorityQueue<Integer, Job> routing() {
    switch (PQueueType) {
      case "Unsorted List":
        return new UnsortedListPriorityQueue<>();
      case "Sorted List":
        return new SortedListPriorityQueue<>();
      case "LinkedList-based Heap":
        return new LinkedHeapPriorityQueue<>();
      default:
        return new ArrayListHeapPriorityQueue<>();
    }
  }

  public void execute() {
    PriorityQueue<Integer, Job> PQ = routing();
    double startTime = System.currentTimeMillis();
    for (Job value : jobsInputArray) {
      time++;
      value.setEntryTime(time);
      PQ.insert(value.getJobPriority(), value);
    }
    while (!PQ.isEmpty()) {
      time++;
      Pair<Integer, Job> currentJob = PQ.removeMin();
      int length = currentJob.getValue().getCurrentJobLength();
      System.out.println(currentJob.getValue());
      if (length > 0) {
        currentJob.getValue().setCurrentJobLength(length - 1);
        PQ.insert(currentJob.getKey(), currentJob.getValue());
      } else {
        currentJob.getValue().setEndTime(time);
        long entryTime = currentJob.getValue().getEntryTime();
        long waitTime = time - entryTime - currentJob.getValue().getJobLength();
        currentJob.getValue().setWaitTime(waitTime);
        totalWaitingTime += waitTime;
        if ((jobsInputArray.length - PQ.size()) % 30 == 0) {
          checkStarvation(PQ);
        }
      }
    }
    double endTime = System.currentTimeMillis();
    systemTime = endTime - startTime;
    printResult();
  }

  private void checkStarvation(PriorityQueue pq) {
    if (PQueueType.equals("Unsorted List")) {
      checkUnsorted(pq);
    } else if (PQueueType.equals("Sorted List")) {
      checkSorted(pq);
    } else if (PQueueType.equals("ArrayList-based Heap")) {
      checkArrayHeap(pq);
    }
    // else  checkLinkedHeap(pq);
  }

  private void printResult() {
    PrintWriter printWriter = null;
    try {
      printWriter =
        new PrintWriter(
          new FileOutputStream("SimulatorPerformanceResults.txt", true)
        );
    } catch (FileNotFoundException e) {
      System.out.println("Error opening the file!");
      System.exit(0);
    }
    printWriter.println(PQueueType + " Priority Queue");
    printWriter.println("Current system time (cycles): " + time);
    printWriter.println(
      "Total number of jobs executed: " + jobsInputArray.length + " jobs"
    );
    printWriter.println(
      "Average process waiting time: " +
      totalWaitingTime /
      jobsInputArray.length +
      " cycles"
    );
    printWriter.println("Total number of priority changes: " + priorityChanges);
    printWriter.println(
      "Actual system time needed to execute all jobs: " + systemTime + " ms"
    );
    printWriter.println();
    printWriter.flush();
  }

  private void checkUnsorted(PriorityQueue pq) {
    long oldest = 0;
    UnsortedListPriorityQueue<Integer, Job> temp = (UnsortedListPriorityQueue<Integer, Job>) pq;
    Iterator<Position<Pair<Integer, Job>>> iterator = temp.iterator();
    Position<Pair<Integer, Job>> tochange = null;
    while (iterator != null && iterator.hasNext()) {
      Position<Pair<Integer, Job>> position = iterator.next();
      time++;
      Job job = position.getElement().getValue();
      boolean isExecuted = (job.getJobLength() > job.getCurrentJobLength());
      if ((!isExecuted) && job.getEntryTime() > oldest) {
        oldest = job.getEntryTime();
        tochange = position;
      }
    }
    if (tochange != null) {
      Pair<Integer, Job> pair = tochange.getElement();
      pair.setKey(1);
      pair.getValue().setFinalPriority(1);
      priorityChanges++;
    }
  }

  private void checkSorted(PriorityQueue pq) {
    long oldest = 0;
    SortedListPriorityQueue<Integer, Job> temp = (SortedListPriorityQueue<Integer, Job>) pq;
    Iterator<Position<Pair<Integer, Job>>> iterator = temp.iterator();
    Position<Pair<Integer, Job>> tochange = null;
    while (iterator != null && iterator.hasNext()) {
      Position<Pair<Integer, Job>> position = iterator.next();
      time++;
      Job job = position.getElement().getValue();
      boolean isExecuted = (job.getJobLength() > job.getCurrentJobLength());
      if ((!isExecuted) && job.getEntryTime() > oldest) {
        oldest = job.getEntryTime();
        tochange = position;
      }
    }
    if (tochange != null) {
      priorityChanges++;
      Pair<Integer, Job> pair = tochange.getElement();
      pair.setKey(1);
      pair.getValue().setFinalPriority(1);
      temp.removePosition(tochange);
      temp.insert(pair.getKey(), pair.getValue());
    }
  }

  private void checkArrayHeap(PriorityQueue pq) {
    long oldest = 0;
    ArrayListHeapPriorityQueue<Integer, Job> temp = (ArrayListHeapPriorityQueue<Integer, Job>) pq;
    Iterator<Pair<Integer, Job>> iterator = temp.iterator();
    int index = 0;
    int changeIndex = -1;
    Pair<Integer, Job> toChange = null;
    while (iterator != null && iterator.hasNext()) {
      Pair<Integer, Job> entry = iterator.next();
      time++;
      Job job = entry.getValue();
      boolean isExecuted = (job.getJobLength() > job.getCurrentJobLength());
      if ((!isExecuted) && job.getEntryTime() > oldest) {
        oldest = job.getEntryTime();
        changeIndex = index;
        toChange = entry;
      }
      index++;
    }
    if (changeIndex != -1 && toChange != null) {
      priorityChanges++;
      toChange.getValue().setFinalPriority(1);
      Job newJob = toChange.getValue();
      temp.replace(changeIndex, 1, newJob);
    }
  }
  /*  private void checkLinkedHeap(PriorityQueue pq) {
        long oldest = 0;
        LinkedHeapPriorityQueue<Integer, Job> temp = (LinkedHeapPriorityQueue<Integer, Job>) pq;
        Iterator<Pair<Integer, Job>> iterator = temp.iterator();
        int index = 0;
        int changeIndex = -1;
        Pair<Integer, Job> toChange = null;
        while (iterator != null && iterator.hasNext()) {
            Pair<Integer, Job> pair = iterator.next();
            time++;
            Job job = pair.getValue();
            boolean isExecuted = (job.getJobLength() > job.getCurrentJobLength());
            if ((!isExecuted) && job.getEntryTime() > oldest) {
                oldest = job.getEntryTime();
                changeIndex = index;
                toChange = pair;
            }
            index++;
        }
        if (changeIndex != -1 && toChange != null) {
            priorityChanges++;
            toChange.getValue().setFinalPriority(1);
            Job newJob = toChange.getValue();
            temp.insert(1, newJob);
        }
    }
*/
}
