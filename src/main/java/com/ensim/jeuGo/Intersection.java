package com.ensim.jeuGo;

public class Intersection {
	String contenu;
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	Boolean libre=true;
	Chaine chaine=null; //permettra de savoir à quelle chaine appartient chaque pion
	
	public Intersection(String txt) {
		contenu=txt;
	}
	
}