abstract public class Resepter { //Oppretter klassen Resepter. Bruker abstract da det ikke skal kunne oprettes objekt av Resepter.
  protected int id; //Oppretter variabler som protected og deklarerer de. Slik er de tilgjengelig for suklassene.
  protected Legemiddel legemiddel; //legemiddel fra klassen Legemiddel.
  protected Lege utskrivendeLege; //utskrivendeLegee fra klassen Lege
  public Pasient pasientId;
  protected int reit;
  protected static int tellerId = 0; //Oppretter static int tellerId som legges til id slik at de faar hver sin unike id.

  public Resepter(Legemiddel middel, Lege lege, Pasient pasient, int r) { //Konstruktoeren tar inn legemiddel, lege, pasientId og reit.
    legemiddel = middel; //Argumentene settes til aa vere verdien til hver variabel.
    utskrivendeLege = lege;
    pasientId = pasient;
    reit = r;
    id = tellerId++; //Legger til 1 for hvert resept som opprettes.
  }

  public int hentId() { //Metode for aa hente id.
    return id;
  }

  public Legemiddel hentLegemiddel() { //Metode for aa hente legemiddel.
    return legemiddel;
  }

  public Lege hentLege() { //Metode for aa hente utskrivendeLege.
    return utskrivendeLege;
  }

  public int hentPasientId() { //Metode for aa hente pasientId.
    return pasientId.hentID();
  }

  public int hentReit() { //Metode for aa hente reit.
    return reit;
  }

  public boolean bruk() { //Metode bruk() sjekker antallet igjen paa reit.
    if (reit != 0) { //Dersom det ikke er 0 returnerer den true og trekker 1 fra reit.
      reit--;
      return true;
    }
    return false; //Returnerer false hvis det ikke er igjen noe paa reit.
  }

  public String toString() { //Metode toString() som returnerer et tilpasset utskrift til et objekt av klassen.
    return legemiddel + "\nSkrevet ut av " + utskrivendeLege + " til pasient: " + pasientId.hentID() + "\nReit igjen: " + reit;
  }

  abstract public String farge(); //Oppretter metoden farge().

  abstract public double prisAaBetale(); //Oppretter metoden prisAaBetale().

}
