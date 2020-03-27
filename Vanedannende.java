public class Vanedannende extends Legemiddel { //subklasse av Legemiddel.
  protected int styrke; //Samme variabel som i Narkotisk.

  public Vanedannende(String navn, double pris, double stoff, int s) {
    super(navn, pris, stoff); //For aa aksessere superklassens konstruktoer.
    styrke = s; //Agrumentet til s settes som verdien til styrke.
  }

  @Override 
  public int hentStyrke() { //Metode for aa hente fram styrke.
    return styrke;
  }

  @Override //Overskriver toString() metoden med aa legge til styrke.
  public String toString() {
    return super.toString() + " og styrke " + styrke;
  }

}
