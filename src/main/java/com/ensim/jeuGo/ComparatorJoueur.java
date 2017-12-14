package com.ensim.jeuGo;

import java.util.Comparator;

public class ComparatorJoueur implements Comparator<Joueur> {

	public int compare(Joueur j1, Joueur j2) {
		int res;
		if(j1.getScore()<j2.getScore()) {
			res=-1;
		}
		else if(j1.getScore()==j2.getScore()) {
			res=0;
		}
		else {
			res=+1;
		}
		return res;
	}

}
