package com.napier.sem;

import org.junit.jupiter.api.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for classes in com.napier.sem.
 *
 * @see App
 * @see CapitalCityReport
 * @see CityReport
 * @see CountryReport
 * @see LanguageReport
 * @see PopulationQueries
 * @see PopulationReport
 *
 */


class MyTest
{

    //Checks constructor runs without throwing exception
    @Test
    void appCreateObject(){
        App app = new App();
        assertNotNull(app);
    }

    //Checks whether con (in App.java via .disconnect()) starts as null.
    @Test
    void conStartsAsNull(){
        App app = new App();
        assertNull(app.con);
    }

    //Tests whether user input is accepting a valid number as input
    @Test
    void readUserInputAcceptsValidNum()
    {
        Scanner in = new Scanner("5\n");
        assertEquals(5, App.readUserInput(in));
    }

    //Tests whether user input has whitespace trimmed correctly.
    @Test
    void readUserInputTrimsWhitespace()
    {
        Scanner in = new Scanner("      5      \n");
        assertEquals(5, App.readUserInput(in));
    }


    //illustrates how to test if a method throws an exception.
    //By default, any exception thrown fails a test if no assertThrows matches.
    @Test
    void unitTest9()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }

    void throwsException() throws NullPointerException
    {
        throw new NullPointerException();
    }
}


