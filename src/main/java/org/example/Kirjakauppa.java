package org.example;

import java.util.*;

public class Kirjakauppa {
    private KirjakauppaDB kirjakauppaDB;
    private Map<Integer, Asiakas> asiakkaat;
    private int nextAsiakasId;


    public Kirjakauppa() {
        kirjakauppaDB = new KirjakauppaDB();
        asiakkaat = new HashMap<>();
        nextAsiakasId = 1;
    }

    public KirjakauppaDB getKirjakauppaDB() {
        return kirjakauppaDB;
    }

}
