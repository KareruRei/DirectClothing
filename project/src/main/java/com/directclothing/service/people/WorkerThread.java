package com.directclothing.service.people;

import com.directclothing.service.business.DirectClothing;

public class WorkerThread extends Thread {
    final DirectClothing business;
    final Worker workers[];
    

    public WorkerThread(DirectClothing business, OrderTaker[] OT_List) {
        this.business = business;
        this.workers = OT_List;
    }

    @Override
    public void run() {

        // Thread will execute for the entire length of program runtime
        while (true) {

            // Code inside loop is synchronized to make it thread safe
            for (Worker worker : workers) {
                synchronized (business) {
                    // If order taker is not occupied, and there are orders in the queue,
                    // assign the order to the order taker
                    if (!worker.isOccupied() && !business.orderQueueIsEmpty()) {
                        worker.setWorkload(business.dequeueOrder());
                        worker.startWork();
                    }
                        
                }
            }

            for (Worker worker : workers)
                if (worker.isOccupied()) worker.doWork();


            // Sleep the thead for 1 second
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
