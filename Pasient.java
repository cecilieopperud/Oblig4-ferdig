//En Pasient er en typisk bruker av resepter. Pasienten har et navn og et
//fødselsnummer-tekststreng. Når en ny pasient registreres skal denne i tillegg få en unik ID.
//Pasienter har også en liste over reseptene de har fått utskrevet. Siden pasienten ofte vil
//bruke en resept kort tid etter at den er utskrevet, bruker vi en Stabel<Resept> til å lagre
//pasientens resepter. Det skal både være mulig å legge til nye resepter og hente ut hele
//reseptlisten.


public class Pasient {
	int ID; 
	static int teller = 0; //static teller som legges til ID for aa gi hver pasient en unik id.
	String fodselsnummer; 
	String navn;
	Stabel<Resepter> stabel = new Stabel<Resepter>(); //Pasientens stabel med resepter.

	public Pasient(String n, String f){ //gir variabelene verdier og legger til teller paa ID.
    	fodselsnummer = f;
			navn = n;
    	ID = teller ++;
    }

	public String hentFodselsnummer (){ //returnerer fodselsnummer.
		return fodselsnummer;
		}

	public String hentNavn(){ //returnerer pasientens navn.
		return navn;
		}

	public int hentID(){ //returnerer pasientens id.
		return ID;
	}

	public void leggTilResept(Resepter r){ //legger til resept i pasientens stabel.
		stabel.leggPaa(r);
	}

	public Stabel<Resepter> hentReseptene(){ //Returnerer pasientens resepter.
		return stabel;
	}

	@Override
	public String toString() { //returnerer ryddig utskrift med nodvendig informasjon.
		return "Pasient: " + navn + ", Foedselsnummer: " + fodselsnummer + ", ID: " + ID;
	}
}
