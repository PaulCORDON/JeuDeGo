package com.ensim.jeuGo;

import java.util.Scanner;

public class Partie {
	Scanner sc = new Scanner(System.in);
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
		plateau = new Plateau(this);
		isFinish=false;
		tour=0;
		System.out.println(plateau.toString());
	}
	
	
	public void JouerPartie() {
		while(!isFinish) {

			JouerTour();
			System.out.println(plateau.toString());
			tour++;
			
			if(tour%2==0) {
				jCourant=j2;
			}
			else jCourant=j1;
		}		
		j1.score=CompterPoint(j1);
		j2.score=CompterPoint(j2);
	}
	
	
	
	
	public void JouerTour(){
			int ligne;
			int colonne;
			System.out.println("sur quelle ligne voulez vous jouer :");
			
			while((ligne=sc.nextInt())>18 || ligne<0) {
				System.out.println("le nombre de lignes est compris entre 0 et 18 veuillez resaisir la ligne :");
				
			}
			System.out.println("sur quelle colonne voulez vous jouer :");
			while((colonne=sc.nextInt())>18 || colonne<0) {
				System.out.println("le nombre de colonnes est compris entre 0 et 18 veuillez resaisir la ligne :");
				
			}
					
		if(plateau.VerifCoupValide(ligne, colonne)) {
			
			plateau.contenuPlateau.get(ligne).get(colonne).contenu=jCourant.couleur;
		}
		
	}
	
	
	
	public int CompterPoint(Joueur j) {
		int score=0;
		
		
		
		return score;
	}
	public static void main(String[] args) {
		Partie p =new Partie();
		
		p.JouerPartie();
		
		
	}
}
