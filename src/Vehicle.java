import javax.net.ssl.SSLPeerUnverifiedException;

public abstract class Vehicle {
    private String id ;
    private String brand;
    private double speed;
    private int mileage;
    private boolean rented;

    public void rent (){
        try{
            if(this.rented==true)throw new Exception("schema");
        }
        catch (Exception e){
            System.out.println("mama");
        }
    }

    public void returnVehicle(int km){
        try {
            if(this.rented==false)throw new Exception("schema");
            if(km<0)throw new Exception("schema1");
            this.mileage=this.mileage+km;
            this.rented=false;
        }
        catch (Exception e){
            System.out.println("mama");
        }
    }

    public Vehicle(){
        this.brand=" nu stiu";
        this.speed= 0.0;
    }

    public Vehicle(String brand, double speed) {
        this.brand = brand;
        this.speed = speed;
    }
    public String getBrand(){
        return this.brand;

    }

    public void setBrand(String s){
        this.brand=s;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void setSpeed( double c){
        this.speed=c;
    }

    public void move(){
        System.out.println("ne am miscat");
    }

    @Override
    public boolean equals(Object p){
        if(this== p)return  true;
        if(!(p instanceof Vehicle))return false;

        Vehicle p1= (Vehicle) p;

        return this.speed<p1.speed;
    }

    public int getMileage(){
        return this.mileage;
    }
    abstract public boolean needsService();
    abstract public double rentalPrice(int d);
}
