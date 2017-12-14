package com.ensim.jeuGo;

public class Joueur {
	
	private String couleur;
	private int score;
	private boolean aPasse;
	
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isaPasse() {
		return aPasse;
	}

	public void setaPasse(boolean aPasse) {
		this.aPasse = aPasse;
	}

	Joueur(String couleur){
		this.couleur=couleur;
	}
	

}
