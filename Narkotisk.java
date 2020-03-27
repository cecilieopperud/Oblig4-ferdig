class Narkotisk extends Legemiddel { //Subklasse av Legemiddel.
  protected int styrke; //oppretter protected variabel og deklarer den som int.

  public Narkotisk (String navn, double pris, double stoff, int s) {
    super(navn, pris, stoff); //For aksessere superklassens konstruktoer.
    styrke = s; //agrumentet til s settes som verdien til styrke.
  }
  @Override 
  public int hentStyrke() { //metode for aa hente styrken.
    return styrke;
  }

  @Override //Overskriver toString() metoden for aa legge til styrke.
  public String toString() {
    return super.toString() + " og styrke " + styrke;
  }
}
