package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class KirjakauppaDB {
    private DatabaseConnection dbConn;

    public KirjakauppaDB() {
        dbConn = new DatabaseConnection();
    }

    public void lisaaKirja(String isbn, String nimi, String kirjailija, Integer varasto_kpl) {
        String query = "INSERT INTO Kirjat (isbn, nimi, kirjailija, varasto_kpl) VALUES (?, ? ,?, ?)";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            stmt.setString(2, nimi);
            stmt.setString(3, kirjailija);
            stmt.setInt(4, varasto_kpl);
            stmt.executeUpdate();
            System.out.println("Kirja lisätty tietokantaan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKirja(String isbn, String nimi, String kirjailija, Integer varasto_kpl) {
        String query = "UPDATE Kirjat SET nimi = ?, kirjailija = ?, varasto_kpl = ? WHERE isbn = ?";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nimi);
            stmt.setString(2, kirjailija);
            stmt.setString(3, String.valueOf(varasto_kpl));
            stmt.setString(4, isbn);
            stmt.executeUpdate();
            System.out.println("Kirja päivitetty tietokannassa.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kirja getBookByISBN(String isbn) {
        String query = "SELECT * FROM Kirjat WHERE isbn = ?";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Kirja kirja = new Kirja(
                        rs.getString("isbn"),
                        rs.getString("nimi"),
                        rs.getString("kirjailija"),
                        rs.getInt("varasto_kpl")
                );
                return kirja;
            } else {
                System.out.println("Mikään kirjaa ei löytyny kyseisellä ISBN:llä: ");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void lisaaAsiakas(String etunimi, String sukunimi) {
        String query = "INSERT INTO asiakkaat (etunimi, sukunimi) VALUES (?, ?)";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, etunimi);
            stmt.setString(2, sukunimi);
            stmt.executeUpdate();
            System.out.println("Asiakas lisätty tietokantaan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaAsiakas(int asiakasId) {
        String query = "DELETE FROM Asiakkaat WHERE id = ?";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, asiakasId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Asiakasta poistettu onnistuneesti!");
            } else {
                System.out.println("Asiakasta ei löytynyt ID:llä " + asiakasId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Asiakas getAsiakasById(int asiakasId) {
        String query = "SELECT * FROM asiakkaat WHERE id = ?";
        Asiakas asiakas = null;

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, asiakasId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String etunimi = rs.getString("etunimi");
                String sukunimi = rs.getString("sukunimi");
                asiakas = new Asiakas(asiakasId, etunimi, sukunimi);

                String historyQuery = "SELECT kirja_isbn FROM ostotapahtumat WHERE asiakas_id = ?";
                try (PreparedStatement historyStmt = conn.prepareStatement(historyQuery)) {
                    historyStmt.setInt(1, asiakasId);
                    ResultSet historyRs = historyStmt.executeQuery();

                    while (historyRs.next()) {
                        String bookIsbn = historyRs.getString("kirja_isbn");
                        asiakas.lisaaOstotapahtuma(bookIsbn);
                    }
                }
            } else {
                System.out.println("Asiakas ei löytynyt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asiakas;
    }

    public void lisaaOstotapahtuma(int asiakasId, String isbn) {
        String query = "INSERT INTO ostotapahtumat (asiakas_id, kirja_isbn) VALUES (?, ?)";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, asiakasId);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
            System.out.println("Ostotapahtuma lisätty historiaan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPurchaseHistoryForCustomer(int asiakasId) {
        String query = "SELECT o.kirja_isbn, k.nimi, k.kirjailija FROM ostotapahtumat o JOIN kirjat k ON o.kirja_isbn = k.isbn WHERE o.asiakas_id = ?";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, asiakasId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String kirjaIsbn = rs.getString("kirja_isbn");
                String kirjaNimi = rs.getString("nimi");
                System.out.println("ISBN: " + kirjaIsbn + " Kirjan nimi: " + kirjaNimi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Asiakas> getAllCustomers() {
        List<Asiakas> asiakkaat = new ArrayList<>();
        String query = "SELECT * FROM asiakkaat ORDER BY sukunimi";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int asiakasId = rs.getInt("id");
                String etunimi = rs.getString("etunimi");
                String sukunimi = rs.getString("sukunimi");
                Asiakas asiakas = new Asiakas(asiakasId, etunimi, sukunimi);

                String historyQuery = "SELECT kirja_isbn FROM ostotapahtumat WHERE asiakas_id = ?";
                try (PreparedStatement historyStmt = conn.prepareStatement(historyQuery)) {
                    historyStmt.setInt(1, asiakasId);
                    ResultSet historyRs = historyStmt.executeQuery();

                    while (historyRs.next()) {
                        String kirjaIsbn = historyRs.getString("kirja_isbn");
                        asiakas.lisaaOstotapahtuma(kirjaIsbn);
                    }
                }

                asiakkaat.add(asiakas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asiakkaat;
    }

    public boolean reduceBookQuantity(String isbn, int varasto_kpl) {
        String query = "UPDATE Kirjat SET varasto_kpl = varasto_kpl - ? WHERE isbn = ? AND varasto_kpl >= ?";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, varasto_kpl);
            stmt.setString(2, isbn);
            stmt.setInt(3, varasto_kpl);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void tulostaKirjatSortedVarasto() {
        String query = "SELECT * FROM kirjat ORDER BY kirjailija ASC";

        try (Connection conn = dbConn.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            List<Kirja> sortedBooks = new ArrayList<>();
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("nimi");
                String author = rs.getString("kirjailija");
                int varasto_kpl = rs.getInt("varasto_kpl");

                Kirja kirja = new Kirja(isbn, title, author, varasto_kpl);
                sortedBooks.add(kirja);
            }
            for (Kirja kirja : sortedBooks) {
                System.out.printf("Nimi: %s | Kirjailija: %s | ISBN: %s | Varasto: %d\n",
                        kirja.getNimi(), kirja.getKirjailija(), kirja.getIsbn(), kirja.getVarasto_kpl());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}