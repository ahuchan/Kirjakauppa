package org.example;

import java.util.ArrayList;
import java.util.List;

// Tämä luokka vastaa asiakkaiden tiedoista

public class Asiakas {
    private int asiakasId;
    private String etunimi;
    private String sukunimi;
    private List<String> ostoHistoria;

    public Asiakas(int asiakasId, String etunimi, String sukunimi){
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.ostoHistoria = new ArrayList<>();
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void lisaaOstotapahtuma(String kirjaInfo) {
        ostoHistoria.add(kirjaInfo);
    }

    public List<String> getOstoHistoria() {
        return ostoHistoria;
    }

    @Override
    public String toString() {
        return "Asiakastunnus: " + asiakasId + ", Nimi: " + etunimi + ", Sukunimi: " + sukunimi;
    }
}
