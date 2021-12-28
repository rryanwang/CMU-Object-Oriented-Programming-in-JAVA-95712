
package lab9;

public class ImpatientCustomer extends Customer {
    static int customerCount;

    public ImpatientCustomer() {
        ++customerCount;
    }

    public boolean joinQueue() {
        try {
            if (MovieHall.customerQueue.size() > MovieHall.balkQueueLength) {
                System.out.println("ImpateientCustomer "+ id +" balked");
                return false;
            }
            MovieHall.customerQueue.offer(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
