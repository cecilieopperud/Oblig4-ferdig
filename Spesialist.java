class Spesialist extends Lege implements Godkjenningsfritak { //Klassen Speialist arver fra Lege og implementerer grensesnittet Godkjenningsfritak.
  int kontrollID; //Deklarerer i tillegg variabelen kontrollID.

  public Spesialist(String legeNavn, int kontroll) { //To parameter.
    super(legeNavn); //Aksesserer superklassens Lege sin kontruktoer.
    kontrollID = kontroll; //Oppretter og gir verdi til kontrollID.
  }

  @Override //Overskriver grensesnittets metode ved aa returnere kontrollID.
  public int hentKontrollID() {
    return kontrollID;
  }

  @Override //Overskriver toString() metoden med aa legge til den informasjonen som trengs.
  public String toString() {
    return super.toString() + " med godkjenningsfritaket " + kontrollID;
  }
  @Override
  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit){
      HvitResept hResept = new HvitResept(legemiddel , this , pasient, reit);
      utskrevedeResepter.leggTil(hResept);
      pasient.leggTilResept(hResept);
      return hResept;
  }

  @Override
  public MilaeterResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) {
      MilaeterResept mResept = new MilaeterResept(legemiddel, this, pasient, reit);
      utskrevedeResepter.leggTil(mResept);
      pasient.leggTilResept(mResept);
      return mResept;
  }

  @Override
  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient){
        PResept pResept = new PResept(legemiddel , this, pasient);
        utskrevedeResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);
        return pResept;
    }
  @Override
  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {

      BlaaResept bResept = new BlaaResept(legemiddel , this , pasient, reit);
      utskrevedeResepter.leggTil(bResept);
      pasient.leggTilResept(bResept);
      return bResept;

  }
}
