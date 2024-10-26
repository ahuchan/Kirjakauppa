package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kirjakauppa kirjakauppa = new Kirjakauppa();
        KirjakauppaDB kirjakauppaDB = new KirjakauppaDB();

        while (true) {
            System.out.println("1. Lisää kirja varastoon");
            System.out.println("2. Päivitä kirjan tietoja");
            System.out.println("3. Tarkastele kirjan tietoja ISBN tunnuksella");
            System.out.println("4. Tarkastele varastoa");
            System.out.println("5. Lisää asiakasta");
            System.out.println("6. Tarkastele asiakasta");
            System.out.println("7. Poista asiakas");
            System.out.println("8. Rekisteröi ostotapahtuma");
            System.out.println("9. Tarkastele ostohistoriaa");
            System.out.println("10. Näytä asiakkaat");
            System.out.println("0. Poistu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Syötä ISBN:");
                    String isbn = scanner.nextLine();
                    System.out.println("Syötä nimi:");
                    String nimi = scanner.nextLine();
                    System.out.println("Syötä kirjailija:");
                    String kirjailija = scanner.nextLine();
                    System.out.println("Syötä määrä:");
                    int varasto_kpl = scanner.nextInt();
                    kirjakauppa.getKirjakauppaDB().lisaaKirja(isbn, nimi, kirjailija, varasto_kpl);
                    break;
                case 2:
                    System.out.println("Syötä ISBN:");
                    isbn = scanner.nextLine();
                    System.out.println("Syötä nimi:");
                    nimi = scanner.nextLine();
                    System.out.println("Syötä kirjailija:");
                    kirjailija = scanner.nextLine();
                    System.out.println("Syötä määrä:");
                    varasto_kpl = scanner.nextInt();
                    kirjakauppa.getKirjakauppaDB().updateKirja(isbn, nimi, kirjailija, varasto_kpl);
                    break;
                case 3:
                    System.out.println("Syötä ISBN:");
                    isbn = scanner.nextLine();
                    Kirja kirja = kirjakauppa.getKirjakauppaDB().getBookByISBN(isbn);
                    if (kirja != null) {
                        System.out.println(kirja);
                    } else {
                        System.out.println("Kirja ei löydy");
                    }
                    break;
                case 4:
                    kirjakauppa.getKirjakauppaDB().tulostaKirjatSortedVarasto();
                    break;
                case 5:
                    System.out.println("Syötä asiakkaan etunimi:");
                    String etuNimi = scanner.nextLine();
                    System.out.println("Syötä asiakkaan sukunimi:");
                    String sukuNimi = scanner.nextLine();
                    kirjakauppaDB.lisaaAsiakas(etuNimi, sukuNimi);
                    break;
                case 6:
                    System.out.println("Syötä asiakkaan ID:");
                    int asiakasId = scanner.nextInt();
                    Asiakas asiakas = kirjakauppaDB.getAsiakasById(asiakasId);
                    if (asiakas != null) {
                        System.out.println(asiakas);
                    } else {
                        System.out.println("Asiakasta ei löydy");
                    }
                    break;
                case 7:
                    System.out.println("Syötä asiakkaan ID:");
                    asiakasId = scanner.nextInt();
                    kirjakauppa.getKirjakauppaDB().poistaAsiakas(asiakasId);
                    break;
                case 8:
                    System.out.println("Syötä asiakkaan ID:");
                    asiakasId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Syötä ISBN:");
                    isbn = scanner.nextLine();

                    boolean success = kirjakauppaDB.reduceBookQuantity(isbn, 1);
                    if (success) {
                        kirjakauppaDB.lisaaOstotapahtuma(asiakasId, isbn);
                    } else {
                        System.out.println("Varaston vähennys epäonnistui, ostotapahtumaa ei rekisteröity.");
                    }
                    break;
                case 9:
                    System.out.println("Syötä asiakkaan ID:");
                    asiakasId = scanner.nextInt();
                    kirjakauppaDB.getPurchaseHistoryForCustomer(asiakasId);
                    break;
                case 10:
                    List<Asiakas> asiakkaat = kirjakauppaDB.getAllCustomers();
                    if (asiakkaat.isEmpty()) {
                        System.out.println("Ei asiakkaita.");
                    } else {
                        for (Asiakas asiakas1 : asiakkaat) {
                            System.out.println(asiakas1); // Will use the new toString() method to print each customer's details
                        }
                    }
                    break;
                case 0:
                    System.out.println("Poistutaan...");
                    return;

                default:
                    System.out.println("Yritä uudelleen & valitse luku väliltä 0-10 ");
            }
        }
    }
}