package test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.InvariantBroken;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by tosnos on 06/11/16.
 */
@RunWith(Parameterized.class)
public class VideoTest {

    private String code;
    private Localisation localisation;
    private String titre;
    private String auteur;
    private String annee;
    private Genre genre;
    private int duree;
    private String mentionLegale;
    private Video videotest;


    public VideoTest(String code, Localisation localisation, String titre, String auteur, String annee, Genre genre, int duree, String mentionLegale){
        this.code=code;
        this.localisation=localisation;
        this.titre=titre;
        this.auteur=auteur;
        this.annee=annee;
        this.genre=genre;
        this.duree=duree;
        this.mentionLegale=mentionLegale;

        try {
            this.videotest = new Video(code, localisation, titre, auteur, annee, genre, duree, mentionLegale);
        } catch (OperationImpossible e) {}
        catch (InvariantBroken e) {}
    }

    @Parameterized.Parameters
    public static Collection<Object> parameters() throws IOException{
        return Arrays.asList(
            new Object[] {"codeTest", new Localisation("l1", "l2"), "titreTest", "auteurTest", "anneeTest", new Genre("genreTest"), 120, "mentionLegaleTest"},
            new Object[] {"codeTestbis", new Localisation("l1bis", "l2bis"), "titreTestbis", "auteurTestbis", "anneeTestbis", new Genre("genreTestbis"), 140, "mentionLegaleTestbis"}
        );
    }

    @Test
    public void getStat() throws Exception {

    }

    @Test
    public void emprunter() throws Exception {
        this.videotest.metEmpruntable();
        this.videotest.emprunter();
    }

    @Test
    public void dureeEmprunt() throws Exception {
        assertEquals(2*7, this.videotest.dureeEmprunt());
    }

    @Test
    public void tarifEmprunt() throws Exception {
        assertEquals(1.5, this.videotest.tarifEmprunt(),0.0);
    }

    @Test
    public void getMentionLegale() throws Exception {
        assertEquals(this.mentionLegale, videotest.getMentionLegale());

    }

    @Test
    public void getDureeFilm() throws Exception {
        assertEquals(this.duree, duree);
    }

    @Test//(expected=OperationImpossible.class)
    public void invariantVideo(){
        try{
            Video videoTestImpossible = new Video("codeTest", new Localisation("l1", "l2"), "titreTest", "auteurTest", "anneeTest", new Genre("genreTest"), -120, "mentionLegaleTest");
            fail("Emprunt d'une video à durée négative");
        }catch (OperationImpossible e){}
        catch (InvariantBroken e) {}
    }

    @Test
    public void testRestituer(){
        try{
            this.videotest.restituer();
            fail("Resituer mais non empruntable");
        } catch (OperationImpossible e) {}
        catch (InvariantBroken e) {}
        try{
            this.videotest.metEmpruntable();
            this.videotest.restituer();
            fail("Restituer et non emprunter");
        } catch (OperationImpossible e){}
        catch (InvariantBroken e) {}
        try{
            this.videotest.emprunter();
            this.videotest.restituer();
        } catch (OperationImpossible e) {}
        catch (InvariantBroken e) {}

    }



}