import java.util.concurrent.TimeUnit;

class CustomerGenerator implements Runnable {
    BarberShop shop;

    public CustomerGenerator(BarberShop shop) {
        this.shop = shop;
    }

    public void run() {
        while (true) {
            Customer customer = new Customer(shop);
            Thread customerThread = new Thread(customer);
            customer.setName("Customer " + customerThread.getName());
            customerThread.start();

            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
