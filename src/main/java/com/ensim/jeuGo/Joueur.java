package com.ensim.jeuGo;

public class Joueur {
	
	private String couleur;
	private float score;
	private boolean aPasse;
	
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public boolean isaPasse() {
		return aPasse;
	}

	public void setaPasse(boolean aPasse) {
		this.aPasse = aPasse;
	}

	public Joueur(String couleur){
		this.couleur=couleur;
	}
	

}
