package com.napier.sem;
import java.util.Scanner;   //Allows user input
import java.sql.*;          //Allows JDBC: Connection, SQLException etc

/**
 *
 * Main class of the program: connects to MySQL world db and runs
 * reports from CapitalCityReport.java (et al). Once finished, disconnects.
 *
 * Workflow:
 * 1) main() creates an App object
 * 2) connect() starts database connection, retrying up to 10 times.
 * 3) main() calls the reports method in CapitalCityReport (et al) + passes in db connection
 *4) disconnect() closes connection.
 */

public class App
{

    /**
     * Connection to the db.
     *
     * Starts as null (no connection). Then connect() fills it in via .getConnection
     * which uses the URL, username & pwd for MYSQL connection.
     */
    public Connection con = null;

    /**
     *
     * Connects to MySQL db. Retries 10 times.
     *
     * @param location - location of the db (i.e.localhost:33060)
     * @param delay - how many ms wait before each connection attempt
     */

    public void connect(String location, int delay)
    {
        //1) Load SQL driver
        // Allows communiccation with mySQL. If missing, exit.
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        //2) connect attempt (up to 10 times (loop))
        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(delay);
                //Provides location and name of db for connection to MYSQL (con = null by default, this provides location)
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            //If connection fails, print failure message, then loop back for another attempt.
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

    /**
     * Closes db connection once finished.
     * Checks if isn't null, so it doesn't crash if never connected in the first place.
     */

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

    /**
     *
     * Prompts user to type number (validation if isn't valid i.e. number is 0 or empty etc.)
     *
     * @param in - Scanner for user input
     * @return - number the user entered
     *
     */

    public static int readUserInput(Scanner in)
    {
        int n = 0; //Start at 0 (the loops will allow it to loop only once)

        while (n < 1)
        {
            System.out.print("Enter nmber of capital cities to display: ");
            try
            {

                //Read line as text, parse string (whatever number user inputs) to int, then trim any space
                n = Integer.parseInt(in.nextLine().trim());
                if (n < 1)
                {
                    System.out.println("Please enter your number greater than 0");
                }
            }
            //Display exception if a letter, emptyline or decimal number is entered.
            catch (NumberFormatException e)
            {
                System.out.println("You cannot add decimals. Please enter a whole number");
            }
        }
        return n;

    }


    /**
     * Program starts here. Connection, then run reports, then disconnect
     *
     * @param args - commandline arguments: args[0] = db connection; args[1] = delay in ms
     */

    public static void main(String[] args)
    {

        //Create new object of App allowing use of connect() & disconnect()
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

        //Capital City Reports 1-3: all capitals in world, Europe, Central America:
        CapitalCityReport.print(app.con);

        //Capital City N Report: user chooses N - will then show top N capital cities in world
        System.out.println("### User Input Report (Capital Cities) ###");
        Scanner scanner = new Scanner(System.in);
        int n = readUserInput(scanner);

        //Capital City Reports 4-6: user specifies a value, the value is used to determine amount of rows to print for each report
        CapitalCityReport.topWorldN(app.con, n);
        CapitalCityReport.topContinentN(app.con, "Europe", n);
        CapitalCityReport.topRegionN(app.con, "Central America", n);

        //Disconnect once reports are done
        app.disconnect();
    }
}