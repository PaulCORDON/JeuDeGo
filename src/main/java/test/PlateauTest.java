package test;

import com.ensim.jeuGo.Joueur;
import com.ensim.jeuGo.Partie;
import com.ensim.jeuGo.Plateau;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlateauTest{

	 @Test 
	 public void testPlateau(){
		
	 	Partie partie = new Partie();
		 Plateau p = new Plateau(partie);
		 
		 
			
		 assertEquals("test intersection 1", "vide", p.contenuPlateau.get(1).get(1).getContenu());
		 assertEquals("test intersection 2", "bord", p.contenuPlateau.get(20).get(1).getContenu());
		 assertEquals("test intersection 3", "bord", p.contenuPlateau.get(1).get(20).getContenu());


		 p.contenuPlateau.get(5).get(5).setContenu("blanc");
		 assertEquals("test ajout pion", "blanc", p.contenuPlateau.get(5).get(5).getContenu());
		 
		 p.contenuPlateau.get(5).get(7).setContenu("blanc");
		 p.contenuPlateau.get(4).get(6).setContenu("blanc");
		 p.contenuPlateau.get(6).get(6).setContenu("blanc");
		 Joueur jNoir=new Joueur("noir");
		 Joueur jBlanc=new Joueur("blanc");
		 /*on forme un cercle de blanc et on verifie qu'on ne puisse pas ajouter un noir au milieu, si VerifCoupValide renvoi trois le coup est suicidaire*/
		 assertEquals("test coup suicidaire 1", 3, p.VerifCoupValide(jNoir, 5, 6));
		 
		 
		 /*on essaye de jouer en dehors du plateau, JouerTour devrait renvoyer 1*/
		 assertEquals("test coup en dehors plateau 1", 1, partie.JouerTour("25", "6"));

		 /*on essaye de jouer a un emplacement deja occupe, VerifCoupValide devrait renvoyer 2*/
		 assertEquals("test coup en dehors plateau 2", 2, p.VerifCoupValide(jNoir, 5, 5));

		 
		  /* on place 2 pions noirs
		  * on les encercle par 6 pions blancs
		  * on verifie a chaque ajout s'il se passe qq chose grace a la methode VerifIlSePasseQqChose puis on ajoute le pion joue
		  * on verifie a la fin que les pions noirs se sont transformes en "vide"
		  */
		 p.VerifIlSePasseQqChose(jNoir, 11, 10);
		 p.contenuPlateau.get(11).get(10).setContenu("noir");
		 p.VerifIlSePasseQqChose(jNoir, 12, 10);
		 p.contenuPlateau.get(12).get(10).setContenu("noir");
		 p.VerifIlSePasseQqChose(jBlanc, 10, 10);
		 p.contenuPlateau.get(10).get(10).setContenu("blanc");
		 p.VerifIlSePasseQqChose(jBlanc, 13, 10);
		 p.contenuPlateau.get(13).get(10).setContenu("blanc");
		 p.VerifIlSePasseQqChose(jBlanc,11, 9);
		 p.contenuPlateau.get(11).get(9).setContenu("blanc");
		 p.VerifIlSePasseQqChose(jBlanc, 11, 11);
		 p.contenuPlateau.get(11).get(11).setContenu("blanc");
		 p.VerifIlSePasseQqChose(jBlanc, 12, 9);
		 p.contenuPlateau.get(12).get(9).setContenu("blanc");
		 p.VerifIlSePasseQqChose(jBlanc, 12, 11);
		 p.contenuPlateau.get(12).get(11).setContenu("blanc");
		 
		 assertEquals("test capture pion 1", "vide", p.contenuPlateau.get(11).get(10).getContenu());
		 assertEquals("test capture pion 2", "vide", p.contenuPlateau.get(12).get(10).getContenu());
		 
		 }

}
