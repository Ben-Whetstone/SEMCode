package com.napier.sem;

import java.sql.*;

//A country report requires the following columns:

// - Code.
// - Name.
// - Continent.
// - Region.
// - Population.
// - Capital.

public class CountryReport {
    public static void CountriesLargeToSmall(Connection con) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital " +
                            "FROM country " +
                            "ORDER BY country.Population DESC;"
            );
            while (resultSet.next()){
                System.out.println(resultSet.getString("Code") + " - "
                        + resultSet.getString("Name") + " - "
                        + resultSet.getString("Continent") + " - "
                        + resultSet.getString("Region") + " - "
                        + resultSet.getString("Population") + " - "
                        + resultSet.getString("Capital"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CountriesInContinentLargeToSmall() {

    }

    public void CountriesInRegionLargeToSmall() {

    }
}
