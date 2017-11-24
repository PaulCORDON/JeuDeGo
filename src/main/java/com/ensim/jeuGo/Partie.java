package com.ensim.jeuGo;

import java.util.Scanner;

public class Partie {
	Scanner sc = new Scanner(System.in);
	Joueur j1;
	Joueur j2;
	Joueur jCourant;
	Joueur jAttendant;
	Plateau plateau;
	Boolean isFinish;
	int tour;
	
	
	Partie(){	//initialisation de la partie
		j1 = new Joueur("blanc");
		j2 = new Joueur("noir");
		jCourant= j2;
		jAttendant=j1;
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
				jAttendant=jCourant;
				jCourant=j2;
			}
			else {
				jAttendant=jCourant;
				jCourant=j1;
			}
		}
		System.out.println("Partie terminée.");
		j1.score=CompterPoint(j1);
		j2.score=CompterPoint(j2);
	}
	
	
	
	
	public void JouerTour(){
			int ligne;
			int colonne;
			System.out.println(jCourant.couleur+"Voulez-vous passez votre tour? 0=oui 1=non");
			if(sc.nextInt()==0) {
				jCourant.aPasse=true;
				if(jAttendant.aPasse==true) {
					isFinish=true;
				}
			}
			else {
				jCourant.aPasse=false;
					System.out.println(jCourant.couleur+" sur quelle ligne voulez vous jouer :");
				
				while((ligne=sc.nextInt())>19 || ligne<1) {
					System.out.println("Le nombre de lignes est compris entre 0 et 18 veuillez resaisir la ligne :");
					
				}
				System.out.println(jCourant.couleur+" sur quelle colonne voulez vous jouer :");
				while((colonne=sc.nextInt())>19 || colonne<1) {
					System.out.println("Le nombre de colonnes est compris entre 0 et 18 veuillez resaisir la ligne :");
					
				}
						
				if(plateau.VerifCoupValide(ligne, colonne)) {
				
					plateau.contenuPlateau.get(ligne).get(colonne).contenu=jCourant.couleur;
				}

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
