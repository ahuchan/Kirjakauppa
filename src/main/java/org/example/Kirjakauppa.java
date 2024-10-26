package org.example;

import java.util.*;

public class Kirjakauppa {
    private KirjakauppaDB kirjakauppaDB;
    private Map<Integer, Asiakas> asiakkaat;
    private int nextAsiakasId;


// Käytetään Map -rakenteita kirjojen ja asiakkaiden käsittelyyn.
// ISBN-koodin ja asiakasnron perusteella.


    public Kirjakauppa() {
        kirjakauppaDB = new KirjakauppaDB();
        asiakkaat = new HashMap<>();
        nextAsiakasId = 1;
    }

    public KirjakauppaDB getKirjakauppaDB() {
        return kirjakauppaDB;
    }

    public void lisaaAsiakas(String etunimi, String sukunimi) {
        asiakkaat.put(nextAsiakasId, new Asiakas(nextAsiakasId, etunimi, sukunimi));
        System.out.println("Asiakas lisätty (ID): " + nextAsiakasId);
        nextAsiakasId++;
    }

    public Asiakas getAsiakasId(int asiakasId) {
        return asiakkaat.get(asiakasId);
    }

    public void poistaAsiakas(int asiakasId) {
        asiakkaat.remove(asiakasId);
    }

    public void kirjaaOsto(int asiakasId, String isbn) {
        Asiakas asiakas = asiakkaat.get(asiakasId);

        if (asiakas != null && kirjakauppaDB.reduceBookQuantity(isbn, 1)) {
            asiakas.lisaaOstotapahtuma(isbn);
            System.out.println("Tilaus rekisteröity!");
            System.out.println("Kirja: " + isbn);
            System.out.println("Tilaaja: " + asiakas.getEtunimi());
        } else {
            System.out.println("Tilaus epäonnistui");
        }
    }

    public void tulostaOstoHistoria(int asiakasId) {
        Asiakas asiakas = asiakkaat.get(asiakasId);
        if (asiakas != null) {
            System.out.println("Ostohistoria asiakkaalle " + asiakas.getEtunimi() + ":");
            for (String osto : asiakas.getOstoHistoria()) {
                System.out.println(osto);
            }
        } else {
            System.out.println("Asiakas ei löydy");
        }
    }
}
