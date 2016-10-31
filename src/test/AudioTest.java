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
	
	private Audio audiotest;
	
	@Before
	public void setUp (){
		String stringNotNull = new String("blabla");
		Genre genreNotNull = new Genre("test");
		Localisation localisationNotNull = new Localisation("test", "test");
		try {
			audiotest = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);
		} catch (OperationImpossible e) {} 
		  catch (InvariantBroken e) {}
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
	
	@Test
	public void testNotNullInstance(){
		assertNotNull("Instance n'a pas été créée", audiotest);
		
	}
	
	@Test
	public void testInstanciation(){
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getAnnee());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getAuteur());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getCode());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getTitre());
		assertEquals("Problème instanciation localisation", new Localisation("test","test"), audiotest.getLocalisation());
		assertEquals("Problème instanciation du genre", new Genre("test"), audiotest.getGenre());
		assertEquals("L'emprunt n'a pas été initialisé à zéro", 0, audiotest.getNbEmprunts());
	}
	
	@Test
	public void testEmprunter(){
		try {
			audiotest.emprunter();
			assertEquals("Problème incrémentation nombre d'emprunts", 1, audiotest.getNbEmprunts());
		} catch (InvariantBroken e) {} 
		catch (OperationImpossible e) {}
		
	}

}
