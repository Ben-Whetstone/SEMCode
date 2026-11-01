import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;



import com.napier.sem.App;
import com.napier.sem.CapitalCityReport;
import com.napier.sem.CityReport;
import com.napier.sem.CountryReport;
import com.napier.sem.LanguageReport;
import com.napier.sem.PopulationQueries;
import com.napier.sem.PopulationReport;
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



/**
 * Integration tests for {@link App}
 *
 * Tests need a MySQL container with the world database
 * published on localhost:33060. The tests check that {@link App} and
 * the database work together, in addition to checking that the population
 * reports etc run successfully...
 */
class MyTest
{

    /**
     * Checks that {@link App} will open a connection to the world database.
     */
    @Test
    void connectsToDatabase()
    {
        App app = new App();
        app.connect();
        assertNotNull(app.con);
        app.disconnect();
    }


    @Test
    void appCreateObject(){
        App app = new App();
        assertNotNull(app);
    }

    @Test
    void unitTest()
    {
        assertEquals(5,5);
    }


    // illustrates how we can add a message to a test.
    @Test
    void unitTest2()
    {
        assertEquals(5, 5, "Messages are equal");
    }

    //illustrates how to test floating point values with an error range.
    @Test
    void unitTest3()
    {
        assertEquals(5.0, 5.01, 0.02);
    }

    //illustrates how to compare array contents in a test.
    @Test
    void unitTest4()
    {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertArrayEquals(a, b);
    }

    //illustrates how to test if a value is true.
    @Test
    void unitTest5()
    {
        assertTrue(5 == 5);
    }

    //illustrates how to test if a value is false.
    @Test
    void unitTest6()
    {
        assertFalse(5 == 4);
    }

    //illustrates how to test if a value is null.
    @Test
    void unitTest7()
    {
        assertNull(null);
    }

    //illustrates how to test if a value is not null.
    @Test
    void unitTest8()
    {
        assertNotNull("Hello");
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


