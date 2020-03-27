public class BlaaResept extends Resepter { //Subklasse av Resepter.

  public BlaaResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int r) {
    super(legemiddel, lege, pasient, r); //aksesserer superklassens konstruktoer.
  }

  @Override //Overskriver metoden prisAabetale da BlaaResept er annerledes.
  public double prisAaBetale() { //Skal faa 75% avslag.
    return legemiddel.hentPris() * 0.25; //Returnerer legemiddelets pris * 0,25.
  }

  @Override //Overskriver metoden farge().
  public String farge() {
    return "blaa"; //Returnerer stringen "blaa".
  }

  @Override //Overskriver metoden toString().
  public String toString() {
    return super.toString() + " \nBlaa resept"; //Legger til en string tilpasset til blaa resepter.
  }
}
