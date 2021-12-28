package lab9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import static org.junit.Assert.*;

public class MovieHall {
    static int examPart;
    static int maxSeats;
    static final int MIN_TICKETS = 1;
    static final int MAX_TICKETS = 10;
    static Queue<Customer> customerQueue = new LinkedList<>();
    static int balkQueueLength;
    int ticketProcessingTime;
    int customerDelay;
    long startTime, endTime;
    QueueManager queueManager;
    TicketWindow ticketWindow;

    public static void main(String[] args) {
        MovieHall movieHall = new MovieHall();
        movieHall.getInputs();
        movieHall.startThreads();
        movieHall.printReport();
        movieHall.testResults();
    }

    public void getInputs() {
        Scanner input = new Scanner(System.in);
        System.out.println("Part 1 or 2?");
        examPart = Integer.parseInt(input.nextLine());
        System.out.println("Enter single ticket processing time (ms):");
        ticketProcessingTime = Integer.parseInt(input.nextLine());
        ticketWindow = new TicketWindow(ticketProcessingTime);
        System.out.println("Enter max tickets to be sold:");
        maxSeats = Integer.parseInt(input.nextLine());
        System.out.println("Enter max customer delay(ms):");
        customerDelay = Integer.parseInt(input.nextLine());
        queueManager = new QueueManager(customerDelay);
        if (examPart == 2) {
            System.out.println("Enter impatient customer's balk-queue-length:");
            balkQueueLength = Integer.parseInt(input.nextLine());
        }
    }

    public void startThreads() {
        Thread thread1 = new Thread(queueManager);
        Thread thread2 = new Thread(ticketWindow);
        startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();		//wait for t1 to complete and join main thread
            thread2.join();		//wait for t2 to complete and join main thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
    }

    public void printReport() {
        System.out.println("-------------- Part 1 Report --------------");
        System.out.printf("%-25s%,d ms%n", "Ticket windows open duration: ", endTime - startTime);
        System.out.printf("%-25s%5d%n", "Total customer:", Customer.customerCount);
        System.out.printf("%-25s%5d%n", "Customer who bought tickets:", ticketWindow.customerList.size());
        System.out.printf("%-25s%5d%n", "Customer int queue when window closed:", Customer.customerCount - ticketWindow.customerList.size());
        System.out.println();
        System.out.printf("%-25s%5d%n", "Total tickets sold:", TicketWindow.ticketSoldCount);
        System.out.println("-------------- Customer Summary Report --------------");
        int i = 1;
        int total = 0;
        for (Customer c : ticketWindow.customerList) {
            total += c.numberOfTickets;
            if (c instanceof ImpatientCustomer) {
                System.out.println(i + ".      " + "ImpatientCustomer " + c.id + " bought: " + c.numberOfTickets + " tickets.     Cumulative total: " + total);
            } else {
                System.out.println(i + ".      " + "Customer " + c.id + " bought: " + c.numberOfTickets + " tickets.     Cumulative total: " + total);
            }
            ++i;
        }
        if (examPart == 2) {
            System.out.println("-------------- Part 2 Report --------------");
            System.out.printf("%-25s%5d%n", "Impatient customers:", ImpatientCustomer.customerCount);
            System.out.printf("%-25s%5d%n", "Customers who balked:", QueueManager.balkCount);                             
        }
    }

    public void testResults() {

    	int ticketsSold = 0;  //total tickets sold

    	int minTickets = MAX_TICKETS, maxTickets = MIN_TICKETS;

    	//find the min, max, and total tickets sold from the customerList

    	for (Customer c : ticketWindow.customerList) {

    	ticketsSold += c.numberOfTickets;
    	if (minTickets > c.numberOfTickets) minTickets = c.numberOfTickets;
    	if (maxTickets < c.numberOfTickets) maxTickets = c.numberOfTickets;

    	}

    	//test whether total customerCount matches the sum of customers in customerList, //customers in customerQueue, and customers who balked

    	assertEquals("Total customers", Customer.customerCount,

    	ticketWindow.customerList.size() + customerQueue.size() + queueManager.balkCount);

    	//test total tickets sold calculated above matches the total count at TicketWindow

    	assertEquals("Total tickets sold", ticketsSold, TicketWindow.ticketSoldCount );

    	//test that total tickets sold is equal to or more than maxSeats 

    	assertTrue(ticketsSold >= maxSeats);

    	//test the minTickets calculated above is greater than or equal to MIN_TICKETS

    	assertTrue("Min tickets", minTickets >= MIN_TICKETS);

    	//test the maxTickets calculated above is less than or equal to MIN_TICKETS

    	assertTrue("Max tickets", maxTickets <= MAX_TICKETS);

    	}
    }

