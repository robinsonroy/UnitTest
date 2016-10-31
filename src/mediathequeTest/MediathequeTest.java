package mediathequeTest;

import mediatheque.Mediatheque;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Created by Robinson on 31/10/2016.
 */
public class MediathequeTest {

    private Mediatheque mediatheque;

    @Before
    public void setUp(){
        mediatheque = new Mediatheque("Mediatest");

    }

    @Test
    public void constructorTest(){
        assertNotNull(mediatheque);
    }

}