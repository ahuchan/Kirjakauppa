# Kirjakaupan hallintajärjestelmä

Java-pohjainen sovellus kirjakaupan varaston, asiakastietojen 
ja ostohistorian hallintaan. Tämä järjestelmä muodostaa yhteyden 
relaatiotietokantaan kirjojen, asiakkaiden ja tapahtumien seuraamiseksi.


## Ominaisuudet

### Varastonhallinta:
Lisää, päivitä ja hae kirjan tietoja (ISBN, nimi, tekijä & varasto).

### Asiakashallinta:
Lisää uusia asiakkaita luoduilla tunnuksilla ja tarkastele asiakastietoja (etunimi, sukunimi, id).

### Ostosten seuranta:
Tallenna ja hae asiakkaan ostohistoria.

### Tietokanta:
SQL ja JDBC CRUD-toimintoihin relaatiotietokannassa.

## Ohjelma:
Java: Ohjelmointikieli

SQL: Tietokanta

JDBC: Yhteys tietokantaan

## :

1. Varmista, että käytössä on JDK 11+ ja MySQL.
2. Tietokanta: 

    Lisää seuraavat pöydät omaan tietokantaan:

   CREATE TABLE kirjat (
   isbn VARCHAR(20) PRIMARY KEY,
   nimi VARCHAR(100),
   kirjailija VARCHAR(100),
   varasto_kpl INT
   );

    CREATE TABLE asiakkaat (
    id VARCHAR(6) PRIMARY KEY,
    etunimi VARCHAR(50),
    sukunimi VARCHAR(50)
    );
    
    CREATE TABLE ostotapahtumat (
    asiakas_id VARCHAR(6),
    kirja_isbn VARCHAR(20),
    FOREIGN KEY (asiakas_id) REFERENCES asiakkaat(id),
    FOREIGN KEY (kirja_isbn) REFERENCES kirjat(isbn)
    );

3. Kloonaus:

   git clone https://github.com/ahuchan/kirjakauppa.git

4. Tietokannan konfiguraatio:

   Muokkaa DatabaseConnection.java oman tietokannan URL-osoitteella, 
   käyttäjätunnuksella ja salasanalla.

5. Suorita sovellus:
    
    Ajaa Main -luokkaa.

## Käyttö:
1. Lisää kirja: syötä kirjan tiedot, jotta kirja lisätään tietokantaan
2. Lisää asiakas: syötä asiakkaan tiedot, jotta asiakas lisätään tietokantaan
3. Tallenna ostotapahtuma: syötä asiakkaan ID ja kirjan ISBN, jotta tapahtuma rekisteröidään.
    
