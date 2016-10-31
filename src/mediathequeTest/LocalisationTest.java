package mediathequeTest;

import mediatheque.Localisation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 31/10/2016.
 */
public class LocalisationTest {

    private Localisation localisation;

    @Before
    public void setUp() {
        localisation = new Localisation("salleTest", "rayonTest");
    }

    @Test
    public void constructorTest() {
        assertNotNull(localisation);
    }

    @After
    public void testTearDown() {

    }

    @Test
    public void testGetSalle() {
        assertEquals("salleTest", localisation.getSalle());

    }

    @Test
    public void testSetSalle() {
        localisation.setSalle("salle2Test");
        assertEquals("salle2Test", localisation.getSalle());
    }

    @Test
    public void testGetRayon() {
        assertEquals("rayonTest", localisation.getRayon());

    }

    @Test
    public void testSetRayon() {
        localisation.setRayon("rayon2Test");
        assertEquals("rayon2Test", localisation.getRayon());
    }

    @Test
    public void testToString() {
        assertEquals("Salle/Rayon : salleTest/rayonTest", localisation.toString());
    }

    @Test
    public void testHashCode() {
        fail("Not Yet Implemented");
    }

    @Test
    public void testEquals() {
        assertTrue(localisation.equals(localisation));



        //If object diff of Localiastion
        assertFalse(localisation.equals(1));
        assertFalse(localisation.equals(1.1));
        assertFalse(localisation.equals("test"));
        assertFalse(localisation.equals(null));
        assertFalse(localisation.equals(new Object()));

        //is salle or/and rayon dif of rayon localisation or salle localisation
        Localisation loc2 = new Localisation("salle1", "rayon1");
        assertFalse(localisation.equals(loc2));
        loc2.setRayon("rayonTest");
        assertFalse(localisation.equals(loc2));
        loc2.setSalle("salleTest");
        loc2.setRayon("rayon2");
        assertFalse(localisation.equals(loc2));
        loc2.setSalle("salleTest");
        loc2.setRayon("rayonTest");
        assertTrue(localisation.equals(loc2));

        //if salle or/and rayon null
        loc2.setSalle(null);
        assertFalse(localisation.equals(loc2));

        loc2.setRayon(null);
        assertFalse(localisation.equals(loc2));
        assertFalse(loc2.equals(localisation));

        localisation.setRayon(null);
        assertFalse(localisation.equals(loc2));
        assertFalse(loc2.equals(localisation));

        loc2.setSalle("salleTest");
        assertFalse(loc2.equals(localisation));

        // if 2 object have salle and rayon null
        loc2.setSalle(null);
        localisation.setRayon(null);
        localisation.setSalle(null);
        assertTrue(localisation.equals(loc2));

    }
}