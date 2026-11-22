package com.napier.sem;

import java.sql.*;

/**
 * Generates various reports showing capital city information and their populations
 *
 * Reports display columns: Capital / Country / Population
 *
 * Uses JDBC (Java DB Connectivity) to connect to MySQL db & execute queries.
 *
 * Overview:
 * 1) App.java opens db connection & calls report methods
 * 2) Each report builds a title and SQL query.
 * 3) Each is hands to run(), which sends SQL and prints the rows.
 */

public class CapitalCityReport {

    /**
     * Represents capital city and its associated country & population
     */
    public static class City
    {
        public String name;             //Capital City name
        public String country;          //
        public int population;
    }

    /**
     * Prints three primary reports (static reports with no user input):
     *
     * 1) All capital cities worldwide ordered by population
     * 2) All capital cities in a continent by population
     * 3) ... in a region by population
     *
     * @param con - the database connection passes from App.java
     */

    public static void print(Connection con) {

        //Display all capital cities worldwide, most populated first
        //1) JOIN links each country to its capital
        //2) This is matched to city.ID to get capital's name & pop.
        //3) ORDER BY population descending
        //Calls on run method
        run(con, "Capital City Report: all capital cities in the world organised by population",
                "SELECT city.Name, country.Name, city.Population "
                        + "FROM country JOIN city ON country.Capital = city.ID "
                        + "ORDER BY city.Population DESC");

        //Display all capital cities in a continent, most populated first
        //1),2),3)... 4) WHERE restricts to one continent (Euirope)
        //Calls on run method
        run (con, "Capital City Report: all capital cities in a continent organised by population",
                "SELECT city.Name, country.Name, city.Population "
                    + "FROM country JOIN city ON country.Capital = city.ID "
                    + "WHERE country.Continent = 'Europe' "
                    + "ORDER BY city.Population DESC");

        //Display all capital cities in a region, most populated first
        //1),2),3)... 4) WHERE restricts to one region (Central America)
        //Calls on run method
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
     *
     * Calls on run method
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
     *
     * Calls on run method
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
     *
     * Calls on run method
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
     * - Statement: (part of JDBC) sends the SQL query to the database
     * - ResultSet: (part of JDBC) stores results returned by the SQL query
     *
     * @param con - the database connection passes from App.java
     * @param title - report heading
     * @param sql - SQL query to execute
     */

    private static void run(Connection con, String title, String sql) {
        //1)Print title and column headings (new line separates from the prior report)
        System.out.println("\n" + title);
        System.out.println("Capital / Country / Population");


        //2) Create Statement and execute the SQL query
        //con.createStatement = create a statement from connection to db
        //.executeQuery(sql) = send SQL, get ResultSet back
        try (Statement statement = con.createStatement();
             ResultSet resultset = statement.executeQuery(sql)) {
            //Loop through each row returned from the db and print each result.
            //.next moves to the next row to find the next correct row (no rows left = stop loop)
            while (resultset.next()) {

                //3) Print row:
                //Column 1 = capital name; 2 = country, 3 = population
                System.out.println(resultset.getString(1) + " "
                        + resultset.getString(2) + " "
                        + resultset.getInt(3));
            }
        } catch (SQLException e) {

            //Display error if SQL query fails (i.e. lost connection or SQL has errors) instead of crashing
            System.out.println(e.getMessage());
        }
    }
}