class Barber implements Runnable {
    BarberShop shop;

    public Barber(BarberShop shop) {
        this.shop = shop;
    }

    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barber started...");
        while (true) {
            shop.cutHair();
        }
    }
}
