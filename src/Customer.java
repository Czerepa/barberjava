class Customer implements Runnable {
    String name;

    BarberShop shop;

    public Customer(BarberShop shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        goForHairCut();
    }

    private synchronized void goForHairCut() {
        shop.add(this);
    }
}
