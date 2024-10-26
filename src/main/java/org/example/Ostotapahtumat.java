package org.example;

public class Ostotapahtumat {
    private int id;
    private int asiakasId;
    private String kirjaIsbn;

    public Ostotapahtumat(int id, int asiakasId, String kirjaIsbn) {
        this.id = id;
        this.asiakasId = asiakasId;
        this.kirjaIsbn = kirjaIsbn;
    }

    public int getId() {
        return id;
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public String getKirjaIsbn() {
        return kirjaIsbn;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Asiakas ID: " + asiakasId + ", Kirja ISBN: " + kirjaIsbn;
    }
}
