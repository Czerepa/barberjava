import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

class BarberShop {
    int nchair;
    List<Customer> listCustomer;

    public BarberShop() {
        nchair = 3;
        listCustomer = new LinkedList<>();
    }

    public void cutHair() {
        Customer customer;
        System.out.println("Barber waiting for lock.");
        synchronized (listCustomer) {

            while (listCustomer.size() == 0) {
                System.out.println("Barber is waiting for customer.");
                try {
                    listCustomer.wait();
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
            System.out.println("Barber found a customer in the queue.");
            customer = (Customer) ((LinkedList<?>) listCustomer).poll();
        }
        long duration = 0;
        try {
            System.out.println("Cutting hair of Customer: " + Objects.requireNonNull(customer).getName());
            duration = (long) (Math.random() * 10) + 1;
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Completed Cutting hair of Customer: " + Objects.requireNonNull(customer).getName() + " in " + duration + " seconds.");
    }

    public void add(Customer customer) {
        System.out.println(customer.getName() + " entered the shop.");

        synchronized (listCustomer) {
            if (listCustomer.size() == nchair) {
                System.out.println("No chair available for customer " + customer.getName());
                System.out.println("Customer " + customer.getName() + " exists...");
                return;
            }

            ((LinkedList<Customer>) listCustomer).offer(customer);
            System.out.println(customer.getName() + " got the chair.");

            if (listCustomer.size() == 1)
                listCustomer.notify();
        }
    }
}
