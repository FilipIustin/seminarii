public class Truck extends Cars{
    public Truck(String brand, double speed) {
        super(brand, speed);
    }

    public Truck() {
        super();
    }
    @Override
    public  void move(){
        System.out.println("tir miscat");
    }
}
