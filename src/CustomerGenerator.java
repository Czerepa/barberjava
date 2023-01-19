import java.util.Date;
import java.util.concurrent.TimeUnit;

class CustomerGenerator implements Runnable {
    BarberShop shop;

    public CustomerGenerator(BarberShop shop) {
        this.shop = shop;
    }

    public void run() {
        while (true) {
            Customer customer = new Customer(shop);
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            customer.setName("Customer " + thcustomer.getName());
            thcustomer.start();

            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 10) + 1);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
}
