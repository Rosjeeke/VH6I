package ectl;

import java.sql.*;

/**
 *
 * @author Rogier
 */
public class MysqlDatabase {

    private Connection con;

    /**
     * Constructor voor de klasse Database. Hierin wordt automatisch verbinding
     * gemaakt met de database
     *
     * @param databasenaam naam van de database waarmee automatisch verbinding
     * gemaakt wordt
     */
    public MysqlDatabase(String databasenaam) {

        String connectiestring;

        connectiestring = "jdbc:mysql://localhost:3306/" + databasenaam;

        try {
            //verbinding maken met de database (connectiestring, gebruiker, wachtwoord)
            con = DriverManager.getConnection(connectiestring, "root", "");
            if (!con.isClosed()) { //als de verbinding niet gesloten is, dus open wordt onderstaande code uitgevoerd
                System.out.println("Succesvol verbonden met de MySQL server.\n");
            }
        } catch (Exception e) { // als de verbinding gesloten is, wordt er een melding weergegeven
            System.err.println("Melding: " + e.getMessage());
        }
    }

    /**
     * Methode waarmee gegevens kunnen worden opgehaald
     *
     * @param query query die uitgevoerd moet worden
     */
    public void sqlExecute(String query) {

        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query); // resultaat van de query

            while (rs.next()) { // zolang dat er een nieuwe rij met gegevens gevonden kan worden wordt onderstaande code uitgevoerd
                int numColumns = rs.getMetaData().getColumnCount(); // aantal kolommen ophalen

                for (int i = 1; i <= numColumns; i++) { //for-loop om alle kolommen van een rij uit te printen
                    //kolomnummers starten op 1
                    System.out.println( //kolomnaam weergeven van de geselecteerde tabel + waarde
                            rs.getMetaData().getColumnName(i) + " = "
                            + rs.getObject(i));
                }
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
    }

    /**
     * Methode waarmee wijzigingen kunnen worden aan gebracht in de database
     * (Insert, Delete)
     *
     * @param query query die uitgevoerd moet worden
     */
    public void sqlUpdate(String query) {

        Statement stmt;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query); // uitvoeren van de query
            stmt.close();
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
    }

    /**
     * Methode die de verbinding met de database sluit
     *
     * @return boolean die succesvol is als hij gesloten wordt
     */
    public void Sluitconnectie() {
        boolean x = false;
        try {
            if (con != null) {
                con.close();
                System.out.println("Database verbinding gesloten!");
                x = true;
            }
        } catch (SQLException e) {
            System.out.println("Database verbinding niet gesloten!");
            x = false;
        }
    }
}
