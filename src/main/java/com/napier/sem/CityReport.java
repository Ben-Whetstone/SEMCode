package com.napier.sem;
import java.sql.*;

//A city report requires the following columns:

// - Name.
// -Country.
// - District.
// - Population.

public class CityReport {
    /**
     * this method displays all the cities in the world organized in decending order by population, to use it, you must enter the con as the parameter or else it will not
     * connect to the database, the con parameter is available in the App.Java file, after that it will run. The method operates by creating an SQL statement and using a string to hold the query, a
     * afterwards executes the SQL query and prints out the desired data in proper format, it also includes error handling.
     * @param con to connect the database
     */
    public void cityreportdesc(Connection con) { //All the cities in the world organised by population
        try {
            Statement stmt = con.createStatement();//create SQL statement
            String strSelect =//create string that holds SQL query
                    "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);//execute the SQL statement, rset holds the rows that the database sends back

            System.out.println("Name / Country / District / Population");//print out header
            while (rset.next()) {//use while loop to print out all the data
                System.out.println(rset.getString("Name") + " " +
                                rset.getString("Country") + " " +
                                rset.getString("District") + " " +
                                rset.getInt("Population")
                );
            }
        }
        catch (Exception e) {//display error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
        }
    }


    /**
     * this method displays all the cities in the world in a chosen continent organized in decending order by population, to use it, you must enter the con as the parameter or else it will not
     * connect to the database, the con parameter is available in the App.Java file, you also must enter the continent you wish to have it filter by, for example entering "Asia" would only
     * display countries in that continent,after that it will run. The method operates by creating an SQL statement and using a string to hold the query, a
     * afterwards executes the SQL query and prints out the desired data in proper format, it also includes error handling.
     * @param con to connect the database
     * @param continent the name of the continent which it filters by
     */
    public void cityreportcontinent(Connection con, String continent) { //All cities in a given continent
        try {
            Statement stmt = con.createStatement();//create SQL statement
            String strSelect =//create string that holds SQL query
                    "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Continent = " + "'" + continent + "'"
                            + "ORDER BY city.Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);//execute SQL statement, rset holds the rows that the database sends back

            System.out.println("City Population Report for: " + continent);//print out header
            System.out.println("Name / Country / District / Population");
            while (rset.next()) {//use while loop to print out all the data
                System.out.println(rset.getString("Name") + " " +
                                rset.getString("Country") + " " +
                                rset.getString("District") + " " +
                                rset.getInt("Population")
                );
            }
        }
        catch (Exception e) {//display error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent city report details");
        }
    }

    /**
     * this method displays all the cities in the world in a chosen region organized in decending order by population, to use it, you must enter the con as the parameter or else it will not
     * connect to the database, the con parameter is available in the App.Java file, you also must enter the region you wish to have it filter by, for example entering "Western Europe" would only
     * display countries in that region,after that it will run. The method operates by creating an SQL statement and using a string to hold the query, and
     * afterwards executes the SQL query and prints out the desired data in proper format, it also includes error handling.
     * @param con connection to database
     * @param region the name of the region which it filters by
     */
    public void cityreportregion(Connection con, String region) { //All cities in a given region
        try {
            Statement stmt = con.createStatement();//create SQL statement
            String strSelect =//create string that holds SQL query
                    "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Region = " + "'" + region + "'"
                            + "ORDER BY city.Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);//execute SQL statement, rset holds the rows that the database sends back

            System.out.println("City Population Report for: " + region);//print out header
            System.out.println("Name / Country / District / Population");
            while (rset.next()) {//use while loop to print out all the data
                System.out.println(rset.getString("Name") + " " +
                        rset.getString("Country") + " " +
                        rset.getString("District") + " " +
                        rset.getInt("Population")
                );
            }
        }
        catch (Exception e) {//display error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get region city report details");
        }
    }


    public static void main(String[] args) {
    }
}