package com.napier.sem;
import java.util.ArrayList;
import java.sql.*;

public class App
{
    public Connection con = null;

    public void connect(String location, int delay)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(delay);
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }



    public static void main(String[] args)
    {
        App app = new App();

        if (args.length < 1)
        {
            app.connect("localhost:33060", 3000);
        }
        else
        {
            app.connect(args[0], Integer.parseInt(args[1]));
        }


        //Calls CapitalCityReport class (located in CapitalCityReport.java)

        CapitalCityReport.print(app.con);

        CapitalCityReport.topWorldN(app.con, 5);
        CapitalCityReport.topContinentN(app.con, "Europe", 5);
        CapitalCityReport.topRegionN(app.con, "Central America", 5);

        app.disconnect();
    }
}