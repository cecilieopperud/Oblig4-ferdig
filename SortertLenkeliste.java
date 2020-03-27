public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
  public SortertLenkeliste() { //Konstruktoer.
    super(); //Aksesserer superklassens Konstruktoer.
  }

  @Override
  public void leggTil(T x) { //Metoden legger til elementer i rekkefolge.
    Node nyNode = new Node(x);
    if (forst.neste == null) { //Dersom forst sin neste er lik null.
      forst.neste = nyNode; //Setter jeg den til aa vere nyNode.
    }
    else { //Hvis ikke utforer den det under.
      Node denneNoden = forst; //Oppretter denneNode for aa kunne itere gjennom listen.

      //Saa lenge neste noden ikke er lik null og denne denneNoden er storre eller lik x.
      while (denneNoden.neste != null && denneNoden.neste.verdi.compareTo(x)<0) {
          denneNoden = denneNoden.neste;
      }
      if(denneNoden.neste != null){ //Dersom den neste noden ikke er lik null.
        nyNode.neste = denneNoden.neste; //Setter jeg nyNode sin neste til aa vere denneNoden sin neste.
      }
      denneNoden.neste = nyNode; //denneNoden sin neste settes til aa vere lik nyNode.
    }
  }

  @Override
  public T fjern() { //Fjerner det storste elementet i listen.
    int lengdePaaListen = this.stoerrelse(); //lagrer lengden paa listen i lengdePaaListen.
    return fjern(lengdePaaListen-1); //Storste elementet vil vere bakerst slik at jeg kaller paa fjern(int pos) med siste elementet.
  }

  @Override
  public void leggTil(int pos, T x) { //kaster unntaket UnsupportedOperationException.
    throw new UnsupportedOperationException();
  }

  @Override
  public void sett(int pos, T x) { //Kaster unntaket UnsupportedOperationException.
    throw new UnsupportedOperationException();
  }
}
