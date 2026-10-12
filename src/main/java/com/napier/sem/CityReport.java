package com.napier.sem;
import java.sql.*;

//A city report requires the following columns:

// - Name.
// -Country.
// - District.
// - Population.

public class   CityReport {

    public void displayreportDesc(Connection con) { //All the cities in the world organised by largest population to smallest.
        try {
            // create an SQL statement
            Statement stmt = con.createStatement();

            // create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC;";

            // execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //print out the intial header with appropriate spacing
            System.out.println("                             City Population Report                               ");
            System.out.println("==================================================================================");
            System.out.printf("%-30s | %-25s | %-20s | %-15s%n", "Name", "Country", "District", "Population");

            //start a while loop that extracts and prints data from the database with correct spacing
            while (rset.next()) {

                String cityName = rset.getString("Name");
                String countryName = rset.getString("Country");
                String district = rset.getString("District");
                int population = rset.getInt("Population");

                System.out.printf("%-30s | %-25s | %-20s | %,d%n", cityName, countryName, district, population);
            }


        }
        //to report on any errors
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
        }
    }
    public static void main(String[] args){

    }

}
