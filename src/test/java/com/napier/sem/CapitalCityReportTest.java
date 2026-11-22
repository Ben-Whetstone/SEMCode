package com.napier.sem;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing for CapitalCityReport
 *
 * Each test checks for a small thing which could go wrong in the code. @Test tells
 * JUnit to run each as a test.
 *
 */


public class CapitalCityReportTest{

    //Check the city name is not empty when it's set. Name is set ourselves so just
    //checking to see if it's been set first.
    @Test
    void cityNameIsNotEmpty(){
        CapitalCityReport.City city = new CapitalCityReport.City();
        city.name = "London";
        assertFalse(city.name.isEmpty());
    }

    //Check the population is positive, allowing test for anomalies in data.
    @Test void cityPopulationIsPositive(){
        CapitalCityReport.City city = new CapitalCityReport.City();
        city.population = 1000;
        assertTrue(city.population > 0);
    }

}
