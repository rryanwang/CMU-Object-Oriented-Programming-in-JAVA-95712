package lab9;

public class QueueManager implements Runnable {
    int customerDelay;
    static int balkCount;

    public QueueManager(int customerDelay) {
        this.customerDelay = customerDelay;
    }

    @Override
    public void run() {
        while (TicketWindow.isWindowOpen) {
            try {
                Thread.sleep((int) (Math.random()*customerDelay));
                if (Math.random() > 0.8) {
                    Customer customer = new ImpatientCustomer();
                    synchronized (MovieHall.customerQueue) {
                        boolean b = customer.joinQueue();
                        if (b){
                        	
                        	System.out.println("ImpatientCustomer " + customer.id + " joined Q");
                        } else {
                            ++balkCount;
                        }
                    }
                } else {
                    Customer customer = new Customer();
                    synchronized (MovieHall.customerQueue) {
                        boolean b = customer.joinQueue();
                        if (b) System.out.println("Customer " + customer.id + " joined Q");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
