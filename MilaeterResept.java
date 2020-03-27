public class MilaeterResept extends HvitResept { //Subklasse av HvitResept.

  public MilaeterResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int r) {
    super(legemiddel, lege, pasient, r); //Aksesserer superklassens konstruktoer.
  }

  @Override //Overskriver metoden prisAabetale da den er ulik av fra HvitResept.
  public double prisAaBetale() {
    double rabatt = legemiddel.hentPris(); //Henter prisen til legemiddelet i variabelen rabatt.
    return legemiddel.hentPris() - rabatt; //Henter prisen paa nytt i tillegg til at jeg trekker fra samme verdi med rabatt. Slik at det blir 0.
  }
}
