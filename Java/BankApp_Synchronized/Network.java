package BankApp_Synchronized;

import java.util.concurrent.*;

public class Network extends Thread {

    private static int maxNbPackets;                           /* Maximum number of simultaneous transactions handled by the network buffer */
    private static int inputIndexClient, inputIndexServer, outputIndexServer, outputIndexClient; /* Network buffer indices for accessing the input buffer (inputIndexClient, outputIndexServer) and output buffer (inputIndexServer, outputIndexClient) */
    private static String clientIP;                            /* IP number of the client application*/
    private static String serverIP;                            /* IP number of the server application */
    private static int portID;                                 /* Port ID of the client application */
    private static String clientConnectionStatus;              /* Client connection status - connected, disconnected, idle */
    private static String serverConnectionStatus;              /* Server connection status - connected, disconnected, idle */
    private static Transactions inComingPacket[];              /* Incoming network buffer */
    private static Transactions outGoingPacket[];              /* Outgoing network buffer */
    private static String inBufferStatus, outBufferStatus;     /* Current status of the network buffers - normal, full, empty */
    private static String networkStatus;                       /* Network status - active, inactive */

    private static Semaphore semEmpty;                                /* Semaphore for empty network buffer */
    private static Semaphore semFull;                                 /* Semaphore for full network buffer */
    private static Semaphore semEmpty2;                                /* Semaphore for empty network buffer */
    private static Semaphore semFull2;                                  /* Semaphore for full network buffer */

    private static final Object transInLock = new Object();
    private static final Object transOutLock = new Object();

    /**
     * Constructor of the Network class
     *
     * @return
     * @param
     */
    Network( )
    {
        int i;

        System.out.println("\n Activating the network ...");
        clientIP = "192.168.2.0";
        serverIP = "216.120.40.10";
        clientConnectionStatus = "idle";
        serverConnectionStatus = "idle";
        portID = 0;
        maxNbPackets = 10;
        inComingPacket = new Transactions[maxNbPackets];
        outGoingPacket = new Transactions[maxNbPackets];
        for (i=0; i < maxNbPackets; i++)
        {   inComingPacket[i] = new Transactions();
            outGoingPacket[i] = new Transactions();
        }
        inBufferStatus = "empty";
        outBufferStatus = "empty";
        inputIndexClient = 0;
        inputIndexServer = 0;
        outputIndexServer = 0;
        outputIndexClient = 0;

        //Initialize Semaphores
        semEmpty = new Semaphore(maxNbPackets);
        semFull = new Semaphore(0);
        semEmpty2 = new Semaphore(maxNbPackets);
        semFull2 = new Semaphore(0);

        networkStatus = "active";
    }

