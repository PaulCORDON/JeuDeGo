package com.ensim.jeuGo;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	
	public Intersection contenuPlateau[][]= new Intersection [19][19];
	
	
	public Plateau(){
		
		
		for(Intersection[] ligne:contenuPlateau) {
			for(Intersection intersection:ligne)
			intersection.contenu="vide";
		}
	}
	
	
	
	
	
	public Boolean VerifCoupValide(int ligne, int colonne) {
		Boolean valide=false;
		
		if(ligne>=0 && ligne<=19 && colonne>=0 && colonne<=19) {
			if(contenuPlateau[ligne][colonne].contenu.equals("vide")) {
				valide=true;
			}
		}
		
		return valide;
	}
	
	
	/*public void VerifIlSePasseQqChose(int ligne, int colonne) { //dans cette methode on verifie s'il se passe qq chose. Si oui, on effectue les changement sur le plateau
	
		
		
		
		
	}*/
}
