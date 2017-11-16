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
		return true;
	}
	
	
	public Boolean VerifIlSePasseQqChose(int ligne, int colonne) {
	
		
		
		return false;
		
	}
}
