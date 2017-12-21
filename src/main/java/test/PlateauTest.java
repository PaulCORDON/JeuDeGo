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
		 		 
		 p.contenuPlateau.get(5).get(5).setContenu("blanc");
		 assertEquals("test ajout pion", "blanc", p.contenuPlateau.get(5).get(5).getContenu());
		 
		 p.contenuPlateau.get(5).get(7).setContenu("blanc");
		 p.contenuPlateau.get(4).get(6).setContenu("blanc");
		 p.contenuPlateau.get(6).get(6).setContenu("blanc");
		 Joueur j=new Joueur("noir");
		 /*on forme un cercle de blanc et on verifie qu'on ne puisse pas ajouter un noir au milieu, si VerifCoupValide renvoi trois le coup est suicidaire*/
		 assertEquals("test coup suicidaire 1", 3, p.VerifCoupValide(j, 5, 6));
		 
		 
		 /*on essaye de jouer en dehors du plateau, JouerTour devrait renvoyer 1*/
		 assertEquals("test coup en dehors plateau", 1, partie.JouerTour("25", "6"));

		 /*on essaye de jouer a un emplacement deja occupe, VerifCoupValide devrait renvoyer 2*/
		 assertEquals("test coup en dehors plateau", 2, p.VerifCoupValide(j, 5, 5));


		 
		
	 }

}
