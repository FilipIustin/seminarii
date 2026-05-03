public class Motorcycle extends Vehicle
{
    public Motorcycle() {
        super();
    }

    public Motorcycle(String brand, double speed) {
        super(brand, speed);
    }
    @Override
    public  void move(){
        System.out.println("motocivleta miscat");
    }

    @Override
    public boolean needsService() {
        return false;
    }

    @Override
    public double rentalPrice(int d) {
        return 0;
    }
}
