package test;

import mediatheque.client.HashClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 05/11/2016.
 */
public class HashClientTest {

    private HashClient hc1;
    private HashClient hc2;
    private HashClient hc3;
    private HashClient hc4;
    private HashClient hc5;
    private HashClient hc6;

    @Before
    public void setUp(){
        hc1 = new HashClient("Damiens", "Francois");
        hc2 = new HashClient("Debouze", "Jamel");
        hc3 = new HashClient("Debouze", "Francois");
        hc4 = new HashClient("Damiens", "Jamel");
        hc5 = new HashClient("Damiens", "Francois");

        hc6 = new HashClient(null, null);

    }

    @Test
    public void testEquals() {
        assertTrue(hc1.equals(hc1));
        assertFalse(hc1.equals(hc2));
        assertFalse(hc1.equals(hc3));
        assertFalse(hc1.equals(hc4));
        assertTrue(hc1.equals(hc5));

        assertFalse(hc1.equals(hc6));

        assertFalse(hc6.equals(hc1));
    }

    @Test
    public void testHashCode() {
        assertEquals(hc1.hashCode(), hc1.hashCode());
        assertEquals(hc1.hashCode(), hc5.hashCode());

        assertNotEquals(hc1.hashCode(), hc2.hashCode());
        assertNotEquals(hc1.hashCode(), hc3.hashCode());
        assertNotEquals(hc1.hashCode(), hc4.hashCode());

        assertNotEquals(hc1.hashCode(), hc6.hashCode());
    }

    @Test
    public void tesGetNom() {
        assertEquals("Damiens", hc1.getNom());
        assertEquals("Debouze", hc2.getNom());
        assertEquals(null, hc6.getNom());
    }

    @Test
    public void testGetPrenom() {
        assertEquals("Francois", hc1.getPrenom());
        assertEquals("Jamel", hc2.getPrenom());
        assertEquals(null, hc6.getPrenom());
    }

}