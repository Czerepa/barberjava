public class Main {

    public static void main(String[] args) {
        BarberShop shop = new BarberShop();

        Barber barber = new Barber(shop);
        CustomerGenerator cg = new CustomerGenerator(shop);

        Thread thbarber = new Thread(barber);
        Thread thcg = new Thread(cg);
        thcg.start();
        thbarber.start();
    }
}
