//A capital city report requires the following columns:

// - Name.
// - Country.
// -  Population.




//Info about Java Database Connectivity:

//JDBC = java's built-in way of talking to a database (i.e classes: Connection, Statement, ResultSet). Code written sends SQL through and gets results back as Java objects.


package com.napier.sem;

import java.sql.*;

public class CapitalCityReport
{
    //Connection - database connection passed in from App.
    public static void print(Connection con)
    {
        //Try/catch validation to handle SQLExceptions
        try
        {
            //Statement - sends SQL to the database
            Statement statement = con.createStatement();

            //ResultSet - holds rows the database sends back
            ResultSet resultSet = statement.executeQuery(
                    "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population AS Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC");

            System.out.println("Capital / Country / Population");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("CapitalName") + " " + resultSet.getString("CountryName") + " " + resultSet.getInt("Population"));
            }
        }
        //
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

//Info about Java Database Connectivity
