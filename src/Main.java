import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cars b = new Cars();
        b.setBrand("ferari");
        System.out.println(b.getBrand());

        Truck b1 = new Truck();

        b1.move();
        b.move();

        Vehicle B[] = new Vehicle[10];
        B[0] = b1;
        B[1] = b;

        B[1].move();

        Scanner s = new Scanner(System.in);
        try {
            System.out.println("baga ceva");
            int a = s.nextInt();
            // restul codului tău cu try/catch
        } catch (Exception e) {
            // tratare exceptie
        }
    }
}