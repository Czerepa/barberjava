class Barber implements Runnable {
    BarberShop shop;
    long sleepDuration;

    public Barber(BarberShop shop, int minSleepDuration, int maxSleepDuration) {
        this.shop = shop;
        this.sleepDuration = (long)(Math.random() * (maxSleepDuration - minSleepDuration + 1) + minSleepDuration);
    }

    public void run() {
        try {
            Thread.sleep(sleepDuration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barber started...");
        while (true) {
            shop.cutHair();
        }
    }
}
