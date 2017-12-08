package com.ensim.jeuGo;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;



public class Partie {
	Logger logger = Logger . getLogger ( Main. class . getName ());

	
	Scanner sc = new Scanner(System.in);
	Joueur j1;
	Joueur j2;
	Joueur jCourant;
	Joueur jAttendant;
	Plateau plateau;
	Boolean isFinish;
	int tour;
	
	
	public Partie(){	//initialisation de la partie
		logger.info ("La Partie commence.");
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
		logger.info ("La Partie est terminée");
		
		System.out.println("Partie terminée.");
		/*j1.score=CompterPoint(j1);
		j2.score=CompterPoint(j2);*/
		ArrayList<Joueur> joueur = new ArrayList<Joueur>();
		j1.score=12;
		j2.score=2;
		joueur.add(j1);
		joueur.add(j2);
		Collections.sort(joueur, new ComparatorJoueur());
		System.out.println("le gagnant est "+ joueur.get(0).couleur);		
	}
	
	
	
	
	public void JouerTour(){
			int ligne;
			int colonne;
			System.out.println(jCourant.couleur+" Voulez-vous passez votre tour? 0=oui 1=non");
			if(sc.nextInt()==0) {
				logger.info("Joueur "+jCourant.couleur+" a passer");
				jCourant.aPasse=true;
				if(jAttendant.aPasse==true) {
					isFinish=true;
					logger.info("La partie est finie car les deux joueurs ont passé");
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
		Logger logger = Logger . getLogger ( Main. class . getName ());
		logger.info ("La Partie va commence.");
		Partie p =new Partie();
		
		p.JouerPartie();
		
		
	}
}
