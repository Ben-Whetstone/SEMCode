package com.napier.sem;
import java.sql.*;

//A city report requires the following columns:

// - Name.
// -Country.
// - District.
// - Population.

public class CityReport {

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
                System.out.println(
                        rset.getString("Name") + " " +
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

    public static void main(String[] args) {
    }
}