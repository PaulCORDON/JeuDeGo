package com.ensim.jeuGo;


import java.util.ArrayList;


public class Plateau {

	public ArrayList<ArrayList<Intersection>> contenuPlateau= new ArrayList<ArrayList<Intersection>>();
	
	Partie p;
	int tailleGrille=21;
	
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
	
	
	
	public int VerifCoupValide(Joueur j, int ligne,int colonne) {
		
		String couleur= j.getCouleur();
		String couleurAdverse=null;  
		if(couleur.equals("blanc")) couleurAdverse="noir";
		else if(couleur.equals("noir")) couleurAdverse="blanc";
		int renvoi=4;
			
				  
		
		if(contenuPlateau.get(ligne).get(colonne).contenu.equals("vide")) {
			
			
			
			if((contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne-1).get(colonne).contenu.equals("bord"))
				&& (contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne).get(colonne-1).contenu.equals("bord"))
				&& (contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne).get(colonne+1).contenu.equals("bord")) 
				&&  (contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleurAdverse) || contenuPlateau.get(ligne+1).get(colonne).contenu.equals("bord"))) {
						renvoi=3;
						System.out.println("coup suicidaire pion entouré");
				}//on verifie que le pion que l'on veut jouer n'est pas entoure par 4 pions adverses
		
			else if(!CalculLiberteChaine(ligne, colonne, couleur)) renvoi=3;
			
			
		
		}
		else {
			renvoi=2;
		}
			
		
		System.out.println("renvoi: "+renvoi);
		
		return renvoi;	
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
				 contenuPlateau.get(ligne).get(colonne).chaine=c;//la chaine du pion jouï¿½ est donc la chaine c
				 c.intersectionsContenuesDsLaChaine.add(contenuPlateau.get(ligne).get(colonne));//on ajoute le pion a sa propre chaine
				 
			  }
			  
			 
			  //ajout a une chaine deja existante si on trouve un voisin de la meme couleur
			  
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleur)) {
				  
				  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne-1).get(colonne).chaine;
				  contenuPlateau.get(ligne-1).get(colonne).chaine.intersectionsContenuesDsLaChaine.add(contenuPlateau.get(ligne).get(colonne));//on ajoute a cette chaine
				  
			  }
				  
			 if(contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleur)) {
				  System.out.println("chaine du pion joué: "+contenuPlateau.get(ligne).get(colonne).chaine);
				  
				  if(contenuPlateau.get(ligne).get(colonne).chaine==null) {
					  
					  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne).get(colonne-1).chaine;
					  contenuPlateau.get(ligne).get(colonne-1).chaine.intersectionsContenuesDsLaChaine.add(contenuPlateau.get(ligne).get(colonne));
				  }
				  
				  else {
					  contenuPlateau.get(ligne).get(colonne).chaine.intersectionsContenuesDsLaChaine.addAll(contenuPlateau.get(ligne).get(colonne-1).chaine.intersectionsContenuesDsLaChaine);
					  for(Intersection i:contenuPlateau.get(ligne).get(colonne-1).chaine.intersectionsContenuesDsLaChaine) {
						  i.chaine=contenuPlateau.get(ligne).get(colonne).chaine;
					  }//pour chaque pion de la chaine que l'on va supprimer, on dit sur quelle chaine ils vont maintenant pointer
					  
				  }
				  
			  }
						
			  if(contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleur)) {
				  
				  if(contenuPlateau.get(ligne).get(colonne).chaine==null) {
					  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne).get(colonne+1).chaine;
					  contenuPlateau.get(ligne).get(colonne+1).chaine.intersectionsContenuesDsLaChaine.add(contenuPlateau.get(ligne).get(colonne));
				  }
				  else {
					  contenuPlateau.get(ligne).get(colonne).chaine.intersectionsContenuesDsLaChaine.addAll(contenuPlateau.get(ligne).get(colonne+1).chaine.intersectionsContenuesDsLaChaine);
					  for(Intersection i:contenuPlateau.get(ligne).get(colonne+1).chaine.intersectionsContenuesDsLaChaine) {
						  i.chaine=contenuPlateau.get(ligne).get(colonne).chaine;
					  }
				  }
			  }
				
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleur)) {
				  
				  if(contenuPlateau.get(ligne).get(colonne).chaine==null) {
					  contenuPlateau.get(ligne).get(colonne).chaine=contenuPlateau.get(ligne+1).get(colonne).chaine;
					  contenuPlateau.get(ligne+1).get(colonne).chaine.intersectionsContenuesDsLaChaine.add(contenuPlateau.get(ligne).get(colonne));
				  }
				  else {
					  contenuPlateau.get(ligne).get(colonne).chaine.intersectionsContenuesDsLaChaine.addAll(contenuPlateau.get(ligne+1).get(colonne).chaine.intersectionsContenuesDsLaChaine);
					  for(Intersection i:contenuPlateau.get(ligne+1).get(colonne).chaine.intersectionsContenuesDsLaChaine) {
						  i.chaine=contenuPlateau.get(ligne).get(colonne).chaine;
					  }
				  }
			  }
			  
			 
			  //cas ou l'on trouve un voisin de la couleur adverse
			  //on verifie dans la chaine de celui-ci la liberte de chaque pion
			  //si aucun pion de la chaine n'est libre la chaine n'est plus libre
			  
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleurAdverse)) {
				  
				  ArrayList<Intersection> c=contenuPlateau.get(ligne-1).get(colonne).chaine.intersectionsContenuesDsLaChaine;
				  Boolean verif=false;
				  for(Intersection inter: c) {
					  if(inter.libre) verif=true;
				  }
				  if(verif.equals(false)) {
					  					  
					  for(Intersection inter: c) {
						  inter.contenu="vide";
						  inter.libre=true;
					  }
					
					 contenuPlateau.get(ligne-1).get(colonne).chaine=null;
					 
					
				  }
				  
			  }
				  
			  if(contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleurAdverse)) {
				  
				  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne-1).chaine.intersectionsContenuesDsLaChaine;
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
					
					 contenuPlateau.get(ligne).get(colonne-1).chaine=null;
				  }
			  }
						
			  if(contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleurAdverse)) {
				  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne+1).chaine.intersectionsContenuesDsLaChaine;
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
					
					 contenuPlateau.get(ligne).get(colonne+1).chaine=null;
				  }
				  
			  }
				
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleurAdverse)) {
				  ArrayList<Intersection> c=contenuPlateau.get(ligne+1).get(colonne).chaine.intersectionsContenuesDsLaChaine;
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
					
					 contenuPlateau.get(ligne).get(colonne+1).chaine=null;
				  }
			  }
			 
			
	}
	
	
	
	
	
	public void calculLibertePion(int ligne, int colonne) {	
		//cette methode sera appelee dans verifIlSePasseQqchose pour dire si le pion posï¿½ et ses 4 voisins sont toujours libres suite au coup jouï¿½
		
		 if(contenuPlateau.get(ligne-1).get(colonne).contenu!="vide" && contenuPlateau.get(ligne).get(colonne-1).contenu!="vide"
			&& contenuPlateau.get(ligne).get(colonne+1).contenu!="vide" && contenuPlateau.get(ligne+1).get(colonne).contenu!="vide") {
			 //on verifie la libertï¿½ du pion posï¿½
			 
			 contenuPlateau.get(ligne).get(colonne).libre=false;
			 
		 }
		 	
		
	
		 if(contenuPlateau.get(ligne-1).get(colonne).contenu!="vide" && ligne!=1) {
			 if(contenuPlateau.get(ligne-2).get(colonne).contenu!="vide" && contenuPlateau.get(ligne-1).get(colonne-1).contenu!="vide"
				&& contenuPlateau.get(ligne-1).get(colonne+1).contenu!="vide") {
				 //on verifie la libertï¿½ du pion au dessus du pion posï¿½
				 
				 contenuPlateau.get(ligne-1).get(colonne).libre=false;
			 }
		 }
		 
		 if(contenuPlateau.get(ligne).get(colonne-1).contenu!="vide" && colonne!=1) {
			 if(contenuPlateau.get(ligne-1).get(colonne-1).contenu!="vide" && contenuPlateau.get(ligne).get(colonne-2).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne-1).contenu!="vide") {
				//on verifie la libertï¿½ du pion a gauche du pion posï¿½
						 
				contenuPlateau.get(ligne).get(colonne-1).libre=false;
			 }
		 }
		 
		 if(contenuPlateau.get(ligne+1).get(colonne).contenu!="vide" && ligne!=tailleGrille-2) {
			 if(contenuPlateau.get(ligne+1).get(colonne-1).contenu!="vide" && contenuPlateau.get(ligne+2).get(colonne).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne+1).contenu!="vide") {
				//on verifie la libertï¿½ du pion en dessous du pion posï¿½
								 
					 contenuPlateau.get(ligne+1).get(colonne).libre=false;
			 }
		 }
	
		 
		 if(contenuPlateau.get(ligne).get(colonne+1).contenu!="vide" && colonne!=tailleGrille-2) {
			 if(contenuPlateau.get(ligne-1).get(colonne+1).contenu!="vide" && contenuPlateau.get(ligne).get(colonne+2).contenu!="vide"
				&& contenuPlateau.get(ligne+1).get(colonne+1).contenu!="vide") {
				//on verifie la libertï¿½ du pion a droite du pion posï¿½
										 
					 contenuPlateau.get(ligne).get(colonne+1).libre=false;
			 }
		 }
	}
	
	
	
	public Boolean CalculLiberteChaine(int ligne, int colonne, String couleur) { 
		 //cette methode sera appelee dans verifcoupvalide pour savoir si lorsqu'on ajoute un pion a une chaine on ne rend pas la chaine capturee
		 //si a cause de l'ajout du pion la chaine n'est plus libre, le coup est suicidaire donc on l'interdit
		
		Boolean renvoi=false;
		
		
		//on verifie si le pion va s'ajouter a une chaine de sa couleur
		//si cest le cas on regarde si la chaine a une autre liberte que l'intersection sur laquelle on veut jouer
		
		 if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals(couleur)) {
			
			  ArrayList<Intersection> c=contenuPlateau.get(ligne-1).get(colonne).chaine.intersectionsContenuesDsLaChaine;
			  Boolean b=false;
			  
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals("vide")||contenuPlateau.get(ligne).get(colonne-1).contenu.equals("vide") 
					  || contenuPlateau.get(ligne).get(colonne+1).contenu.equals("vide")) {
				  b=true;
				  renvoi=true;
			  }
			  else {
			  for(Intersection inter: c) {
				  if(inter!=contenuPlateau.get(ligne).get(colonne+1) && inter!=contenuPlateau.get(ligne).get(colonne-1) && inter!=contenuPlateau.get(ligne+1).get(colonne) && inter!=contenuPlateau.get(ligne-1).get(colonne) && inter.libre && c.size()>1) b=true; renvoi=true;
				  //si une intersection autre que celle d'a cote est libre la chaine reste libre donc le coup n'est pas suicidaire
				  if(c.size()==1) {
					  if(contenuPlateau.get(ligne-2).get(colonne).contenu=="vide" || contenuPlateau.get(ligne-1).get(colonne-1).contenu=="vide"
						|| contenuPlateau.get(ligne-1).get(colonne+1).contenu =="vide" && ligne!=1) {
						  b=true;
						  renvoi=true;
					  }
					  if(ligne==1 && contenuPlateau.get(ligne).get(colonne).libre) {
						  b=true;
						  renvoi=true;
					  }
					  
				  }
			  }
			  }
			  if(b.equals(false)) {
				  					  
				  renvoi=false;	//si il n'y a pas d'autre liberte on ne peux pas jouer donc on retourne false
			  }
		}
		else if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals(couleur)) {
			  
			  ArrayList<Intersection> c=contenuPlateau.get(ligne+1).get(colonne).chaine.intersectionsContenuesDsLaChaine;
			  Boolean b=false;
			  if(contenuPlateau.get(ligne-1).get(colonne).contenu.equals("vide")||contenuPlateau.get(ligne).get(colonne-1).contenu.equals("vide") 
					  || contenuPlateau.get(ligne).get(colonne+1).contenu.equals("vide")) {
				  b=true;
				  renvoi=true;
			  }
			  else {
			  for(Intersection inter: c) {
				  if(inter!=contenuPlateau.get(ligne).get(colonne+1) && inter!=contenuPlateau.get(ligne).get(colonne-1) && inter!=contenuPlateau.get(ligne+1).get(colonne) && inter!=contenuPlateau.get(ligne-1).get(colonne) && inter.libre && c.size()>1) b=true; renvoi=true;
				  //si une intersection autre que celle d'a cote est libre la chaine reste libre donc le coup n'est pas suicidaire
				  if(c.size()==1) {
					  if(contenuPlateau.get(ligne+1).get(colonne-1).contenu=="vide" || contenuPlateau.get(ligne+2).get(colonne).contenu=="vide"
						|| contenuPlateau.get(ligne+1).get(colonne+1).contenu=="vide" && ligne!= tailleGrille-2) {
					  b=true;
					  renvoi=true;
					  }
					  if(ligne==tailleGrille-2 && contenuPlateau.get(ligne).get(colonne).libre) {
						  b=true;
						  renvoi=true;
					  }
				  }
			  }
		}
			  if(b.equals(false)) {
				  					  
				  renvoi=false;	//si il n'y a pas d'autre liberte on ne peux pas jouer donc on retourne false
			  }
		}
		else if(contenuPlateau.get(ligne).get(colonne-1).contenu.equals(couleur)) {
			  
			  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne-1).chaine.intersectionsContenuesDsLaChaine;
			  Boolean b=false;
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals("vide")||contenuPlateau.get(ligne+1).get(colonne).contenu.equals("vide") 
					  || contenuPlateau.get(ligne).get(colonne+1).contenu.equals("vide")) {
				  b=true;
				  renvoi=true;
			  }
			  else {
			  for(Intersection inter: c) {
				  if(inter!=contenuPlateau.get(ligne).get(colonne+1) && inter!=contenuPlateau.get(ligne).get(colonne-1) && inter!=contenuPlateau.get(ligne+1).get(colonne) && inter!=contenuPlateau.get(ligne-1).get(colonne) && inter.libre && c.size()>1) b=true; renvoi=true;
				  //si une intersection autre que celle d'a cote est libre la chaine reste libre donc le coup n'est pas suicidaire
				  if(c.size()==1) {
					  if(contenuPlateau.get(ligne-1).get(colonne-1).contenu=="vide" || contenuPlateau.get(ligne).get(colonne-2).contenu=="vide"
						|| contenuPlateau.get(ligne+1).get(colonne-1).contenu=="vide" && colonne!=1) {
						  b=true;
						  renvoi=true;
					  }
					  if(colonne==1 && contenuPlateau.get(ligne).get(colonne).libre) {
						  b=true;
						  renvoi=true;
					  }
					  
				  }
			  }
			  }
			  if(b.equals(false)) {
				  					  
				  renvoi=false;	//si il n'y a pas d'autre liberte on ne peux pas jouer donc on retourne false
			  }
		}
		else if(contenuPlateau.get(ligne).get(colonne+1).contenu.equals(couleur)) {
			  
			  ArrayList<Intersection> c=contenuPlateau.get(ligne).get(colonne+1).chaine.intersectionsContenuesDsLaChaine;
			  Boolean b=false;
			  if(contenuPlateau.get(ligne+1).get(colonne).contenu.equals("vide")||contenuPlateau.get(ligne).get(colonne-1).contenu.equals("vide") 
					  || contenuPlateau.get(ligne-1).get(colonne).contenu.equals("vide")) {
				  b=true;
				  renvoi=true;
			  }
			  else {
			  for(Intersection inter: c) {
				  if(inter!=contenuPlateau.get(ligne).get(colonne+1) && inter!=contenuPlateau.get(ligne).get(colonne-1) && inter!=contenuPlateau.get(ligne+1).get(colonne) && inter!=contenuPlateau.get(ligne-1).get(colonne) && inter.libre && c.size()>1) b=true; renvoi=true;
				  //si une intersection autre que celle d'a cote est libre la chaine reste libre donc le coup n'est pas suicidaire
				  if(c.size()==1) {
					  if(contenuPlateau.get(ligne-1).get(colonne+1).contenu=="vide" || contenuPlateau.get(ligne).get(colonne+2).contenu=="vide"
						|| contenuPlateau.get(ligne+1).get(colonne+1).contenu=="vide" && colonne!=tailleGrille-2) {
					  b=true;
					  renvoi=true;
					  }
					  
					  if(colonne==tailleGrille-2 && contenuPlateau.get(ligne).get(colonne).libre) {
						  b=true;
						  renvoi=true;
					  }
				  }
			  }
			  }
			  if(b.equals(false)) {
				  					  
				  renvoi=false;	//si il n'y a pas d'autre liberte on ne peux pas jouer donc on retourne false
			  }
		}
		 
		else renvoi=true;
		 
		return renvoi;
		
	}
	
	
	public void SupprimerPion(int ligne, int colonne) {
		/* methode utilisee a la fin de la partie pour supprimer les pions morts*/
		
		contenuPlateau.get(ligne).get(colonne).contenu="vide";
		
	}
}
	
	
	

