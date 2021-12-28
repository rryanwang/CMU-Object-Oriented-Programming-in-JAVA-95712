
package lab9;

public class Customer {
    static int customerCount;
    int id;
    int numberOfTickets;

    public Customer() {
        ++customerCount;
        id = customerCount;
        numberOfTickets = (int) (Math.random()*MovieHall.MAX_TICKETS + MovieHall.MIN_TICKETS);
    }

    public boolean joinQueue() {
        try {
            MovieHall.customerQueue.offer(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
