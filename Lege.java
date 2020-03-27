class Lege implements Comparable<Lege> { //Oppretter klassen Lege.
  String legeNavn; //protected variabel legeNavn slik at den er tilgjengelig for subklasser.
  Lenkeliste<Resepter> utskrevedeResepter = new Lenkeliste<Resepter>();
  public Lege(String navn) { //Konstruktoer som tar imot en String navn.
    legeNavn = navn; //settes som verdien til legeNavn.

  }

  public int hentKontrollID() { //Metode for aa returnere 0 for lege.
    return 0;
  }

  public String hentNavn() { //Metode for aa hente legeNavn.
    return legeNavn;
  }

  public String toString() { //Metode for aa returnere en string til utskrift.
    return legeNavn;
  }

  @Override
  public int compareTo(Lege lege){ 
    return (legeNavn.compareTo(lege.hentNavn()));
  }

  public Lenkeliste<Resepter> hentUtResepter(){ //Returnerer de utskrevede reseptene til legen.
    return utskrevedeResepter;
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{ //Oppretter hvit resept.
    HvitResept hResept = new HvitResept(legemiddel , this , pasient, reit);
    if (hResept.hentLegemiddel() instanceof Narkotisk) { //Dersom det er narkotisk.
      throw new UlovligUtskrift(this, legemiddel, pasient.hentID());  //Kaster unntaket UlovligUtskrift.
    }
    else{ //Hvis ikke legges reseptet til i legens utskrevede resepter og pasientens resepter.
      utskrevedeResepter.leggTil(hResept);
      pasient.leggTilResept(hResept);
      return hResept;
    }
  }

  public MilaeterResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { //Oppretter hvit resept.
    MilaeterResept mResept = new MilaeterResept(legemiddel, this, pasient, reit);
    if (mResept.hentLegemiddel() instanceof Narkotisk) { //Dersom det er narkotisk.
      throw new UlovligUtskrift( this, legemiddel, pasient.hentID()); //Kaster unntaket UlovligUtskrift.
    }
    else{ //Hvis ikke legges reseptet til i legens utskrevede resepter og pasientens resepter.
      utskrevedeResepter.leggTil(mResept);
      pasient.leggTilResept(mResept);
      return mResept;
    }
  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{ //Oppretter hvit resept.
    PResept pResept = new PResept(legemiddel , this, pasient);
      if (pResept.hentLegemiddel() instanceof Narkotisk){ //Dersom det er narkotisk.
        throw new UlovligUtskrift( this, legemiddel, pasient.hentID()); //Kaster unntaket UlovligUtskrift.
      }
      else{ //Hvis ikke legges reseptet til i legens utskrevede resepter og pasientens resepter.
        utskrevedeResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);
        return pResept;
      }
    }

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { //Oppretter hvit resept.

    BlaaResept bResept = new BlaaResept(legemiddel , this , pasient, reit);

    if (bResept.hentLegemiddel() instanceof Narkotisk) { //Dersom det er narkotisk.
      throw new UlovligUtskrift(this, legemiddel, pasient.hentID()); //Kaster unntaket UlovligUtskrift.
    }

    else{ //Hvis ikke legges reseptet til i legens utskrevede resepter og pasientens resepter.
      utskrevedeResepter.leggTil(bResept);
      pasient.leggTilResept(bResept);
      return bResept;
    }
  }

}
