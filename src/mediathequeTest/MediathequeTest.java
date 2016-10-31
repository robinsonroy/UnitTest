package mediathequeTest;

import mediatheque.Mediatheque;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 31/10/2016.
 */
public class MediathequeTest {

    private Mediatheque mediatheque;

    @Before
    public void setUp(){
        mediatheque = new Mediatheque("Mediatest");

    }



}