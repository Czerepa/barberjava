public class Main {

    public static void main(String[] args) {
        BarberShop shop = new BarberShop(1, 5,5);

        Barber barber = new Barber(shop, 5, 5);
        CustomerGenerator customerGenerator = new CustomerGenerator(shop, 3 , 5);

        Thread barberThread = new Thread(barber);
        Thread customerGeneratorThread = new Thread(customerGenerator);
        customerGeneratorThread.start();
        barberThread.start();
    }
}
