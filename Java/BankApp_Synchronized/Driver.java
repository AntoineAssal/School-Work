package BankApp_Synchronized;

public class Driver {

    /**
     * main class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        Network objNetwork = new Network(); /* Activate the network */
        objNetwork.start();

        //Create 2 Server threads and start them
        Server objServer1 = new Server("Thread1");      /* Start the first Server thread */
        objServer1.start();
        Server objServer2 = new Server("Thread2");      /* Start the Second Server thread */
        objServer2.start();

        Client objClient1 = new Client("sending");          /* Start the sending client thread */
        objClient1.start();
        Client objClient2 = new Client("receiving"); /* Start the receiving client thread */
        objClient2.start();

        objServer1.join();
        objServer2.join();
        objClient1.join();
        objClient2.join();

    }
}
