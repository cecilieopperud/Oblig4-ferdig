public class HvitResept extends Resepter { //Subklasse av Resepter.

  public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int r) {
    super(legemiddel, lege, pasient, r); //Aksesserer superklassens konstruktoer.
  }

  @Override //Overskriver metoden prisAaBetale().
  public double prisAaBetale() {
    return legemiddel.hentPris(); //Returnerer bare prisen til legemiddelet.
  }

  @Override //Overskriver metoden farge().
  public String farge() {
    return "hvit"; //Returnerer stringen "hvit".
  }

  @Override //Overskriver metoden for utskrift.
  public String toString() {
    return super.toString() + " \nHvit resept"; //Returnerer super sin toString() i tillegg som jeg legger til en string.
  }
}
