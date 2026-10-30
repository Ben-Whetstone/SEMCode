//A capital city report requires the following columns:

// - Name.
// - Country.
// -  Population.




//Info about Java Database Connectivity:

//JDBC = java's built-in way of talking to a database (i.e classes: Connection, Statement, ResultSet). Code written sends SQL through and gets results back as Java objects.


package com.napier.sem;

import java.sql.*;

public class CapitalCityReport {
    //Connection - database connection passed in from App.
    public static void print(Connection con) {
        run(con, "Capital City Report: all capital cities in the world organised by population",
                "SELECT city.Name, country.Name, city.Population "
                        + "FROM country JOIN city ON country.Capital = city.ID "
                        + "ORDER BY city.Population DESC");

        run (con, "Capital City Report: all capital cities in a continent organised by population",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Continent = 'Europe' "
                    + "ORDER BY city.Population DESC");

        run (con, "Capital City Report: all capital cities in a region organised by population",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Region = 'Central America' "
                    + "ORDER BY city.Population DESC");

    }

    private static void run(Connection con, String title, String sql) {
        System.out.println("\n" + title);
        System.out.println("Capital / Country / Population");

        try (Statement statement = con.createStatement();
             ResultSet resultset = statement.executeQuery(sql)) {
            while (resultset.next()) {
                System.out.println(resultset.getString(1) + " "
                        + resultset.getString(2) + " "
                        + resultset.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
//Info about Java Database Connectivity
