import java.util.Comparator;

public class Books  {
    private String nume;
    private int nrP;
    private String autor;

    public Books(){
        this.nume="";
        this.nrP=0;
        this.autor="";
    }

    public Books(String nume_){
        this.nume=nume_;
    }

    public Books(String nume_, String autor_, int nrP_){
        this.nume=nume_;
        this.autor=autor_;
        this.nrP=nrP_;
    }

    public String getNume() {
        return nume;
    }

    public int getNrP(){
        return nrP;
    }

    public void setNume(String nume_){
        this.nume=nume_;
    }

    public void sound(){
        System.out.println("am citit");
    }
    @Override
    public String toString(){
        return "nume:" + nume;
    }
    @Override
    public boolean equals(Object p){
        if(this == p)return true;
        if(!(p instanceof Books))return false;

        Books d= (Books) p;
        return this.nrP==d.nrP && this.nume.equals(d.nume);
    }

}
