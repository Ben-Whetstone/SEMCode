package com.napier.sem;

import java.sql.*;

/**
 * Generates various reports showing capital city information and their populations
 *
 * Reports display columns: Capital / Country / Population
 *
 * Uses JDBC (Java DB Connectivity) to connect to MySQL db & execute queries.
 */

public class CapitalCityReport {

    /**
     * Represents capital city and its associated country & population
     */
    public static class City
    {
        public String name;
        public String country;
        public int population;
    }

    /**
     * Prints three primary reports:
     *
     * 1) All capital cities worldwide ordered by population
     * 2) All capital cities in a continent by population
     * 3) ... in a region by population
     *
     * @param con - the database connection passes from App.java
     */

    public static void print(Connection con) {

        //Display all capital cities worldwide, most populated first
        run(con, "Capital City Report: all capital cities in the world organised by population",
                "SELECT city.Name, country.Name, city.Population "
                        + "FROM country JOIN city ON country.Capital = city.ID "
                        + "ORDER BY city.Population DESC");

        //Display all capital cities in a continent, most populated first
        run (con, "Capital City Report: all capital cities in a continent organised by population",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Continent = 'Europe' "
                    + "ORDER BY city.Population DESC");

        //Display all capital cities in a region, most populated first
        run (con, "Capital City Report: all capital cities in a region organised by population",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Region = 'Central America' "
                    + "ORDER BY city.Population DESC");

    }

    /**
     * Display top N most populated capital cities worldwide (where N is provided by the user)
     *
     * @param con - the database connection passes from App.java
     * @param n - number of capital cities to display
     */
    public static void topWorldN(Connection con, int n){
        run(con, "Top " + n + " populated capital cities in the world",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "ORDER BY city.Population DESC LIMIT " + n);
    }

    /**
     * Display top N most populated capital cities in a continent (where N is provided by the user)
     *
     * @param con - the database connection passes from App.java
     * @param continent - selected continent to filter capital cities
     * @param n - number of capital cities to display
     */
    public static void topContinentN(Connection con, String continent, int n){
        run(con, "Top " + n + " populated capital cities in " + continent,
                "SELECT city.Name, country.Name, city.Population "
                        + "FROM country JOIN city ON country.Capital = city.ID "
                        + "WHERE country.Continent = '" + continent + "' "
                        + "ORDER BY city.Population DESC LIMIT " + n);
    }

    /**
     * Display top N most populated capital cities in a region (where N is provided by the user)
     *
     * @param con - the database connection passes from App.java
     * @param region - selected region to filter capital cities
     * @param n - number of capital cities to display
     */
    public static void topRegionN(Connection con, String region, int n){
        run(con, "Top " + n + " populated capital cities in a " + region,
                "SELECT city.Name, country.Name, city.Population "
                        + "FROM country JOIN city ON country.Capital = city.ID "
                        + "WHERE country.Region = '" + region + "' "
                        + "ORDER BY city.Population DESC LIMIT " + n);
    }


    /**
     * Executes an SQL query and prints the returned capital city information report.
     *
     * JDBC components:
     *
     * - Statement: sends the SQL query to the database
     * - ResultSet: stores results returned by the SQL query
     *
     * @param con - the database connection passes from App.java
     * @param title - report heading
     * @param sql - SQL query to execute
     */

    private static void run(Connection con, String title, String sql) {
        //Print title and column headings
        System.out.println("\n" + title);
        System.out.println("Capital / Country / Population");

        //Create Statement and execute the SQL query
        //ResultSet contains rows returned from the db
        try (Statement statement = con.createStatement();
             ResultSet resultset = statement.executeQuery(sql)) {
            //Loop through each row returned from the db and print each result.
            while (resultset.next()) {

                //Print city
                System.out.println(resultset.getString(1) + " "
                        + resultset.getString(2) + " "
                        + resultset.getInt(3));
            }
        } catch (SQLException e) {

            //Display error if SQL query fails.
            System.out.println(e.getMessage());
        }
    }
}