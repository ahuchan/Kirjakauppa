package org.example;

public class Kirja implements Comparable<Kirja>{

    private String isbn;
    private String nimi;
    private String kirjailija;
    private int varasto_kpl;

    public Kirja(String isbn, String nimi, String kirjailija, int varasto_kpl) {
        this.isbn = isbn;
        this.nimi = nimi;
        this.kirjailija = kirjailija;
        this.varasto_kpl = varasto_kpl;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public int getVarasto_kpl() {
        return varasto_kpl;
    }

    @Override
    public int compareTo(Kirja other) {
        return Integer.compare(this.varasto_kpl, other.varasto_kpl);
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Nimi: " + nimi + ", Kirjailija: " + kirjailija + ",Varasto: " + varasto_kpl;
    }
}
