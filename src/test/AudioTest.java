package test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import junit.framework.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InvariantBroken;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Audio;


public class AudioTest extends TestCase{
	
	private Audio audiotest;
	private Audio audiotestbis;
	
	/***
	 * Set up des tests
	 */
	
	@Before
	public void setUp (){
		String stringNotNull = new String("blabla");
		Genre genreNotNull = new Genre("test");
		Localisation localisationNotNull = new Localisation("test", "test");
		try {
			audiotest = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);
			audiotestbis = new Audio(stringNotNull, localisationNotNull, stringNotNull, stringNotNull, stringNotNull, genreNotNull, stringNotNull);

		} catch (OperationImpossible e) {} 
		  catch (InvariantBroken e) {}
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	/***
	 * Teste la levéé d'exception si variables nulles
	 */
	
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
	
	/***
	 * Teste l'instanciation de la classe
	 */
	
	@Test
	public void testNotNullInstance(){
		assertNotNull("Instance n'a pas été créée", audiotest);
		
	}
	
	/***
	 * Teste l'initialisation des variables
	 */
	
	@Test
	public void testInstanciationVariables(){
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getAnnee());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getAuteur());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getCode());
		assertEquals("Problème d'instanciation des variables String", "blabla", audiotest.getTitre());
		assertEquals("Problème instanciation localisation", new Localisation("test","test"), audiotest.getLocalisation());
		assertEquals("Problème instanciation du genre", new Genre("test"), audiotest.getGenre());
		assertEquals("L'emprunt n'a pas été initialisé à zéro", 0, audiotest.getNbEmprunts());
	}
	
	/***
	 * Teste la levée d'exception d'un document emprunté et non empruntable
	 */
	
	@Test(expected=OperationImpossible.class)
	public void testNonEmpruntableInstanciation(){
		try{
		    audiotest.emprunter();
		    fail("Empruntable à la création");
		} catch (OperationImpossible exception){}
		 catch (InvariantBroken exception){}
	}
		
	
	
	/***
	 * Teste de l'emprunnt des fichiers vérification des fonctions getNbEmprunts, getStat, metEmpruntable et emprunter
	 * @throws OperationImpossible
	 * @throws InvariantBroken
	 */
	
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken{
		
		assertEquals(false, audiotest.estEmpruntable());
		audiotest.metEmpruntable();
		audiotestbis.metEmpruntable();
		assertEquals(true, audiotest.estEmpruntable());
		audiotest.emprunter();
		audiotestbis.emprunter();
		assertEquals(true, audiotest.estEmprunte());
		assertEquals(1, audiotest.getNbEmprunts());
		assertEquals(1, audiotestbis.getNbEmprunts());
		assertEquals(2, Audio.getStat());
		
	}
	
	/***
	 * Teste la fonction metConsultable
	 * @throws OperationImpossible
	 * @throws InvariantBroken
	 */
	
	@Test
	public void testMettreConsultable() throws OperationImpossible, InvariantBroken{
		assertEquals(false, audiotest.estEmpruntable());
		audiotest.metEmpruntable();
		assertEquals(true, audiotest.estEmpruntable());
		audiotest.metConsultable();
		assertEquals(false, audiotest.estEmpruntable());
		
	}
	
	@Test
	public void testEquals(){
		Object objNotNull = new Object();
		Object objNull = null;
		assertFalse(audiotest.equals(objNull));
		objNotNull = (Audio) audiotest;
		assertTrue(audiotest.equals(objNotNull));
	}

	@Test
	public void testGetClassification(){
		assertEquals("blabla", audiotest.getClassification());
	}

	@Test
	public void testDureeEmprunt(){
		assertEquals(4*7, audiotest.dureeEmprunt());
	}

	@Test
	public void testTarifEmprunt(){
		assertEquals(1.0, audiotest.tarifEmprunt());
	}

	@Test
    public void testGetAnnee(){
        assertEquals("blabla", audiotest.getAnnee());
    }

    @Test
    public void testGetGenre(){
        assertEquals(new Genre("test"), audiotest.getGenre());
    }

    @Test
    public void testGetAuteur(){
        assertEquals("blabla", audiotest.getAuteur());
    }

    @Test
    public void testGetCode(){
        assertEquals("blabla", audiotest.getCode());
    }

    @Test
	public void testToString(){
		assertEquals(String.class, audiotest.toString().getClass());
	}





	


}
