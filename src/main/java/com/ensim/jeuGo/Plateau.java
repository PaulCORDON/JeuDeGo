package com.ensim.jeuGo;


import java.util.ArrayList;


public class Plateau {

	public ArrayList<ArrayList<Intersection>> contenuPlateau= new ArrayList<ArrayList<Intersection>>();
	Partie p;
	
	public Plateau(Partie pa){	
		for(int i=0;i<21;i++) {			
			contenuPlateau.add(new ArrayList<Intersection>());
			for(int j=0;j<21;j++) {
				if(i==0||i==20||j==0||j==20) {
					contenuPlateau.get(i).add(new Intersection("bord"));
				}
				else {
					contenuPlateau.get(i).add(new Intersection("vide"));
				}
				
			}
		}
		p=pa;
	}
	
	public Boolean VerifCoupValide(int ligne,int colonne) {

		if(contenuPlateau.get(ligne).get(colonne).contenu.equals("vide")) {
			return true;
		}
		else {
			System.out.println("il y a déja une pierre ici");
			p.JouerTour();
			return false;
		}
		
	}
	
	public String toString() {
		String goban="";
		for(ArrayList<Intersection> ligne:contenuPlateau) {
			System.out.print("\n");
			for(Intersection intersection:ligne) {
				if(intersection.contenu.equals("bord")) {
					System.out.print(" O");
				}
				else if(intersection.contenu.equals("vide")) {
					System.out.print(" +");
				}
				else if(intersection.contenu.equals("blanc")){
					System.out.print(" A");
				}
				else {
					System.out.print(" B");
				}
				
			}
		}
		
		return goban;
	}
	public Boolean VerifIlSePasseQqChose(int ligne, int colonne) {		
		
		return false;		
	}
}
