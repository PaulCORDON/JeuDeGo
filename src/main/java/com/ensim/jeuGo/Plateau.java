package com.ensim.jeuGo;


import java.util.ArrayList;


public class Plateau {

	public ArrayList<ArrayList<Intersection>> contenuPlateau= new ArrayList<ArrayList<Intersection>>();
	Partie p;
	int tailleGrille=12;
	
	public Plateau(Partie pa){	
		for(int i=0;i<tailleGrille;i++) {			
			contenuPlateau.add(new ArrayList<Intersection>());
			for(int j=0;j<tailleGrille;j++) {
				if(i==0||i==tailleGrille-1||j==0||j==tailleGrille-1) {
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
				else if(intersection.contenu.equals("noir")){
					System.out.print(" B");
				}
				
			}
		}
		
		return goban;
	}
	
	
	public void VerifIlSePasseQqChose(Joueur j, int ligne, int colonne) { //dans cette methode on verifie s'il se passe qq chose. Si oui, on effectue les changement sur le plateau
		  String couleur= j.couleur;
		
		  if(couleur.equals("blanc")) {
			  
			  
			  /*****pseudo code******
			   * 
			   * on vérifie les 4 voisins
			   * tant que( pas libre ou pas capturé)
			   *		on prend les voisins noir
			   * 		on verifie leurs 3 autres voisins (on enleve celui par le quel est deja passé)
			   * 
			   * 		si un seul voisin est vide: libre!
			   * 
			   * 		si tous les voisins sont blancs: capturé!
			   * 
			   */
		  }
		  
		  
		  
		  
		  
		  /*
		 //verif pour un pion entouré par 4 pions adverses
		 for (int i=0; i<=tailleGrille-1; i++) {
			 for(int j=0; j<=tailleGrille-1; j++) {
		 
				 if(contenuPlateau.get(i).get(j).contenu.equals("noir"))
					 	if(contenuPlateau.get(i+1).get(j).contenu.equals("blanc")) 
					 		if(contenuPlateau.get(i).get(j+1).contenu.equals("blanc"))
					 			if(contenuPlateau.get(i-1).get(j).contenu.equals("blanc"))
					 				if(contenuPlateau.get(i).get(j-1).contenu.equals("blanc"))
					 					contenuPlateau.get(i).get(j).contenu="vide";
				 
				 if(contenuPlateau.get(i).get(j).contenu.equals("blanc"))
					 	if(contenuPlateau.get(i+1).get(j).contenu.equals("noir")) 
					 		if(contenuPlateau.get(i).get(j+1).contenu.equals("noir"))
					 			if(contenuPlateau.get(i-1).get(j).contenu.equals("noir"))
					 				if(contenuPlateau.get(i).get(j-1).contenu.equals("noir"))
					 					contenuPlateau.get(i).get(j).contenu="vide";
				 
			 }
		 }
		 */
		 
	}
		 	
	
	
}
	
	
	

