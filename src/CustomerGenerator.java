import java.util.concurrent.TimeUnit;

class CustomerGenerator implements Runnable {
    BarberShop shop;
    int min,max;

    public CustomerGenerator(BarberShop shop, int minInterval, int maxInterval) {
        this.min = minInterval;
        this.max = maxInterval;
        this.shop = shop;
    }

    public void run() {
        while (true) {
            Customer customer = new Customer(shop);
            Thread customerThread = new Thread(customer);
            customer.setName("Customer " + customerThread.getName());
            customerThread.start();

            try {
                long sleepDuration = (long)(Math.random() * (max - min + 1) + min);
                //System.out.println("Timeout: " + sleepDuration);
                TimeUnit.SECONDS.sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
