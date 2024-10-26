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

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }

    public int getVarasto_kpl() {
        return varasto_kpl;
    }

    public void lisaaVarasto_kpl(int maara) {
        this.varasto_kpl += maara;
    }

    public boolean reduceVarasto_kpl(int maara){
        if (this.varasto_kpl >= maara) {
            this.varasto_kpl -= maara;
            return true;
        }
        return false;

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
