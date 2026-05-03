public class Cars extends Vehicle{

    public Cars(String brand, double speed) {
        super(brand, speed);
    }
    public Cars() {
        super();
    }
    @Override
    public  void move(){
        System.out.println("masina miscat");
    }
    @Override
    public boolean needsService(){
        if(this.getMileage()>10000) return true;
        return false;
    }

    @Override
    public double rentalPrice( int d){
        double chestie= 50*d;
        System.out.println("cate usi?");

        return chestie;
    }
}
