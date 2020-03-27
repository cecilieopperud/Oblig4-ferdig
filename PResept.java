public class PResept extends HvitResept { //Subklasse av HvitResept.

  public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient) { //Tar ikke inn reit da jeg legger inn 3 selv i super().
    super(legemiddel, lege, pasient, 3); //akkseserer superklassens konstruktoer.
  }

  @Override //Overskriver metoden prisAabetale() da det er den eneste metoden som skiller seg ut.
  public double prisAaBetale() {
    double rabatt = 108; //Lagrer 108 i variabelen rabatt.
    if (legemiddel.hentPris() >= rabatt) { //dersom prisen til legemiddelet er stoerre eller lik 108/rabatt.
      return legemiddel.hentPris() - rabatt; //Returnerer prisen - rabatt.
    }
    return 0; //Hvis ikke returnerer den 0.
  }
  @Override
  public String farge(){
    return "p";
  }
}
