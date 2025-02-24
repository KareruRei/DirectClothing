package people;

import business.DirectClothing;

public class WorkerThread extends Thread {
    DirectClothing business;
    OrderTaker orderTakers[];
    

    public WorkerThread(DirectClothing business, OrderTaker[] OT_List) {
        this.business = business;
        this.orderTakers = OT_List;
    }

    @Override
    public void run() {

        // Thread will execute for the entire length of program runtime
        while (true) {

            // Code inside loop is synchronized to make it thread safe
            for (OrderTaker ot : orderTakers) {
                synchronized (business) {
                    // If order taker is not occupied, and there are orders in the queue,
                    // assign the order to the order taker
                    if (!ot.isOccupied() && !business.orderQueueIsEmpty())
                        if (!business.orderQueueIsEmpty()) {
                            ot.setOrderToProcess(business.dequeueOrder());
                            ot.startWork();

                            System.out.println("| THREAD 1 -> " + business.getOrderQueueString());
                        }
                }
            }

            for (OrderTaker ot : orderTakers)
                if (ot.isOccupied()) ot.doWork();


            // Sleep the thead for 1 second
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
