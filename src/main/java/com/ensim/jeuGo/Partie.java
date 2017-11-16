package com.ensim.jeuGo;

public class Partie {
	
	Joueur j1;
	Joueur j2;
	Joueur jCourant;
	Plateau plateau;
	Boolean isFinish;
	int tour;
	
	Partie(){	//initialisation de la partie
		j1 = new Joueur("blanc");
		j2 = new Joueur("noir");
		jCourant= j2;
		plateau = new Plateau();
		isFinish=false;
		tour=0;
	}
	
	public void JouerPartie() {
		while(!isFinish) {
			JouerTour(jCourant, ligne, colonne );
			tour++;
			
			if(tour%2==0) {
				jCourant=j2;
			}
			else jCourant=j1;
		}
	}
	
	public void JouerTour(Joueur jc, int ligne, int colonne){
		
		jc=jCourant;
		
		if(plateau.VerifCoupValide(ligne, colonne)) {
			
			plateau.contenuPlateau[ligne][colonne].contenu=jc.couleur;
		}
	}
	
	
}
