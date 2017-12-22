package com.ensim.jeuGo;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;


public class Partie {
	
	Logger logger = Logger . getLogger ( Main. class . getName ());

	
	Scanner sc = new Scanner(System.in);
	private Joueur j1;
	private Joueur j2;
	private Joueur jCourant;	
	private Joueur jAttendant;	
	public Plateau plateau;
	Boolean isFinish;
	int tour;
	
	
	
	
	public Joueur getJ1() {
		return j1;
	}


	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}


	public Joueur getJ2() {
		return j2;
	}


	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}
	
	public Joueur getjAttendant() {
		return jAttendant;
	}


	public void setjAttendant(Joueur jAttendant) {
		this.jAttendant = jAttendant;
	}
	public Joueur getjCourant() {
		return jCourant;
	}


	public void setjCourant(Joueur jCourant) {
		this.jCourant = jCourant;
	}
	
	
	/**
	 * Constructeur de la classe partie
	 */
	public Partie(){	//initialisation de la partie
		logger.info ("La Partie commence.");
		j1 = new Joueur("blanc");
		j2 = new Joueur("noir");
		jCourant= j2;
		jAttendant=j1;
		plateau = new Plateau(this);
		isFinish=false;
		tour=0;
		
	}
	
	

	/**
	 * Methode qui change le joueur courant.
	 */
	public void changerJoueur() {
		logger.info("Le joueur courant change");
		if(jCourant==j1) {
			jCourant=j2;
			jAttendant=j1;
		}
		else {
			jCourant=j1;
			jAttendant=j2;
		}
	}
	
	/**
	 * Méthode qui est déclanchée quand le joueur courant passe son tour et qui regarde
	 * si le tour d'avant l'autre joueur n'a pas passé auquel cas la partie serait terminer.
	 */
	public void passerTour() {
		jCourant.setaPasse(true);
		if(jAttendant.isaPasse()==true) {
			isFinish=true;
			logger.info("La partie est finie car les deux joueurs ont passé");
		}
		else {
			changerJoueur();
		}
		
	}
	
	/**
	 * Méthode qui verifie si les coordonnées entrées par le joueur sont valides.
	 * Si elles le sont elle place le pion.
	 * @param abs
	 * @param ord
	 * @return un entier qui permetra à JouerTour de Goban.java de savoir si le coup est valdide ou non et pourquoi.
	 */
	public int JouerTour(String abs,String ord){
			
			int rep=4;
			Integer ligne=new Integer(ord);
			Integer colonne=new Integer(abs);		
			jCourant.setaPasse(false);		
			
			if(ligne<1||ligne>19||colonne<1||colonne>19) {				
				logger.info("les coordonnees ne sont pas valides");
				rep=1;
			}
			else if(plateau.VerifCoupValide(jCourant, ligne, colonne)>=4) {
				logger.info("on verifie si le coup est valide");
				plateau.contenuPlateau.get(ligne).get(colonne).contenu=jCourant.getCouleur();
				plateau.VerifIlSePasseQqChose(jCourant, ligne, colonne);
				changerJoueur();	
			}
			else {
				rep=plateau.VerifCoupValide(jCourant, ligne, colonne);				
				logger.info("Le coup n'est pas valide.");			
			}
			return rep;
	}
	
	

	/**
	 * Méthode qui permet de sauvgarder la partie grace à la serialisation XML
	 */
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
	/**
	 * Méthode qui permet de charger la dernière partie sauvgardée grace à la serialisation XML
	 */
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
	
}
