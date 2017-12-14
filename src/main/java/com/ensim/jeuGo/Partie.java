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

import application.Goban;
import javafx.scene.control.Label;



public class Partie {
	Logger logger = Logger . getLogger ( Main. class . getName ());

	
	Scanner sc = new Scanner(System.in);
	Joueur j1;
	Joueur j2;
	private Joueur jCourant;
	Label info;
	private Joueur jAttendant;	
	Plateau plateau;
	Boolean isFinish;
	int tour;
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
	
	public Partie(){	//initialisation de la partie
		logger.info ("La Partie commence.");
		j1 = new Joueur("blanc");
		j2 = new Joueur("noir");
		jCourant= j2;
		jAttendant=j1;
		plateau = new Plateau(this);
		isFinish=false;
		tour=0;
		info=Goban.Info;
	}
	
	
	public void JouerPartie() {
			
	}
	
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
	
	public void JouerTour(String abs,String ord){
			logger.info("coucou");
			Integer ligne=new Integer(ord);
			Integer colonne=new Integer(abs);		
			jCourant.setaPasse(false);				
			if(ligne<1||ligne>19||colonne<1||colonne>19) {
				info.setText("Les valeurs saisient ne sont pas valide.");
				logger.info("les coordonnees ne sont pas valides");
				
			}
			else if(plateau.VerifCoupValide(ligne, colonne)) {
				logger.info("on verifie si le coup est valide");
				plateau.contenuPlateau.get(ligne).get(colonne).contenu=jCourant.getCouleur();
				plateau.VerifIlSePasseQqChose(jCourant, ligne, colonne);
				changerJoueur();
			}
			else {
				info.setText("Le coup n'est pas valide.");
				logger.info("Le coup n'est pas valide.");
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
	
}
