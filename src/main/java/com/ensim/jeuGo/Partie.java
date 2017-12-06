package com.ensim.jeuGo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
					

				
				while((ligne=sc.nextInt())>=plateau.tailleGrille-1 || ligne<1) {
					System.out.println("Le nombre de lignes est compris entre 0 et "+(plateau.tailleGrille-2)+" veuillez resaisir la ligne :");
					
				}
				System.out.println(jCourant.couleur+" sur quelle colonne voulez vous jouer :");
				while((colonne=sc.nextInt())>=plateau.tailleGrille-1 || colonne<1) {
					System.out.println("Le nombre de colonnes est compris entre 0 et "+(plateau.tailleGrille-2)+" veuillez resaisir la colonne :");
					
				}
						
				if(plateau.VerifCoupValide(ligne, colonne)) {
				
					plateau.contenuPlateau.get(ligne).get(colonne).contenu=jCourant.couleur;
					plateau.VerifIlSePasseQqChose(jCourant, ligne, colonne);
				}

			}
					
	}
	
	
	
	public int CompterPoint(Joueur j) {
		int score=0;
		
		
		
		return score;
	}
	
	public void SauvgarderPartie() {
		XMLEncoder encoder = null;
		try {
		      encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("partie.xml")));
		      encoder.writeObject(this);
		      encoder.flush();
		    } catch (final java.io.IOException e) {
		      e.printStackTrace();
		    } finally {
		      if (encoder != null) {
		        encoder.close();
		      }
		    }
		
	}
	public void ChargerPartie() {
		 XMLDecoder decoder = null;
		    try {
		      decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("partie.xml")));
		      final Partie partie = (Partie) decoder.readObject();
		      System.out.println(partie);
		    } catch (final Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (decoder != null) {
		        decoder.close();
		      }
		    }
		
	}
	
	public static void main(String[] args) {
		Partie p =new Partie();
		
		p.JouerPartie();
		
		
	}
}
