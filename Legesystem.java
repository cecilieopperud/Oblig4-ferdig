import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Legesystem{
    private static Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();
    private static Liste<Lege> legeListe = new SortertLenkeliste<Lege>();
    private static Liste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
    private static Liste<Resepter> reseptListe = new Lenkeliste<Resepter>();
    private String fil;
    public Legesystem(String f){
      fil = f;
      gaaGjennom();
    }

    public void gaaGjennom() {
      File filen = new File(fil);
      Scanner innlesing = null;

      try { //try og catch uttalelse for aa sjekke input til innlesing.
        innlesing = new Scanner(filen);
        System.out.println("Fant fil og henter data");
      }

      catch (FileNotFoundException e) { //Dersom det blir error blir stringen under skrevet ut.
        System.out.println("Fant desverre ikke filen");

      }

      int teller = 0;
      while(innlesing.hasNextLine()){
        String denneLinja = innlesing.nextLine();

        if(denneLinja.startsWith("#")){
        teller ++;
        denneLinja = innlesing.nextLine();
      }
        if(teller == 1){

            String[] biter= denneLinja.split(",");
            if(biter.length == 2){
              String navn = biter[0];
              String fnr = biter[1];
              Pasient pasient = new Pasient(navn, fnr);
              pasientListe.leggTil(pasient);

         }
         else{
          feilmelding();
          }
          }

        if(teller == 2){

          String[] biter = denneLinja.split(",");
          if (biter.length == 5){
          String navn = biter[0];
          String type = biter[1];
          double pris = Double.parseDouble(biter[2]);
          double virkestoff = Double.parseDouble(biter[3]);
          int styrke = Integer.parseInt(biter[4]);

          if(type.contains("narkotisk")){
            Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
            legemiddelListe.leggTil(narkotisk);

          }

          else if(type.contains("vanedannende")){
            Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
            legemiddelListe.leggTil(vanedannende);

          }
        }
          else if(biter.length == 4 && biter[1].contains("vanlig")){
            String navn = biter[0];
            double pris = Double.parseDouble(biter[2]);
            double virkestoff = Double.parseDouble(biter[3]);
            Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
              legemiddelListe.leggTil(vanlig);

          }

        else{
          feilmelding();
        }

        }

        if(teller == 3){

          String [] biter = denneLinja.split(",");
          if(biter.length == 2){
          String navn = biter[0];
          int kontrollid = Integer.parseInt(biter[1]);



          if(kontrollid == 0){
            Lege lege = new Lege(navn);
            legeListe.leggTil(lege);
        }

          else{
            Spesialist spesialist = new Spesialist(navn, kontrollid);
            legeListe.leggTil(spesialist);
          }
        }
        else{
          feilmelding();
        }

      }

        if(teller == 4){

          String [] biter = denneLinja.split(",");

              int legemiddelNummer = Integer.parseInt(biter[0]);
              String legeNavn = biter[1];
              int pasientID = Integer.parseInt(biter[2]);
              String type = biter[3];


          Legemiddel legemiddelet = legemiddelListe.hent(legemiddelNummer);
          Pasient riktigPasient = pasientListe.hent(pasientID);
          Lege rettLege = null;

          for(int i = 0; i < legeListe.stoerrelse(); i++){
            Lege legen = legeListe.hent(i);
            if(legen.hentNavn().equals(legeNavn)){
              rettLege = legen;
            }
          }

          Resepter resepten = null;
          try{
            if(type.contains("blaa")){
                resepten = rettLege.skrivBlaaResept(legemiddelet, riktigPasient, Integer.parseInt(biter[4]));

              }

          else if(type.contains("hvit")){
                resepten =  rettLege.skrivHvitResept(legemiddelet, riktigPasient, Integer.parseInt(biter[4]));

                }

          else if(type.contains("militaer")){
              resepten =  rettLege.skrivMillitaerResept(legemiddelet, riktigPasient, Integer.parseInt(biter[4]));

              }

          else if(type.contains("p")){
              resepten = rettLege.skrivPResept(legemiddelet, riktigPasient);

              }
              reseptListe.leggTil(resepten);
              }

          catch(UlovligUtskrift u){
            System.out.println("Ugyldig");
          }
        }
      }
    }

    public void feilmelding(){
      System.out.println("Ugyldig input");
    }

    public void menyLinje(){
      System.out.println("\n\nVelkommen");

      Scanner les = new Scanner(System.in);

      int input = 0;
      while (input != 10) {
        System.out.println("\nHOVEDMENY\n\nValgmuligheter:\n\n-Tast 0 for oversikt over pasienter\n-Tast 1 for leger\n-Tast 2 for legemidler");
        System.out.println("-Tast 3 for resepter\n-Tast 4 for aa legge til en ny pasient\n-Tast 5 for aa legge til en ny lege/spesialist\n-Tast 6 for aa legge til et nytt legemiddel");
        System.out.println("-Tast 7 for aa opprette en ny resept\n-Tast 8 for aa skrive til fil\n-Tast 9 for aa se statistikk\n");
        System.out.println("-Tast 10 for aa avslutte\n");
        input = Integer.parseInt(les.nextLine());

        if(input == 0) {
          System.out.println("Hvilken pasient vil du se resepter for? Skriv inn ID.\n");
          for (int i = 0; i < pasientListe.stoerrelse(); i++) {
            System.out.println(pasientListe.hent(i));
          }
          System.out.println("");
          int pasientId = Integer.parseInt(les.nextLine());
          Resepter riktigResept = null;
          System.out.println("Valgt " + pasientListe.hent(pasientId) + "\n");
          for (int i = 0; i < reseptListe.stoerrelse(); i++){
            if(pasientListe.hent(pasientId).hentID() == reseptListe.hent(i).hentPasientId()){
              riktigResept = reseptListe.hent(i);
              System.out.println("Pasientens resept: " + riktigResept);
              System.out.println("Onsker du aa bruke resepten? Tast 1, om ikke tast 0");
              int svar = Integer.parseInt(les.nextLine());
              if(svar == 1){
                riktigResept.bruk();
                System.out.println("Resepten er brukt\n");
              }
            }
          }
          if (riktigResept == null){
            System.out.println("Pasienten " + pasientListe.hent(pasientId).hentNavn() + " eier ingen resepter");
          }
        }
          if(input == 1) {
            skrivUtlegeListe();
          }

          if(input == 2) {
            skrivUtlegemiddelListe();
          }

          if(input == 3) {
            skrivUtReseptListe();
          }

          if(input == 4){
            System.out.println("Skriv inn navn paa pasient: ");
            String pasientNavn = les.nextLine().toLowerCase();
            System.out.println("Skriv inn foedselsnummer: ");
            String pasientFnr = les.nextLine().toLowerCase();
            Pasient nyPasient = new Pasient(pasientNavn, pasientFnr);
            pasientListe.leggTil(nyPasient);
          }
          if(input == 5){
            System.out.println("Vil du legge til en lege eller en spesialist?");
            String svar = les.nextLine().toLowerCase();
            if(svar.contains("lege")){
              System.out.println("Skriv inn navn paa lege: Oppgi med Dr. + (navnet)");
              String legensNavn = les.nextLine();
              Lege nyLege = new Lege(legensNavn);
              legeListe.leggTil(nyLege);
            }
            else if(svar.contains("spesialist")){
              System.out.println("Skriv inn navn paa spesialist: Oppgi med Dr. + (navnet)");
              String spesialistensNavn = les.nextLine();
              System.out.println("Skriv inn kontroll-ID: ");
              int kontrollen = Integer.parseInt(les.nextLine());
              Spesialist nySpesialist = new Spesialist(spesialistensNavn, kontrollen);
              legeListe.leggTil(nySpesialist);
              }
            else {
              System.out.println("");
              feilmelding();
            }
            }

          if(input == 6){
            System.out.println("Skriv inn legemiddel (narkotisk, vanlig eller vanedannende): ");
            String svaret = les.nextLine().toLowerCase();
            if(svaret.contains("narkotisk")){
              System.out.println("Skriv inn navn: ");
              String n = les.nextLine().toLowerCase();
              System.out.println("Skriv inn pris: ");
              double p = Double.parseDouble(les.nextLine());
              System.out.println("Skriv inn stoff: ");
              double s = Double.parseDouble(les.nextLine());
              System.out.println("Skriv inn styrke: ");
              int sty = Integer.parseInt(les.nextLine());
              Narkotisk nyNark = new Narkotisk(n, p, s, sty);
              legemiddelListe.leggTil(nyNark);
            }
            else if(svaret.contains("vanlig")){
              System.out.println("Skriv inn navn: ");
              String n = les.nextLine().toLowerCase();
              System.out.println("Skriv inn pris: ");
              double p = Double.parseDouble(les.nextLine());
              System.out.println("Skriv inn stoff: ");
              double s = Double.parseDouble(les.nextLine());
              Vanlig nyVanlig = new Vanlig(n, p, s);
              legemiddelListe.leggTil(nyVanlig);
            }
            else if(svaret.contains("vanedannende")){
              System.out.println("Skriv inn navn: ");
              String n = les.nextLine().toLowerCase();
              System.out.println("Skriv inn pris: ");
              double p = Double.parseDouble(les.nextLine());
              System.out.println("Skriv inn stoff: ");
              double s = Double.parseDouble(les.nextLine());
              System.out.println("Skriv inn styrke: ");
              int sty = Integer.parseInt(les.nextLine());
              Vanedannende nyVane = new Vanedannende(n, p, s, sty);
              legemiddelListe.leggTil(nyVane);
            }
            else{
              System.out.println("");
              feilmelding();
            }
          }
          if(input == 7){
            System.out.println("Hva slags type resept vil du opprette? Hvit, blaa, militaer eller p-resept?");
            String svar = les.nextLine().toLowerCase();
            Resepter resepten = null;
            if(svar.contains("hvit") || svar.contains("blaa") || svar.contains("militaer") || svar.contains("p-resept")){
              System.out.println("Velg legemiddel: Tast inn id");

              skrivUtlegemiddelListe();
              int lm = Integer.parseInt(les.nextLine());

              System.out.println("Velg lege: ");
              skrivUtlegeListe();
              String l = (les.nextLine().toLowerCase());

              System.out.println("Velg pasient: Tast inn id");
              skrivUtpasientListe();
              int p = Integer.parseInt(les.nextLine());

              Legemiddel legemiddel = legemiddelListe.hent(lm);
              Pasient pasient = pasientListe.hent(p);
              Lege lege = null;
              for(int i = 0; i < legeListe.stoerrelse(); i++){
                if(legeListe.hent(i).hentNavn().contains(l)){
                  lege = legeListe.hent(i);
                }
              }
              if(lege == null || pasient == null || legemiddel == null){
                feilmelding();
              }
              try{
                if(svar.contains("militaer")){
                  System.out.println("Skriv inn reit: ");
                  int reit = Integer.parseInt(les.nextLine());
                  resepten = lege.skrivMillitaerResept(legemiddel, pasient, reit);

                  System.out.println("Reseptet er naa opprettet og lagt til");
                }
                else if(svar.contains("hvit")){
                  System.out.println("Skriv inn reit: ");
                  int reit = Integer.parseInt(les.nextLine());
                  resepten = lege.skrivHvitResept(legemiddel, pasient, reit);
                  System.out.println("Reseptet er naa opprettet og lagt til");
                }
                else if(svar.contains("blaa")){
                  System.out.println("Skriv inn reit: ");
                  int reit = Integer.parseInt(les.nextLine());
                  resepten = lege.skrivBlaaResept(legemiddel, pasient, reit);

                  System.out.println("Reseptet er naa opprettet og lagt til");
                }
                else if(svar.contains("p-resept")){
                  resepten = lege.skrivPResept(legemiddel, pasient);
                  System.out.println("Reseptet er naa opprettet og lagt til");
              }
              if(resepten == null){
                feilmelding();
              }
              else{
              reseptListe.leggTil(resepten);
            }
            }
            catch (UlovligUtskrift u){
              System.out.println(u);
            }
          }
        }

        if(input == 8){
          File fil = new File("nyFil.txt");
          try{
            PrintWriter skrivTilFil = new PrintWriter(fil);
            skrivTilFil.println("# Pasienter (navn, fnr)");
            for (int i = 0; i < pasientListe.stoerrelse(); i++) {
              String pasientnavn = pasientListe.hent(i).hentNavn();
              String fnr = pasientListe.hent(i).hentFodselsnummer();
              skrivTilFil.println(pasientnavn + "," + fnr);
            }
            skrivTilFil.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for(int i = 0; i < legemiddelListe.stoerrelse(); i++) {
              Legemiddel legemiddel = legemiddelListe.hent(i);
              String legemiddelnavn = legemiddel.hentNavn();
              double pris = legemiddel.hentPris();
              double stoff = legemiddel.hentVirkestoff();
              if (legemiddelListe.hent(i) instanceof Vanedannende){
                skrivTilFil.println(legemiddelnavn + ",vanedannende," + pris + "," + stoff + "," + legemiddelListe.hent(i).hentStyrke());
              }
              else if (legemiddelListe.hent(i) instanceof Narkotisk){
                skrivTilFil.println(legemiddelnavn + ",narkotisk," + pris + "," + stoff + "," + legemiddelListe.hent(i).hentStyrke());
              }
              else{
                skrivTilFil.println(legemiddelnavn + ",vanlig," + pris + "," + stoff);
              }
            }
            skrivTilFil.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for(int i = 0; i < legeListe.stoerrelse(); i++){
              if(legeListe.hent(i) instanceof Spesialist){
                String navnet = legeListe.hent(i).hentNavn();
                skrivTilFil.println(navnet + "," + legeListe.hent(i).hentKontrollID());
              }
              else{
                String navn = legeListe.hent(i).hentNavn();
                skrivTilFil.println(navn + "," + "0");
              }
            }
            skrivTilFil.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for(int i = 0; i < reseptListe.stoerrelse(); i++) {
              Resepter resept = reseptListe.hent(i);
              int legemiddelNummer = resept.hentLegemiddel().hentId();
              String legeNavn = resept.hentLege().hentNavn();
              String farge = resept.farge();
              if(resept instanceof PResept){
              skrivTilFil.println(legemiddelNummer + "," + legeNavn + "," + farge);
              }
            else {
              int reit = resept.hentReit();
              skrivTilFil.println(legemiddelNummer + "," + legeNavn + "," + farge + "," + reit );
              }
            }
            BufferedReader br = new BufferedReader(new FileReader(fil));
            String line;
            while ((line = br.readLine()) != null) {
              System.out.println(line);
            }
            System.out.println("\nHar naa skrevet all informasjon til fil");
            skrivTilFil.close();
         }

        catch(IOException e){
           System.out.println("Ugyldig");
         }
}
       if(input == 9){
         System.out.println("\nSTATISTIKK");
         totaltAntallnarkotisk();
         totaltAntallvanedannende();
         pasienterGyldig();
       }

    }
}
    private void skrivUtpasientListe(){
      for (int i = 0; i < pasientListe.stoerrelse(); i++){
        System.out.println(pasientListe.hent(i));
      }
    }
    private void skrivUtlegemiddelListe(){
      for(int i = 0; i < legemiddelListe.stoerrelse(); i++) {
        System.out.println(legemiddelListe.hent(i));
      }
    }
    private void skrivUtlegeListe(){
      for(int i = 0; i < legeListe.stoerrelse(); i++) {
        System.out.println(legeListe.hent(i));
      }
    }
    private void skrivUtReseptListe(){
      for(int i = 0; i < reseptListe.stoerrelse(); i++) {
        System.out.println(reseptListe.hent(i));
      }
    }
    private void totaltAntallvanedannende(){
      int teller = 0;
      for (int i = 0; i< legeListe.stoerrelse(); i ++){
        Lege legen = legeListe.hent(i);
        Lenkeliste<Resepter> utResepter = legen.hentUtResepter();
        for(int j = 0; j< utResepter.stoerrelse(); j++){
          if(utResepter.hent(j).hentLegemiddel() instanceof Vanedannende){
              teller ++;
          }
        }
      }
        System.out.println("Totalt antall vanedannende legemidler som er skrevet ut som resept:  " + teller);
    }
    private void totaltAntallnarkotisk(){
      int teller = 0;
      System.out.println("Oversikt over narkotiske legemidler pr Lege: \n");
      for (int i = 0; i< legeListe.stoerrelse(); i ++){
        Lege legen = legeListe.hent(i);
        int legerTeller = 0;
        Lenkeliste<Resepter> utResepter = legen.hentUtResepter();
        for(int j = 0; j< utResepter.stoerrelse(); j++){
          if(utResepter.hent(j).hentLegemiddel() instanceof Narkotisk){
            legerTeller ++;
              teller++;
          }

        }
          System.out.print(legen.hentNavn() + " har skrevet ut " + legerTeller + " narkotiske legemidler \n");
      }
      System.out.println("\nTotalt antall narkotiske legemidler som er skrevet ut som resept:  " + teller);
  }
  private void pasienterGyldig(){
    int teller = 0;
    System.out.println("\nOversikt over narkotiske legemidler pr Pasient: \n");
    for (int i = 0; i< pasientListe.stoerrelse(); i ++){
      Pasient pasient = pasientListe.hent(i);
      int pteller = 0;
      Stabel<Resepter> utResepter = pasient.hentReseptene();
      for(int j = 0; j< utResepter.stoerrelse(); j++){
        if(utResepter.hent(j).hentLegemiddel() instanceof Narkotisk){
          pteller ++;
            teller++;
        }

      }
        System.out.print(pasient.hentNavn() + " har " + pteller + " gyldig resept for narkotiske legemidler \n");
    }
        System.out.println("\nTotalt antall narkotiske legemidler som er skrevet ut som resept:  " + teller);
  }
}
