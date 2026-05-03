public class chestie extends  Books implements facem_chestie{

    public chestie(){
    super();
    }
    @Override
    public void sound() {

        System.out.println("nu am citit");
    }
    @Override
    public void schema(){
    System.out.println("t");
    }

    @Override
    public int compareTo(Books o) {
        return 0;
    }
}
