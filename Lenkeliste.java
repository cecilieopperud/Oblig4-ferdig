
import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> { //implementerer grensesnittet.
  public Node forst = new Node(null);   //Deklarerer og opprettter forst som Node med verdien null.

  public class Node { //Indre klasse Node.
    T verdi; //Deklarerer verdi som type.
    Node neste = null;   //Deklarer neste som Node.

    public Node(T v) { //Setter verdien til parameteren til aa vere verdi.
      verdi = v;
    }
  }

  public int stoerrelse() { //Metode som returnerer storrelse/lengde paa lista.
    int antall = 0; //Deklarer og oppretter antall som 0 til å begynne med.
    Node denneNoden = forst; //Node for aa iterere gjennom listen.
    while(denneNoden.neste != null) { //Saa lenge neste noden ikke er null.
      antall++; //Legger jeg til en i antall.
      denneNoden = denneNoden.neste; //og setter denneNoden til den neste noden.
    }
    return antall; //Returnerer antallet.
  }

  @Override
  public void leggTil(T x) { //Metode leggTil som legger til paa slutten av lista.
    Node nyNode = new Node(x); //Oppretter nyNode med verdien x.
    if (forst.neste == null) { //Dersom den andre noden i lista er null.
      forst.neste = nyNode; //setter den til aa vere nyNode.
    }
    else { //Hvis ikke, utforer den under.
      Node naaVerendeNode = forst; //Node for aa iterere gjennom lista.
      while (naaVerendeNode.neste != null) { //Saa lenge naaVerendeNode sin neste ikke er null.
        naaVerendeNode = naaVerendeNode.neste; //saa setter jeg naaVerendeNode til aa vere den neste noden.
      }
      naaVerendeNode.neste = nyNode; //nyNode settes til aa vere den siste i listen.
    }
  }

  @Override
  public T fjern() { //Metoden fjern skal fjerne elementet i starten av listen.
    if (forst.neste==null) { //dersom den andre elementet i listen er lik null.
      throw new UgyldigListeIndeks(-1); //Kaster -1 med unntaket dersom det er true.
    }
    else{
      Node denneNoden = forst.neste; //Oppretter denneNoden til aa vere lik den forste sin nest node.
      forst.neste = denneNoden.neste; ////Forst sin neste node settes til aa vere denneNoden sin neste.
      return denneNoden.verdi; //Returnerer denneNoden sin verdi.
    }
  }

  @Override
  public void sett(int pos, T x) { //Metoden sett skal overskrive et element paa den gitte posisjonen.
    if (stoerrelse() <= 0 || pos < 0 || pos >= stoerrelse()) {
      throw new UgyldigListeIndeks(pos); //Kaster posisjonen med unntaket dersom det er true.
    }
    Node nyNode = forst; //Setter nyNode til aa vere den forste noden.
    for(int i = 0; i < pos; i++) { //Itererer saa gjennom listen til den har naadd posisjonen.
      nyNode = nyNode.neste;
    }
    nyNode.neste.verdi = x; //Setter x til a vere verdien til nyNode sin neste.
  }

  @Override
  public void leggTil(int pos, T x) { //Legger til et nytt element i posisjonen og skyver det bak fremover.
    if (pos < 0 || pos > stoerrelse()) {
      throw new UgyldigListeIndeks(pos); //Kaster posisjonen med unntaket dersom det er true.
    }
    else if (pos == 0) { //Dersom bare den gitte posisjonen er lik 0.
      Node nyNode = new Node(x); //Oppretter ny node med x.
      nyNode.neste = forst.neste; //Setter neste noden til nyNode til aa vere forst sin neste.
      forst.neste = nyNode; //Setter saa forst til aa vere nyNode.
    }
    else if (pos == stoerrelse()) { //Dersom den gitte posisjonen er lik storrelse.
      leggTil(x); //Legger da til noden paa slutten av listen.
    }
    else { //Hvis ingen av de over er true.
      Node nyNode = new Node(x); //Oppretter nyNode med x.
      Node denneNoden = forst; //Oppretter denneNoden for aa kunne iterere gjennom listen.

      int posisjon = 0; //Oppretter variabelen posisjon for aa iterere gjennnom listen.
      while(posisjon < pos) { //Itererer gjennom listen.
        denneNoden = denneNoden.neste;
        posisjon++;
      }

      nyNode.neste = denneNoden.neste; //Setter nyNodens neste til aa vere denneNodens neste.
      denneNoden.neste = nyNode; //denneNoden sin neste node settes til aa vere den nye noden.
    }
  }

  @Override
  public T fjern(int pos) { //Metoden fjern som fjerner et element paa den gitte posisjonen.
    if (pos<0 || pos>= stoerrelse()) {
      throw new UgyldigListeIndeks(pos); //Kaster posisjonen med unntaket dersom det er true.
    }
    Node denneNoden = forst; //Oppretter denneNoden til aa vere forst, saa jeg kan itere gjennom lista.
    for (int i = 0; i < pos; i++) {
      denneNoden = denneNoden.neste; //itererer til den kommer paa gitt posisjon.
    }
    Node skalFjernes = denneNoden.neste; //Oppretter noden skalFjernes til aa vere denneNoden sin neste.
    denneNoden.neste = skalFjernes.neste; //denneNoden sin neste settes til aa vere skalFjernes sin neste.
    return skalFjernes.verdi; //Returnerer verdien til skalFjernes.

  }

  @Override
  public T hent(int pos) { //Metoden henter ut elementet paa oppgitt indeks.
    if (stoerrelse() <= 0 || pos < 0 || pos >= stoerrelse()) {
      throw new UgyldigListeIndeks(pos); //Kaster posisjonen med unntaket dersom det er true.
    }
    Node oppgittNode = forst.neste; //Oppretter oppgittNode til aa vere forst slik at jeg kan iterere gjennom listen.
    for (int i = 0; i < pos; i++) {
      oppgittNode = oppgittNode.neste;
    }
    return oppgittNode.verdi; //returnerer verdien til elementet paa den oppgitte indeksen.
  }



  public LenkelisteIterator iterator(){
      return new LenkelisteIterator(this);
    }


  public class LenkelisteIterator implements Iterator<T>{
    private int indeks = 0;
    private Liste<T> liste;

    public LenkelisteIterator(Liste<T> n){
    liste = n;
    }
    @Override
    public T next(){
      return liste.hent(indeks++);
    }
    @Override
    public boolean hasNext(){
      return indeks<liste.stoerrelse();
  }
  @Override
  public void remove(){
    throw new UnsupportedOperationException();
  }
}
}
