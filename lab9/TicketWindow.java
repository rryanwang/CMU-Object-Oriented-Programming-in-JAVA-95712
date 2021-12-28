
package lab9;

import java.util.ArrayList;
import java.util.List;

public class TicketWindow implements Runnable {
    static int ticketSoldCount;
    static boolean isWindowOpen = true;
    int ticketProcessTime;
    List<Customer> customerList = new ArrayList<>();

    public TicketWindow(int ticketProcessTime) {
        this.ticketProcessTime = ticketProcessTime;
    }

    @Override
    public void run() {
        while (isWindowOpen) {
            try {
                Customer customer = null;
                synchronized (MovieHall.customerQueue) {
                    customer = MovieHall.customerQueue.poll();
                }
                if (customer != null) {
                    customerList.add(customer);
                    if (customer instanceof ImpatientCustomer) {
                        System.out.println("\tImpatientCustomer " + customer.id + " bought " + customer.numberOfTickets + " tickets");

                    } else {
                        System.out.println("\tCustomer " + customer.id + " bought " + customer.numberOfTickets + " tickets");

                    }
                    Thread.sleep(ticketProcessTime);
                    ticketSoldCount += customer.numberOfTickets;
                }
                if (ticketSoldCount >= MovieHall.maxSeats) isWindowOpen = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
