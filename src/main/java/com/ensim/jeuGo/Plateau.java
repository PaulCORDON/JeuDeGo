package com.ensim.jeuGo;


import java.util.ArrayList;


public class Plateau {

	public ArrayList<ArrayList<Intersection>> contenuPlateau= new ArrayList<ArrayList<Intersection>>();
	public ArrayList<Chaine> listeChaines= new ArrayList<Chaine>();
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
	
	public Boolean VerifCoupValide(Joueur j, int ligne,int colonne) {

		String couleur= j.getCouleur();
		String couleurAdverse=null;  if(couleur.equals("blanc")) couleurAdverse="noir";
		else if(couleur.equals("noir")) couleurAdverse="blanc";
			
		if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleurAdverse)|| contenuPlateau.get(ligne-1).get(colonne).contenu.equals("bord") && contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleurAdverse)
			|| contenuPlateau.get(ligne).get(colonne-1).contenu.equals("bord") && contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne).get(colonne+1).contenu.equals("bord")
			&& contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne+1).get(colonne).contenu.equals("bord")) {
				return false;
		}//on verifie que le pion que l'on veut jouer n'est pas entoure par 4 pions adverses ou de 3 si bord
		  
		  
		if(contenuPlateau.get(ligne).get(colonne).contenu.equals("vide")) {
			return true;
		}
		else {
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
		  String couleur= j.getCouleur();
		  String couleurAdverse=null;
		  
		  if(couleur.equals("blanc")) couleurAdverse="noir";
		  else if(couleur.equals("noir")) couleurAdverse="blanc";

		  
		  calculLibertePion(ligne, colonne);
		  
		  
			
				
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu!=couleur && contenuPlateau.get(ligne).get(colonne-1).contenu != couleur 
				&& contenuPlateau.get(ligne).get(colonne+1).contenu != couleur && contenuPlateau.get(ligne+1).get(colonne).contenu != couleur) {
				  //si le pion que l'on pose ne peut se rattacher a aucune chaine existante on en cree une nouvelle
				 Chaine c= new Chaine(couleur);
				 contenuPlateau.get(ligne).get(colonne).chaine=c;//la chaine du pion joué est donc la chaine c
				 listeChaines.add(c);
				 c.chaine.add(contenuPlateau.get(ligne).get(colonne));//on ajoute le pion a sa propre chaine
				 
			  }
			  
			  
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleurAdverse) && contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleurAdverse) 
				&& contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleurAdverse) && contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleurAdverse)) {
				  ////////////on verifie que le pion que l'on a pose n'est pas entre 4 noirs, si cest le cas, sa chaine n'est pas libre
					  c.libre=false;
			  } 
			  
			  //ajout a une chaine deja existante si on trouve un voisin de la meme couleur
			  
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleur)) {
				  
				  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne-1).get(colonne).chaine;
				  contenuPlateau.get(ligne-1).get(colonne).chaine.chaine.add(contenuPlateau.get(ligne).get(colonne));//on ajoute a cette chaine
				  
			  }
				  
			  else if(contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleur)) {
				  
				  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne).get(colonne-1).chaine;
				  contenuPlateau.get(ligne).get(colonne-1).chaine.chaine.add(contenuPlateau.get(ligne).get(colonne));
				  
			  }
						
			  else if(contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleur)) {
				  
				  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne).get(colonne+1).chaine;
				  contenuPlateau.get(ligne).get(colonne+1).chaine.chaine.add(contenuPlateau.get(ligne).get(colonne));
			  }
				
			  else if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleur)) {
				  
				  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne+1).get(colonne).chaine;
				  contenuPlateau.get(ligne+1).get(colonne).chaine.chaine.add(contenuPlateau.get(ligne).get(colonne));
			  }
			  
			  
			  
			  //cas ou l'on trouve un voisin de la couleur adverse
			  //on verifie dans la chaine de celui-ci la liberte de chaque pion
			  //si aucun pion de la chaine n'est libre la chaine n'est plus libre
			  
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleurAdverse)) {
				  System.out.println("le pion du dessus est adverse");
				  
				  ArrayList<Intersection> c=contenuPlateau.get(ligne-1).get(colonne).chaine.chaine;
				  System.out.println("la chaine du pion du dessus est: "+c);
				  Boolean verif=false;
				  for(Intersection inter: contenuPlateau.get(ligne-1).get(colonne).chaine.chaine) {
					  System.out.println("je verifie la liberte de chaque pion de la chaine du pion adverse");
					  if(inter.libre) verif=true;
				  }
				  if(verif.equals(false)) {
					  System.out.println("je suis la");
					  
					  for(Intersection inter: contenuPlateau.get(ligne-1).get(colonne).chaine.chaine) {
						  inter.contenu="vide";
						  inter.libre=true;
					  }
					
					 contenuPlateau.get(ligne-1).get(colonne).chaine.chaine.remove(contenuPlateau.get(ligne-1).get(colonne).chaine.chaine);
					 
					
				  }
				  
			  }
				  
			  if(contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleurAdverse)) {
				  
				  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne-1).chaine.chaine;
				  Boolean verif=false;
				  for(Intersection inter: c) {
					  if(inter.libre) verif=true;
				  }
				  if(verif.equals(false)) {
					  contenuPlateau.get(ligne).get(colonne-1).chaine.libre=false;
					  for(Intersection inter: c) {
						  inter.contenu="vide";
						  inter.libre=true;
					  }
					
					 contenuPlateau.get(ligne).get(colonne-1).chaine.chaine.remove(contenuPlateau.get(ligne).get(colonne-1).chaine.chaine);
				  }
			  }
						
			  if(contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleurAdverse)) {
				  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne+1).chaine.chaine;
				  Boolean verif=false;
				  for(Intersection inter: c) {
					  if(inter.libre) verif=true;
				  }
				  if(verif.equals(false)) {
					  contenuPlateau.get(ligne).get(colonne+1).chaine.libre=false;
					  for(Intersection inter: c) {
						  inter.contenu="vide";
						  inter.libre=true;
					  }
					
					 contenuPlateau.get(ligne).get(colonne+1).chaine.chaine.remove(contenuPlateau.get(ligne).get(colonne+1).chaine.chaine);
				  }
				  
			  }
				
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleurAdverse)) {
				  ArrayList<Intersection> c=contenuPlateau.get(ligne+1).get(colonne).chaine.chaine;
				  Boolean verif=false;
				  for(Intersection inter: c) {
					  if(inter.libre) verif=true;
				  }
				  if(verif.equals(false)) {
					  contenuPlateau.get(ligne+1).get(colonne).chaine.libre=false;
					  for(Intersection inter: c) {
						  inter.contenu="vide";
						  inter.libre=true;
					  }
					
					 contenuPlateau.get(ligne).get(colonne+1).chaine.chaine.remove(contenuPlateau.get(ligne).get(colonne).chaine.chaine);
				  }
			  }
			  
	
		
	}
	
	
	
	
	
	public void calculLibertePion(int ligne, int colonne) {	
		//cette methode sera appelee dans verifIlSePasseQqchose pour dire si le pion posé et ses 4 voisins sont toujours libres suite au coup joué
		
		 if(contenuPlateau.get(ligne-1).get(colonne).contenu!="vide" && contenuPlateau.get(ligne).get(colonne-1).contenu!="vide"
			&& contenuPlateau.get(ligne).get(colonne+1).contenu!="vide" && contenuPlateau.get(ligne+1).get(colonne).contenu!="vide") {
			 //on verifie la liberté du pion posé
			 
			 contenuPlateau.get(ligne).get(colonne).libre=false;
			 System.out.println("liberte pion pose:"+contenuPlateau.get(ligne).get(colonne).libre);
		 }
		 	
	
		 if(contenuPlateau.get(ligne-1).get(colonne).contenu!="vide") {
			 if(contenuPlateau.get(ligne-2).get(colonne).contenu!="vide" && contenuPlateau.get(ligne-1).get(colonne-1).contenu!="vide"
				&& contenuPlateau.get(ligne-1).get(colonne+1).contenu!="vide") {
				 //on verifie la liberté du pion au dessus du pion posé
				 
				 contenuPlateau.get(ligne-1).get(colonne).libre=false;
				 System.out.println("liberte pion au dessus:"+contenuPlateau.get(ligne-1).get(colonne).libre);
			 }
		 }
		 
		 if(contenuPlateau.get(ligne).get(colonne-1).contenu!="vide") {
			 if(contenuPlateau.get(ligne-1).get(colonne-1).contenu!="vide" && contenuPlateau.get(ligne).get(colonne-2).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne-1).contenu!="vide") {
				//on verifie la liberté du pion a gauche du pion posé
						 
				contenuPlateau.get(ligne).get(colonne-1).libre=false;
				System.out.println("liberte pion gauche:"+contenuPlateau.get(ligne).get(colonne-1).libre);
			 }
		 }
		 
		 if(contenuPlateau.get(ligne+1).get(colonne).contenu!="vide") {
			 if(contenuPlateau.get(ligne+1).get(colonne-1).contenu!="vide" && contenuPlateau.get(ligne+2).get(colonne).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne+1).contenu!="vide") {
				//on verifie la liberté du pion en dessous du pion posé
								 
					 contenuPlateau.get(ligne+1).get(colonne).libre=false;
					 System.out.println("liberte pion dessous:"+contenuPlateau.get(ligne+1).get(colonne).libre);
			 }
		 }
	
		 
		 if(contenuPlateau.get(ligne).get(colonne+1).contenu!="vide") {
			 if(contenuPlateau.get(ligne-1).get(colonne+1).contenu!="vide" && contenuPlateau.get(ligne).get(colonne+2).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne+1).contenu!="vide") {
				//on verifie la liberté du pion a droite du pion posé
										 
					 contenuPlateau.get(ligne).get(colonne+1).libre=false;
					 System.out.println("liberte pion droit:"+contenuPlateau.get(ligne).get(colonne+1).libre);
			 }
		 }
	}
}
	
	
	

