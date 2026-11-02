package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testTopWorldCapitalCities()
    {
        //check db connection exists. If 'null', test will fail.
        assertNotNull(app.con);

        //check calling 'top 5 capital cities' won't throw exception.
        assertDoesNotThrow(() -> CapitalCityReport.topWorldN(app.con, 5));
    }

    @Test
    void testTopContinentCapitalCities()
    {
        //check db connection exists. If 'null', test will fail.
        assertNotNull(app.con);

        //check calling 'top 5 capital cities' won't throw exception.
        assertDoesNotThrow(() -> CapitalCityReport.topContinentN(app.con, "Europe", 5));
    }

    @Test
    void testTopRegionCapitalCities()
    {
        //check db connection exists. If 'null', test will fail.
        assertNotNull(app.con);

        //check calling 'top 5 capital cities' won't throw exception.
        assertDoesNotThrow(() -> CapitalCityReport.topRegionN(app.con, "Central America", 5));
    }
}