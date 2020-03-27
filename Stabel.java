class Stabel<T> extends Lenkeliste<T> { //Arver Lenkeliste<T> sine egenskaper.
  public Stabel() { //Konstruktoer.
    super(); //Aksesserer konstruktoerens super.
  }

  public void leggPaa(T x) { //Legger til et element paa slutten av listen.
    super.leggTil(x); //Bruker super ved aa kalle paa metoden leggTil med x.
  }

  public T taAv() { //Fjerner siste elementet i lista.
    int lengdePaaListen = this.stoerrelse(); //Lagrer lengden til lista i en variabel.
    return fjern(lengdePaaListen-1); //Returnerer elementet som fjernes fra slutten av lista.
  }
}
