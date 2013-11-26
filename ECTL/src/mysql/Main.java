
package mysql;

/**
 *
 * @author Rogier
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Aanmaken nieuwe Database met de naam van de databank 
        Database db1 = new Database("myfirstdwh");
        //een klant toevoegen
        //db1.sqlUpdate("INSERT INTO klant VALUES('1' , 'J. van Drunen' , '0162686655', '0628956325', 'Made', 'NL', '5' )");
        //De gegevens in de database uitprinten
        System.out.println("Gegevens in de tabel voor verwijderen team J. van Drunen:");
        db1.sqlExecute("SELECT * FROM klant");
        // De gegevens in de database verwijderen
        db1.sqlUpdate("DELETE FROM klant WHERE KlantNummer = '1'");

        //Sluiten van de verbinding
        boolean y = false;
        y = db1.Sluitconnectie();
        if (y) {
            System.out.println("Database verbinding gesloten!");
        } else {
            System.out.println("Database verbinding niet gesloten!");
        }
    }
}
