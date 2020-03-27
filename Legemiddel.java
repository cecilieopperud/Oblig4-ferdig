abstract public class Legemiddel {  //Oppretter klassen Legemiddel
  protected String navn;  //Oppretter variabler som protected og deklarerer de. Slik er den tilgjengelig for subklasser.
  protected double pris;
  protected double virkestoff;
  protected int id;
  protected static int tellerId = 0; //Oppretter en static variabel tellerId slik at hver Legemiddel faar hver sin unike id.

  public Legemiddel (String n, double p, double stoff) { //konstruktor som tar inn verdier som settes til lik variablene navn, pris og virkestoff.
    navn = n;
    pris = p;
    virkestoff = stoff;

    id = tellerId++; //Legger til 1 i id til hver Legemiddel som opprettes.
  }

  public int hentId() { //metode for aa hente id.
    return id;
  }

  public String hentNavn() { //metode for aa hente navn.
    return navn;
  }

  public double hentPris() { //metode for aa hente pris.
    return pris;
  }

  public double hentVirkestoff() { //metode for aa hente virkestoffet.
    return virkestoff;
  }

  public void settNyPris(double nyPris) { //metode for aa sette ny pris med argumentet i parameteren.
    pris = nyPris;
  }

  public int hentStyrke() { //Metode for aa hente fram styrke.
    return 0;
  }

  public String toString() { //toString() metode slik at jeg lett kan skrive ut all informasjonen om objektene.
    return navn + " " + "ID: " + id + ": koster opprinnelig " + pris + " kroner. Har virkestoff " + virkestoff + " mg";
  }
}