    /**
     * Accessor method of Network class
     *
     * @return clientIP
     * @param
     */
    public static String getClientIP()
    {
        return clientIP;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param cip
     */
    public static void setClientIP(String cip)
    {
        clientIP = cip;
    }

    /**
     *  Accessor method of Network class
     *
     * @return serverIP
     * @param
     */
    public static String getServerIP()
    {
        return serverIP;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param sip
     */
    public static void setServerIP(String sip)
    {
        serverIP = sip;
    }

    /**
     *  Accessor method of Network class
     *
     * @return clientConnectionStatus
     * @param
     */
    public static String getClientConnectionStatus()
    {
        return clientConnectionStatus;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param connectStatus
     */
    public static void setClientConnectionStatus(String connectStatus)
    {
        clientConnectionStatus = connectStatus;
    }

    /**
     *  Accessor method of Network class
     *
     * @return serverConnectionStatus
     * @param
     */
    public static String getServerConnectionStatus()
    {
        return serverConnectionStatus;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param connectStatus
     */
    public static void setServerConnectionStatus(String connectStatus)
    {
        serverConnectionStatus = connectStatus;
    }

    /**
     *  Accessor method of Network class
     *
     * @return portID
     * @param
     */
    public static int getPortID()
    {
        return portID;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param pid
     */
    public static void setPortID(int pid)
    {
        portID = pid;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return inBufferStatus
     * @param
     */
    public static String getInBufferStatus()
    {
        return inBufferStatus;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param inBufStatus
     */
    public static void setInBufferStatus(String inBufStatus)
    {
        inBufferStatus = inBufStatus;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return outBufferStatus
     * @param
     */
    public static String getOutBufferStatus()
    {
        return outBufferStatus;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param outBufStatus
     */
    public static void setOutBufferStatus(String outBufStatus)
    {
        outBufferStatus = outBufStatus;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return networkStatus
     * @param
     */
    public static String getNetworkStatus()
    {
        return networkStatus;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param netStatus
     */
    public static void setNetworkStatus(String netStatus)
    {
        networkStatus = netStatus;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return inputIndexClient
     * @param
     */
    public static int getinputIndexClient()
    {
        return inputIndexClient;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param i1
     */
    public static void setinputIndexClient(int i1)
    {
        inputIndexClient = i1;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return inputIndexServer
     * @param
     */
    public static int getinputIndexServer()
    {
        return inputIndexServer;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param i2
     */
    public static void setinputIndexServer(int i2)
    {
        inputIndexServer = i2;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return outputIndexServer
     * @param
     */
    public static int getoutputIndexServer()
    {
        return outputIndexServer;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param o1
     */
    public static void setoutputIndexServer(int o1)
    {
        outputIndexServer = o1;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return outputIndexClient
     * @param
     */
    public static int getoutputIndexClient()
    {
        return outputIndexClient;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param o2
     */
    public static void setoutputIndexClient(int o2)
    {
        outputIndexClient = o2;
    }

    /**
     *  Accessor method of Netowrk class
     *
     * @return maxNbPackets
     * @param
     */
    public static int getMaxNbPackets()
    {
        return maxNbPackets;
    }

    /**
     *  Mutator method of Network class
     *
     * @return
     * @param maxPackets
     */
    public static void setMaxNbPackets(int maxPackets)
    {
        maxNbPackets = maxPackets;
    }

    /**
     *  Transmitting the transactions from the client to the server through the network
     *
     * @return
     * @param inPacket transaction transferred from the client
     *
     */
    public static boolean send(Transactions inPacket)
    {
        try {
            semEmpty.acquire();

            inComingPacket[inputIndexClient].setAccountNumber(inPacket.getAccountNumber());
            inComingPacket[inputIndexClient].setOperationType(inPacket.getOperationType());
            inComingPacket[inputIndexClient].setTransactionAmount(inPacket.getTransactionAmount());
            inComingPacket[inputIndexClient].setTransactionBalance(inPacket.getTransactionBalance());
            inComingPacket[inputIndexClient].setTransactionError(inPacket.getTransactionError());
            inComingPacket[inputIndexClient].setTransactionStatus("transferred");

         

            setinputIndexClient(((getinputIndexClient() + 1) % getMaxNbPackets()));    /* Increment the input buffer index  for the client */
            /* Check if input buffer is full */
            if (getinputIndexClient() == getoutputIndexServer()) {
                setInBufferStatus("full");
            } else {
                setInBufferStatus("normal");
            }

            semFull.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    /** Transmitting the transactions from the server to the client through the network
     * @return
     * @param outPacket updated transaction received by the client
     *
     */
    public static boolean receive(Transactions outPacket)
    {
        try {
            semFull2.acquire();

            outPacket.setAccountNumber(outGoingPacket[outputIndexClient].getAccountNumber());
            outPacket.setOperationType(outGoingPacket[outputIndexClient].getOperationType());
            outPacket.setTransactionAmount(outGoingPacket[outputIndexClient].getTransactionAmount());
            outPacket.setTransactionBalance(outGoingPacket[outputIndexClient].getTransactionBalance());
            outPacket.setTransactionError(outGoingPacket[outputIndexClient].getTransactionError());
            outPacket.setTransactionStatus("done");

            setoutputIndexClient(((getoutputIndexClient() + 1) % getMaxNbPackets())); /* Increment the output buffer index for the client */
            /* Check if output buffer is empty */
            if (getoutputIndexClient() == getinputIndexServer()) {
                setOutBufferStatus("empty");

            } else {
                setOutBufferStatus("normal");
            }

            semEmpty2.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     *  Transferring the completed transactions from the server to the network buffer
     *
     * @return
     * @param outPacket updated transaction transferred by the server to the network output buffer
     *
     */
    public static boolean transferOut(Transactions outPacket)
    {
        synchronized (transOutLock) {
            try {
                semEmpty2.acquire();

                outGoingPacket[inputIndexServer].setAccountNumber(outPacket.getAccountNumber());
                outGoingPacket[inputIndexServer].setOperationType(outPacket.getOperationType());
                outGoingPacket[inputIndexServer].setTransactionAmount(outPacket.getTransactionAmount());
                outGoingPacket[inputIndexServer].setTransactionBalance(outPacket.getTransactionBalance());
                outGoingPacket[inputIndexServer].setTransactionError(outPacket.getTransactionError());
                outGoingPacket[inputIndexServer].setTransactionStatus("transferred");

                setinputIndexServer(((getinputIndexServer() + 1) % getMaxNbPackets())); /* Increment the output buffer index for the server */
                /* Check if output buffer is full */
                if (getinputIndexServer() == getoutputIndexClient()) {
                    setOutBufferStatus("full");

                } else {
                    setOutBufferStatus("normal");
                }

                semFull2.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }
    }

    /**
     *  Transferring the transactions from the network buffer to the server
     * @return
     * @param inPacket transaction transferred from the input buffer to the server
     *
     */
    public static boolean transferIn(Transactions inPacket)
    {
        synchronized (transInLock){
            try {
                semFull.acquire();
                inPacket.setAccountNumber(inComingPacket[outputIndexServer].getAccountNumber());
                inPacket.setOperationType(inComingPacket[outputIndexServer].getOperationType());
                inPacket.setTransactionAmount(inComingPacket[outputIndexServer].getTransactionAmount());
                inPacket.setTransactionBalance(inComingPacket[outputIndexServer].getTransactionBalance());
                inPacket.setTransactionError(inComingPacket[outputIndexServer].getTransactionError());
                inPacket.setTransactionStatus("received");

                setoutputIndexServer(((getoutputIndexServer() + 1) % getMaxNbPackets()));    /* Increment the input buffer index for the server */
                if (getoutputIndexServer() == getinputIndexClient()) {
                    setInBufferStatus("empty");
                } else {
                    setInBufferStatus("normal");
                }

                semEmpty.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }
    }

    /**
     *  Handling of connection requests through the network
     *
     * @return valid connection
     * @param IP
     *
     */
    public static boolean connect(String IP)
    {
        if (getNetworkStatus().equals("active"))
        {
            if (getClientIP().equals(IP))
            {
                setClientConnectionStatus("connected");
                setPortID(0);
            }
            else
            if (getServerIP().equals(IP))
            {
                setServerConnectionStatus("connected");
            }
            return true;
        }
        else
            return false;
    }

    /**
     *  Handling of disconnection requests through the network
     * @return valid disconnection
     * @param IP
     *
     */
    public static boolean disconnect(String IP)
    {
        if (getNetworkStatus( ).equals("active"))
        {
            if (getClientIP().equals(IP))
            {
                setClientConnectionStatus("disconnected");
            }
            else
            if (getServerIP().equals(IP))
            {
                setServerConnectionStatus("disconnected");
            }
            return true;
        }
        else
            return false;
    }

    /**
     *  Create a String representation based on the Network Object
     *
     * @return String representation
     */
    public String toString()
    {
        return ("\n Network status " + getNetworkStatus() + "Input buffer " + getInBufferStatus() + "Output buffer " + getOutBufferStatus());
    }

    /**
     *  Code for the run method
     *
     * @return
     * @param
     */
    public void run()
    {

        while (true)
        {
            if(clientConnectionStatus.equals("disconnected") && serverConnectionStatus.equals("disconnected"))
                break;
            else
                Thread.yield();
        }

        System.out.println("\n Terminating network thread  - Client disconnected, Server disconnected");
    }
}

