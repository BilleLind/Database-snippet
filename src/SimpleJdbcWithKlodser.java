import java.sql.*;
import java.util.Scanner;

public class SimpleJdbcWithKlodser {
    public static void main(String[] args) throws SQLException {
        String klodsFarve ="";

            System.out.println("\n****** \nProgram til hentning af klodser databasen. \n****** ");
        while (!klodsFarve.equalsIgnoreCase("exit")) {
            // #1. Connect to the database
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/klodser?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "171019"
            );

            System.out.println("\nDatabase connected.");

            // #2. Create a statement

            // ** Med Scanner kan vi få brugeren til at indtaste, hvilket land, de vil se info om
            Scanner scanner = new Scanner(System.in);
            System.out.print("Hvilket farver klodser vil du have information om? ±");
            klodsFarve = scanner.nextLine();
            String mitQuery = "SELECT * FROM klodser.klodser WHERE FARVE LIKE  '" + klodsFarve + '%' + "';";

            /* ** Uden scanner kan vi kun vise det land, som vi hardcode i query-et
            String mitQuery = "SELECT * FROM world.country WHERE name LIKE 'D%';";     */
            Statement statement = connection.createStatement();

            // #3. Execute the statement and send the SQL-query to the SQL-server
            ResultSet resultSet = statement.executeQuery
                    (mitQuery);
            System.out.println("Følgende query er sendt til MySQL-serveren: " + mitQuery);
            System.out.println("Svar fra databasen: " + "\n");


            // #4. Iterate through the result and print the results (vi har måske flere resultater end 1, derfor vil en løkke læse alle rækker ud fra resultSettet)
            while (resultSet.next())
                System.out.printf("ID: "+resultSet.getString(1) + " \t Knopper: " +
                resultSet.getString(3) + " \t Højde: " +
                resultSet.getString(4) + ", \t \t" +
                "Type: \t" +
                resultSet.getString("type") + "\n");

            // #5. Close the connection (always!)
            connection.close();
            System.out.println();

        }
        }
    }

