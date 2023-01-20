import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

class BarberShop {
    int nchair, min, max;
    List<Customer> customerList;

    public BarberShop(int waitingRoomSize, int minCuttingDuration, int maxCuttingDuration) {
        this.nchair = waitingRoomSize;
        this.min = minCuttingDuration;
        this.max = maxCuttingDuration;
        customerList = new LinkedList<>();
    }

    public void cutHair() {
        Customer customer;
        System.out.println("Barber waiting for lock.");
        synchronized (customerList) {

            while (customerList.size() == 0) {
                System.out.println("Barber is waiting for customer.");
                try {
                    customerList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Barber found a customer in the queue.");
            customer = (Customer) ((LinkedList<?>) customerList).poll();
        }
        long cuttingDuration = 0;
        try {
            System.out.println("Cutting hair of Customer: " + Objects.requireNonNull(customer).getName());
            cuttingDuration = (long)(Math.random() * (max - min + 1) + min);
            TimeUnit.SECONDS.sleep(cuttingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed cutting hair of Customer: " + Objects.requireNonNull(customer).getName() + " in " + cuttingDuration + " seconds.");
    }

    public void add(Customer customer) {
        System.out.println(customer.getName() + " entered the shop.");

        synchronized (customerList) {
            if (customerList.size() == nchair) {
                System.out.println("No chair available for customer " + customer.getName());
                System.out.println("Customer " + customer.getName() + " exits...");
                return;
            }

            ((LinkedList<Customer>) customerList).offer(customer);
            System.out.println(customer.getName() + " got the chair.");

            if (customerList.size() == 1)
                customerList.notify();
        }
    }
}
