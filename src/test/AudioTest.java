package test;

import static org.junit.Assert.*;
import junit.framework.*;

import org.junit.Before;
import org.junit.Test;

import util.InvariantBroken;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Audio;

public class AudioTest extends TestCase{
	
	@Before
	public void setUp (){
	}
	
	@Test(expected=OperationImpossible.class)
	public void testNullImplementation() {
		String stringNull = null;
		String stringNotNull = new String("blabla");
		Genre genreNull = null;
		Genre genreNotNull = new Genre("test");
		Localisation localisationNull = null;
		Localisation localisationNotNull = new Localisation("test", "test");
		try {
			Audio audio1 = new Audio(stringNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);
			Audio audio2 = new Audio(stringNotNull, localisationNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);
			Audio audio3 = new Audio(stringNotNull, localisationNotNull, stringNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);
			Audio audio4 = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNull, stringNotNull, genreNotNull, stringNotNull);
			Audio audio5 = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNull, genreNotNull, stringNotNull);
			Audio audio6 = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNull, stringNotNull);
			Audio audio7 = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNull);
			fail("Operation Impossible Exception Expected");
		} catch (OperationImpossible e) {} 
		  catch (InvariantBroken e) {}
	}

}
